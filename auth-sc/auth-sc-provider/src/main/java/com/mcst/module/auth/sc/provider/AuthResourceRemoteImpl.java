package com.mcst.module.auth.sc.provider;

import com.mcst.easyfk.authority.constant.ResourceSecurityLevel;
import com.mcst.easyfk.authority.request.AuthResourceReq;
import com.mcst.easyfk.authority.response.AuthResourceResp;
import com.mcst.easyfk.core.dto.response.ResponseResult;
import com.mcst.easyfk.remote.sc.BaseRemoteImpl;
import com.mcst.module.auth.server.service.IAuthResourceService;
import com.mcst.module.resource.sc.client.api.remote.IAuthResourceRemote;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author liuyijun
 */
@ResponseBody
@RequestMapping("${easyfk.config.remote.resource.base-path:/remote/auth/resource}")
public class AuthResourceRemoteImpl extends BaseRemoteImpl<IAuthResourceService, AuthResourceResp, String, AuthResourceReq> implements IAuthResourceRemote {

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
