package com.mcst.module.auth.sc.client.impl;

import com.mcst.easyfk.authority.vo.UserAuthResources;
import com.mcst.easyfk.core.dto.login.LoginResult;
import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.remote.sc.BaseApiRemoteImpl;
import com.mcst.module.auth.api.IEmployeeApi;
import com.mcst.module.auth.api.request.EmployeeReq;
import com.mcst.module.auth.api.response.EmployeeResp;
import com.mcst.module.auth.api.vo.BmsLoginRequest;
import com.mcst.module.auth.api.vo.ChangePwdVO;
import com.mcst.module.auth.api.vo.RestLoginPwdVO;
import com.mcst.module.auth.sc.client.api.remote.IEmployeeScRemote;
import jakarta.annotation.Resource;

import java.util.List;

/**
 * <p>
 * 员工 Api接口SC层实现
 * </p>
 *
 * @author liuyijun
 */
public class EmployeeApiScImpl extends BaseApiRemoteImpl<IEmployeeScRemote, EmployeeResp, String, EmployeeReq> implements IEmployeeApi {
    @Resource
    private IEmployeeScRemote employeeScRemote;

    @Override
    public LoginResult login(BmsLoginRequest loginRequest) {
        return this.employeeScRemote.login(loginRequest);
    }

    @Override
    public boolean checkLoginName(String loginName) {
        return this.employeeScRemote.checkLoginName(loginName);
    }

    @Override
    public BaseResult<?> updateEmployeePwd(ChangePwdVO changePwdVO) {
        return this.employeeScRemote.updateEmployeePwd(changePwdVO);
    }

    @Override
    public BaseResult<?> resetPwd(String id) {
        return this.employeeScRemote.resetPwd(id);
    }

    @Override
    public List<UserAuthResources> queryEmployeeResource(String cacheKey) {
        return this.employeeScRemote.queryEmployeeResource(cacheKey);
    }

    @Override
    public BaseResult<?> loginOut() {
        return this.employeeScRemote.loginOut();
    }

    @Override
    public LoginResult dingTalkLogin(String mobile, String userCode) {
        return this.employeeScRemote.dingTalkLogin(mobile, userCode);
    }

    @Override
    public LoginResult loginById(String employeeId) {
        return this.employeeScRemote.loginById(employeeId);
    }

    @Override
    public List<EmployeeResp> getEmpByResourceId(String resourceId) {
        return this.employeeScRemote.getEmpByResourceId(resourceId);
    }

    @Override
    public BaseResult<?> restLoginPwd(RestLoginPwdVO restLoginPwdVO) {
        return this.employeeScRemote.restLoginPwd(restLoginPwdVO);
    }
}
