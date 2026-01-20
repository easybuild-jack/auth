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
import com.mcst.module.auth.api.IRoleApi;
import com.mcst.module.auth.api.request.RoleEditReq;
import com.mcst.module.auth.api.request.RoleReq;
import com.mcst.module.auth.api.response.RoleResp;
import com.mcst.module.auth.api.vo.RoleGrantVO;
import com.mcst.module.auth.api.vo.RoleResourceVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuyijun
 */
@Tag(name = "角色相关接口")
@RestController
@RequestMapping("/api/auth/role")
@ResourceController(group = "authManage", id = "authManage", name = "权限管理", path = "/auth", sort = 99000)
public class RoleController {
    @Resource
    private IRoleApi roleApi;

    /**
     * 根据Id值查询
     */
    @Operation(summary = "详情")
    @GetMapping("/detail")
    public ResponseResult<RoleResp> detail(String id) {
        return RRBuilder.buildSuccessBody(this.roleApi.queryById(id));
    }

    @Operation(summary = "分页查询")
    @GetMapping("/queryPage")
    @AuthResource(id = "roleManage", name = "角色管理", pId = "authManage", pName = "权限管理", sort = 99200, path = "/auth/role", category = ResourceCategory.menu)
    public ResponseResult<List<RoleResp>> queryPage(@Validated RoleReq roleParam) {
        SRPBuilder<RoleReq> requestParamBuilder = SRPBuilder.<RoleReq>builder().example(roleParam);
        if (EmptyUtil.isNotEmpty(roleParam.getRoleName())) {
            requestParamBuilder.likeFields(RoleReq::getRoleName);
        }
        return RRBuilder.buildBodyByPageResult(this.roleApi.queryByPage(requestParamBuilder.build()));
    }

    @Operation(summary = "新增/编辑")
    @PostMapping("/addOrEdit")
    @AuthResource(id = "roleAddOrEdit", name = "新增/编辑角色", title = "新增/编辑", pId = "roleManage", pName = "角色管理", sort = 99201, category = ResourceCategory.action, actionCode = "addOrEdit")
    public ResponseResult<?> addOrEdit(@RequestBody RoleEditReq roleDTO) {
        ModifyRequest<RoleReq> requestParam = MRPBuilder.buildRequest(roleDTO, RoleReq.class);
        return RRBuilder.buildBodyByBaseResult(this.roleApi.save(requestParam));
    }

    @Operation(summary = "删除", description = "根据Id值批量删除")
    @PostMapping("/delete")
    @AuthResource(id = "roleDelete", name = "删除角色", title = "删除", pId = "roleManage", pName = "角色管理", sort = 99202, category = ResourceCategory.action, actionCode = "delete")
    public ResponseResult<?> delete(@Validated @RequestBody BatchBasicReq<String> batchBasicReq) {
        List<String> idArray = SplitUtil.split(batchBasicReq.getIds());
        ModifyRequest<RoleReq> requestParam = MRPBuilder.<RoleReq>builder().ids(idArray).build();
        return RRBuilder.buildBodyByBaseResult(this.roleApi.delete(requestParam));
    }

    @Operation(summary = "角色下拉列表数据接口")
    @GetMapping("/selectList")
    public ResponseResult<List<RoleResp>> roleSelectList(@RequestParam(value = "name", required = false) String name) {
        RoleReq ex = new RoleReq();
        ex.setSupperStatus(0);
        SRPBuilder<RoleReq> requestParamBuilder = SRPBuilder.<RoleReq>builder().example(ex).selectFields(RoleReq::getRoleId, RoleReq::getRoleName, RoleReq::getForbiddenFlag);
        if (EmptyUtil.isNotEmpty(name)) {
            requestParamBuilder.likeFields(RoleReq::getRoleName);
            ex.setSearchKeyWord(name);
        }
        return RRBuilder.buildSuccessBody(this.roleApi.queryList(requestParamBuilder.build()));
    }

    @Operation(summary = "启用/禁用")
    @PostMapping("/disable")
    @AuthResource(id = "roleDisable", name = "启用/禁用角色", title = "启用/禁用", pId = "roleManage", pName = "角色管理", sort = 99203, category = ResourceCategory.action, actionCode = "disable")
    public ResponseResult<?> disable(@RequestBody @Validated DisableFieldReq disableFieldVO) {
        DisableActionParam<RoleReq> disableActionParam = new DisableActionParam<RoleReq>(disableFieldVO).setIdField(RoleReq::getRoleId).setValueField(RoleReq::getForbiddenFlag).setTClass(RoleReq.class);
        List<RoleReq> list = DisableUtil.createObjListByDisableAction(disableActionParam);
        if (EmptyUtil.isEmpty(list)) {
            return RRBuilder.buildFailedBody("参数错误");
        }
        ModifyRequest<RoleReq> requestParam = MRPBuilder.<RoleReq>builder().paramList(list).build();
        return RRBuilder.buildBodyByBaseResult(this.roleApi.save(requestParam));
    }

    /**
     * 角色分配资源
     */
    @Operation(summary = "角色分配资源")
    @AuthResource(id = "roleGrantResource", actionCode = "grant", title = "分配资源", name = "角色分配资源", pId = "roleManage", pName = "角色管理", sort = 99204, category = ResourceCategory.action, remark = "角色分配资源")
    @PostMapping("/grant")
    public ResponseResult<?> grant(@RequestBody @Validated RoleGrantVO vo) {
        return RRBuilder.buildBodyByBaseResult(this.roleApi.grant(vo));
    }

    @Operation(summary = "角色资源数据接口")
    @GetMapping("/parentResourceByRoleId")
    public ResponseResult<List<RoleResourceVO>> getResourceByRoleId(@RequestParam("roleId") String roleId) {
        List<RoleResourceVO> tree = this.roleApi.getRoleResourceByRoleId(roleId);
        return RRBuilder.buildSuccessBody(tree);
    }

}
