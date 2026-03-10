package com.mcst.module.auth.orm.flex.persistence.model;

import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.core.annotation.SingleUniqueField;
import com.mcst.easyfk.service.flex.persistence.BaseFlexEntity;
import com.mcst.module.auth.api.dto.RoleDto;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author liuyijun
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Table("auth_role")
@AutoMapper(target = RoleDto.class)
public class Role extends BaseFlexEntity<Role> {

    @PrimaryKey
    @Id(keyType = KeyType.Generator, value = "uuid")
    private String roleId;

    /**
     * 名称
     */
    @SingleUniqueField(repetitionMsg = "角色名称不可重复")
    private String roleName;

    /**
     * 禁用状态 0可用，1禁用
     */
    private Integer forbiddenFlag;

    private String remark;

    /**
     * 上级ID
     */
    private String parentId;
    /**
     * 上级ID
     */
    private String parentName;

    /**
     * 超级管理员标识，0：非，1：是
     */
    private Integer supperStatus;

    /**
     * 类型
     */
    private String type;

    /**
     * 所属代理商ID
     */
    private String agentId;

    /**
     * 所属代理商名称
     */
    private String agentName;

    /**
     * 所属商户ID
     */
    private String merchantId;

    /**
     * 所属商户名称
     */
    private String merchantName;

    /**
     * 业务类型
     */
    private String bizType;

}
