package com.mcst.module.auth.server.persistence.model;

import com.baomidou.mybatisplus.annotation.*;
import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.core.annotation.SingleUniqueField;
import com.mcst.easyfk.service.mybatisplus.persistence.BaseMyBatisPlusEntity;
import com.mcst.module.auth.api.dto.EmployeeDto;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 员工
 * </p>
 *
 * @author liuyijun
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("auth_employee")
@AutoMapper(target = EmployeeDto.class)
public class Employee extends BaseMyBatisPlusEntity<Employee> {

    @PrimaryKey
    @TableId(type = IdType.ASSIGN_UUID)
    private String employeeId;

    /**
     * 登录账户
     */
    @SingleUniqueField(repetitionMsg = "登录账户不可重复")
    private String loginName;

    /**
     * 姓名
     */
    private String employeeName;

    /**
     * 工号
     */
    private String employeeCode;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 禁用状态 0可用，1禁用
     */
    private Integer forbiddenFlag;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String headerPic;

    /**
     * 部门ID
     */
    private String departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 职位
     */
    private String position;

    /**
     * 所属SaasID
     */
    private String saasId;

    /**
     * 所属Saas名称
     */
    private String saasName;

    /**
     * 角色ID
     */
    private String roles;

    /**
     * 角色名称
     */
    private String rolesName;

    /**
     * 扩展信息，可使用JSON存储
     */
    private String extendInfo;

    /**
     * 业务类型
     */
    private String bizType;

    private String orgId;

    private String orgName;

    /**
     * 账号类型
     */
    private String type;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime lastUpdatePwd;

}
