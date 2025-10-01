package com.mcst.module.auth.server.impl;

import com.mcst.easyfk.authority.IAuthResourceApi;
import com.mcst.easyfk.authority.constant.ResourceSecurityLevel;
import com.mcst.easyfk.authority.dto.AuthResourceDto;
import com.mcst.easyfk.authority.request.AuthResourceReq;
import com.mcst.easyfk.authority.response.AuthResourceResp;
import com.mcst.easyfk.core.dto.response.ResponseResult;
import com.mcst.easyfk.service.base.BaseApiServiceImpl;
import com.mcst.module.auth.server.service.IAuthResourceService;
import jakarta.annotation.Resource;


import java.util.List;

/**
 * @author liu yijun
 */
public class AuthResourceApiServerImpl extends BaseApiServiceImpl<IAuthResourceService, AuthResourceResp, String, AuthResourceReq> implements IAuthResourceApi {

    @Resource
    private IAuthResourceService authResourceService;

    @Override
    public ResponseResult<List<AuthResourceResp>> authorityResourceList() {
        return this.authResourceService.authorityResourceList();
    }

    @Override
    public ResourceSecurityLevel checkUriSecurityLevel(String url) {
        return this.authResourceService.checkUriSecurityLevel(url);
    }

    @Override
    public void saveResourceByBath(List<AuthResourceReq> list) {
        this.authResourceService.saveResourceByBath(list);
    }

    @Override
    public List<AuthResourceResp> getPlatformAuthResources(String type, Integer defaultStatus) {
        return this.authResourceService.getPlatformAuthResources(type, defaultStatus);
    }
}
