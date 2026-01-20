package com.mcst.module.auth.server.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.crypto.SecureUtil;
import com.mcst.easyfk.authority.dto.AuthResourceDto;
import com.mcst.easyfk.authority.enums.ResourceCategory;
import com.mcst.easyfk.authority.enums.UserTypeEnum;
import com.mcst.easyfk.authority.manager.UserDataManager;
import com.mcst.easyfk.authority.response.AuthResourceResp;
import com.mcst.easyfk.authority.vo.UserAuth;
import com.mcst.easyfk.authority.vo.UserAuthResources;
import com.mcst.easyfk.core.builders.BEBuilder;
import com.mcst.easyfk.core.constants.CharacterConstant;
import com.mcst.easyfk.core.dto.login.LoginResult;
import com.mcst.easyfk.core.dto.login.LoginUser;
import com.mcst.easyfk.core.dto.login.UserData;
import com.mcst.easyfk.core.dto.page.PageResult;
import com.mcst.easyfk.core.dto.request.ModifyRequest;
import com.mcst.easyfk.core.dto.request.SearchRequest;
import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.core.function.FunctionExecutor;
import com.mcst.easyfk.core.utils.common.EmptyUtil;
import com.mcst.easyfk.core.utils.common.I18NUtil;
import com.mcst.easyfk.core.utils.common.MyBeanUtils;
import com.mcst.easyfk.core.utils.common.TransformUtil;
import com.mcst.easyfk.repository.search.SCBuilder;
import com.mcst.easyfk.repository.search.SearchCondition;
import com.mcst.easyfk.repository.util.ConditionUtil;
import com.mcst.easyfk.service.util.ServiceUtil;
import com.mcst.easyfk.service.util.UserDataFiltrationUtil;
import com.mcst.module.auth.api.dto.EmployeeDto;
import com.mcst.module.auth.api.dto.RoleDto;
import com.mcst.module.auth.api.dto.RoleResourceDto;
import com.mcst.module.auth.api.request.EmployeeReq;
import com.mcst.module.auth.api.response.EmployeeResp;
import com.mcst.module.auth.api.vo.BmsLoginRequest;
import com.mcst.module.auth.api.vo.ChangePwdVO;
import com.mcst.module.auth.api.vo.RestLoginPwdVO;
import com.mcst.module.auth.server.enums.AuthEnum;
import com.mcst.module.auth.server.properties.EmpPwdProperties;
import com.mcst.module.auth.server.repository.IEmployeeRepository;
import com.mcst.module.auth.server.repository.IRoleRepository;
import com.mcst.module.auth.server.repository.IRoleResourceRepository;
import com.mcst.module.auth.server.service.IAuthResourceService;
import com.mcst.module.auth.server.service.IEmployeeService;
import jakarta.annotation.Resource;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 员工 服务实现类
 * </p>
 *
 * @author liuyijun
 */

public class EmployeeServiceImpl implements IEmployeeService {
    @Resource
    private IEmployeeRepository employeeRepository;

    @Resource
    private IRoleRepository roleRepository;
    @Resource
    private IRoleResourceRepository roleResourceRepository;

    @Resource
    private UserDataManager userDataManager;

    @Resource
    private EmpPwdProperties empPwdProperties;


    @Resource
    private IAuthResourceService authResourceService;

    @Override
    public EmployeeResp queryById(String id, String... selectColumns) {
        return TransformUtil.transformObj(this.employeeRepository.queryById(id, selectColumns), EmployeeResp.class);
    }

    @Override
    public List<EmployeeResp> queryList(SearchRequest<EmployeeReq> param) {
        UserDataFiltrationUtil.saasDataFiltration(param, EmployeeReq.class, Map.of(EmployeeReq::getAgentId, UserData::getAgentId, EmployeeReq::getMerchantId, UserData::getMerchantId));
        return TransformUtil.transformList(this.employeeRepository.queryByCondition(ConditionUtil.conditionByRequest(param)), EmployeeResp.class);
    }

    @Override
    public EmployeeResp queryOne(SearchRequest<EmployeeReq> param) {
        return TransformUtil.transformObj(this.employeeRepository.queryOneByCondition(ConditionUtil.conditionByRequest(param)), EmployeeResp.class);
    }

    @Override
    public long count(SearchRequest<EmployeeReq> param) {
        return this.employeeRepository.countByCondition(ConditionUtil.conditionByRequest(param));
    }

