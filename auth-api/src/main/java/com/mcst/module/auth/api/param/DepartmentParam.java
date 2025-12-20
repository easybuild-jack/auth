package com.mcst.module.auth.api.param;

import com.mcst.easyfk.core.dto.request.BasicParam;
import com.mcst.module.auth.api.request.DepartmentReq;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门数据参数对象
 * </p>
 *
 * @author liu yijun
 * @since 2024-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "部门数据参数对象")
@AutoMapper(target = DepartmentReq.class)
public class DepartmentParam extends BasicParam {


    /**
     * 部门ID
     */
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
    @Schema(description = "编号")
    private String code;

    /**
     * 禁用状态 0可用，1禁用
     */
    @Schema(description = "禁用状态 0可用，1禁用")
    private Integer forbiddenFlag;

    /**
     * SaasId
     */
    @Schema(description = "SaasId")
    private String saasId;

    /**
     * Saas名称
     */
    @Schema(description = "Saas名称")
    private String saasName;

    /**
     * 平台类型
     */
    @Schema(description = "平台类型")
    private String type;


}
