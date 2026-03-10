package com.mcst.module.auth.remote.provider.impl;

import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.remote.dubbo.DubboBaseRemoteImpl;
import com.mcst.module.auth.api.request.RoleReq;
import com.mcst.module.auth.api.response.RoleResp;
import com.mcst.module.auth.api.vo.RoleGrantVO;
import com.mcst.module.auth.api.vo.RoleResourceVO;
import com.mcst.module.auth.remote.IRoleRemote;
import com.mcst.module.auth.server.service.IRoleService;
import jakarta.annotation.Resource;

import java.util.List;

/**
 * <p>
 * 角色 RemoteImpl
 * </p>
 *
 * @author liuyijun
 */
public class RoleRemoteImpl extends DubboBaseRemoteImpl<IRoleService, RoleResp, String, RoleReq> implements IRoleRemote {

    @Resource
    private IRoleService roleService;

    @Override
    public String getRoleResources(String roleId) {
        return this.roleService.getRoleResources(roleId);
    }

    @Override
    public BaseResult<?> grant(RoleGrantVO grantVO) {
        return this.roleService.grant(grantVO);
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
