package com.mcst.module.auth.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.mcst.module.auth.api.param.DepartmentParam;

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
public class DepartmentReq extends DepartmentParam {

}
