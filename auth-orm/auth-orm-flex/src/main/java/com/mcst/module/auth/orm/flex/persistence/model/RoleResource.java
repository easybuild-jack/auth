package com.mcst.module.auth.orm.flex.persistence.model;

import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.service.flex.persistence.BaseFlexEntity;
import com.mcst.module.auth.api.dto.RoleResourceDto;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.github.linpeilie.annotations.AutoMapper;
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
@Table("auth_role_resource")
@AutoMapper(target = RoleResourceDto.class)
public class RoleResource extends BaseFlexEntity<RoleResource> {

    @PrimaryKey
    @Id(keyType = KeyType.Generator, value = "uuid")
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