    @Override
    public EmployeeResp queryOneByField(String field, Object value, String... selectColumns) {
        return TransformUtil.transformObj(this.roleRepository.queryOneByField(field, value, selectColumns), EmployeeResp.class);
    }

    @Override
    public String insertAndReturnId(EmployeeReq employeeReq) {
        return ServiceUtil.insertAndReturnId(employeeReq, this.employeeRepository, EmployeeDto.class);
    }

    @Override
    public boolean exists(SearchRequest<EmployeeReq> param) {
        return this.employeeRepository.exists(ConditionUtil.conditionByRequest(param));
    }

    @Override
    public PageResult<EmployeeResp> queryByPage(SearchRequest<EmployeeReq> condition) {
        EmployeeReq employeeDTO = condition.getParmaObj();
        if (EmptyUtil.isEmpty(employeeDTO)) {
            employeeDTO = new EmployeeReq();
        }
        if (EmptyUtil.isEmpty(employeeDTO.getLoginName())) {
            employeeDTO.setLoginName("dev");
            condition.setNotFields(ArrayUtil.addAll(condition.getNotFields(), new String[]{"loginName"}));
        }
        UserDataFiltrationUtil.saasDataFiltration(condition, EmployeeReq.class, Map.of(EmployeeReq::getAgentId, UserData::getAgentId, EmployeeReq::getMerchantId, UserData::getMerchantId));
        SCBuilder<EmployeeDto> SCBuilder = new SCBuilder<>(ConditionUtil.conditionByRequest(condition));
        return TransformUtil.transformPageResult(this.employeeRepository.queryByPage(SCBuilder.build()), EmployeeResp.class);
    }

    @Override
    public BaseResult<?> save(ModifyRequest<EmployeeReq> param) {
        EmployeeReq employee = param.getParmaObj();
        UserDataFiltrationUtil.setSaasData(param, Map.of(EmployeeReq::getAgentId, UserData::getAgentId, EmployeeReq::getAgentName, UserData::getAgentName, EmployeeReq::getMerchantId, UserData::getMerchantId, EmployeeReq::getMerchantName, UserData::getMerchantName));
        if (EmptyUtil.isNotEmpty(employee)) {
            if (EmptyUtil.isEmpty(employee.getEmployeeId())) {
                employee.setLastUpdatePwd(LocalDateTime.now()).setType(UserTypeEnum.Platform.getValue());
                if (EmptyUtil.isNotEmpty(employee.getMerchantId())) {
                    employee.setType(UserTypeEnum.Merchant.getValue());
                } else if (EmptyUtil.isNotEmpty(employee.getAgentId())) {
                    employee.setType(UserTypeEnum.Agent.getValue());
                }
            }
            FunctionExecutor.notEmptyFun(employee.getRoles(), roleIds -> {
                Set<String> types = new HashSet<>();
                for (String roleId : roleIds.split(CharacterConstant.COMMA_DELIMITERS)) {
                    RoleDto roleDTO = this.roleRepository.queryById(roleId);
                    FunctionExecutor.notEmptyFun(roleDTO, role -> FunctionExecutor.notEmptyFun(role.getBizType(), types::add));
                }
                FunctionExecutor.notEmptyFun(types, typeSet -> employee.setBizType(String.join(CharacterConstant.COMMA_DELIMITERS, typeSet)));
            });
        }
        return ServiceUtil.save(param, employeeRepository, EmployeeDto.class);
    }

    @Override
    public BaseResult<?> delete(ModifyRequest<EmployeeReq> param) {
        return ServiceUtil.delete(param, employeeRepository, EmployeeDto.class);
    }

