package com.mcst.module.auth.server.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.SecureUtil;
import com.mcst.easyfk.authority.dto.AuthResourceDto;
import com.mcst.easyfk.authority.enums.UserTypeEnum;
import com.mcst.easyfk.core.builders.BEBuilder;
import com.mcst.easyfk.core.constants.CharacterConstant;
import com.mcst.easyfk.core.dto.login.UserData;
import com.mcst.easyfk.core.dto.page.PageResult;
import com.mcst.easyfk.core.dto.request.ModifyRequest;
import com.mcst.easyfk.core.dto.request.SearchRequest;
import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.core.utils.common.EmptyUtil;
import com.mcst.easyfk.core.utils.common.TransformUtil;
import com.mcst.easyfk.repository.search.SCBuilder;
import com.mcst.easyfk.repository.search.SearchCondition;
import com.mcst.easyfk.repository.util.ConditionUtil;
import com.mcst.easyfk.service.util.ServiceUtil;
import com.mcst.easyfk.service.util.UserDataFiltrationUtil;
import com.mcst.module.auth.api.dto.EmployeeDto;
import com.mcst.module.auth.api.dto.RoleDto;
import com.mcst.module.auth.api.dto.RoleResourceDto;
import com.mcst.module.auth.api.request.RoleReq;
import com.mcst.module.auth.api.response.RoleResp;
import com.mcst.module.auth.api.vo.RoleGrantVO;
import com.mcst.module.auth.api.vo.RoleResourceVO;
import com.mcst.module.auth.server.enums.AuthEnum;
import com.mcst.module.auth.server.properties.AuthProperties;
import com.mcst.module.auth.server.repository.IAuthResourceRepository;
import com.mcst.module.auth.server.repository.IEmployeeRepository;
import com.mcst.module.auth.server.repository.IRoleRepository;
import com.mcst.module.auth.server.repository.IRoleResourceRepository;
import com.mcst.module.auth.server.service.IRoleService;
import com.mcst.module.auth.server.util.RoleGrantUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author liuyijun
 */
@SuppressWarnings("all")
public class RoleServiceImpl implements IRoleService, InitializingBean {
    @Resource
    private IRoleRepository roleRepository;

    @Resource
    private IEmployeeRepository employeeRepository;

    @Resource
    private IRoleResourceRepository roleResourceRepository;

    @Resource
    private IAuthResourceRepository authResourceRepository;

    @Resource
    private AuthProperties authProperties;

    @Override
    public RoleResp queryById(String id, String... selectColumns) {
        return TransformUtil.transformObj(this.roleRepository.queryById(id, selectColumns), RoleResp.class);
    }

    @Override
    public RoleResp queryOneByField(String field, Object value, String... selectColumns) {
        return TransformUtil.transformObj(this.roleRepository.queryOneByField(field, value, selectColumns), RoleResp.class);
    }

    @Override
    public String insertAndReturnId(RoleReq roleReq) {
        return ServiceUtil.insertAndReturnId(roleReq, roleRepository, RoleDto.class);
    }

    @Override
    public List<RoleResp> queryList(SearchRequest<RoleReq> param) {
        UserDataFiltrationUtil.saasDataFiltration(param, RoleReq.class, Map.of(RoleReq::getAgentId, UserData::getAgentId, RoleReq::getMerchantId, UserData::getMerchantId));
        return TransformUtil.transformList(this.roleRepository.queryByCondition(ConditionUtil.conditionByRequest(param)), RoleResp.class);
    }

    @Override
    public RoleResp queryOne(SearchRequest<RoleReq> param) {
        return TransformUtil.transformObj(this.roleRepository.queryOneByCondition(ConditionUtil.conditionByRequest(param)), RoleResp.class);
    }

    @Override
    public long count(SearchRequest<RoleReq> param) {
        return this.roleRepository.countByCondition(ConditionUtil.conditionByRequest(param));
    }

    @Override
    public boolean exists(SearchRequest<RoleReq> param) {
        return this.roleRepository.exists(ConditionUtil.conditionByRequest(param));
    }

    @Override
    public PageResult<RoleResp> queryByPage(SearchRequest<RoleReq> condition) {
        UserDataFiltrationUtil.saasDataFiltration(condition, RoleReq.class, Map.of(RoleReq::getAgentId, UserData::getAgentId, RoleReq::getMerchantId, UserData::getMerchantId));
        return TransformUtil.transformPageResult(this.roleRepository.queryByPage(ConditionUtil.conditionByRequest(condition)), RoleResp.class);
    }

