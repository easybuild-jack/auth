package com.mcst.module.auth.orm.hibernate.persistence.model;

import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.service.hibernate.persistence.BaseHibernateEntity;
import com.mcst.module.auth.api.dto.RoleResourceDto;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.UuidGenerator;

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
@Entity
@Table(name = "auth_role_resource")
@AutoMapper(target = RoleResourceDto.class)
public class RoleResource extends BaseHibernateEntity {

    @PrimaryKey
    @Id
    @UuidGenerator
    @Column(name = "role_resource_id")
    private String roleResourceId;
    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private String roleId;

    /**
     * 资源ID
     */
    @Column(name = "resource_id")
    private String resourceId;

}