    @Override
    public LoginResult login(BmsLoginRequest loginRequest) {
        if (EmptyUtil.isEmpty(loginRequest.getLoginName()) || EmptyUtil.isEmpty(loginRequest.getPassword())) {
            throw BEBuilder.exceptionByI18n("AccountOrPwdEmpty", AuthEnum.I18N_PATH.getCode());
        }
        EmployeeDto employee = this.employeeRepository.queryOneByField("loginName", loginRequest.getLoginName());
        if (EmptyUtil.isEmpty(employee)) {
            throw BEBuilder.exceptionByI18n("LoginNameError", AuthEnum.I18N_PATH.getCode());
        }
        if (EmptyUtil.isNotEmpty(employee.getForbiddenFlag()) && employee.getForbiddenFlag() == 1) {
            throw BEBuilder.exceptionByI18n("AccountDisabled", AuthEnum.I18N_PATH.getCode());
        }
        if (empPwdProperties.getUpdate()) {
            LocalDateTime lastTime = Optional.ofNullable(employee.getLastUpdatePwd()).orElse(employee.getInsertTime());
            if (lastTime.plusDays(30).isBefore(LocalDateTime.now())) {
                String msg = I18NUtil.getMessage("LoginPwdExpired", AuthEnum.I18N_PATH.getCode());
                return new LoginResult().setSuccess(false).setMsg(msg);
            }
        }
        if (!ObjUtil.equals(loginRequest.getPassword(), employee.getPassword())) {
            throw BEBuilder.exceptionByI18n("LoginPwdError", AuthEnum.I18N_PATH.getCode());
        }
        return this.employeeLogin(employee);
    }

    @Override
    public boolean checkLoginName(String loginName) {
        return this.employeeRepository.exists("loginName", loginName);
    }

    @Override
    public BaseResult<?> updateEmployeePwd(ChangePwdVO changePwdVO) {
        EmployeeDto employee = this.employeeRepository.queryById(changePwdVO.getEmployeeId());
        if (EmptyUtil.isEmpty(employee)) {
            //throw new BusinessException("员工信息错误");
            throw BEBuilder.exceptionByI18n("EmpInfoError", AuthEnum.I18N_PATH.getCode());
        }
        if (!changePwdVO.getOldPwd().equals(employee.getPassword())) {
            // throw new BusinessException("旧密码错误");
            throw BEBuilder.exceptionByI18n("OldPwdError", AuthEnum.I18N_PATH.getCode());
        }
        employee.setPassword(changePwdVO.getNewPwd()).setLastUpdatePwd(LocalDateTime.now());
        return this.employeeRepository.updateBySelective(employee);
    }

    @Override
    public BaseResult<?> resetPwd(String id) {
        EmployeeDto employee = this.employeeRepository.queryById(id);
        if (EmptyUtil.isEmpty(employee)) {
            throw BEBuilder.exceptionByI18n("EmpInfoError", AuthEnum.I18N_PATH.getCode());
        }
        employee.setPassword(SecureUtil.md5(employee.getLoginName())).setLastUpdatePwd(LocalDateTime.now());
        return this.employeeRepository.updateBySelective(employee);
    }

    @Override
    public List<UserAuthResources> queryEmployeeResource(String cacheKey) {
        List<UserAuthResources> result = new ArrayList<>();
        UserAuth userAuth = this.userDataManager.getUserAuth(cacheKey);
        List<AuthResourceDto> resources = userAuth.getResources();
        if (EmptyUtil.isNotEmpty(resources)) {
            for (AuthResourceDto resource : resources) {
                if (EmptyUtil.isEmpty(resource.getPid())) {
                    UserAuthResources menu = MyBeanUtils.copyBean(resource, UserAuthResources.class);
                    result.add(menu);
                }
            }
            subResourceMethod(result, resources);
        }
        return result;
    }

    @Override
    public BaseResult<?> loginOut() {
        return this.userDataManager.loginOut();
    }

    @Override
    public LoginResult dingTalkLogin(String mobile, String userCode) {
        SCBuilder<EmployeeDto> conditionBuilder = SCBuilder.builder();
        if (EmptyUtil.isNotEmpty(userCode)) {
            conditionBuilder.equalsConditions(EmployeeDto::getLoginName, userCode);
        } else {
            conditionBuilder.equalsConditions(EmployeeDto::getLoginName, mobile);
        }
        EmployeeDto employeeDTO = this.employeeRepository.queryOneByCondition(conditionBuilder.build());
        if (EmptyUtil.isEmpty(employeeDTO)) {
            //throw new BusinessException("用户不存在");
            throw BEBuilder.exceptionByI18n("UserNotExist", AuthEnum.I18N_PATH.getCode());
        }
        return this.employeeLogin(employeeDTO);
    }

    @Override
    public LoginResult loginById(String employeeId) {
        EmployeeDto employeeDTO = this.employeeRepository.queryById(employeeId);
        if (EmptyUtil.isEmpty(employeeDTO)) {
            throw BEBuilder.exceptionByI18n("UserNotExist", AuthEnum.I18N_PATH.getCode());
        }
        return this.employeeLogin(employeeDTO);
    }

