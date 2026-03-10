package com.mcst.module.auth.remote;

import com.mcst.easyfk.authority.constant.ResourceSecurityLevel;
import com.mcst.easyfk.authority.request.AuthResourceReq;
import com.mcst.easyfk.authority.response.AuthResourceResp;
import com.mcst.easyfk.core.dto.response.ResponseResult;
import com.mcst.easyfk.remote.dubbo.IDubboBaseRemote;

import java.util.List;

/**
 * @author liuyijun
 */
public interface IAuthResourceRemote extends IDubboBaseRemote<AuthResourceResp, String, AuthResourceReq> {

    /**
     * 权限资源列表数据,获取系统所有权限类型的资源
     */
    ResponseResult<List<AuthResourceResp>> authorityResourceList();

    /**
     * 检查当前uri 的访问权限
     */
    ResourceSecurityLevel checkUriSecurityLevel(String url);

    /**
     * 获取所有平台端的权限资源
     */
    List<AuthResourceResp> getAllPlatformAuthResources();

    /**
     * 获取所有商户端的权限资源
     */
    List<AuthResourceResp> getAllSaasAuthResources();

    /**
     * 获取所有商户端默认的权限资源
     */
    List<AuthResourceResp> getAllSaasDefaultAuthResources();

    /**
     * 批量添加
     */
    void saveResourceByBath(List<AuthResourceReq> list);

    /**
     * 获取平台端资源
     */
    List<AuthResourceResp> getPlatformAuthResources(String type, Integer defaultStatus);
}
