package com.mcst.module.auth.server.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.service.mybatisplus.persistence.BaseMyBatisPlusSimpleEntity;
import com.mcst.module.auth.api.dto.RoleResourceDto;
import com.mcst.module.auth.api.param.RoleResourceParam;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
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
@TableName("auth_role_resource")
@AutoMappers({@AutoMapper(target = RoleResourceDto.class), @AutoMapper(target = RoleResourceParam.class)})
public class RoleResource extends BaseMyBatisPlusSimpleEntity<RoleResource> {

    @PrimaryKey
    @TableId(type = IdType.ASSIGN_UUID)
    private String roleResourceId;
    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 资源ID
     */
    private String resourceId;

}
