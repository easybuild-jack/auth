package com.mcst.module.auth.remote;

import com.mcst.easyfk.authority.vo.UserAuthResources;
import com.mcst.easyfk.core.dto.login.LoginResult;
import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.remote.dubbo.IDubboBaseRemote;
import com.mcst.module.auth.api.request.EmployeeReq;
import com.mcst.module.auth.api.response.EmployeeResp;
import com.mcst.module.auth.api.vo.BmsLoginRequest;
import com.mcst.module.auth.api.vo.ChangePwdVO;
import com.mcst.module.auth.api.vo.RestLoginPwdVO;

import java.util.List;

/**
 * <p>
 * 员工 Remote接口类
 * </p>
 *
 * @author liuyijun
 */
public interface IEmployeeRemote extends IDubboBaseRemote<EmployeeResp, String, EmployeeReq> {

    LoginResult login(BmsLoginRequest loginRequest);

    boolean checkLoginName(String loginName);

    /**
     * 修改密码
     */
    BaseResult<?> updateEmployeePwd(ChangePwdVO changePwdVO);

    /**
     * 初始化员工密码
     */
    BaseResult<?> resetPwd(String id);

    /**
     * 获取员工所有资源
     */
    List<UserAuthResources> queryEmployeeResource(String cacheKey);

    /**
     * 退出登录
     */
    BaseResult<?> loginOut();

    /**
     * 钉钉登录
     */
    LoginResult dingTalkLogin(String mobile, String userCode);

    /**
     * 根据ID登录
     */
    LoginResult loginById(String employeeId);

    /**
     * 根据资源ID 查找用户
     */
    List<EmployeeResp> getEmpByResourceId(String resourceId);

    /**
     * 定期修改登录密码
     */
    BaseResult<?> restLoginPwd(RestLoginPwdVO restLoginPwdVO);
}
