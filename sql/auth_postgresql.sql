-- PostgreSQL建表SQL
-- 生成时间: 2024-10-26
-- 说明: 基于Java模型类生成的数据库表结构

-- 1. 权限资源表 (auth_resource)
CREATE TABLE auth_resource (
    resource_id VARCHAR(50) NOT NULL,
    name VARCHAR(150),
    icon VARCHAR(150),
    url VARCHAR(150),
    pid VARCHAR(50),
    pname VARCHAR(50),
    title VARCHAR(150),
    category VARCHAR(50),
    remark VARCHAR(150),
    sort INTEGER DEFAULT 0,
    path VARCHAR(50),
    action_code VARCHAR(50),
    resource_level INTEGER DEFAULT 0,
    un_pass_msg VARCHAR(100),
    root_status INTEGER DEFAULT 0,
    group_code VARCHAR(50),
    type_flag VARCHAR(100),
    default_flag VARCHAR(100),
    insert_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0,
    PRIMARY KEY (resource_id)
);

COMMENT ON TABLE auth_resource IS '权限资源表';
COMMENT ON COLUMN auth_resource.resource_id IS '资源ID';
COMMENT ON COLUMN auth_resource.name IS '名称';
COMMENT ON COLUMN auth_resource.icon IS 'icon';
COMMENT ON COLUMN auth_resource.url IS '资源路径';
COMMENT ON COLUMN auth_resource.pid IS '父资源ID';
COMMENT ON COLUMN auth_resource.pname IS '父资源名称';
COMMENT ON COLUMN auth_resource.title IS '标题';
COMMENT ON COLUMN auth_resource.category IS '资源类型，menu 菜单，action 功能';
COMMENT ON COLUMN auth_resource.remark IS '备注';
COMMENT ON COLUMN auth_resource.sort IS '排序';
COMMENT ON COLUMN auth_resource.path IS '前端路由地址';
COMMENT ON COLUMN auth_resource.action_code IS '功能代码';
COMMENT ON COLUMN auth_resource.resource_level IS '资源等级，0：无需验证，1：需要登录，2：需要权限';
COMMENT ON COLUMN auth_resource.un_pass_msg IS '无权限时的提示';
COMMENT ON COLUMN auth_resource.root_status IS '根目录标识，0：否，1：是';
COMMENT ON COLUMN auth_resource.group_code IS '分组';
COMMENT ON COLUMN auth_resource.type_flag IS '类型标识 例如：all所有，system等，表示该资源属于系统/企业等，用于处理各个平台端资源归类';
COMMENT ON COLUMN auth_resource.default_flag IS '默认类型 all所有，system等，表示该资源是否是系统/企业等各平台端的默认资源';
COMMENT ON COLUMN auth_resource.insert_time IS '插入时间';
COMMENT ON COLUMN auth_resource.last_update_time IS '最后更新时间';
COMMENT ON COLUMN auth_resource.deleted IS '删除标记，0否 1是';

-- 创建更新时间触发器
CREATE OR REPLACE FUNCTION update_last_update_time()
RETURNS TRIGGER AS $$
BEGIN
    NEW.last_update_time = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_auth_resource_update_time
    BEFORE UPDATE ON auth_resource
    FOR EACH ROW
    EXECUTE FUNCTION update_last_update_time();

-- 2. 部门表 (auth_department)
CREATE TABLE auth_department (
    department_id VARCHAR(50) NOT NULL,
    department_name VARCHAR(100),
    code VARCHAR(100),
    forbidden_flag INTEGER DEFAULT 0,
    agent_id VARCHAR(200),
    agent_name VARCHAR(200),
    merchant_id VARCHAR(200),
    merchant_name VARCHAR(200),
    type VARCHAR(200),
    insert_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0,
    PRIMARY KEY (department_id),
    CONSTRAINT uk_code UNIQUE (code)
);

