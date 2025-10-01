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
 * 部门
 * </p>
 *
 * @author liuyijun
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("auth_department")
@Table(name = "auth_department", comment = "部门")
public class Department extends BaseMyBatisPlusEntity<Department> {

    @PrimaryKey
    @TableId(type = IdType.ASSIGN_UUID)
    @Column(comment = "部门ID", isKey = true, length = 50, type = MySqlTypeConstant.VARCHAR)
    private String departmentId;

    /**
     * 名称
     */
    @Column(length = 100, comment = "名称")
    private String departmentName;

    /**
     * 编号
     */
    @SingleUniqueField(repetitionMsg = "部门名称不可重复")
    @Column(length = 100, comment = "编号")
    private String code;

    /**
     * 禁用状态 0可用，1禁用
     */
    @Column(length = 2, comment = "禁用状态 0可用，1禁用", defaultValue = "0")
    private Integer forbiddenFlag;

    /**
     * SaasId
     */
    @Column(length = 100, comment = "SaasId")
    private String saasId;
    /**
     * Saas名称
     */
    @Column(length = 100, comment = "Saas名称")
    private String saasName;
    /**
     * 平台类型
     */
    @Column(comment = "平台类型", length = 200)
    private String type;

}