    @Override
    public BaseResult<?> save(ModifyRequest<RoleReq> param) {
        UserDataFiltrationUtil.setSaasData(param, Map.of(RoleReq::getAgentId, UserData::getAgentId, RoleReq::getAgentName, UserData::getAgentName, RoleReq::getMerchantId, UserData::getMerchantId, RoleReq::getMerchantName, UserData::getMerchantName));
        return ServiceUtil.save(param, roleRepository, RoleDto.class);
    }

    @Override
    public BaseResult<?> delete(ModifyRequest<RoleReq> param) {
        List<?> ids = param.getIdList();
        if (EmptyUtil.isEmpty(ids)) {
            // throw new BusinessException("id不可为空");
            throw BEBuilder.exceptionByI18n("IdEmptyError", AuthEnum.I18N_PATH.getCode());
        }
        ids.forEach(id -> {
            SearchCondition searchCondition = SCBuilder.builder().likeConditions("roles", id.toString()).build();
            if (this.employeeRepository.exists(searchCondition)) {
                //throw new BusinessException("角色正在使用，不可删除");
                throw BEBuilder.exceptionByI18n("RoleDeleteError", AuthEnum.I18N_PATH.getCode());
            }
            deleteSubRoleByParentId(id.toString());
        });
        return ServiceUtil.delete(param, roleRepository, RoleDto.class);
    }

    @Override
    public void afterPropertiesSet() {
        if (authProperties.getInit()) {
            RoleDto role = this.queryOneByField("supperStatus", 1);
            if (EmptyUtil.isEmpty(role)) {
                role = new RoleDto().setRoleName("开发者").setRemark("系统开发所需的测试权限").setForbiddenFlag(0).setType(UserTypeEnum.Platform.getValue());
                role.setSupperStatus(1);
                String roleId = this.roleRepository.insertAndReturnId(role);
                role.setRoleId(roleId);
            }
            EmployeeDto employee = this.employeeRepository.queryOneByField("loginName", "dev");
            if (EmptyUtil.isEmpty(employee)) {
                employee = new EmployeeDto().setForbiddenFlag(0).setType(UserTypeEnum.Platform.getValue()).setEmployeeName("开发专用").setLoginName("dev").setPassword(SecureUtil.md5("dev@123")).setRoles(role.getRoleId());
                this.employeeRepository.insert(employee);
            }
        }
    }

    @Override
    public String getRoleResources(String roleUid) {
        StringBuilder result = new StringBuilder();
        List<RoleResourceDto> roleResourceList = this.roleResourceRepository.queryByField("roleId", roleUid);
        for (RoleResourceDto roleResource : roleResourceList) {
            result.append(roleResource.getResourceId()).append(CharacterConstant.COMMA_DELIMITERS);
        }
        if (result.toString().endsWith(CharacterConstant.COMMA_DELIMITERS)) {
            result = new StringBuilder(result.substring(0, result.length() - 1));
        }
        return result.toString();
    }

    @Override
    public BaseResult<?> grant(RoleGrantVO grantVO) {
        this.roleResourceRepository.deleteByCondition(SCBuilder.<RoleResourceDto>builder().equalsConditions("roleId", grantVO.getId()).build());
        if (EmptyUtil.isNotEmpty(grantVO.getResourceIds())) {
            for (String resourceId : grantVO.getResourceIds().split(CharacterConstant.COMMA_DELIMITERS)) {
                this.roleResourceRepository.insert(new RoleResourceDto().setResourceId(resourceId).setRoleId(grantVO.getId()));
            }
        }
        RoleDto roleDTO = new RoleDto().setType(grantVO.getType());
        roleDTO.setRoleId(grantVO.getId());
        return this.roleRepository.updateBySelective(roleDTO, "type");
    }

    @Override
    public List<RoleResp> getAllSubRolesByParentId(String parentId) {
        List<RoleDto> roles = CollUtil.newArrayList();
        this.getSubRoleByParentId(parentId, roles);
        return TransformUtil.transformList(roles, RoleResp.class);
    }

