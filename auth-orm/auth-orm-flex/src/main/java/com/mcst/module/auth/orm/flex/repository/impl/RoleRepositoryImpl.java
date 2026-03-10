package com.mcst.module.auth.orm.flex.repository.impl;

import com.mcst.easyfk.service.flex.impl.BaseFlexRepositoryImpl;
import com.mcst.module.auth.api.dto.RoleDto;
import com.mcst.module.auth.orm.flex.persistence.mapper.RoleMapper;
import com.mcst.module.auth.orm.flex.persistence.model.Role;
import com.mcst.module.auth.orm.repository.IRoleRepository;

public class RoleRepositoryImpl extends BaseFlexRepositoryImpl<RoleMapper, RoleDto, Role, String> implements IRoleRepository {
}
