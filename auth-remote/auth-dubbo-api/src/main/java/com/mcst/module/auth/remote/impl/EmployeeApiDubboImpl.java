package com.mcst.module.auth.remote.impl;

import com.mcst.easyfk.authority.vo.UserAuthResources;
import com.mcst.easyfk.core.dto.login.LoginResult;
import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.remote.dubbo.DubboBaseApiRemoteImpl;
import com.mcst.module.auth.api.IEmployeeApi;
import com.mcst.module.auth.api.request.EmployeeReq;
import com.mcst.module.auth.api.response.EmployeeResp;
import com.mcst.module.auth.api.vo.BmsLoginRequest;
import com.mcst.module.auth.api.vo.ChangePwdVO;
import com.mcst.module.auth.api.vo.RestLoginPwdVO;
import com.mcst.module.auth.remote.IEmployeeRemote;
import jakarta.annotation.Resource;

import java.util.List;

/**
 * <p>
 * 员工 Api接口Dubbo层实现
 * </p>
 *
 * @author liuyijun
 */
public class EmployeeApiDubboImpl extends DubboBaseApiRemoteImpl<IEmployeeRemote, EmployeeResp, String, EmployeeReq> implements IEmployeeApi {
    @Resource
    private IEmployeeRemote employeeRemote;

    @Override
    public LoginResult login(BmsLoginRequest loginRequest) {
        return this.employeeRemote.login(loginRequest);
    }

    @Override
    public boolean checkLoginName(String loginName) {
        return this.employeeRemote.checkLoginName(loginName);
    }

    @Override
    public BaseResult<?> updateEmployeePwd(ChangePwdVO changePwdVO) {
        return this.employeeRemote.updateEmployeePwd(changePwdVO);
    }

    @Override
    public BaseResult<?> resetPwd(String id) {
        return this.employeeRemote.resetPwd(id);
    }

    @Override
    public List<UserAuthResources> queryEmployeeResource(String cacheKey) {
        return this.employeeRemote.queryEmployeeResource(cacheKey);
    }

    @Override
    public BaseResult<?> loginOut() {
        return this.employeeRemote.loginOut();
    }

    @Override
    public LoginResult dingTalkLogin(String mobile, String userCode) {
        return this.employeeRemote.dingTalkLogin(mobile, userCode);
    }

    @Override
    public LoginResult loginById(String employeeId) {
        return this.employeeRemote.loginById(employeeId);
    }

    @Override
    public List<EmployeeResp> getEmpByResourceId(String resourceId) {
        return this.employeeRemote.getEmpByResourceId(resourceId);
    }

    @Override
    public BaseResult<?> restLoginPwd(RestLoginPwdVO restLoginPwdVO) {
        return this.employeeRemote.restLoginPwd(restLoginPwdVO);
    }
}
