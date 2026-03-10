package com.mcst.module.auth.remote.impl;

import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.remote.dubbo.DubboBaseApiRemoteImpl;
import com.mcst.module.auth.api.IRoleApi;
import com.mcst.module.auth.api.request.RoleReq;
import com.mcst.module.auth.api.response.RoleResp;
import com.mcst.module.auth.api.vo.RoleGrantVO;
import com.mcst.module.auth.api.vo.RoleResourceVO;
import com.mcst.module.auth.remote.IRoleRemote;
import jakarta.annotation.Resource;

import java.util.List;

/**
 * <p>
 * 角色 Api接口Dubbo层实现
 * </p>
 *
 * @author liuyijun
 */
public class RoleApiDubboImpl extends DubboBaseApiRemoteImpl<IRoleRemote, RoleResp, String, RoleReq> implements IRoleApi {
    @Resource
    private IRoleRemote roleRemote;

    @Override
    public String getRoleResources(String roleUid) {
        return this.roleRemote.getRoleResources(roleUid);
    }

    @Override
    public BaseResult<?> grant(RoleGrantVO roleGrantVO) {
        return this.roleRemote.grant(roleGrantVO);
    }

    @Override
    public List<RoleResp> getAllSubRolesByParentId(String parentId) {
        return this.roleRemote.getAllSubRolesByParentId(parentId);
    }

    @Override
    public List<RoleResp> getEmployeeAllSubRoles(String employeeId) {
        return this.roleRemote.getEmployeeAllSubRoles(employeeId);
    }

    @Override
    public List<RoleResourceVO> getRoleResourceByRoleId(String roleId) {
        return this.roleRemote.getRoleResourceByRoleId(roleId);
    }
}
