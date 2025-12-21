package com.mcst.module.auth.api.request;

import com.mcst.module.auth.api.dto.RoleDto;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色数据编辑对象
 * </p>
 *
 * @author liu yijun
 * @since 2024-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "角色数据编辑对象")
@AutoMapper(target = RoleReq.class)
public class RoleEditReq extends RoleDto {

}
