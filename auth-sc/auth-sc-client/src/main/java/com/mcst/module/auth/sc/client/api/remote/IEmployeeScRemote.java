package com.mcst.module.auth.sc.client.api.remote;

import com.mcst.easyfk.authority.vo.UserAuthResources;
import com.mcst.easyfk.core.dto.login.LoginResult;
import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.remote.sc.IBaseRemote;
import com.mcst.module.auth.api.request.EmployeeReq;
import com.mcst.module.auth.api.response.EmployeeResp;
import com.mcst.module.auth.api.vo.BmsLoginRequest;
import com.mcst.module.auth.api.vo.ChangePwdVO;
import com.mcst.module.auth.api.vo.RestLoginPwdVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 员工 ScRemote接口类
 * </p>
 *
 * @author liuyijun
 */
@FeignClient(value = "${easyfk.config.remote.auth.service-id:auth-server}", path = "${easyfk.config.remote.auth" + ".base-path:/remote}/auth/employee")
public interface IEmployeeScRemote extends IBaseRemote<EmployeeResp, String, EmployeeReq> {

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    LoginResult login(@RequestBody BmsLoginRequest loginRequest);

    @GetMapping("/checkLoginName")
    boolean checkLoginName(@RequestParam("loginName") String loginName);

    /**
     * 修改密码
     *
     * @param changePwdVO
     * @return
     */
    @PostMapping(value = "/updateEmployeePwd", consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResult<?> updateEmployeePwd(@RequestBody ChangePwdVO changePwdVO);

    /**
     * 初始化员工密码
     *
     * @param id
     * @return
     */
    @GetMapping("/resetPwd")
    BaseResult<?> resetPwd(@RequestParam("id") String id);

    /**
     * 获取员工所有资源
     *
     * @param cacheKey
     * @return
     */
    @GetMapping("/queryEmployeeResource")
    List<UserAuthResources> queryEmployeeResource(@RequestParam("cacheKey") String cacheKey);

    /**
     * 退出登录
     *
     * @return
     */
    @PostMapping(value = "/loginOut", consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResult<?> loginOut();

    /**
     * 钉钉登录
     *
     * @param mobile
     * @param userCode
     * @return
     */
    @GetMapping("/dingTalkLogin")
    LoginResult dingTalkLogin(@RequestParam("mobile") String mobile, @RequestParam(value = "userCode", required = false) String userCode);

    /**
     * 根据ID登录
     *
     * @param employeeId
     * @return
     */
    @GetMapping("/loginById")
    LoginResult loginById(@RequestParam("employeeId") String employeeId);

    /**
     * 根据资源ID 查找用户
     *
     * @param resourceId
     * @return
     */
    @GetMapping("/getEmpByResourceId")
    List<EmployeeResp> getEmpByResourceId(@RequestParam("resourceId") String resourceId);

    /**
     * 定期修改登录密码
     *
     * @param restLoginPwdVO
     * @return
     */
    @PostMapping(value = "/restLoginPwd", consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResult<?> restLoginPwd(@RequestBody RestLoginPwdVO restLoginPwdVO);
}
