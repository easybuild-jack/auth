package com.mcst.module.auth.server.service.impl;

import com.mcst.easyfk.core.builders.BEBuilder;
import com.mcst.easyfk.core.dto.login.UserData;
import com.mcst.easyfk.core.dto.page.PageResult;
import com.mcst.easyfk.core.dto.request.ModifyRequest;
import com.mcst.easyfk.core.dto.request.SearchRequest;
import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.core.exception.ValidateException;
import com.mcst.easyfk.core.utils.common.EmptyUtil;
import com.mcst.easyfk.core.utils.common.TransformUtil;
import com.mcst.easyfk.repository.search.SCBuilder;
import com.mcst.easyfk.repository.search.SearchCondition;
import com.mcst.easyfk.repository.util.ConditionUtil;
import com.mcst.easyfk.service.util.ServiceUtil;
import com.mcst.easyfk.service.util.UserDataFiltrationUtil;
import com.mcst.module.auth.api.dto.DepartmentDto;
import com.mcst.module.auth.api.request.DepartmentReq;
import com.mcst.module.auth.api.response.DepartmentResp;
import com.mcst.module.auth.server.enums.AuthEnum;
import com.mcst.module.auth.server.repository.IDepartmentRepository;
import com.mcst.module.auth.server.repository.IEmployeeRepository;
import com.mcst.module.auth.server.service.IDepartmentService;
import jakarta.annotation.Resource;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author liuyijun
 */

public class DepartmentServiceImpl implements IDepartmentService {
    @Resource
    private IDepartmentRepository departmentRepository;

    @Resource
    private IEmployeeRepository employeeRepository;

    @Override
    public DepartmentResp queryById(String id, String... selectColumns) {
        return TransformUtil.transformObj(this.departmentRepository.queryById(id, selectColumns), DepartmentResp.class);
    }

    @Override
    public List<DepartmentResp> queryList(SearchRequest<DepartmentReq> param) {
        UserDataFiltrationUtil.saasDataFiltration(param, DepartmentReq.class, Map.of(
            DepartmentReq::getAgentId, UserData::getAgentId,
            DepartmentReq::getMerchantId, UserData::getMerchantId
        ));
        return TransformUtil.transformList(this.departmentRepository.queryByCondition(ConditionUtil.conditionByRequest(param)), DepartmentResp.class);
    }

    @Override
    public DepartmentResp queryOne(SearchRequest<DepartmentReq> param) {
        return TransformUtil.transformObj(this.departmentRepository.queryOneByCondition(ConditionUtil.conditionByRequest(param)), DepartmentResp.class);
    }

    @Override
    public long count(SearchRequest<DepartmentReq> param) {
        return this.departmentRepository.countByCondition(ConditionUtil.conditionByRequest(param));
    }

    @Override
    public boolean exists(SearchRequest<DepartmentReq> param) {
        return this.departmentRepository.exists(ConditionUtil.conditionByRequest(param));
    }

    @Override
    public PageResult<DepartmentResp> queryByPage(SearchRequest<DepartmentReq> condition) {
        UserDataFiltrationUtil.saasDataFiltration(condition, DepartmentReq.class, Map.of(
            DepartmentReq::getAgentId, UserData::getAgentId,
            DepartmentReq::getMerchantId, UserData::getMerchantId
        ));
        return TransformUtil.transformPageResult(this.departmentRepository.queryByPage(ConditionUtil.conditionByRequest(condition)), DepartmentResp.class);
    }

    @Override
    public BaseResult<?> save(ModifyRequest<DepartmentReq> param) {
        UserDataFiltrationUtil.setSaasData(param, Map.of(
            DepartmentReq::getAgentId, UserData::getAgentId,
            DepartmentReq::getAgentName, UserData::getAgentName,
            DepartmentReq::getMerchantId, UserData::getMerchantId,
            DepartmentReq::getMerchantName, UserData::getMerchantName
        ));
        return ServiceUtil.save(param, departmentRepository, DepartmentDto.class);
    }

    @Override
    public BaseResult<?> delete(ModifyRequest<DepartmentReq> param) {
        List<?> ids = param.getIdList();
        if (EmptyUtil.isEmpty(ids)) {
            throw new ValidateException("id不可为空");
        }
        ids.forEach(id -> {
            SearchCondition searchCondition = SCBuilder.builder().equalsConditions("departmentId", id.toString()).build();
            if (this.employeeRepository.exists(searchCondition)) {
                throw BEBuilder.exceptionByI18n("DepartmentDeleteError", AuthEnum.I18N_PATH.getCode());
            }
        });
        return ServiceUtil.delete(param, departmentRepository, DepartmentDto.class);
    }

    @Override
    public String insertAndReturnId(DepartmentReq param) {
        return ServiceUtil.insertAndReturnId(param, departmentRepository, DepartmentDto.class);
    }

    @Override
    public DepartmentResp queryOneByField(String field, Object value, String... selectColumns) {
        return TransformUtil.transformObj(this.departmentRepository.queryOneByField(field, value, selectColumns), DepartmentResp.class);
    }
}
