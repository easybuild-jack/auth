package com.mcst.module.auth.orm.hibernate.persistence.model;

import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.core.annotation.SingleUniqueField;
import com.mcst.easyfk.service.hibernate.persistence.BaseHibernateEntity;
import com.mcst.module.auth.api.dto.EmployeeDto;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.UuidGenerator;

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
@Entity
@Table(name = "auth_employee")
@AutoMapper(target = EmployeeDto.class)
public class Employee extends BaseHibernateEntity {

    @PrimaryKey
    @Id
    @UuidGenerator
    @Column(name = "employee_id")
    private String employeeId;

    /**
     * 登录账户
     */
    @SingleUniqueField(repetitionMsg = "登录账户不可重复")
    @Column(name = "login_name")
    private String loginName;

    /**
     * 姓名
     */
    @Column(name = "employee_name")
    private String employeeName;

    /**
     * 工号
     */
    @Column(name = "employee_code")
    private String employeeCode;

    /**
     * 手机
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 禁用状态 0可用，1禁用
     */
    @Column(name = "forbidden_flag")
    private Integer forbiddenFlag;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 头像
     */
    @Column(name = "header_pic")
    private String headerPic;

    /**
     * 部门ID
     */
    @Column(name = "department_id")
    private String departmentId;

    /**
     * 部门名称
     */
    @Column(name = "department_name")
    private String departmentName;

    /**
     * 职位
     */
    @Column(name = "position")
    private String position;

    /**
     * 所属代理商ID
     */
    @Column(name = "agent_id")
    private String agentId;

    /**
     * 所属代理商名称
     */
    @Column(name = "agent_name")
    private String agentName;

    /**
     * 所属商户ID
     */
    @Column(name = "merchant_id")
    private String merchantId;

    /**
     * 所属商户名称
     */
    @Column(name = "merchant_name")
    private String merchantName;

    /**
     * 角色ID
     */
    @Column(name = "roles")
    private String roles;

    /**
     * 角色名称
     */
    @Column(name = "roles_name")
    private String rolesName;

    /**
     * 扩展信息，可使用JSON存储
     */
    @Column(name = "extend_info")
    private String extendInfo;

    /**
     * 业务类型
     */
    @Column(name = "biz_type")
    private String bizType;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "org_name")
    private String orgName;

    /**
     * 类型
     */
    @Column(name = "type")
    private String type;

    @Column(name = "last_update_pwd")
    private LocalDateTime lastUpdatePwd;

}
