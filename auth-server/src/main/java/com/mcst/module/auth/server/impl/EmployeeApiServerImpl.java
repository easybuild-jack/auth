package com.mcst.module.auth.server.impl;

import com.mcst.easyfk.authority.vo.UserAuthResources;
import com.mcst.easyfk.core.dto.login.LoginResult;
import com.mcst.easyfk.core.dto.login.UserData;
import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.service.base.BaseApiServiceImpl;
import com.mcst.module.auth.api.IEmployeeApi;
import com.mcst.module.auth.api.request.EmployeeReq;
import com.mcst.module.auth.api.response.EmployeeResp;
import com.mcst.module.auth.api.vo.BmsLoginRequest;
import com.mcst.module.auth.api.vo.ChangePwdVO;
import com.mcst.module.auth.api.vo.RestLoginPwdVO;
import com.mcst.module.auth.server.service.IEmployeeService;
import jakarta.annotation.Resource;

import java.util.List;

/**
 * <p>
 * 员工 Api接口Server层实现
 * </p>
 *
 * @author liuyijun
 */
public class EmployeeApiServerImpl extends BaseApiServiceImpl<IEmployeeService, EmployeeResp, String, EmployeeReq> implements IEmployeeApi {
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
    public List<UserAuthResources> queryEmployeeResource(String loginToke) {
        return this.employeeService.queryEmployeeResource(loginToke);
    }

    @Override
    public BaseResult<?> loginOut(UserData userData) {
        return this.employeeService.loginOut(userData);
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
