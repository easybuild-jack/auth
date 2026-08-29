# Auth 权限管理模块

> 基于 `easyfk` 基础框架的**后台权限管理**模块，提供员工、部门、角色、权限资源的统一管理能力，同时内置登录、登出、密码维护等账号相关能力，并支持 **Spring Cloud（Feign）** 与 **Apache Dubbo** 两种远程调用方式，方便单体或微服务架构集成。

- **项目版本**：`<latest>`（请以实际发布的最新版本为准）
- **Maven Group**：`com.mcst`
- **构建工具**：Gradle（版本以根 `gradle/wrapper/gradle-wrapper.properties` 为准）
- **JDK**：Java 21+
- **框架**：Spring Boot 3.x、Spring Cloud OpenFeign、Apache Dubbo 3.x
- **辅助工具**：Lombok、MapStruct Plus、Hutool

---

## 一、项目主要功能

1. **账号与登录**
   - 账号密码登录、钉钉免密登录、按 `employeeId` 直接登录。
   - JWT 颁发与注销，登录上下文缓存（依赖 `easyfk-authority` 的 `UserDataManager`）。
   - 登录/登出记录持久化（与 `login-server` 联动）。
   - 密码自助修改、管理员重置、定期强制更新（30 天有效期策略）。
   - 多语言异常提示（中文简体/繁体/香港、英文、越南语）。

2. **员工管理（Employee）**
   - 员工信息的增删改查、分页/列表查询、模糊搜索、启用/禁用。
   - 登录账号唯一性校验，多角色绑定，平台/代理/商户三级归属。
   - 业务类型自动聚合：根据员工所绑定角色的 `bizType` 自动合并写入员工表。

3. **部门管理（Department）**
   - 部门信息的增删改查、启用/禁用、下拉数据。
   - 支持代理商 / 商户隔离（`agentId` / `merchantId`）。

4. **角色管理（Role）**
   - 角色信息的增删改查、启用/禁用、上下级角色树。
   - 超级管理员标识（`supperStatus`），支持配置拥有全部平台资源的特殊角色。
   - 角色授权：把权限资源树批量授予角色，并提供树形结构数据。

5. **权限资源管理（AuthResource）**
   - 资源（菜单 / 功能按钮）维护，支持层级结构（`pid`）、排序、平台类型标记。
   - 三级资源安全等级：`0` 无需验证、`1` 需登录、`2` 需权限。
   - 提供按平台（system/agent/saas 等）获取资源、按 URI 检查安全级别的能力。
   - 配合 `@AuthResource` / `@LoginResource` 注解自动注册资源。

6. **角色资源关系（RoleResource）**
   - 角色 ↔ 资源的多对多绑定，支持按角色查询资源树、按资源查询可用员工。

7. **数据隔离**
   - 内置 `UserDataFiltrationUtil`，自动按当前登录用户的代理商 / 商户维度过滤数据。

8. **国际化与可配置**
   - 多语言错误码（基于 `i18n/authMessage_*.properties`）。
   - 通过 `easyfk.config.auth.init`、`easyfk.config.pwd.update` 控制模块初始化与密码周期策略。

---

## 二、项目架构设计

### 2.1 模块划分

```
auth
├── auth-api          接口契约层：DTO / Req / Resp / Param / VO / Service API
├── auth-server       服务实现层：Service + ApiServer 实现 + 工具类 + 配置 + 单测
├── auth-prd          Web 表现层：Spring Boot Controller（基于 easyfk-web-prd）
├── auth-orm          持久化层
│   ├── auth-orm-api         仓储接口定义（IRepository）
│   ├── auth-orm-plus        MyBatis-Plus 实现
│   ├── auth-orm-flex        MyBatis-Flex 实现
│   └── auth-orm-hibernate   Hibernate/JPA 实现
├── auth-remote       远程调用层（可选接入）
│   ├── auth-cloud-api         Spring Cloud 客户端：Feign 接口 + Api 适配
│   ├── auth-cloud-provider    Spring Cloud 服务端：暴露 /remote/auth/** 的 Controller
│   ├── auth-dubbo-api         Dubbo 接口 + Api 适配
│   └── auth-dubbo-provider    Dubbo 服务端实现
└── doc               数据库脚本与映射规范说明
    ├── sql/auth_mysql.sql
    ├── sql/auth_postgresql.sql
    ├── sql/test_init_mysql.sql
    └── 实体对象映射关系调整指南.md
```

### 2.2 层次与调用关系

