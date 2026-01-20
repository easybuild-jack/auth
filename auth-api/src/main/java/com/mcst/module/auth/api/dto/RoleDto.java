package com.mcst.module.auth.api.dto;

import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.core.annotation.SingleUniqueField;
import com.mcst.easyfk.core.dto.BaseDto;
import com.mcst.module.auth.api.response.RoleResp;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author liu yijun
 * @since 2024-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "角色数据对象")
@AutoMapper(target = RoleResp.class)
public class RoleDto extends BaseDto {

    /**
     * 角色ID
     */
    @PrimaryKey
    @Schema(description = "角色ID")
    private String roleId;

    /**
     * 名称
     */
    @SingleUniqueField(repetitionMsg = "角色名称不可重复")
    @Schema(description = "名称")
    private String roleName;

    /**
     * 禁用状态 0可用，1禁用
     */
    @Schema(description = "禁用状态 0可用，1禁用")
    private Integer forbiddenFlag;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String remark;

    /**
     * 上级ID
     */
    @Schema(description = "上级ID")
    private String parentId;

    /**
     * 上级名称
     */
    @Schema(description = "上级名称")
    private String parentName;

    /**
     * 超级管理员标识，0：非，1：是
     */
    @Schema(description = "超级管理员标识，0：非，1：是")
    private Integer supperStatus;

    /**
     * 类型
     */
    @Schema(description = "类型")
    private String type;

    /**
     * 所属代理商ID
     */
    @Schema(description = "所属代理商ID")
    private String agentId;

    /**
     * 所属代理商名称
     */
    @Schema(description = "所属代理商名称")
    private String agentName;

    /**
     * 所属商户ID
     */
    @Schema(description = "所属商户ID")
    private String merchantId;

    /**
     * 所属商户名称
     */
    @Schema(description = "所属商户名称")
    private String merchantName;

    /**
     * 业务类型
     */
    @Schema(description = "业务类型")
    private String bizType;

}
