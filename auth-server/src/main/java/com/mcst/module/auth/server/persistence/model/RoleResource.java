package com.mcst.module.auth.server.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.service.mybatisplus.persistence.BaseMyBatisPlusSimpleEntity;
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
@Table(name = "auth_role_resource", comment = "角色资源")
public class RoleResource extends BaseMyBatisPlusSimpleEntity<RoleResource> {

    @PrimaryKey
    @TableId(type = IdType.ASSIGN_UUID)
    @Column(comment = "RoleResourceID", isKey = true, length = 50, type = MySqlTypeConstant.VARCHAR)
    private String roleResourceId;
    /**
     * 角色ID
     */
    @Column(length = 100, comment = "角色ID")
    private String roleId;

    /**
     * 资源ID
     */
    @Column(length = 100, comment = "资源ID")
    private String resourceId;

}