```
┌──────────────────────────────────────────────────────────────────────┐
│  客户端 / 调用方                                                       │
└──────────────────────────────────────────────────────────────────────┘
            │                           │                        │
            ▼                           ▼                        ▼
     ┌────────────┐               ┌────────────┐           ┌────────────┐
     │  auth-prd  │               │ auth-cloud │           │ auth-dubbo │
     │ (Controller│               │    -api    │           │    -api    │
     │  本地直连)  │               │ (Feign 客户│           │ (Dubbo 消费│
     └────────────┘               │  端 + Api) │           │  端 + Api) │
            │                     └────────────┘           └────────────┘
            │                           │                        │
            │                           │ HTTP                   │ Dubbo RPC
            ▼                           ▼                        ▼
                          ┌──────────────────────────────────────┐
                          │ auth-cloud-provider / auth-dubbo-    │
                          │ provider （统一委托给 auth-server）    │
                          └──────────────────────────────────────┘
                                              │
                                              ▼
                              ┌──────────────────────────────┐
                              │          auth-server          │
                              │   Service + ApiServer 实现   │
                              └──────────────────────────────┘
                                              │
                                              ▼
                              ┌──────────────────────────────┐
                              │ auth-orm-*（Plus/Flex/Hib）   │
                              │   仓储接口 + 持久化实现        │
                              └──────────────────────────────┘
                                              │
                                              ▼
                                          数据库
```

### 2.3 关键设计点

- **接口与实现分离**：`auth-api` 只暴露接口与数据模型；`auth-server` 提供本地默认实现，通过 Spring Boot 自动装配（`AuthServerConfig`）以 `@ConditionalOnMissingBean` 形式暴露为 Bean，便于上层覆盖。
- **多 ORM 适配**：`auth-orm` 把仓储接口抽到 `auth-orm-api`，底层分别在 `auth-orm-plus / auth-orm-flex / auth-orm-hibernate` 中提供实现，业务侧按需引入对应依赖即可切换 ORM。
- **远程调用可插拔**：`auth-remote` 同时提供 Feign 和 Dubbo 两套客户端，每套又拆出 `-api`（消费端）与 `-provider`（服务端）。消费端把 `IRoleApi` / `IEmployeeApi` 等接口适配到远程 `Remote`，再通过 `@AutoConfiguration` 注册到容器；服务端把 `auth-server` 的 `Service` 透出成 HTTP / Dubbo 接口。
- **统一参数模型**：遵循 `doc/实体对象映射关系调整指南.md` 中约定的流向（`Sub → Req → Dto → Entity` / `Entity → Dto → Resp`），结合 MapStruct Plus 的 `@AutoMapper` 自动转换。
- **权限资源声明式注册**：在 Controller 上通过 `@ResourceController` / `@AuthResource` 注解声明资源节点和动作码，运行期由 `IAuthResourceApi` 提供校验与拉取能力。
- **数据隔离与权限**：`UserDataFiltrationUtil` 在查询/写入时自动注入代理商/商户过滤条件，避免业务代码重复处理。

### 2.4 数据模型

| 表名 | 说明 |
| --- | --- |
| `auth_employee` | 员工主表，含账号、姓名、所属部门/代理/商户、角色 ID 列表、业务类型等 |
| `auth_role` | 角色表，含上下级、超级管理员标识、业务类型等 |
| `auth_resource` | 权限资源表（菜单 / 按钮），含层级、路径、安全等级、平台类型等 |
| `auth_department` | 部门表，含编码、所属代理 / 商户 |
| `auth_role_resource` | 角色 ↔ 资源多对多关联表 |

完整建表脚本见 [doc/sql/auth_mysql.sql](doc/sql/auth_mysql.sql)、[doc/sql/auth_postgresql.sql](doc/sql/auth_postgresql.sql)。

---

## 三、模块详细说明

### 3.1 `auth-api`
- 定义业务接口：`IEmployeeApi`、`IRoleApi`、`IDepartmentApi`，并继承 `IBaseApi` 提供通用 CRUD。
- 定义权限资源接口：`IAuthResourceApi`（由 `easyfk-authority` 提供，本模块实现）。
- 维护所有数据传输对象：`dto/`、`request/`、`response/`、`param/`、`vo/`。
- 业务接口方法示例：`login`、`loginOut`、`updateEmployeePwd`、`resetPwd`、`grant`、`getRoleResourceByRoleId`、`dingTalkLogin`、`loginById` 等。

### 3.2 `auth-server`
- `service/`：业务 Service 接口与实现（`EmployeeServiceImpl`、`RoleServiceImpl`、`DepartmentServiceImpl`、`AuthResourceServiceImpl`）。
- `impl/`：`ApiServer` 实现，继承 `BaseApiServiceImpl`，把 Service 暴露为 `I*Api`。
- `util/`：`ResourceSecurityLevelUtil`（资源等级转换）、`RoleGrantUtil`（角色授权树构建）。
- `properties/`：`AuthProperties`（`easyfk.config.auth`）、`EmpPwdProperties`（`easyfk.config.pwd`）。
- `config/AuthServerConfig`：Spring Boot 自动装配入口。
- `src/test/`：基于 JUnit 5 的单元测试（参考 `TestMsg`）。

