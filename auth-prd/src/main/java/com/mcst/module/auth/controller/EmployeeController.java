package com.mcst.module.auth.controller;

import com.mcst.easyfk.authority.annotation.AuthResource;
import com.mcst.easyfk.authority.enums.ResourceCategory;
import com.mcst.easyfk.core.builders.MRPBuilder;
import com.mcst.easyfk.core.builders.RRBuilder;
import com.mcst.easyfk.core.builders.SRPBuilder;
import com.mcst.easyfk.core.dto.request.BatchBasicReq;
import com.mcst.easyfk.core.dto.request.DisableActionParam;
import com.mcst.easyfk.core.dto.request.DisableFieldReq;
import com.mcst.easyfk.core.dto.request.ModifyRequest;
import com.mcst.easyfk.core.dto.response.ResponseResult;
import com.mcst.easyfk.core.utils.common.DisableUtil;
import com.mcst.easyfk.core.utils.common.EmptyUtil;
import com.mcst.easyfk.core.utils.common.SplitUtil;
import com.mcst.module.auth.api.IEmployeeApi;
import com.mcst.module.auth.api.request.EmployeeEditReq;
import com.mcst.module.auth.api.request.EmployeeReq;
import com.mcst.module.auth.api.response.EmployeeResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.mcst.easyfk.authority.annotation.ResourceController;

import java.util.List;

/**
 * @author liuyijun
 */
@Tag(name = "员工相关接口", description = "员工相关接口")
@RestController
@RequestMapping("/api/auth/employee")
@ResourceController(group = "systemSetting", id = "systemSetting", name = "系统设置", sort = 99000, path = "/system")
public class EmployeeController {
    @Resource
    private IEmployeeApi employeeApi;

    /**
     * 根据Id值查询
     */
    @Operation(summary = "详情")
    @GetMapping("/detail")
    public ResponseResult<EmployeeResp> detail(String id) {
        return RRBuilder.buildSuccessBody(this.employeeApi.queryById(id));
    }

    @Operation(summary = "分页查询")
    @GetMapping("/queryPage")
    @AuthResource(id = "employeeManage", name = "员工管理", pId = "systemSetting", pName = "系统设置", sort = 99300, path = "/system/employee", category = ResourceCategory.menu)
    public ResponseResult<List<EmployeeResp>> queryPage(@Validated EmployeeReq employeeParam) {
        SRPBuilder<EmployeeReq> builder = SRPBuilder.<EmployeeReq>builder().example(employeeParam);
        if (EmptyUtil.isNotEmpty(employeeParam.getSearchKeyWord())) {
            builder.likeFields(EmployeeReq::getEmployeeName, EmployeeReq::getMobile, EmployeeReq::getLoginName);
        }
        if (EmptyUtil.isNotEmpty(employeeParam.getRoles())) {
            builder.likeFields(EmployeeReq::getRoles);
        }
        return RRBuilder.buildBodyByPageResult(this.employeeApi.queryByPage(builder.build()));
    }

    @Operation(summary = "新增/编辑")
    @PostMapping("/addOrEdit")
    @AuthResource(id = "employeeAddOrEdit", name = "新增/编辑员工", title = "新增/编辑", pId = "employeeManage", pName = "员工管理", sort = 99301,
        category = ResourceCategory.action, actionCode = "addOrEdit")
    public ResponseResult<?> addOrEdit(@RequestBody EmployeeEditReq employeeDTO) {
        ModifyRequest<EmployeeReq> requestParam = MRPBuilder.buildRequest(employeeDTO, EmployeeReq.class);
        return RRBuilder.buildBodyByBaseResult(this.employeeApi.save(requestParam));
    }

    @Operation(summary = "删除", description = "根据Id值批量删除")
    @PostMapping("/delete")
    @AuthResource(id = "employeeDelete", name = "删除员工", title = "删除", pId = "employeeManage", pName = "员工管理", sort = 99302, category = ResourceCategory.action,
        actionCode = "delete")
    public ResponseResult<?> delete(@Validated @RequestBody BatchBasicReq<String> batchBasicReq) {
        List<String> idArray = SplitUtil.split(batchBasicReq.getIds());
        ModifyRequest<EmployeeReq> requestParam = MRPBuilder.<EmployeeReq>builder().ids(idArray).build();
        return RRBuilder.buildBodyByBaseResult(this.employeeApi.delete(requestParam));
    }

    @Operation(summary = "启用/禁用")
    @PostMapping("/disable")
    @AuthResource(id = "employeeDisable", name = "启用/禁用员工", title = "启用/禁用", pId = "employeeManage", pName = "员工管理", sort = 99303, category = ResourceCategory.action,
        actionCode = "disable")
    public ResponseResult<?> disable(@RequestBody @Validated DisableFieldReq disableFieldVO) {
        DisableActionParam<EmployeeReq> disableActionParam =
            new DisableActionParam<EmployeeReq>(disableFieldVO).setIdField(EmployeeReq::getEmployeeId).setValueField(EmployeeReq::getForbiddenFlag).setTClass(EmployeeReq.class);
        List<EmployeeReq> list = DisableUtil.createObjListByDisableAction(disableActionParam);
        if (EmptyUtil.isEmpty(list)) {
            return RRBuilder.buildFailedBody("参数错误");
        }
        ModifyRequest<EmployeeReq> requestParam = MRPBuilder.<EmployeeReq>builder().paramList(list).build();
        return RRBuilder.buildBodyByBaseResult(this.employeeApi.save(requestParam));
    }

}
