package com.mcst.module.auth.sc.client.api.remote;

import com.mcst.easyfk.remote.sc.IBaseRemote;
import com.mcst.module.auth.api.request.DepartmentReq;
import com.mcst.module.auth.api.response.DepartmentResp;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 部门 ScRemote接口类
 * </p>
 *
 * @author liuyijun
 */
@FeignClient(value = "${easyfk.config.remote.auth.service-id:auth-server}", path = "${easyfk.config.remote.auth" + ".base-path:/remote}/auth/department")
public interface IDepartmentScRemote extends IBaseRemote<DepartmentResp, String, DepartmentReq> {

}
