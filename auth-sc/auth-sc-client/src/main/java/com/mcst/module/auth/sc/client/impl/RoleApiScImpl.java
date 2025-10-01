package com.mcst.module.auth.sc.client.impl;

import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.remote.sc.BaseApiRemoteImpl;
import com.mcst.module.auth.api.IRoleApi;
import com.mcst.module.auth.api.request.RoleReq;
import com.mcst.module.auth.api.response.RoleResp;
import com.mcst.module.auth.api.vo.RoleGrantVO;
import com.mcst.module.auth.api.vo.RoleResourceVO;
import com.mcst.module.auth.sc.client.api.remote.IRoleScRemote;
import jakarta.annotation.Resource;

import java.util.List;

/**
 * <p>
 * 角色 Api接口SC层实现
 * </p>
 *
 * @author liuyijun
 */
public class RoleApiScImpl extends BaseApiRemoteImpl<IRoleScRemote, RoleResp, String, RoleReq> implements IRoleApi {
    @Resource
    private IRoleScRemote roleScRemote;

    @Override
    public String getRoleResources(String roleUid) {
        return this.roleScRemote.getRoleResources(roleUid);
    }

    @Override
    public BaseResult<?> grant(RoleGrantVO roleGrantVO) {
        return this.roleScRemote.grant(roleGrantVO);
    }

    @Override
    public List<RoleResp> getAllSubRolesByParentId(String parentId) {
        return this.roleScRemote.getAllSubRolesByParentId(parentId);
    }

    @Override
    public List<RoleResp> getEmployeeAllSubRoles(String employeeId) {
        return this.roleScRemote.getEmployeeAllSubRoles(employeeId);
    }

    @Override
    public List<RoleResourceVO> getRoleResourceByRoleId(String roleId) {
        return this.roleScRemote.getRoleResourceByRoleId(roleId);
    }
}
