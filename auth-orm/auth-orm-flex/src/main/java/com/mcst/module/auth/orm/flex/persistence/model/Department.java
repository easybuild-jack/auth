package com.mcst.module.auth.orm.flex.persistence.model;

import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.core.annotation.SingleUniqueField;
import com.mcst.easyfk.service.flex.persistence.BaseFlexEntity;
import com.mcst.module.auth.api.dto.DepartmentDto;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.github.linpeilie.annotations.AutoMapper;
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
@Table("auth_department")
@AutoMapper(target = DepartmentDto.class)
public class Department extends BaseFlexEntity<Department> {

    @PrimaryKey
    @Id(keyType = KeyType.Generator, value = "uuid")
    private String departmentId;

    /**
     * 名称
     */
    private String departmentName;

    /**
     * 编号
     */
    @SingleUniqueField(repetitionMsg = "部门名称不可重复")
    private String code;

    /**
     * 禁用状态 0可用，1禁用
     */
    private Integer forbiddenFlag;

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
     * 平台类型
     */
    private String type;

}
