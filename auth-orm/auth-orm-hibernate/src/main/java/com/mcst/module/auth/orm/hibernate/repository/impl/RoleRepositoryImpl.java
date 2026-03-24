package com.mcst.module.auth.orm.hibernate.repository.impl;

import com.mcst.easyfk.service.hibernate.impl.BaseHibernateRepositoryImpl;
import com.mcst.module.auth.api.dto.RoleDto;
import com.mcst.module.auth.orm.hibernate.persistence.model.Role;
import com.mcst.module.auth.orm.hibernate.persistence.mapper.RoleMapper;
import com.mcst.module.auth.orm.repository.IRoleRepository;

public class RoleRepositoryImpl extends BaseHibernateRepositoryImpl<RoleMapper, RoleDto, Role, String> implements IRoleRepository {
}
