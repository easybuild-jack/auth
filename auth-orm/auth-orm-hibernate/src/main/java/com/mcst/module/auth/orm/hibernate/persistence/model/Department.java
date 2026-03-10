package com.mcst.module.auth.orm.hibernate.persistence.model;

import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.core.annotation.SingleUniqueField;
import com.mcst.easyfk.service.hibernate.persistence.BaseHibernateEntity;
import com.mcst.module.auth.api.dto.DepartmentDto;
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
 * 部门
 * </p>
 *
 * @author liuyijun
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "auth_department")
@AutoMapper(target = DepartmentDto.class)
public class Department extends BaseHibernateEntity {

    @PrimaryKey
    @Id
    @UuidGenerator
    @Column(name = "department_id")
    private String departmentId;

    /**
     * 名称
     */
    @Column(name = "department_name")
    private String departmentName;

    /**
     * 编号
     */
    @SingleUniqueField(repetitionMsg = "部门名称不可重复")
    @Column(name = "code")
    private String code;

    /**
     * 禁用状态 0可用，1禁用
     */
    @Column(name = "forbidden_flag")
    private Integer forbiddenFlag;

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
     * 平台类型
     */
    @Column(name = "type")
    private String type;

}
