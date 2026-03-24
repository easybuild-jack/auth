package com.mcst.module.auth.orm.hibernate.repository.impl;

import com.mcst.easyfk.service.hibernate.impl.BaseHibernateRepositoryImpl;
import com.mcst.module.auth.api.dto.RoleResourceDto;
import com.mcst.module.auth.orm.hibernate.persistence.model.RoleResource;
import com.mcst.module.auth.orm.hibernate.persistence.mapper.RoleResourceMapper;
import com.mcst.module.auth.orm.repository.IRoleResourceRepository;

public class RoleResourceRepositoryImpl extends BaseHibernateRepositoryImpl<RoleResourceMapper, RoleResourceDto, RoleResource, String> implements IRoleResourceRepository {
}
