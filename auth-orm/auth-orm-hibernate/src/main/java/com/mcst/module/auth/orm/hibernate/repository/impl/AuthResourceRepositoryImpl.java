package com.mcst.module.auth.orm.hibernate.repository.impl;

import com.mcst.easyfk.authority.dto.AuthResourceDto;
import com.mcst.easyfk.service.hibernate.impl.BaseHibernateRepositoryImpl;
import com.mcst.module.auth.orm.hibernate.persistence.model.AuthResource;
import com.mcst.module.auth.orm.hibernate.persistence.mapper.AuthResourceMapper;
import com.mcst.module.auth.orm.repository.IAuthResourceRepository;

public class AuthResourceRepositoryImpl extends BaseHibernateRepositoryImpl<AuthResourceMapper, AuthResourceDto, AuthResource, String> implements IAuthResourceRepository {
}
