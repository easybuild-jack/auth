package com.mcst.module.auth.sc.provider;

import com.mcst.easyfk.remote.sc.BaseRemoteImpl;
import com.mcst.module.auth.api.request.DepartmentReq;
import com.mcst.module.auth.api.response.DepartmentResp;
import com.mcst.module.auth.sc.client.api.remote.IDepartmentScRemote;
import com.mcst.module.auth.server.service.IDepartmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 部门 SCRemoteImpl
 * </p>
 *
 * @author liuyijun
 */
@ResponseBody
@RequestMapping("${easyfk.config.remote.auth.base-path:/remote}/auth/department")
public class DepartmentScRemoteImpl extends BaseRemoteImpl<IDepartmentService, DepartmentResp, String, DepartmentReq> implements IDepartmentScRemote {

}