    @Override
    public List<RoleResp> getEmployeeAllSubRoles(String employeeId) {
        List<RoleDto> list = null;
        EmployeeDto employeeDTO = this.employeeRepository.queryById(employeeId);
        if (EmptyUtil.isNotEmpty(employeeDTO) && EmptyUtil.isNotEmpty(employeeDTO.getRoles())) {
            list = CollUtil.newArrayList();
            for (String roleId : employeeDTO.getRoles().split(CharacterConstant.COMMA_DELIMITERS)) {
                list.addAll(this.getAllSubRolesByParentId(roleId));
            }
        }
        return TransformUtil.transformList(list, RoleResp.class);
    }

    @Override
    public List<RoleResourceVO> getRoleResourceByRoleId(String roleId) {
        List<RoleResourceVO> tree = new ArrayList<>();
        RoleDto r = this.roleRepository.queryById(roleId);
        if (EmptyUtil.isNotEmpty(r)) {
            List<AuthResourceDto> parentResources = this.getResourceTreeByRoleId(r.getParentId(), r.getType());
            RoleGrantUtil.createTreeNode(parentResources, tree);
            if (EmptyUtil.isNotEmpty(tree)) {
                String resourceIds = this.getRoleResources(r.getRoleId());
                if (EmptyUtil.isNotEmpty(resourceIds)) {
                    RoleGrantUtil.checkSelected(tree, resourceIds);
                }
            }
        }
        return tree;
    }

    public void deleteSubRoleByParentId(String parentId) {
        if (EmptyUtil.isNotEmpty(parentId)) {
            return;
        }
        List<RoleDto> subRoles = roleRepository.queryByField("parentId", parentId);
        if (EmptyUtil.isNotEmpty(subRoles)) {
            for (RoleDto role : subRoles) {
                roleRepository.deleteById(role.getRoleId());
                deleteSubRoleByParentId(role.getRoleId());
            }
        }
    }

    private List<AuthResourceDto> getResourceTreeByRoleId(String roleId, String type) {
        String[] columns = {"resourceId", "name", "pid", "title"};
        return this.getResourcesByRoleId(roleId, type, columns);
    }

    private void getSubRoleByParentId(String parentId, List<RoleDto> roleDTOList) {
        SearchCondition subCondition = SCBuilder.<RoleDto>builder().example(new RoleDto().setParentId(parentId).setForbiddenFlag(0)).build();
        List<RoleDto> roleList = roleRepository.queryByCondition(subCondition);
        if (EmptyUtil.isNotEmpty(roleList)) {
            roleDTOList.addAll(roleList);
            for (RoleDto role : roleList) {
                if (EmptyUtil.isNotEmpty(role.getRoleId())) {
                    getSubRoleByParentId(role.getRoleId(), roleDTOList);
                }
            }
        }
    }

    private List<AuthResourceDto> getResourcesByRoleId(String roleId, String type, String... columns) {
        List<AuthResourceDto> orgRes = CollUtil.newArrayList();
        SCBuilder<AuthResourceDto> builder = SCBuilder.<AuthResourceDto>builder().equalsConditions("resourceLevel", 2).ascFields("sort");
        if (EmptyUtil.isNotEmpty(type)) {
            String sql = "(type_flag like '%" + type + "%' or type_flag = 'all')";
           // builder.likeConditions("typeFlag", type);
            builder.customConditionSql(sql);
        }
        if (EmptyUtil.isNotEmpty(columns)) {
            builder.selectFields(columns);
        }
        if (null == roleId) {
            return this.authResourceRepository.queryByCondition(builder.build());
        }
        RoleDto role = this.roleRepository.queryById(roleId);
        if (EmptyUtil.isNotEmpty(role)) {
            if (EmptyUtil.isNotEmpty(role.getSupperStatus()) && role.getSupperStatus() == 1) {
                return this.authResourceRepository.queryByCondition(builder.build());
            }
        }
        SearchCondition condition = SCBuilder.<RoleResourceDto>builder().equalsConditions("roleId", roleId).build();
        List<RoleResourceDto> roleResources = this.roleResourceRepository.queryByCondition(condition);
        if (EmptyUtil.isNotEmpty(roleResources)) {
            for (RoleResourceDto roleResource : roleResources) {
                AuthResourceDto resource = this.authResourceRepository.queryById(roleResource.getResourceId(), columns);
                if (EmptyUtil.isNotEmpty(resource)) {
                    orgRes.add(resource);
                }
            }
        }
        return orgRes;
    }
}
