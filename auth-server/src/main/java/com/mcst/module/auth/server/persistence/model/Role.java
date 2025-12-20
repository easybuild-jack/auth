package com.mcst.module.auth.server.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.core.annotation.SingleUniqueField;
import com.mcst.easyfk.service.mybatisplus.persistence.BaseMyBatisPlusEntity;
import com.mcst.module.auth.api.dto.RoleDto;
import com.mcst.module.auth.api.param.RoleParam;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
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
@TableName("auth_role")
@AutoMappers({@AutoMapper(target = RoleDto.class), @AutoMapper(target = RoleParam.class)})
public class Role extends BaseMyBatisPlusEntity<Role> {

    @PrimaryKey
    @TableId(type = IdType.ASSIGN_UUID)
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
     * 平台类型，如系统/企业/劳务公司等，可根据系统具体业务自定义平台类型
     */
    private String type;

    /**
     * 所属SaasID
     */
    private String saasId;

    /**
     * 所属Saas名称
     */
    private String saasName;

    /**
     * 业务类型
     */
    private String bizType;

}
