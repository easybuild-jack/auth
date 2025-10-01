package com.mcst.module.auth.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuyijun 添加员工或修改员工时角色信息封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AddOrUpdateEmployeeRoleInfo implements Serializable {

    /**
     * 用户已经拥有的角色Id信息
     */
    private String[] employeeRoleIds;

    /**
     * 所有角色列表
     */
    private List<MultipleSelectVO> allRoles;
}
