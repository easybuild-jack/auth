package com.mcst.module.auth.api.request;

import com.mcst.module.auth.api.param.RoleParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author liu yijun
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "角色查询条件对象")
public class RoleReq extends RoleParam {
}
