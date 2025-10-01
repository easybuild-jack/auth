package com.mcst.module.auth.api.response;

import com.mcst.module.auth.api.dto.DepartmentDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门数据响应对象
 * </p>
 *
 * @author liu yijun
 * @since 2024-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "部门数据响应对象")
public class DepartmentResp extends DepartmentDto {

}
