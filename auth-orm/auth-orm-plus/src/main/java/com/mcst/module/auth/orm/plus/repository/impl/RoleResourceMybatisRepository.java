package com.mcst.module.auth.orm.plus.repository.impl;

import com.mcst.easyfk.service.mybatisplus.impl.BaseMyBatisRepositoryImpl;
import com.mcst.module.auth.api.dto.RoleResourceDto;
import com.mcst.module.auth.orm.plus.persistence.mapper.RoleResourceMapper;
import com.mcst.module.auth.orm.plus.persistence.model.RoleResource;
import com.mcst.module.auth.orm.repository.IRoleResourceRepository;

/**
 * <p>
 * 角色资源 Repository实现类
 * </p>
 *
 * @author liuyijun
 */
public class RoleResourceMybatisRepository extends BaseMyBatisRepositoryImpl<RoleResourceMapper, RoleResourceDto, RoleResource, String> implements IRoleResourceRepository {

}
