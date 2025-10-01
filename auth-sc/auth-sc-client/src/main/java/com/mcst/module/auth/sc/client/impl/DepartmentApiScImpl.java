package com.mcst.module.auth.sc.client.impl;

import com.mcst.easyfk.remote.sc.BaseApiRemoteImpl;
import com.mcst.module.auth.api.IDepartmentApi;
import com.mcst.module.auth.api.request.DepartmentReq;
import com.mcst.module.auth.api.response.DepartmentResp;
import com.mcst.module.auth.sc.client.api.remote.IDepartmentScRemote;
import jakarta.annotation.Resource;


/**
 * <p>
 * 部门 Api接口SC层实现
 * </p>
 *
 * @author liuyijun
 */
public class DepartmentApiScImpl extends BaseApiRemoteImpl<IDepartmentScRemote, DepartmentResp, String, DepartmentReq> implements IDepartmentApi {
    @Resource
    private IDepartmentScRemote departmentScRemote;

}