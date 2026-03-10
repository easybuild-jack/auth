package com.mcst.module.auth.remote.provider.impl;

import com.mcst.easyfk.authority.constant.ResourceSecurityLevel;
import com.mcst.easyfk.authority.request.AuthResourceReq;
import com.mcst.easyfk.authority.response.AuthResourceResp;
import com.mcst.easyfk.core.dto.response.ResponseResult;
import com.mcst.easyfk.remote.dubbo.DubboBaseRemoteImpl;
import com.mcst.module.auth.server.service.IAuthResourceService;
import com.mcst.module.auth.remote.IAuthResourceRemote;
import jakarta.annotation.Resource;

import java.util.List;

/**
 * @author liuyijun
 */
public class AuthResourceRemoteImpl extends DubboBaseRemoteImpl<IAuthResourceService, AuthResourceResp, String, AuthResourceReq> implements IAuthResourceRemote {

    @Resource(type = IAuthResourceService.class)
    private IAuthResourceService authResourceService;

    @Override
    public List<AuthResourceResp> getAllPlatformAuthResources() {
        return authResourceService.getAllPlatformAuthResources();
    }

    @Override
    public List<AuthResourceResp> getAllSaasAuthResources() {
        return authResourceService.getAllSaasAuthResources();
    }

    @Override
    public List<AuthResourceResp> getAllSaasDefaultAuthResources() {
        return authResourceService.getAllSaasDefaultAuthResources();
    }

    @Override
    public ResponseResult<List<AuthResourceResp>> authorityResourceList() {
        return authResourceService.authorityResourceList();
    }

    @Override
    public ResourceSecurityLevel checkUriSecurityLevel(String url) {
        return authResourceService.checkUriSecurityLevel(url);
    }

    @Override
    public void saveResourceByBath(List<AuthResourceReq> list) {
        authResourceService.saveResourceByBath(list);
    }

    @Override
    public List<AuthResourceResp> getPlatformAuthResources(String type, Integer defaultStatus) {
        return authResourceService.getPlatformAuthResources(type, defaultStatus);
    }
}
