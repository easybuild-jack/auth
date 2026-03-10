package com.mcst.module.auth.remote.impl;

import com.mcst.easyfk.authority.IAuthResourceApi;
import com.mcst.easyfk.authority.constant.ResourceSecurityLevel;
import com.mcst.easyfk.authority.request.AuthResourceReq;
import com.mcst.easyfk.authority.response.AuthResourceResp;
import com.mcst.easyfk.core.dto.response.ResponseResult;
import com.mcst.easyfk.remote.dubbo.DubboBaseApiRemoteImpl;
import com.mcst.module.auth.remote.IAuthResourceRemote;
import jakarta.annotation.Resource;

import java.util.List;

/**
 * @author liuyijun
 */
public class AuthResourceApiDubboImpl extends DubboBaseApiRemoteImpl<IAuthResourceRemote, AuthResourceResp, String, AuthResourceReq> implements IAuthResourceApi {

    @Resource(type = IAuthResourceRemote.class)
    private IAuthResourceRemote authResourceRemote;

    @Override
    public ResponseResult<List<AuthResourceResp>> authorityResourceList() {
        return this.authResourceRemote.authorityResourceList();
    }

    @Override
    public ResourceSecurityLevel checkUriSecurityLevel(String url) {
        return this.authResourceRemote.checkUriSecurityLevel(url);
    }

    @Override
    public void saveResourceByBath(List<AuthResourceReq> list) {
        this.authResourceRemote.saveResourceByBath(list);
    }

    @Override
    public List<AuthResourceResp> getPlatformAuthResources(String type, Integer defaultStatus) {
        return this.authResourceRemote.getPlatformAuthResources(type, defaultStatus);
    }
}
