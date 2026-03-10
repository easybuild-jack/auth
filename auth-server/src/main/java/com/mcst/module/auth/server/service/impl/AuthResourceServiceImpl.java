package com.mcst.module.auth.server.service.impl;

import com.mcst.easyfk.authority.constant.ResourceSecurityLevel;
import com.mcst.easyfk.authority.dto.AuthResourceDto;
import com.mcst.easyfk.authority.request.AuthResourceReq;
import com.mcst.easyfk.authority.response.AuthResourceResp;
import com.mcst.easyfk.core.builders.RRBuilder;
import com.mcst.easyfk.core.dto.page.PageResult;
import com.mcst.easyfk.core.dto.request.ModifyRequest;
import com.mcst.easyfk.core.dto.request.SearchRequest;
import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.core.dto.response.ResponseResult;
import com.mcst.easyfk.core.utils.common.TransformUtil;
import com.mcst.easyfk.repository.search.SCBuilder;
import com.mcst.easyfk.repository.search.SearchCondition;
import com.mcst.easyfk.repository.util.ConditionUtil;
import com.mcst.easyfk.service.util.ServiceUtil;
import com.mcst.module.auth.orm.repository.IAuthResourceRepository;
import com.mcst.module.auth.server.service.IAuthResourceService;
import com.mcst.module.auth.server.util.ResourceSecurityLevelUtil;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author liuyijun
 */
public class AuthResourceServiceImpl implements IAuthResourceService {

    @Resource(type = IAuthResourceRepository.class)
    private IAuthResourceRepository resourceRepository;

    @Override
    public AuthResourceResp queryById(String id, String... selectColumns) {
        return TransformUtil.transformObj(resourceRepository.queryById(id), AuthResourceResp.class);
    }

    @Override
    public AuthResourceResp queryOneByField(String field, Object value, String... selectColumns) {
        return TransformUtil.transformObj(this.resourceRepository.queryOneByField(field, value, selectColumns), AuthResourceResp.class);
    }

    @Override
    public List<AuthResourceResp> queryList(SearchRequest<AuthResourceReq> param) {
        return TransformUtil.transformList(resourceRepository.queryByCondition(ConditionUtil.conditionByRequest(param)), AuthResourceResp.class);
    }

    @Override
    public AuthResourceResp queryOne(SearchRequest<AuthResourceReq> param) {
        return TransformUtil.transformObj(resourceRepository.queryOneByCondition(ConditionUtil.conditionByRequest(param)), AuthResourceResp.class);
    }

    @Override
    public long count(SearchRequest<AuthResourceReq> param) {
        return resourceRepository.countByCondition(ConditionUtil.conditionByRequest(param));
    }

    @Override
    public boolean exists(SearchRequest<AuthResourceReq> param) {
        return resourceRepository.exists(ConditionUtil.conditionByRequest(param));
    }

    @Override
    public PageResult<AuthResourceResp> queryByPage(SearchRequest<AuthResourceReq> condition) {
        return TransformUtil.transformPageResult(resourceRepository.queryByPage(ConditionUtil.conditionByRequest(condition)), AuthResourceResp.class);
    }

    @Override
    public BaseResult<?> save(ModifyRequest<AuthResourceReq> param) {
        return ServiceUtil.save(param, resourceRepository, AuthResourceDto.class);
    }

    @Override
    public String insertAndReturnId(AuthResourceReq resourceReq) {
        return ServiceUtil.insertAndReturnId(resourceReq, resourceRepository, AuthResourceDto.class);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<?> delete(ModifyRequest<AuthResourceReq> param) {
        return ServiceUtil.delete(param, resourceRepository, AuthResourceDto.class);
    }

    @Override
    public ResponseResult<List<AuthResourceResp>> authorityResourceList() {
        SearchCondition condition = SCBuilder.<AuthResourceDto>builder().equalsConditions("resourceLevel", 2).build();
        List<AuthResourceResp> list = TransformUtil.transformList(this.resourceRepository.queryByCondition(condition), AuthResourceResp.class);
        return RRBuilder.buildSuccessListBody(list);
    }

    @Override
    public ResourceSecurityLevel checkUriSecurityLevel(String url) {
        AuthResourceDto resource = this.queryOneByField("url", url);
        return ResourceSecurityLevelUtil.getResourceLevel(resource);
    }

    @Override
    public List<AuthResourceResp> getAllPlatformAuthResources() {
        List<String> typeFlags = Arrays.asList("all", "system");
        SearchCondition searchCondition = SCBuilder.<AuthResourceDto>builder().inCondition(AuthResourceDto::getTypeFlag, typeFlags).equalsConditions("resourceLevel", 2).ascFields("sort").condition();
        return TransformUtil.transformList(this.resourceRepository.queryByCondition(searchCondition), AuthResourceResp.class);
    }

    /**
     * 获取平台端资源
     *
     * @param type          平台类型
     * @param defaultStatus 是否是默认 0否 1是
     */
    @Override
    public List<AuthResourceResp> getPlatformAuthResources(String type, Integer defaultStatus) {
        defaultStatus = Optional.ofNullable(defaultStatus).orElse(0);
        SCBuilder<AuthResourceDto> searchConditionBuilder = SCBuilder.<AuthResourceDto>builder().likeConditions("type", type).equalsConditions("resourceLevel", 2).ascFields("sort");
        if (defaultStatus == 1) {
            searchConditionBuilder.likeConditions("defaultType", type);
        }
        return TransformUtil.transformList(this.resourceRepository.queryByCondition(searchConditionBuilder.condition()), AuthResourceResp.class);
    }

    @Override
    public List<AuthResourceResp> getAllSaasAuthResources() {
        SearchCondition searchCondition = SCBuilder.<AuthResourceDto>builder().equalsConditions("saasStatus", 1).equalsConditions("resourceLevel", 2).ascFields("sort").condition();
        return TransformUtil.transformList(this.resourceRepository.queryByCondition(searchCondition), AuthResourceResp.class);
    }

    @Override
    public List<AuthResourceResp> getAllSaasDefaultAuthResources() {
        SearchCondition searchCondition = SCBuilder.<AuthResourceDto>builder().equalsConditions("saasDefault", 1).equalsConditions("saasStatus", 1).equalsConditions("resourceLevel", 2).ascFields("sort").condition();
        return TransformUtil.transformList(this.resourceRepository.queryByCondition(searchCondition), AuthResourceResp.class);
    }

    @Override
    public void saveResourceByBath(List<AuthResourceReq> list) {
        this.resourceRepository.insertBatch(TransformUtil.transformList(list, AuthResourceDto.class));
    }
}
