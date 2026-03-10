package com.mcst.module.auth.orm.flex.repository.impl;

import com.mcst.easyfk.authority.dto.AuthResourceDto;
import com.mcst.easyfk.service.flex.impl.BaseFlexRepositoryImpl;
import com.mcst.module.auth.orm.flex.persistence.mapper.AuthResourceMapper;
import com.mcst.module.auth.orm.flex.persistence.model.AuthResource;
import com.mcst.module.auth.orm.repository.IAuthResourceRepository;

public class AuthResourceRepositoryImpl extends BaseFlexRepositoryImpl<AuthResourceMapper, AuthResourceDto, AuthResource, String> implements IAuthResourceRepository {
}