    /**
     * 员工登录
     */
    private LoginResult employeeLogin(EmployeeDto employee) {
        if (EmptyUtil.isNotEmpty(employee.getForbiddenFlag()) && employee.getForbiddenFlag() == 1) {
            throw BEBuilder.exceptionByI18n("AccountDisabled", AuthEnum.I18N_PATH.getCode());
        }
        UserAuth userAuth = new UserAuth();
        UserData userData = new UserData().setType(employee.getType()).setUserId(employee.getEmployeeId()).setAgentId(employee.getAgentId()).setAgentName(employee.getAgentName()).setMerchantId(employee.getMerchantId()).setMerchantName(employee.getMerchantName()).setOrgId(employee.getOrgId()).setOrgName(employee.getOrgName()).setDepartmentId(employee.getDepartmentId()).setDepartmentName(employee.getDepartmentName());
        LoginUser loginUser = new LoginUser();
        loginUser.setUserType(employee.getType());
        Map<String, AuthResourceDto> map = new LinkedHashMap<>();
        List<AuthResourceDto> resources = CollUtil.newArrayList();
        List<RoleDto> roles = CollUtil.newArrayList();
        employee.setBizType(null);
        if (EmptyUtil.isNotEmpty(employee.getRoles())) {
            Set<String> bizType = new HashSet<>();
            for (String roleId : employee.getRoles().split(",")) {
                RoleDto role = this.roleRepository.queryById(roleId);
                if (EmptyUtil.isNotEmpty(role)) {
                    if (EmptyUtil.isNotEmpty(role) && EmptyUtil.isNotEmpty(role.getSupperStatus()) && role.getSupperStatus() == 1) {
                        userAuth.setSupperUser(true);
                    }
                    FunctionExecutor.notEmptyFun(role.getBizType(), type -> bizType.addAll(Arrays.asList(role.getBizType().split(CharacterConstant.COMMA_DELIMITERS))));
                    roles.add(role);
                }
            }
            if (EmptyUtil.isNotEmpty(bizType)) {
                userData.setBizType(bizType);
                employee.setBizType(String.join(CharacterConstant.COMMA_DELIMITERS, bizType));
            }
            this.employeeRepository.updateBySelective(employee, "bizType");
            if (EmptyUtil.isNotEmpty(roles)) {
                for (RoleDto role : roles) {
                    if (EmptyUtil.isNotEmpty(role.getSupperStatus()) && role.getSupperStatus() == 1) {
                        List<AuthResourceResp> allResources = this.authResourceService.getAllPlatformAuthResources();
                        for (AuthResourceDto resource : allResources) {
                            if (!map.containsKey(resource.getResourceId())) {
                                map.put(resource.getResourceId(), resource);
                            }
                        }
                    } else {
                        List<RoleResourceDto> roleResources = this.roleResourceRepository.queryByField("roleId", role.getRoleId());
                        for (RoleResourceDto roleResource : roleResources) {
                            if (!map.containsKey(roleResource.getResourceId())) {
                                AuthResourceDto resource = this.authResourceService.queryById(roleResource.getResourceId());
                                if (EmptyUtil.isNotEmpty(resource)) {
                                    map.put(roleResource.getResourceId(), resource);
                                }
                            }
                        }
                    }
                }
            }

            if (MapUtil.isNotEmpty(map)) {
                for (Map.Entry<String, AuthResourceDto> e : map.entrySet()) {
                    resources.add(e.getValue());
                }
            }
            if (EmptyUtil.isNotEmpty(resources)) {
                userAuth.setResources(resources);
                List<String> urls = new ArrayList<>();
                for (AuthResourceDto resource : resources) {
                    if (EmptyUtil.isNotEmpty(resource.getUrl())) {
                        urls.add(resource.getUrl());
                    }
                }
                userAuth.setUrls(urls);
            }
        }
        String authCachedKey = this.userDataManager.cacheUserAuth(userAuth).getData();
        loginUser.setAuthCachedKey(authCachedKey);
        String dataCachedKey = this.userDataManager.cacheUserData(userData).getData();
        loginUser.setDataCachedKey(dataCachedKey);
        loginUser.setName(Optional.ofNullable(employee.getEmployeeName()).orElse(employee.getLoginName()));
        return new LoginResult(loginUser).setHeadImage(employee.getHeaderPic());
    }

