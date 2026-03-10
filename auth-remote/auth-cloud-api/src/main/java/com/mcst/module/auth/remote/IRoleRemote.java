package com.mcst.module.auth.remote;

import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.remote.sc.IBaseRemote;
import com.mcst.module.auth.api.request.RoleReq;
import com.mcst.module.auth.api.response.RoleResp;
import com.mcst.module.auth.api.vo.RoleGrantVO;
import com.mcst.module.auth.api.vo.RoleResourceVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 角色 Remote接口类
 * </p>
 *
 * @author liuyijun
 */
@FeignClient(value = "${easyfk.config.remote.auth.service-id:auth-server}", path = "${easyfk.config.remote.auth" + ".base-path:/remote}/auth/role")
public interface IRoleRemote extends IBaseRemote<RoleResp, String, RoleReq> {
    @GetMapping("/getRoleResources")
    String getRoleResources(@RequestParam("roleId") String roleId);

    /**
     * 角色分配权限
     */
    @PostMapping(value = "/grant", consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResult<?> grant(@RequestBody RoleGrantVO grantVO);

    /**
     * 根据一个roleId获取该role下所有子类，包括子类的子类
     */
    @GetMapping("/getAllSubRolesByParentId")
    List<RoleResp> getAllSubRolesByParentId(@RequestParam("parentId") String parentId);

    /**
     * 获取employee 拥有的所有子角色
     */
    @GetMapping("/getEmployeeAllSubRoles")
    List<RoleResp> getEmployeeAllSubRoles(@RequestParam("employeeId") String employeeId);

    /**
     * 获取角色资源数据
     *
     * @param roleId
     * @return
     */
    @GetMapping("/getRoleResourceByRoleId")
    List<RoleResourceVO> getRoleResourceByRoleId(@RequestParam("roleId") String roleId);
}