COMMENT ON TABLE auth_department IS '部门表';
COMMENT ON COLUMN auth_department.department_id IS '部门ID';
COMMENT ON COLUMN auth_department.department_name IS '名称';
COMMENT ON COLUMN auth_department.code IS '编号';
COMMENT ON COLUMN auth_department.forbidden_flag IS '禁用状态 0可用，1禁用';
COMMENT ON COLUMN auth_department.agent_id IS '所属代理商ID';
COMMENT ON COLUMN auth_department.agent_name IS '所属代理商名称';
COMMENT ON COLUMN auth_department.merchant_id IS '所属商户ID';
COMMENT ON COLUMN auth_department.merchant_name IS '所属商户名称';
COMMENT ON COLUMN auth_department.type IS '平台类型';
COMMENT ON COLUMN auth_department.insert_time IS '插入时间';
COMMENT ON COLUMN auth_department.last_update_time IS '最后更新时间';
COMMENT ON COLUMN auth_department.deleted IS '删除标记，0否 1是';

CREATE TRIGGER trigger_auth_department_update_time
    BEFORE UPDATE ON auth_department
    FOR EACH ROW
    EXECUTE FUNCTION update_last_update_time();

-- 3. 员工表 (auth_employee)
CREATE TABLE auth_employee (
    employee_id VARCHAR(50) NOT NULL,
    login_name VARCHAR(100),
    employee_name VARCHAR(20),
    employee_code VARCHAR(20),
    mobile VARCHAR(20),
    password VARCHAR(100),
    forbidden_flag INTEGER DEFAULT 0,
    email VARCHAR(100),
    header_pic VARCHAR(200),
    department_id VARCHAR(100),
    department_name VARCHAR(200),
    position VARCHAR(200),
    agent_id VARCHAR(200),
    agent_name VARCHAR(200),
    merchant_id VARCHAR(200),
    merchant_name VARCHAR(200),
    roles TEXT,
    roles_name TEXT,
    extend_info TEXT,
    manage_area TEXT,
    manage_area_id TEXT,
    work_area VARCHAR(200),
    work_area_id VARCHAR(200),
    biz_type VARCHAR(200),
    org_id VARCHAR(200),
    org_name VARCHAR(200),
    org_code VARCHAR(200),
    type VARCHAR(200),
    last_update_pwd TIMESTAMP,
    insert_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0,
    PRIMARY KEY (employee_id),
    CONSTRAINT uk_login_name UNIQUE (login_name)
);

COMMENT ON TABLE auth_employee IS '员工表';
COMMENT ON COLUMN auth_employee.employee_id IS '员工ID';
COMMENT ON COLUMN auth_employee.login_name IS '登录账户';
COMMENT ON COLUMN auth_employee.employee_name IS '姓名';
COMMENT ON COLUMN auth_employee.employee_code IS '工号';
COMMENT ON COLUMN auth_employee.mobile IS '手机';
COMMENT ON COLUMN auth_employee.password IS '密码';
COMMENT ON COLUMN auth_employee.forbidden_flag IS '禁用状态 0可用，1禁用';
COMMENT ON COLUMN auth_employee.email IS '邮箱';
COMMENT ON COLUMN auth_employee.header_pic IS '头像';
COMMENT ON COLUMN auth_employee.department_id IS '部门ID';
COMMENT ON COLUMN auth_employee.department_name IS '部门名称';
COMMENT ON COLUMN auth_employee.position IS '职位';
COMMENT ON COLUMN auth_employee.agent_id IS '所属代理商ID';
COMMENT ON COLUMN auth_employee.agent_name IS '所属代理商名称';
COMMENT ON COLUMN auth_employee.merchant_id IS '所属商户ID';
COMMENT ON COLUMN auth_employee.merchant_name IS '所属商户名称';
COMMENT ON COLUMN auth_employee.roles IS '角色ID';
COMMENT ON COLUMN auth_employee.roles_name IS '角色名称';
COMMENT ON COLUMN auth_employee.extend_info IS '扩展信息，可使用JSON存储';
COMMENT ON COLUMN auth_employee.manage_area IS '管理厂区';
COMMENT ON COLUMN auth_employee.manage_area_id IS '管理厂区ID';
COMMENT ON COLUMN auth_employee.work_area IS '工作厂区';
COMMENT ON COLUMN auth_employee.work_area_id IS '工作厂区ID';
COMMENT ON COLUMN auth_employee.biz_type IS '业务类型';
COMMENT ON COLUMN auth_employee.org_id IS '组织ID';
COMMENT ON COLUMN auth_employee.org_name IS '组织名称';
COMMENT ON COLUMN auth_employee.org_code IS '组织编号';
COMMENT ON COLUMN auth_employee.type IS '类型';
COMMENT ON COLUMN auth_employee.last_update_pwd IS '最后更新密码时间';
COMMENT ON COLUMN auth_employee.insert_time IS '插入时间';
COMMENT ON COLUMN auth_employee.last_update_time IS '最后更新时间';
COMMENT ON COLUMN auth_employee.deleted IS '删除标记，0否 1是';