### 3.3 `auth-prd`
- 基于 `easyfk-web-prd` 的 Spring Boot Web 控制器层。
- 控制器清单（`/api/auth/**`）：
  - `LoginController`：`/api/auth/login`、`/logout`、`/editPwd`、`/restLoginPwd`
  - `EmployeeController`：`/api/auth/employee/**`
  - `RoleController`：`/api/auth/role/**`（含 `grant`、`parentResourceByRoleId`）
  - `DepartmentController`：`/api/auth/department/**`
  - `ResourceController`：`/api/auth/resource/employeeAuthResources`
- 通过 `@AuthResource` / `@LoginResource` / `@ResourceController` 注解自动注册权限元数据。

### 3.4 `auth-orm`
- `auth-orm-api`：仓储接口（`IEmployeeRepository`、`IRoleRepository`、`IDepartmentRepository`、`IAuthResourceRepository`、`IRoleResourceRepository`）。
- `auth-orm-plus`：基于 MyBatis-Plus 的实现 + 自动装配。
- `auth-orm-flex`：基于 MyBatis-Flex 的实现 + 自动装配。
- `auth-orm-hibernate`：基于 Hibernate/JPA 的实现 + 自动装配。

> 三个 ORM 实现互斥，按需在业务工程中只引入其中一个，避免冲突。

### 3.5 `auth-remote`
- `auth-cloud-api`：`FeignClient` 接口 + 继承 `BaseApiRemoteImpl` 的 `Api` 实现；通过 `AuthApiProperties`（`easyfk.config.remote.auth.service-id/base-path`）读取服务地址。
- `auth-cloud-provider`：`@RestController` 把 `auth-server` 的 `Service` 暴露为 `/remote/auth/**`。
- `auth-dubbo-api`：Dubbo 接口 + `Api` 适配实现。
- `auth-dubbo-provider`：Dubbo 服务实现。

### 3.6 `doc`
- `实体对象映射关系调整指南.md`：DTO/Entity/Req/Resp/Param/Sub 之间的 `@AutoMapper` 使用规范。
- `sql/`：MySQL、PostgreSQL 建表脚本以及初始化测试数据。

---

## 四、使用方式

### 4.1 构建与发布

```bash
# 清理并编译全部模块
./gradlew clean build

# 发布到本地 Maven 仓库
./gradlew publishToMavenLocal

# 发布到远程 Nexus（在 build.gradle 中已配置 NexusRepository）
./gradlew publish
```

> Windows 环境可直接使用 `gradlew.bat`。模块统一版本：`<latest>`。

### 4.2 单体应用集成（最简方式）

```groovy
dependencies {
    implementation 'com.mcst:auth-api:<latest>'
    implementation 'com.mcst:auth-server:<latest>'
    // 选择一种 ORM：
    implementation 'com.mcst:auth-orm:auth-orm-plus:<latest>'
    // implementation 'com.mcst:auth-orm:auth-orm-flex:<latest>'
    // implementation 'com.mcst:auth-orm:auth-orm-hibernate:<latest>'

    // 暴露 HTTP 接口（如需）
    implementation 'com.mcst:auth-prd:<latest>'
}
```

`auth-server` 会通过 Spring Boot 自动装配把 `IEmployeeApi`、`IRoleApi`、`IDepartmentApi`、`IAuthResourceApi` 等 Bean 注册到容器，Controller 可直接 `@Resource` 注入使用。

### 4.3 微服务集成（Spring Cloud）

**消费端：**

```groovy
dependencies {
    implementation 'com.mcst:auth-api:<latest>'
    implementation 'com.mcst:auth-remote:auth-cloud-api:<latest>'
}
```

```yaml
easyfk:
  config:
    remote:
      auth:
        service-id: auth-server      # 对应服务在注册中心的服务名
        base-path: /remote           # 可选，默认 /remote
```

**服务端：** 启动 `auth-server` 并加入 `auth-remote:auth-cloud-provider`，即可对外提供 `/remote/auth/**` 接口。

### 4.4 微服务集成（Apache Dubbo）

```groovy
// 消费端
dependencies {
    implementation 'com.mcst:auth-api:<latest>'
    implementation 'com.mcst:auth-remote:auth-dubbo-api:<latest>'
}

// 服务端
dependencies {
    implementation 'com.mcst:auth-server:<latest>'
    implementation 'com.mcst:auth-remote:auth-dubbo-provider:<latest>'
}
```

