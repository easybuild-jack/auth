package com.mcst.module.auth.orm.hibernate.persistence.model;

import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.core.annotation.SingleUniqueField;
import com.mcst.easyfk.service.hibernate.persistence.BaseHibernateEntity;
import com.mcst.module.auth.api.dto.RoleDto;
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
 * 角色
 * </p>
 *
 * @author liuyijun
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "auth_role")
@AutoMapper(target = RoleDto.class)
public class Role extends BaseHibernateEntity {

    @PrimaryKey
    @Id
    @UuidGenerator
    @Column(name = "role_id")
    private String roleId;

    /**
     * 名称
     */
    @SingleUniqueField(repetitionMsg = "角色名称不可重复")
    @Column(name = "role_name")
    private String roleName;

    /**
     * 禁用状态 0可用，1禁用
     */
    @Column(name = "forbidden_flag")
    private Integer forbiddenFlag;

    @Column(name = "remark")
    private String remark;

    /**
     * 上级ID
     */
    @Column(name = "parent_id")
    private String parentId;
    /**
     * 上级ID
     */
    @Column(name = "parent_name")
    private String parentName;

    /**
     * 超级管理员标识，0：非，1：是
     */
    @Column(name = "supper_status")
    private Integer supperStatus;

    /**
     * 类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 所属代理商ID
     */
    @Column(name = "agent_id")
    private String agentId;

    /**
     * 所属代理商名称
     */
    @Column(name = "agent_name")
    private String agentName;

    /**
     * 所属商户ID
     */
    @Column(name = "merchant_id")
    private String merchantId;

    /**
     * 所属商户名称
     */
    @Column(name = "merchant_name")
    private String merchantName;

    /**
     * 业务类型
     */
    @Column(name = "biz_type")
    private String bizType;

}
