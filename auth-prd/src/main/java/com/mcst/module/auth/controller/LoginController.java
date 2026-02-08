package com.mcst.module.auth.controller;

import com.mcst.easyfk.authority.annotation.LoginResource;
import com.mcst.easyfk.core.builders.RRBuilder;
import com.mcst.easyfk.core.context.UserDataContext;
import com.mcst.easyfk.core.dto.login.LoginResult;
import com.mcst.easyfk.core.dto.login.LoginUser;
import com.mcst.easyfk.core.dto.login.UserData;
import com.mcst.easyfk.core.dto.response.ResponseResult;
import com.mcst.easyfk.core.utils.common.EmptyUtil;
import com.mcst.easyfk.web.base.manager.JwtManager;
import com.mcst.easyfk.web.prd.vo.LoginRequestVO;
import com.mcst.module.auth.api.IEmployeeApi;
import com.mcst.module.auth.api.vo.BmsLoginRequest;
import com.mcst.module.auth.api.vo.BmsLoginSuccessVO;
import com.mcst.module.auth.api.vo.ChangePwdVO;
import com.mcst.module.auth.api.vo.RestLoginPwdVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author liuyijun
 */
@Tag(name = "后台登录相关接口")
@ResponseBody
@RequestMapping("/api/auth")
public class LoginController {

    @Resource
    private IEmployeeApi employeeApi;

    @Resource
    @Lazy
    private JwtManager jwtManager;

    /**
     * 登录接口
     */
    @Operation(summary = "登录接口")
    @PostMapping("/login")
    public ResponseResult<?> doLogin(@RequestBody @Validated LoginRequestVO loginVO) {
        BmsLoginRequest loginRequest = new BmsLoginRequest().setLoginName(loginVO.getLoginAccount()).setPassword(loginVO.getPassword());
        LoginResult loginResult = this.employeeApi.login(loginRequest);
        if (!loginResult.getSuccess()) {
            if ("密码已过期".equals(loginResult.getMsg())) {
                return new ResponseResult<>().setCode("RestPwd");
            }
            return RRBuilder.buildFailedBody(loginResult.getMsg());
        }
        LoginUser loginUser = loginResult.getLoginUser();
        BmsLoginSuccessVO successVO = new BmsLoginSuccessVO();
        String jwtToken = this.jwtManager.createJwtByObject(loginUser);
        successVO.setAccessToken(jwtToken).setHeadImage(loginResult.getHeadImage());
        return RRBuilder.buildSuccessBody(successVO);
    }

    @Operation(summary = "退出接口")
    @GetMapping("/logout")
    public ResponseResult<?> logout() {
        UserData loginUser = UserDataContext.getUserData();
        if (EmptyUtil.isNotEmpty(loginUser)) {
            this.employeeApi.loginOut();
        }
        return RRBuilder.buildSuccessBody();
    }

    @LoginResource
    @Operation(summary = "修改登录密码接口")
    @PostMapping("/editPwd")
    public ResponseResult<?> editPwd(@RequestBody @Validated ChangePwdVO changePwdVO) {
        UserData loginUser = UserDataContext.getUserData();
        changePwdVO.setEmployeeId(loginUser.getUserId());
        return RRBuilder.buildBodyByBaseResult(this.employeeApi.updateEmployeePwd(changePwdVO));
    }

    @Operation(summary = "定期修改登录密码")
    @PostMapping("/restLoginPwd")
    public ResponseResult<?> restLoginPwd(@RequestBody @Validated RestLoginPwdVO restLoginPwdVO) {
        return RRBuilder.buildBodyByBaseResult(this.employeeApi.restLoginPwd(restLoginPwdVO));
    }
}
