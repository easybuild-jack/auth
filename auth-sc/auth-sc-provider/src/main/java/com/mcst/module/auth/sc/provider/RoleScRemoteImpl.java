package com.mcst.module.auth.sc.provider;

import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.remote.sc.BaseRemoteImpl;
import com.mcst.module.auth.api.request.RoleReq;
import com.mcst.module.auth.api.response.RoleResp;
import com.mcst.module.auth.api.vo.RoleGrantVO;
import com.mcst.module.auth.api.vo.RoleResourceVO;
import com.mcst.module.auth.sc.client.api.remote.IRoleScRemote;
import com.mcst.module.auth.server.service.IRoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色 SCRemoteImpl
 * </p>
 *
 * @author liuyijun
 */
@RestController
@RequestMapping("${easyfk.config.remote.auth.base-path:/remote}/auth/role")
public class RoleScRemoteImpl extends BaseRemoteImpl<IRoleService, RoleResp, String, RoleReq> implements IRoleScRemote {

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
