package com.mcst.module.auth.api.param;

import com.mcst.easyfk.core.dto.request.BasicParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色资源
 * </p>
 *
 * @author liuyijun
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "角色资源查询条件对象")
public class RoleResourceParam extends BasicParam {

    private String roleResourceId;

    private String roleId;

    private String resourceId;

}
