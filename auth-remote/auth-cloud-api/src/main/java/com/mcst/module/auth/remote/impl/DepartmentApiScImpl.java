package com.mcst.module.auth.remote.impl;

import com.mcst.easyfk.remote.sc.BaseApiRemoteImpl;
import com.mcst.module.auth.api.IDepartmentApi;
import com.mcst.module.auth.api.request.DepartmentReq;
import com.mcst.module.auth.api.response.DepartmentResp;
import com.mcst.module.auth.remote.IDepartmentRemote;
import jakarta.annotation.Resource;


/**
 * <p>
 * 部门 Api接口SC层实现
 * </p>
 *
 * @author liuyijun
 */
public class DepartmentApiScImpl extends BaseApiRemoteImpl<IDepartmentRemote, DepartmentResp, String, DepartmentReq> implements IDepartmentApi {
    @Resource
    private IDepartmentRemote departmentRemote;

}
