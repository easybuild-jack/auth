package com.mcst.module.auth.api.param;

import com.mcst.easyfk.core.dto.request.BasicParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 员工信息数据参数对象
 * </p>
 *
 * @author liu yijun
 * @since 2024-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "员工信息数据参数对象")
public class EmployeeParam extends BasicParam {


    /**
     * 员工ID
     */
    @Schema(description = "员工ID")
    private String employeeId;

    /**
     * 登录账户
     */
    @Schema(description = "登录账户")
    private String loginName;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String employeeName;

    /**
     * 工号
     */
    @Schema(description = "工号")
    private String employeeCode;

    /**
     * 手机
     */
    @Schema(description = "手机")
    private String mobile;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 禁用状态 0可用，1禁用
     */
    @Schema(description = "禁用状态 0可用，1禁用")
    private Integer forbiddenFlag;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String headerPic;

    /**
     * 部门ID
     */
    @Schema(description = "部门ID")
    private String departmentId;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String departmentName;

    /**
     * 职位
     */
    @Schema(description = "职位")
    private String position;

    /**
     * 所属SaasID
     */
    @Schema(description = "所属SaasID")
    private String saasId;

    /**
     * 所属Saas名称
     */
    @Schema(description = "所属Saas名称")
    private String saasName;

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private String roles;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String rolesName;

    /**
     * 扩展信息，可使用JSON存储
     */
    @Schema(description = "扩展信息，可使用JSON存储")
    private String extendInfo;

    /**
     * 管理厂区
     */
    @Schema(description = "管理厂区")
    private String manageArea;

    /**
     * 管理厂区ID
     */
    @Schema(description = "管理厂区ID")
    private String manageAreaId;

    /**
     * 工作厂区
     */
    @Schema(description = "工作厂区")
    private String workArea;

    /**
     * 工作厂区ID
     */
    @Schema(description = "工作厂区ID")
    private String workAreaId;

    /**
     * 业务类型
     */
    @Schema(description = "业务类型")
    private String bizType;

    /**
     * 组织ID
     */
    @Schema(description = "组织ID")
    private String orgId;

    /**
     * 组织名称
     */
    @Schema(description = "组织名称")
    private String orgName;

    /**
     * 组织编号
     */
    @Schema(description = "组织编号")
    private String orgCode;

    /**
     * 类型
     */
    @Schema(description = "类型")
    private String type;

    /**
     * 最后更新密码时间
     */
    @Schema(description = "最后更新密码时间")
    private LocalDateTime lastUpdatePwd;


}
