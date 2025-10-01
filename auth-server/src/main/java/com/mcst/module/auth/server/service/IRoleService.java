package com.mcst.module.auth.server.service;

import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.service.api.IBaseService;
import com.mcst.module.auth.api.request.RoleReq;
import com.mcst.module.auth.api.response.RoleResp;
import com.mcst.module.auth.api.vo.RoleGrantVO;
import com.mcst.module.auth.api.vo.RoleResourceVO;

import java.util.List;

/**
 * <p>
 * 角色 服务接口类
 * </p>
 *
 * @author liuyijun
 */
public interface IRoleService extends IBaseService<RoleResp, String, RoleReq> {

    String getRoleResources(String roleUid);

    /**
     * 角色分配权限
     */
    BaseResult<?> grant(RoleGrantVO grantVO);

    /**
     * 根据一个roleId获取该role下所有子类，包括子类的子类
     */
    List<RoleResp> getAllSubRolesByParentId(String parentId);

    /**
     * 获取employee 拥有的所有子角色
     */
    List<RoleResp> getEmployeeAllSubRoles(String employeeId);

    /**
     * 获取角色资源数据
     *
     * @param roleId
     * @return
     */
    List<RoleResourceVO> getRoleResourceByRoleId(String roleId);
}
