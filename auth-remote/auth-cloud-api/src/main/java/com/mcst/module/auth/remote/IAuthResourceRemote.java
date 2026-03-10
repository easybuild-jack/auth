package com.mcst.module.auth.remote;

import com.mcst.easyfk.authority.constant.ResourceSecurityLevel;
import com.mcst.easyfk.authority.request.AuthResourceReq;
import com.mcst.easyfk.authority.response.AuthResourceResp;
import com.mcst.easyfk.core.dto.response.ResponseResult;
import com.mcst.easyfk.remote.sc.IBaseRemote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author liuyijun
 */
@FeignClient(value = "${easyfk.config.remote.resource.service-id:server-all}", path = "${easyfk.config.remote.resource.base-path:/remote/auth/resource}")
public interface IAuthResourceRemote extends IBaseRemote<AuthResourceResp, String, AuthResourceReq> {

    /**
     * 权限资源列表数据,获取系统所有权限类型的资源
     *
     * @return
     */
    @GetMapping("/authorityResourceList")
    ResponseResult<List<AuthResourceResp>> authorityResourceList();

    /**
     * 检查当前uri 的访问权限
     *
     * @param url
     * @return
     */
    @GetMapping("/checkUriSecurityLevel")
    ResourceSecurityLevel checkUriSecurityLevel(@RequestParam("url") String url);

    /**
     * 获取所有平台端的权限资源
     *
     * @return
     */
    @GetMapping("/getAllPlatformAuthResources")
    List<AuthResourceResp> getAllPlatformAuthResources();

    /**
     * 获取所有商户端的权限资源
     *
     * @return
     */
    @GetMapping("/getAllSaasAuthResources")
    List<AuthResourceResp> getAllSaasAuthResources();

    /**
     * 获取所有商户端默认的权限资源
     *
     * @return
     */
    @GetMapping("/getAllSaasDefaultAuthResources")
    List<AuthResourceResp> getAllSaasDefaultAuthResources();

    /**
     * 批量添加
     *
     * @param list
     */
    @GetMapping("/saveResourceByBath")
    void saveResourceByBath(@RequestBody List<AuthResourceReq> list);

    /**
     * 获取平台端资源
     *
     * @param type          平台类型
     * @param defaultStatus 是否是默认 0否 1是
     */
    @GetMapping("/getPlatformAuthResources")
    List<AuthResourceResp> getPlatformAuthResources(@RequestParam("type") String type, @RequestParam(value = "defaultStatus", required = false) Integer defaultStatus);
}
