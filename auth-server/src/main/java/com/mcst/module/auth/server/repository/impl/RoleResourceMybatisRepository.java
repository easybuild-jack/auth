package com.mcst.module.auth.server.repository.impl;

import com.mcst.easyfk.service.mybatisplus.impl.BaseMyBatisRepositoryImpl;
import com.mcst.module.auth.api.dto.RoleResourceDto;
import com.mcst.module.auth.server.persistence.mapper.RoleResourceMapper;
import com.mcst.module.auth.server.persistence.model.RoleResource;
import com.mcst.module.auth.server.repository.IRoleResourceRepository;

/**
 * <p>
 * 角色资源 Repository实现类
 * </p>
 *
 * @author liuyijun
 */
public class RoleResourceMybatisRepository extends BaseMyBatisRepositoryImpl<RoleResourceMapper, RoleResourceDto, RoleResource, String> implements IRoleResourceRepository {

}