### 4.5 关键配置项

| 配置项 | 含义 | 默认值 |
| --- | --- | --- |
| `easyfk.config.auth.init` | 是否开启员工 / 角色初始数据装载 | `false` |
| `easyfk.config.pwd.update` | 是否启用密码定期更新（30 天） | `false` |
| `easyfk.config.remote.auth.service-id` | 远程调用目标服务名 | `auth-server` |
| `easyfk.config.remote.auth.base-path` | 远程调用基础路径 | `/remote` |
| `easyfk.config.remote.resource.service-id` | 资源远程调用的服务名 | `server-all` |
| `easyfk.config.remote.resource.base-path` | 资源远程调用的基础路径 | `/remote/auth/resource` |

### 4.6 初始化数据库

```bash
# MySQL
mysql -u <user> -p <database> < doc/sql/auth_mysql.sql

# PostgreSQL
psql -U <user> -d <database> -f doc/sql/auth_postgresql.sql

# 可选：导入测试数据
mysql -u <user> -p <database> < doc/sql/test_init_mysql.sql
```

### 4.7 主要 REST 接口一览

| 模块 | 路径 | 方法 | 说明 |
| --- | --- | --- | --- |
| 登录 | `/api/auth/login` | POST | 账号密码登录，返回 JWT |
| 登录 | `/api/auth/logout` | GET | 退出登录 |
| 登录 | `/api/auth/editPwd` | POST | 已登录用户修改密码 |
| 登录 | `/api/auth/restLoginPwd` | POST | 定期强制修改密码 |
| 员工 | `/api/auth/employee/queryPage` | GET | 分页查询 |
| 员工 | `/api/auth/employee/addOrEdit` | POST | 新增 / 编辑 |
| 员工 | `/api/auth/employee/delete` | POST | 批量删除 |
| 员工 | `/api/auth/employee/disable` | POST | 启用 / 禁用 |
| 部门 | `/api/auth/department/queryPage` | GET | 分页查询 |
| 部门 | `/api/auth/department/addOrEdit` | POST | 新增 / 编辑 |
| 部门 | `/api/auth/department/delete` | POST | 批量删除 |
| 部门 | `/api/auth/department/selectList` | GET | 下拉数据 |
| 角色 | `/api/auth/role/queryPage` | GET | 分页查询 |
| 角色 | `/api/auth/role/addOrEdit` | POST | 新增 / 编辑 |
| 角色 | `/api/auth/role/delete` | POST | 批量删除 |
| 角色 | `/api/auth/role/disable` | POST | 启用 / 禁用 |
| 角色 | `/api/auth/role/grant` | POST | 角色授权 |
| 角色 | `/api/auth/role/selectList` | GET | 角色下拉 |
| 角色 | `/api/auth/role/parentResourceByRoleId` | GET | 角色对应的资源树 |
| 资源 | `/api/auth/resource/employeeAuthResources` | GET | 当前登录用户的资源树 |

> 接口均通过 Swagger OpenAPI 注解描述，可在启动后访问 `/swagger-ui.html` 查看完整文档。

---

## 五、扩展指引

- **新增业务字段**：在 `auth-api` 的 `Dto` 中新增字段 → 在 `Entity` 中同步 → 选择对应 ORM 模块更新映射 → 视情况更新 `Resp`、`Req`、`VO`。
- **新增业务接口**：在 `auth-api` 暴露方法 → 在 `auth-server` 的 `Service` 与 `ApiServer` 中实现 → 选择在 `auth-prd` 增加 Controller 或在 `auth-remote` 暴露远程能力。
- **切换 ORM**：只保留目标 ORM 模块的依赖（如只想用 MyBatis-Plus，则移除 `auth-orm-flex`、`auth-orm-hibernate` 依赖）。
- **国际化**：在 `auth-server/src/main/resources/i18n/` 下追加语言键值即可。

---

## 六、目录速览

```
auth
├── build.gradle                根构建脚本（统一版本、Java 21、Lombok、MapStruct Plus）
├── settings.gradle             多模块声明
├── gradle/, gradlew, gradlew.bat
├── auth-api/                   接口契约
├── auth-server/                服务实现 + 单测
├── auth-prd/                   Web 控制器
├── auth-orm/
│   ├── auth-orm-api/
│   ├── auth-orm-plus/
│   ├── auth-orm-flex/
│   └── auth-orm-hibernate/
├── auth-remote/
│   ├── auth-cloud-api/
│   ├── auth-cloud-provider/
│   ├── auth-dubbo-api/
│   └── auth-dubbo-provider/
└── doc/
    ├── sql/
```