    @Override
    public List<EmployeeResp> getEmpByResourceId(String resourceId) {
        AuthResourceDto resource = this.authResourceService.queryById(resourceId);
        if (EmptyUtil.isEmpty(resource)) {
            return null;
        }
        List<RoleResourceDto> roleResourceList = this.roleResourceRepository.queryByField("resourceId", resourceId);
        if (EmptyUtil.isEmpty(roleResourceList)) {
            return null;
        }

        Set<String> roleIds = new HashSet<>();
        roleResourceList.forEach(roleResourceDTO -> {
            if (EmptyUtil.isNotEmpty(roleResourceDTO.getRoleId())) {
                roleIds.add(roleResourceDTO.getRoleId());
            }
        });

        if (EmptyUtil.isEmpty(roleIds)) {
            return null;
        }
        List<EmployeeResp> employeeList = new ArrayList<>();
        roleIds.forEach(roleId -> {
            SearchCondition condition = SCBuilder.<EmployeeDto>builder().likeConditions(EmployeeDto::getRoles, roleId).equalsConditions(EmployeeDto::getForbiddenFlag, 0).build();
            EmployeeDto employeeDTO = this.employeeRepository.queryOneByCondition(condition);
            if (EmptyUtil.isNotEmpty(employeeDTO)) {
                employeeList.add(MyBeanUtils.copyBean(employeeDTO, EmployeeResp.class));
            }
        });
        return employeeList;
    }

    @Override
    public BaseResult<?> restLoginPwd(RestLoginPwdVO restLoginPwdVO) {
        if (restLoginPwdVO.getOldPwd().equals(restLoginPwdVO.getNewPwd())) {
            // throw new BusinessException("新密码和原密码不可相同");
            throw BEBuilder.exceptionByI18n("OldPwdSameNewPwd", AuthEnum.I18N_PATH.getCode());
        }
        EmployeeDto employeeDTO = this.employeeRepository.queryOneByField("loginName", restLoginPwdVO.getAccount());
        if (EmptyUtil.isEmpty(employeeDTO)) {
            //throw new BusinessException("账号信息错误");
            throw BEBuilder.exceptionByI18n("AccountError", AuthEnum.I18N_PATH.getCode());
        }
        if (!restLoginPwdVO.getOldPwd().equals(employeeDTO.getPassword())) {
            //throw new BusinessException("原密码错误");
            throw BEBuilder.exceptionByI18n("OldPwdError", AuthEnum.I18N_PATH.getCode());
        }
        employeeDTO.setPassword(restLoginPwdVO.getNewPwd()).setLastUpdatePwd(LocalDateTime.now());
        return this.employeeRepository.updateBySelective(employeeDTO);
    }

    private void subResourceMethod(List<UserAuthResources> result, List<AuthResourceDto> resources) {
        if (EmptyUtil.isNotEmpty(result)) {
            for (UserAuthResources menu : result) {
                List<UserAuthResources> subMenus = new ArrayList<>();
                for (AuthResourceDto res : resources) {
                    if (EmptyUtil.isNotEmpty(res.getPid()) && res.getPid().equals(menu.getResourceId())) {
                        UserAuthResources subMenu = MyBeanUtils.copyBean(res, UserAuthResources.class);
                        if (ResourceCategory.action.toString().equalsIgnoreCase(res.getCategory())) {
                            if (EmptyUtil.isNotEmpty(res.getActionCode())) {
                                List<String> actions = menu.getActions();
                                if (EmptyUtil.isEmpty(actions)) {
                                    actions = new ArrayList<>();
                                }
                                actions.add(res.getActionCode());
                                menu.setActions(actions);
                            }
                        } else {
                            subMenus.add(subMenu);
                        }
                    }
                }
                if (EmptyUtil.isNotEmpty(subMenus)) {
                    subMenus.sort((o1, o2) -> {
                        int diff = o1.getSort() - o2.getSort();
                        if (diff > 0) {
                            return 1;
                        } else if (diff < 0) {
                            return -1;
                        }
                        return 0;
                    });
                    menu.setChildren(subMenus);
                }
                subResourceMethod(subMenus, resources);
            }
        }

    }
}
