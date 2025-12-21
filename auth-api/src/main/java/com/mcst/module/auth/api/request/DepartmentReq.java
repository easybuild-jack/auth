package com.mcst.module.auth.api.request;

import com.mcst.module.auth.api.dto.DepartmentDto;
import com.mcst.module.auth.api.param.DepartmentParam;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门数据请求对象
 * </p>
 *
 * @author liu yijun
 * @since 2024-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "部门数据请求对象")
@AutoMapper(target = DepartmentDto.class)
public class DepartmentReq extends DepartmentParam {

}
