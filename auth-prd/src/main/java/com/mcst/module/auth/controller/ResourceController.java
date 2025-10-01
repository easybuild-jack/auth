package com.mcst.module.auth.controller;

import com.mcst.easyfk.authority.annotation.LoginResource;
import com.mcst.easyfk.authority.vo.UserAuthResources;
import com.mcst.easyfk.core.builders.RRBuilder;
import com.mcst.easyfk.core.context.UserDataContext;
import com.mcst.easyfk.core.dto.request.UserData;
import com.mcst.easyfk.core.dto.response.ResponseResult;
import com.mcst.module.auth.api.IEmployeeApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liuyijun
 */
@Tag(name = "资源管理接口")
@RestController
@RequestMapping("/api/auth/resource")
public class ResourceController {

    @Resource
    private IEmployeeApi employeeApi;

    @Operation(summary = "获取用户所有权限资源数据接口")
    @GetMapping("/employeeAuthResources")
    @LoginResource
    public ResponseResult<List<UserAuthResources>> getUserAuthResources() {
        UserData loginUser = UserDataContext.getUserData();
        List<UserAuthResources> list = this.employeeApi.queryEmployeeResource(loginUser.getCachedKey());
        return RRBuilder.buildSuccessBody(list);
    }

}
