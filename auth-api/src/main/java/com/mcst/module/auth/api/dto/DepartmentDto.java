package com.mcst.module.auth.api.dto;

import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.core.annotation.SingleUniqueField;
import com.mcst.easyfk.core.dto.BaseDto;
import com.mcst.module.auth.api.response.DepartmentResp;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author liu yijun
 * @since 2024-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "部门数据对象")
@AutoMapper(target = DepartmentResp.class)
public class DepartmentDto extends BaseDto {

    /**
     * 部门ID
     */
    @PrimaryKey
    @Schema(description = "部门ID")
    private String departmentId;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String departmentName;

    /**
     * 编号
     */
    @SingleUniqueField(repetitionMsg = "部门名称不可重复")
    @Schema(description = "编号")
    private String code;

    /**
     * 禁用状态 0可用，1禁用
     */
    @Schema(description = "禁用状态 0可用，1禁用")
    private Integer forbiddenFlag;

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
     * 平台类型
     */
    @Schema(description = "平台类型")
    private String type;

}
