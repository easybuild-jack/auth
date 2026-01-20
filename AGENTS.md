# AGENTS.md

This document provides guidelines and commands for agentic coding agents working in this repository.

## Project Overview

This is a Java 21 Spring Boot authentication module project using Gradle. The project is structured as a multi-module Gradle project with the following modules:

- **auth-api**: API definitions (DTOs, requests, responses, VOs)
- **auth-server**: Core service implementation and persistence layer
- **auth-prd**: REST controllers and presentation layer
- **auth-sc**: Service client modules (auth-sc-client, auth-sc-provider, resource-sc-client)

## Build Commands

```bash
# Build all modules
./gradlew build

# Build without tests
./gradlew build -x test

# Clean build
./gradlew clean build

# Compile only
./gradlew compileJava

# Run tests
./gradlew test

# Run a single test class
./gradlew test --tests "com.mcst.module.auth.test.TestMsg"

# Run a single test method
./gradlew test --tests "com.mcst.module.auth.test.TestMsg.main"

# Publish to local Maven
./gradlew publishToMavenLocal
```

## Code Style Guidelines

### Imports

- Group imports in this order: Java standard library, Jakarta annotations, Spring Framework, third-party libraries (hutool, mapstruct, lombok, mybatis-plus), project internal modules (com.mcst.easyfk.*), then local project imports
- Use wildcard imports sparingly; prefer explicit imports for clarity
- Always use `jakarta.annotation.Resource` (not `javax.annotation.Resource`)

### Naming Conventions

- **Classes**: PascalCase (e.g., `RoleServiceImpl`, `RoleResp`, `AuthEnum`)
- **Interfaces**: Prefix with `I` (e.g., `IRoleService`, `IRoleRepository`)
- **Enums**: PascalCase with `Enum` suffix (e.g., `AuthEnum`, `AccountTypeEnum`)
- **Variables/Fields**: camelCase (e.g., `roleRepository`, `forbiddenFlag`, `saasId`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `I18N_PATH`)
- **Packages**: lowercase (e.g., `com.mcst.module.auth.server.service.impl`)
- **Database Tables**: snake_case with prefix (e.g., `auth_role`, `auth_employee`)
- **Columns/Fields**: camelCase (e.g., `roleId`, `forbiddenFlag`, `saasId`)

### Lombok Usage

Always use Lombok annotations for POJOs and DTOs:

```java
@Data                          // Generates getters, setters, toString, equals, hashCode
@EqualsAndHashCode(callSuper = true)  // Include parent class fields
@Accessors(chain = true)       // Enable fluent setter style
public class RoleResp extends RoleDto {
}
```

For entities with MyBatis Plus:

```java
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("auth_role")
@AutoMapper(target = RoleDto.class)
public class Role extends BaseMyBatisPlusEntity<Role> {
    @PrimaryKey
    @TableId(type = IdType.ASSIGN_UUID)
    private String roleId;
}
```

### MapStruct-Plus (AutoMapper)

Use `@AutoMapper` annotation for DTO-to-Entity conversions:

```java
@AutoMapper(target = RoleDto.class)
public class Role extends BaseMyBatisPlusEntity<Role> { }
```

### Service Layer Patterns

- Services should implement interfaces with `I` prefix (e.g., `IRoleService`)
- Use `@Resource` for dependency injection
- Implement `InitializingBean` when initialization logic is needed
- Use builder utilities: `TransformUtil`, `ServiceUtil`, `ConditionUtil`, `UserDataFiltrationUtil`
- Use `SCBuilder` for search conditions
- Use `BEBuilder` for exception building with i18n support

### Error Handling

- Use `BEBuilder.exceptionByI18n()` for internationalized exceptions
- Use `AuthEnum.I18N_PATH.getCode()` as the i18n path
- Never hardcode error messages; use i18n keys
- Example: `throw BEBuilder.exceptionByI18n("IdEmptyError", AuthEnum.I18N_PATH.getCode());`

### Controller Layer Patterns

- Use `@Tag` for Swagger/OpenAPI documentation
- Use `@ResourceController` annotation for resource grouping
- Use `@AuthResource` for authorization annotations
- Use builder utilities: `RRBuilder`, `MRPBuilder`, `SRPBuilder`
- Return `ResponseResult<?>` for all endpoints
- Use `@Validated` for request validation

### Enums

Use enums for constant values with a `code` field:

```java
public enum AuthEnum {
    I18N_PATH("i18n/authMessage");

    private final String code;

    private AuthEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
```

### JavaDoc

- Use Chinese comments for class and method documentation
- Include author tags in JavaDoc
- Document complex logic with inline comments
- Example:

```java
/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author liuyijun
 */
```

### Utility Classes

- Use `EmptyUtil` for null/empty checks (`EmptyUtil.isEmpty()`, `EmptyUtil.isNotEmpty()`)
- Use `CollUtil` from Hutool for collection operations
- Use `SecureUtil` from Hutool for encryption
- Use `CharacterConstant.COMMA_DELIMITERS` for comma-separated strings

### API Design

- **Request objects**: `*Req` suffix (e.g., `RoleReq`, `RoleEditReq`)
- **Response objects**: `*Resp` suffix (e.g., `RoleResp`, `EmployeeResp`)
- **View objects**: `*VO` suffix (e.g., `RoleGrantVO`, `RoleResourceVO`)
- **DTOs**: `*Dto` suffix (e.g., `RoleDto`, `EmployeeDto`)
- **Search requests**: Wrap in `SearchRequest<T>` with `SRPBuilder`
- **Modify requests**: Wrap in `ModifyRequest<T>` with `MRPBuilder`

### File Locations

- **Controllers**: `auth-prd/src/main/java/com/mcst/module/auth/controller/`
- **Services**: `auth-server/src/main/java/com/mcst/module/auth/server/service/`
- **Repositories**: `auth-server/src/main/java/com/mcst/module/auth/server/repository/`
- **Models/Entities**: `auth-server/src/main/java/com/mcst/module/auth/server/persistence/model/`
- **Mappers**: `auth-server/src/main/java/com/mcst/module/auth/server/persistence/mapper/`
- **Enums**: `auth-server/src/main/java/com/mcst/module/auth/server/enums/`
- **API interfaces**: `auth-api/src/main/java/com/mcst/module/auth/api/`
- **API DTOs/Req/Resp**: `auth-api/src/main/java/com/mcst/module/auth/api/{dto,request,response,vo}/`
- **Resources/i18n**: `auth-server/src/main/resources/i18n/`

### Git Commit Messages

- Use Chinese for commit messages
- Prefix with type: `新增`, `修改`, `修复`, `删除`, `优化`
- Example: `新增角色管理模块`, `修复权限分配逻辑`
