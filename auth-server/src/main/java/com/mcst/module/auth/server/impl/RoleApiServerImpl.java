package com.mcst.module.auth.server.impl;

import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.service.base.BaseApiServiceImpl;
import com.mcst.module.auth.api.IRoleApi;
import com.mcst.module.auth.api.request.RoleReq;
import com.mcst.module.auth.api.response.RoleResp;
import com.mcst.module.auth.api.vo.RoleGrantVO;
import com.mcst.module.auth.api.vo.RoleResourceVO;
import com.mcst.module.auth.server.service.IRoleService;
import jakarta.annotation.Resource;

import java.util.List;

/**
 * <p>
 * 角色 Api接口Server层实现
 * </p>
 *
 * @author liuyijun
 */
public class RoleApiServerImpl extends BaseApiServiceImpl<IRoleService, RoleResp, String, RoleReq> implements IRoleApi {
    @Resource
    private IRoleService roleService;

    @Override
    public String getRoleResources(String roleUid) {
        return this.roleService.getRoleResources(roleUid);
    }

    @Override
    public BaseResult<?> grant(RoleGrantVO roleGrantVO) {
        return this.roleService.grant(roleGrantVO);
    }

    @Override
    public List<RoleResp> getAllSubRolesByParentId(String parentId) {
        return this.roleService.getAllSubRolesByParentId(parentId);
    }

    @Override
    public List<RoleResp> getEmployeeAllSubRoles(String employeeId) {
        return this.roleService.getEmployeeAllSubRoles(employeeId);
    }

    @Override
    public List<RoleResourceVO> getRoleResourceByRoleId(String roleId) {
        return this.roleService.getRoleResourceByRoleId(roleId);
    }
}
