-- MySQL建表SQL
-- 生成时间: 2024-10-26
-- 说明: 基于Java模型类生成的数据库表结构

-- 1. 权限资源表 (auth_resource)
CREATE TABLE `auth_resource` (
    `resource_id` VARCHAR(50) NOT NULL COMMENT '资源ID',
    `name` VARCHAR(150) COMMENT '名称',
    `icon` VARCHAR(150) COMMENT 'icon',
    `url` VARCHAR(150) COMMENT '资源路径',
    `pid` VARCHAR(50) COMMENT '父资源ID',
    `pname` VARCHAR(50) COMMENT '父资源名称',
    `title` VARCHAR(150) COMMENT '标题',
    `category` VARCHAR(50) COMMENT '资源类型，menu 菜单，action 功能',
    `remark` VARCHAR(150) COMMENT '备注',
    `sort` INT(10) DEFAULT 0 COMMENT '排序',
    `path` VARCHAR(50) COMMENT '前端路由地址',
    `action_code` VARCHAR(50) COMMENT '功能代码',
    `resource_level` INT(2) DEFAULT 0 COMMENT '资源等级，0：无需验证，1：需要登录，2：需要权限',
    `un_pass_msg` VARCHAR(100) COMMENT '无权限时的提示',
    `root_status` INT(2) DEFAULT 0 COMMENT '根目录标识，0：否，1：是',
    `group_code` VARCHAR(50) COMMENT '分组',
    `type_flag` VARCHAR(100) COMMENT '类型标识 例如：all所有，system等，表示该资源属于系统/企业等，用于处理各个平台端资源归类',
    `default_flag` VARCHAR(100) COMMENT '默认类型 all所有，system等，表示该资源是否是系统/企业等各平台端的默认资源',
    `insert_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
    `last_update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted` INT(2) DEFAULT 0 COMMENT '删除标记，0否 1是',
    PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限资源表';

-- 2. 部门表 (auth_department)
CREATE TABLE `auth_department` (
    `department_id` VARCHAR(50) NOT NULL COMMENT '部门ID',
    `department_name` VARCHAR(100) COMMENT '名称',
    `code` VARCHAR(100) COMMENT '编号',
    `forbidden_flag` INT(2) DEFAULT 0 COMMENT '禁用状态 0可用，1禁用',
    `agent_id` VARCHAR(200) COMMENT '所属代理商ID',
    `agent_name` VARCHAR(200) COMMENT '所属代理商名称',
    `merchant_id` VARCHAR(200) COMMENT '所属商户ID',
    `merchant_name` VARCHAR(200) COMMENT '所属商户名称',
    `type` VARCHAR(200) COMMENT '平台类型',
    `insert_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
    `last_update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted` INT(2) DEFAULT 0 COMMENT '删除标记，0否 1是',
    PRIMARY KEY (`department_id`),
    UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- 3. 员工表 (auth_employee)
CREATE TABLE `auth_employee` (
    `employee_id` VARCHAR(50) NOT NULL COMMENT '员工ID',
    `login_name` VARCHAR(100) COMMENT '登录账户',
    `employee_name` VARCHAR(20) COMMENT '姓名',
    `employee_code` VARCHAR(20) COMMENT '工号',
    `mobile` VARCHAR(20) COMMENT '手机',
    `password` VARCHAR(100) COMMENT '密码',
    `forbidden_flag` INT(2) DEFAULT 0 COMMENT '禁用状态 0可用，1禁用',
    `email` VARCHAR(100) COMMENT '邮箱',
    `header_pic` VARCHAR(200) COMMENT '头像',
    `department_id` VARCHAR(100) COMMENT '部门ID',
    `department_name` VARCHAR(200) COMMENT '部门名称',
    `position` VARCHAR(200) COMMENT '职位',
    `agent_id` VARCHAR(200) COMMENT '所属代理商ID',
    `agent_name` VARCHAR(200) COMMENT '所属代理商名称',
    `merchant_id` VARCHAR(200) COMMENT '所属商户ID',
    `merchant_name` VARCHAR(200) COMMENT '所属商户名称',
    `roles` TEXT COMMENT '角色ID',
    `roles_name` TEXT COMMENT '角色名称',
    `extend_info` TEXT COMMENT '扩展信息，可使用JSON存储',
    `manage_area` TEXT COMMENT '管理厂区',
    `manage_area_id` TEXT COMMENT '管理厂区ID',
    `work_area` VARCHAR(200) COMMENT '工作厂区',
    `work_area_id` VARCHAR(200) COMMENT '工作厂区ID',
    `biz_type` VARCHAR(200) COMMENT '业务类型',
    `org_id` VARCHAR(200) COMMENT '组织ID',
    `org_name` VARCHAR(200) COMMENT '组织名称',
    `org_code` VARCHAR(200) COMMENT '组织编号',
    `type` VARCHAR(200) COMMENT '类型',
    `last_update_pwd` TIMESTAMP COMMENT '最后更新密码时间',
    `insert_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
    `last_update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted` INT(2) DEFAULT 0 COMMENT '删除标记，0否 1是',
    PRIMARY KEY (`employee_id`),
    UNIQUE KEY `uk_login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工表';

-- 4. 角色表 (auth_role)
CREATE TABLE `auth_role` (
    `role_id` VARCHAR(50) NOT NULL COMMENT '角色ID',
    `role_name` VARCHAR(100) COMMENT '名称',
    `forbidden_flag` INT(2) DEFAULT 0 COMMENT '禁用状态 0可用，1禁用',
    `remark` VARCHAR(200) COMMENT '备注信息',
    `parent_id` VARCHAR(100) COMMENT '上级ID',
    `parent_name` VARCHAR(200) COMMENT '上级名称',
    `supper_status` INT(2) DEFAULT 0 COMMENT '超级管理员标识，0：非，1：是',
    `type` VARCHAR(200) COMMENT '平台类型',
    `agent_id` VARCHAR(200) COMMENT '所属代理商ID',
    `agent_name` VARCHAR(200) COMMENT '所属代理商名称',
    `merchant_id` VARCHAR(200) COMMENT '所属商户ID',
    `merchant_name` VARCHAR(200) COMMENT '所属商户名称',
    `biz_type` VARCHAR(200) COMMENT '业务类型',
    `insert_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
    `last_update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted` INT(2) DEFAULT 0 COMMENT '删除标记，0否 1是',
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `uk_role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 5. 角色资源关联表 (auth_role_resource)
CREATE TABLE `auth_role_resource` (
    `role_resource_id` VARCHAR(50) NOT NULL COMMENT 'RoleResourceID',
    `role_id` VARCHAR(100) COMMENT '角色ID',
    `resource_id` VARCHAR(100) COMMENT '资源ID',
    `insert_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
    `last_update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`role_resource_id`),
    KEY `idx_role_id` (`role_id`),
    KEY `idx_resource_id` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色资源关联表';