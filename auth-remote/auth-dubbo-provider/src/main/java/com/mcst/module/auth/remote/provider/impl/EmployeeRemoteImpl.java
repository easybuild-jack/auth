package com.mcst.module.auth.remote.provider.impl;

import com.mcst.easyfk.authority.vo.UserAuthResources;
import com.mcst.easyfk.core.dto.login.LoginResult;
import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.remote.dubbo.DubboBaseRemoteImpl;
import com.mcst.module.auth.api.request.EmployeeReq;
import com.mcst.module.auth.api.response.EmployeeResp;
import com.mcst.module.auth.api.vo.BmsLoginRequest;
import com.mcst.module.auth.api.vo.ChangePwdVO;
import com.mcst.module.auth.api.vo.RestLoginPwdVO;
import com.mcst.module.auth.remote.IEmployeeRemote;
import com.mcst.module.auth.server.service.IEmployeeService;
import jakarta.annotation.Resource;

import java.util.List;

/**
 * <p>
 * 员工 RemoteImpl
 * </p>
 *
 * @author liuyijun
 */
public class EmployeeRemoteImpl extends DubboBaseRemoteImpl<IEmployeeService, EmployeeResp, String, EmployeeReq> implements IEmployeeRemote {

    @Resource
    private IEmployeeService employeeService;

    @Override
    public LoginResult login(BmsLoginRequest loginRequest) {
        return this.employeeService.login(loginRequest);
    }

    @Override
    public boolean checkLoginName(String loginName) {
        return this.employeeService.checkLoginName(loginName);
    }

    @Override
    public BaseResult<?> updateEmployeePwd(ChangePwdVO changePwdVO) {
        return this.employeeService.updateEmployeePwd(changePwdVO);
    }

    @Override
    public BaseResult<?> resetPwd(String id) {
        return this.employeeService.resetPwd(id);
    }

    @Override
    public List<UserAuthResources> queryEmployeeResource(String cacheKey) {
        return this.employeeService.queryEmployeeResource(cacheKey);
    }

    @Override
    public BaseResult<?> loginOut() {
        return this.employeeService.loginOut();
    }

    @Override
    public LoginResult dingTalkLogin(String mobile, String userCode) {
        return this.employeeService.dingTalkLogin(mobile, userCode);
    }

    @Override
    public LoginResult loginById(String employeeId) {
        return this.employeeService.loginById(employeeId);
    }

    @Override
    public List<EmployeeResp> getEmpByResourceId(String resourceId) {
        return this.employeeService.getEmpByResourceId(resourceId);
    }

    @Override
    public BaseResult<?> restLoginPwd(RestLoginPwdVO restLoginPwdVO) {
        return this.employeeService.restLoginPwd(restLoginPwdVO);
    }
}
