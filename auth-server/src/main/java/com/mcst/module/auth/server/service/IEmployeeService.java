package com.mcst.module.auth.server.service;

import com.mcst.easyfk.authority.vo.UserAuthResources;
import com.mcst.easyfk.core.dto.login.LoginResult;
import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.service.api.IBaseService;
import com.mcst.module.auth.api.request.EmployeeReq;
import com.mcst.module.auth.api.response.EmployeeResp;
import com.mcst.module.auth.api.vo.BmsLoginRequest;
import com.mcst.module.auth.api.vo.ChangePwdVO;
import com.mcst.module.auth.api.vo.RestLoginPwdVO;

import java.util.List;

/**
 * <p>
 * 员工 服务接口类
 * </p>
 *
 * @author liuyijun
 */
public interface IEmployeeService extends IBaseService<EmployeeResp, String, EmployeeReq> {

    LoginResult login(BmsLoginRequest loginRequest);

    boolean checkLoginName(String loginName);

    /**
     * 修改密码
     *
     * @param changePwdVO
     * @return
     */
    BaseResult<?> updateEmployeePwd(ChangePwdVO changePwdVO);

    /**
     * 初始化员工密码
     *
     * @param id
     * @return
     */
    BaseResult<?> resetPwd(String id);

    /**
     * 获取员工所有资源
     *
     * @param cacheKey
     * @return
     */
    List<UserAuthResources> queryEmployeeResource(String cacheKey);

    /**
     * 退出登录
     *
     * @return
     */
    BaseResult<?> loginOut();

    /**
     * 钉钉登录
     *
     * @param mobile
     * @param userCode
     * @return
     */
    LoginResult dingTalkLogin(String mobile, String userCode);

    /**
     * 根据ID登录
     *
     * @param employeeId
     * @return
     */
    LoginResult loginById(String employeeId);

    /**
     * 根据资源ID 查找用户
     *
     * @param resourceId
     * @return
     */
    List<EmployeeResp> getEmpByResourceId(String resourceId);

    /**
     * 定期修改登录密码
     *
     * @param restLoginPwdVO
     * @return
     */
    BaseResult<?> restLoginPwd(RestLoginPwdVO restLoginPwdVO);
}
