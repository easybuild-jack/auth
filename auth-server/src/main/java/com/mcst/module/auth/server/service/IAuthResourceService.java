package com.mcst.module.auth.server.service;

import com.mcst.easyfk.authority.constant.ResourceSecurityLevel;
import com.mcst.easyfk.authority.request.AuthResourceReq;
import com.mcst.easyfk.authority.response.AuthResourceResp;
import com.mcst.easyfk.core.dto.response.ResponseResult;
import com.mcst.easyfk.service.api.IBaseService;

import java.util.List;

/**
 * @author liuyijun
 */
public interface IAuthResourceService extends IBaseService<AuthResourceResp, String, AuthResourceReq> {

    /**
     * 权限资源列表数据,获取系统所有权限类型的资源
     *
     * @return
     */
    ResponseResult<List<AuthResourceResp>> authorityResourceList();

    /**
     * 检查当前uri 的访问权限
     *
     * @param url
     * @return
     */
    ResourceSecurityLevel checkUriSecurityLevel(String url);

    /**
     * 获取所有平台端的权限资源
     *
     * @return
     */
    List<AuthResourceResp> getAllPlatformAuthResources();

    /**
     * 获取所有Saas端的权限资源
     *
     * @return
     */
    List<AuthResourceResp> getAllSaasAuthResources();

    /**
     * 获取所有Saas端默认的权限资源
     *
     * @return
     */
    List<AuthResourceResp> getAllSaasDefaultAuthResources();

    /**
     * 批量添加
     *
     * @param list
     */
    void saveResourceByBath(List<AuthResourceReq> list);

    /**
     * 获取平台端资源
     *
     * @param type          平台类型
     * @param defaultStatus 是否是默认 0否 1是
     */
    List<AuthResourceResp> getPlatformAuthResources(String type, Integer defaultStatus);

}
