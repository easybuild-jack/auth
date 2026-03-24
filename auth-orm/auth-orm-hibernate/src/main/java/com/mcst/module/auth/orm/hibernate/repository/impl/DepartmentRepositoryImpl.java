package com.mcst.module.auth.orm.hibernate.repository.impl;

import com.mcst.easyfk.service.hibernate.impl.BaseHibernateRepositoryImpl;
import com.mcst.module.auth.api.dto.DepartmentDto;
import com.mcst.module.auth.orm.hibernate.persistence.model.Department;
import com.mcst.module.auth.orm.hibernate.persistence.mapper.DepartmentMapper;
import com.mcst.module.auth.orm.repository.IDepartmentRepository;

public class DepartmentRepositoryImpl extends BaseHibernateRepositoryImpl<DepartmentMapper, DepartmentDto, Department, String> implements IDepartmentRepository {
}
