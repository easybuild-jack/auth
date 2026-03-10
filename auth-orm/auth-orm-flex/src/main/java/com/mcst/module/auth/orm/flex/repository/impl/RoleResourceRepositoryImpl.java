package com.mcst.module.auth.orm.flex.repository.impl;

import com.mcst.easyfk.service.flex.impl.BaseFlexRepositoryImpl;
import com.mcst.module.auth.api.dto.RoleResourceDto;
import com.mcst.module.auth.orm.flex.persistence.mapper.RoleResourceMapper;
import com.mcst.module.auth.orm.flex.persistence.model.RoleResource;
import com.mcst.module.auth.orm.repository.IRoleResourceRepository;

public class RoleResourceRepositoryImpl extends BaseFlexRepositoryImpl<RoleResourceMapper, RoleResourceDto, RoleResource, String> implements IRoleResourceRepository {
}
