package com.mcst.module.auth.controller;

import com.mcst.easyfk.authority.annotation.AuthResource;
import com.mcst.easyfk.authority.annotation.ResourceController;
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
import com.mcst.module.auth.api.IDepartmentApi;
import com.mcst.module.auth.api.request.DepartmentEditReq;
import com.mcst.module.auth.api.request.DepartmentReq;
import com.mcst.module.auth.api.response.DepartmentResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuyijun
 */
@Tag(name = "部门相关接口", description = "部门相关接口")
@ResponseBody
@RequestMapping("/api/auth/department")
@ResourceController(group = "authManage", id = "authManage", name = "权限管理", sort = 99000, path = "/auth")
public class DepartmentController {
    @Resource
    private IDepartmentApi departmentApi;

    @Operation(summary = "详情")
    @GetMapping("/detail")
    public ResponseResult<DepartmentResp> detail(String id) {
        return RRBuilder.buildSuccessBody(this.departmentApi.queryById(id));
    }

    @Operation(summary = "分页查询")
    @GetMapping("/queryPage")
    @AuthResource(id = "departmentManage", name = "部门管理", pId = "authManage", pName = "权限管理", sort = 99100, path = "/auth/department", category = ResourceCategory.menu)
    public ResponseResult<List<DepartmentResp>> queryPage(@Validated DepartmentReq departmentParam) {
        SRPBuilder<DepartmentReq> requestParamBuilder = SRPBuilder.<DepartmentReq>builder().example(departmentParam);
        if (EmptyUtil.isNotEmpty(departmentParam.getDepartmentName())) {
            requestParamBuilder.likeFields(DepartmentReq::getDepartmentName);
        }
        return RRBuilder.buildBodyByPageResult(this.departmentApi.queryByPage(requestParamBuilder.build()));
    }

    @Operation(summary = "新增/编辑")
    @PostMapping("/addOrEdit")
    @AuthResource(id = "departmentAddOrEdit", name = "新增/编辑", title = "新增/编辑", pId = "departmentManage", pName = "部门管理", sort = 99101, category = ResourceCategory.action, actionCode = "addOrEdit")
    public ResponseResult<?> addOrEdit(@RequestBody DepartmentEditReq departmentDTO) {
        ModifyRequest<DepartmentReq> requestParam = MRPBuilder.buildRequest(departmentDTO, DepartmentReq.class);
        return RRBuilder.buildBodyByBaseResult(this.departmentApi.save(requestParam));
    }

    @Operation(summary = "批量删除", description = "批量删除")
    @PostMapping("/delete")
    @AuthResource(id = "departmentDelete", name = "删除部门", title = "删除", pId = "departmentManage", pName = "部门管理", sort = 99102, category = ResourceCategory.action, actionCode = "delete")
    public ResponseResult<?> delete(@Validated @RequestBody BatchBasicReq<String> BatchBasicReq) {
        List<String> idArray = SplitUtil.split(BatchBasicReq.getIds());
        ModifyRequest<DepartmentReq> requestParam = MRPBuilder.<DepartmentReq>builder().ids(idArray).build();
        return RRBuilder.buildBodyByBaseResult(this.departmentApi.delete(requestParam));
    }

    @Operation(summary = "启用/禁用")
    @PostMapping("/disable")
    @AuthResource(id = "departmentDisable", name = "启用/禁用部门", title = "启用/禁用", pId = "departmentManage", pName = "部门管理", sort = 99102, category = ResourceCategory.action, actionCode = "disable")
    public ResponseResult<?> disable(@RequestBody @Validated DisableFieldReq disableFieldVO) {
        DisableActionParam<DepartmentReq> disableActionParam = new DisableActionParam<DepartmentReq>(disableFieldVO).setIdField(DepartmentReq::getDepartmentId).setValueField(DepartmentReq::getForbiddenFlag).setTClass(DepartmentReq.class);
        List<DepartmentReq> list = DisableUtil.createObjListByDisableAction(disableActionParam);
        if (EmptyUtil.isEmpty(list)) {
            return RRBuilder.buildFailedBody("参数错误");
        }
        ModifyRequest<DepartmentReq> requestParam = MRPBuilder.<DepartmentReq>builder().paramList(list).build();
        return RRBuilder.buildBodyByBaseResult(this.departmentApi.save(requestParam));
    }

    @Operation(summary = "部门下拉列表数据接口")
    @GetMapping("/selectList")
    public ResponseResult<List<DepartmentResp>> departmentSelectList(@RequestParam(value = "name", required = false) String name) {
        SRPBuilder<DepartmentReq> requestParamBuilder = SRPBuilder.<DepartmentReq>builder().selectFields(DepartmentReq::getDepartmentId, DepartmentReq::getDepartmentName, DepartmentReq::getForbiddenFlag);
        if (EmptyUtil.isNotEmpty(name)) {
            requestParamBuilder.likeFields(DepartmentReq::getDepartmentName);
            DepartmentReq ex = new DepartmentReq();
            ex.setSearchKeyWord(name);
            ex.setTop(20);
            requestParamBuilder.example(ex);
        }
        return RRBuilder.buildSuccessBody(this.departmentApi.queryList(requestParamBuilder.build()));
    }

}
