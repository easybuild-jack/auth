package com.mcst.module.auth.remote.provider.impl;

import com.mcst.easyfk.remote.dubbo.DubboBaseRemoteImpl;
import com.mcst.module.auth.api.request.DepartmentReq;
import com.mcst.module.auth.api.response.DepartmentResp;
import com.mcst.module.auth.remote.IDepartmentRemote;
import com.mcst.module.auth.server.service.IDepartmentService;

/**
 * <p>
 * 部门 RemoteImpl
 * </p>
 *
 * @author liuyijun
 */
public class DepartmentRemoteImpl extends DubboBaseRemoteImpl<IDepartmentService, DepartmentResp, String, DepartmentReq> implements IDepartmentRemote {

}
