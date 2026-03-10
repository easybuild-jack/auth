package com.mcst.module.auth.remote;

import com.mcst.easyfk.remote.dubbo.IDubboBaseRemote;
import com.mcst.module.auth.api.request.DepartmentReq;
import com.mcst.module.auth.api.response.DepartmentResp;

/**
 * <p>
 * 部门 Remote接口类
 * </p>
 *
 * @author liuyijun
 */
public interface IDepartmentRemote extends IDubboBaseRemote<DepartmentResp, String, DepartmentReq> {

}