CREATE TRIGGER trigger_auth_employee_update_time
    BEFORE UPDATE ON auth_employee
    FOR EACH ROW
    EXECUTE FUNCTION update_last_update_time();

-- 4. 角色表 (auth_role)
CREATE TABLE auth_role (
    role_id VARCHAR(50) NOT NULL,
    role_name VARCHAR(100),
    forbidden_flag INTEGER DEFAULT 0,
    remark VARCHAR(200),
    parent_id VARCHAR(100),
    parent_name VARCHAR(200),
    supper_status INTEGER DEFAULT 0,
    type VARCHAR(200),
    agent_id VARCHAR(200),
    agent_name VARCHAR(200),
    merchant_id VARCHAR(200),
    merchant_name VARCHAR(200),
    biz_type VARCHAR(200),
    insert_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0,
    PRIMARY KEY (role_id),
    CONSTRAINT uk_role_name UNIQUE (role_name)
);

COMMENT ON TABLE auth_role IS '角色表';
COMMENT ON COLUMN auth_role.role_id IS '角色ID';
COMMENT ON COLUMN auth_role.role_name IS '名称';
COMMENT ON COLUMN auth_role.forbidden_flag IS '禁用状态 0可用，1禁用';
COMMENT ON COLUMN auth_role.remark IS '备注信息';
COMMENT ON COLUMN auth_role.parent_id IS '上级ID';
COMMENT ON COLUMN auth_role.parent_name IS '上级名称';
COMMENT ON COLUMN auth_role.supper_status IS '超级管理员标识，0：非，1：是';
COMMENT ON COLUMN auth_role.type IS '平台类型';
COMMENT ON COLUMN auth_role.agent_id IS '所属代理商ID';
COMMENT ON COLUMN auth_role.agent_name IS '所属代理商名称';
COMMENT ON COLUMN auth_role.merchant_id IS '所属商户ID';
COMMENT ON COLUMN auth_role.merchant_name IS '所属商户名称';
COMMENT ON COLUMN auth_role.biz_type IS '业务类型';
COMMENT ON COLUMN auth_role.insert_time IS '插入时间';
COMMENT ON COLUMN auth_role.last_update_time IS '最后更新时间';
COMMENT ON COLUMN auth_role.deleted IS '删除标记，0否 1是';

CREATE TRIGGER trigger_auth_role_update_time
    BEFORE UPDATE ON auth_role
    FOR EACH ROW
    EXECUTE FUNCTION update_last_update_time();

-- 5. 角色资源关联表 (auth_role_resource)
CREATE TABLE auth_role_resource (
    role_resource_id VARCHAR(50) NOT NULL,
    role_id VARCHAR(100),
    resource_id VARCHAR(100),
    insert_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (role_resource_id)
);

CREATE INDEX idx_role_id ON auth_role_resource (role_id);
CREATE INDEX idx_resource_id ON auth_role_resource (resource_id);

COMMENT ON TABLE auth_role_resource IS '角色资源关联表';
COMMENT ON COLUMN auth_role_resource.role_resource_id IS 'RoleResourceID';
COMMENT ON COLUMN auth_role_resource.role_id IS '角色ID';
COMMENT ON COLUMN auth_role_resource.resource_id IS '资源ID';
COMMENT ON COLUMN auth_role_resource.insert_time IS '插入时间';
COMMENT ON COLUMN auth_role_resource.last_update_time IS '最后更新时间';

CREATE TRIGGER trigger_auth_role_resource_update_time
    BEFORE UPDATE ON auth_role_resource
    FOR EACH ROW
    EXECUTE FUNCTION update_last_update_time();