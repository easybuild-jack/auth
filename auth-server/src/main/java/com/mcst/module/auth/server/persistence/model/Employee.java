package com.mcst.module.auth.server.persistence.model;

import com.baomidou.mybatisplus.annotation.*;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.core.annotation.SingleUniqueField;
import com.mcst.easyfk.service.mybatisplus.persistence.BaseMyBatisPlusEntity;
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
@Table(name = "auth_department", comment = "员工")
public class Employee extends BaseMyBatisPlusEntity<Employee> {

    @PrimaryKey
    @TableId(type = IdType.ASSIGN_UUID)
    @Column(comment = "员工ID", isKey = true, length = 50, type = MySqlTypeConstant.VARCHAR)
    private String employeeId;

    /**
     * 登录账户
     */
    @SingleUniqueField(repetitionMsg = "登录账户不可重复")
    @Column(length = 100, comment = "登录账户")
    private String loginName;

    /**
     * 姓名
     */
    @Column(length = 20, comment = "姓名")
    private String employeeName;

    /**
     * 工号
     */
    @Column(length = 20, comment = "工号")
    private String employeeCode;

    /**
     * 手机
     */
    @Column(length = 20, comment = "手机")
    private String mobile;

    /**
     * 密码
     */
    @Column(length = 100, comment = "密码")
    private String password;

    /**
     * 禁用状态 0可用，1禁用
     */
    @Column(length = 2, comment = "禁用状态 0可用，1禁用", defaultValue = "0")
    private Integer forbiddenFlag;

    /**
     * 邮箱
     */
    @Column(length = 100, comment = "邮箱")
    private String email;

    /**
     * 头像
     */
    @Column(length = 200, comment = "头像")
    private String headerPic;

    /**
     * 部门ID
     */
    @Column(length = 100, comment = "部门ID")
    private String departmentId;

    /**
     * 部门名称
     */
    @Column(length = 200, comment = "部门名称")
    private String departmentName;

    /**
     * 职位
     */
    @Column(length = 200, comment = "职位")
    private String position;

    /**
     * 所属SaasID
     */
    @Column(length = 200, comment = "所属SaasID")
    private String saasId;

    /**
     * 所属Saas名称
     */
    @Column(length = 200, comment = "所属Saas名称")
    private String saasName;

    /**
     * 角色ID
     */
    @Column(comment = "角色ID")
    private String roles;

    /**
     * 角色名称
     */
    @Column(comment = "角色名称")
    private String rolesName;

    /**
     * 扩展信息，可使用JSON存储
     */
    @Column(comment = "扩展信息，可使用JSON存储")
    private String extendInfo;

    /**
     * 管理厂区
     */
    @Column(comment = "管理厂区")
    private String manageArea;

    /**
     * 管理厂区ID
     */
    @Column(comment = "管理厂区ID")
    private String manageAreaId;

    /**
     * 工作厂区
     */
    @Column(length = 200, comment = "工作厂区")
    private String workArea;

    /**
     * 工作厂区ID
     */
    @Column(length = 200, comment = "工作厂区ID")
    private String workAreaId;

    /**
     * 业务类型
     */
    @Column(length = 200, comment = "业务类型")
    private String bizType;

    @Column(length = 200, comment = "组织ID")
    private String orgId;

    @Column(length = 200, comment = "组织名称")
    private String orgName;

    @Column(length = 200, comment = "组织编号")
    private String orgCode;

    @Column(length = 200, comment = "类型")
    private String type;

    @Column(type = MySqlTypeConstant.TIMESTAMP, comment = "最后更新密码时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime lastUpdatePwd;

}
