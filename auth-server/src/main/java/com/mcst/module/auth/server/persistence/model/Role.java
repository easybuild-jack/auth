package com.mcst.module.auth.server.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.core.annotation.SingleUniqueField;
import com.mcst.easyfk.service.mybatisplus.persistence.BaseMyBatisPlusEntity;
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
@Table(name = "auth_role", comment = "角色")
public class Role extends BaseMyBatisPlusEntity<Role> {

    @PrimaryKey
    @TableId(type = IdType.ASSIGN_UUID)
    @Column(comment = "角色ID", isKey = true, length = 50, type = MySqlTypeConstant.VARCHAR)
    private String roleId;

    /**
     * 名称
     */
    @SingleUniqueField(repetitionMsg = "角色名称不可重复")
    @Column(length = 100, comment = "名称")
    private String roleName;

    /**
     * 禁用状态 0可用，1禁用
     */
    @Column(length = 2, comment = "禁用状态 0可用，1禁用", defaultValue = "0")
    private Integer forbiddenFlag;

    @Column(length = 200, comment = "备注信息")
    private String remark;

    /**
     * 上级ID
     */
    @Column(length = 100, comment = "上级ID")
    private String parentId;
    /**
     * 上级ID
     */
    @Column(length = 200, comment = "上级名称")
    private String parentName;

    /**
     * 超级管理员标识，0：非，1：是
     */
    @Column(comment = "超级管理员标识，0：非，1：是", length = 2, defaultValue = "0")
    private Integer supperStatus;

    /**
     * 平台类型，如系统/企业/劳务公司等，可根据系统具体业务自定义平台类型
     */
    @Column(length = 200, comment = "平台类型")
    private String type;

    /**
     * 所属SaasID
     */
    @Column(length = 100, comment = "所属SaasID")
    private String saasId;

    /**
     * 所属Saas名称
     */
    @Column(length = 200, comment = "所属Saas名称")
    private String saasName;

    /**
     * 业务类型
     */
    @Column(length = 200, comment = "业务类型")
    private String bizType;

}
