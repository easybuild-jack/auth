package com.mcst.module.resource.sc.client.impl;

import com.mcst.easyfk.authority.IAuthResourceApi;
import com.mcst.easyfk.authority.constant.ResourceSecurityLevel;
import com.mcst.easyfk.authority.request.AuthResourceReq;
import com.mcst.easyfk.authority.response.AuthResourceResp;
import com.mcst.easyfk.core.dto.response.ResponseResult;
import com.mcst.easyfk.remote.sc.BaseApiRemoteImpl;
import com.mcst.module.resource.sc.client.api.remote.IAuthResourceRemote;
import jakarta.annotation.Resource;

import java.util.List;

/**
 * @author liuyi
 */
public class AuthResourceApiScImpl extends BaseApiRemoteImpl<IAuthResourceRemote, AuthResourceResp, String, AuthResourceReq> implements IAuthResourceApi {

    @Resource(type = IAuthResourceRemote.class)
    private IAuthResourceRemote authResourceScRemote;

    @Override
    public ResponseResult<List<AuthResourceResp>> authorityResourceList() {
        return this.authResourceScRemote.authorityResourceList();
    }

    @Override
    public ResourceSecurityLevel checkUriSecurityLevel(String url) {
        return this.authResourceScRemote.checkUriSecurityLevel(url);
    }

    @Override
    public void saveResourceByBath(List<AuthResourceReq> list) {
        this.authResourceScRemote.saveResourceByBath(list);
    }

    @Override
    public List<AuthResourceResp> getPlatformAuthResources(String type, Integer defaultStatus) {
        return this.authResourceScRemote.getPlatformAuthResources(type, defaultStatus);
    }
}

