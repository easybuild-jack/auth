package com.mcst.module.auth.api.request;

import com.mcst.module.auth.api.dto.EmployeeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 员工信息数据编辑对象
 * </p>
 *
 * @author liu yijun
 * @since 2024-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "员工信息数据编辑对象")
public class EmployeeEditReq extends EmployeeDto {


}
