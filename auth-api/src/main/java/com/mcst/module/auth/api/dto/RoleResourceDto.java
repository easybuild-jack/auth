package com.mcst.module.auth.api.dto;

import com.mcst.easyfk.core.annotation.PrimaryKey;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.mcst.easyfk.core.dto.BaseDto;

/**
* <p>
* 角色资源
* </p>
*
* @author liu yijun
* @since 2024-12-31
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description="角色资源数据对象")
public class RoleResourceDto extends BaseDto {


    /**
    * RoleResourceID
    */
    @PrimaryKey
    @Schema(description= "RoleResourceID")
    private String roleResourceId;

    /**
    * 角色ID
    */
    @Schema(description= "角色ID")
    private String roleId;

    /**
    * 资源ID
    */
    @Schema(description= "资源ID")
    private String resourceId;


}
