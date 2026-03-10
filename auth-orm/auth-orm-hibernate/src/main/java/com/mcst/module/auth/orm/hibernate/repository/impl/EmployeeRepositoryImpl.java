package com.mcst.module.auth.orm.hibernate.repository.impl;

import com.mcst.easyfk.service.hibernate.impl.BaseHibernateRepositoryImpl;
import com.mcst.module.auth.api.dto.EmployeeDto;
import com.mcst.module.auth.orm.hibernate.persistence.model.Employee;
import com.mcst.module.auth.orm.hibernate.persistence.repository.EmployeeJpaRepository;
import com.mcst.module.auth.orm.repository.IEmployeeRepository;

public class EmployeeRepositoryImpl extends BaseHibernateRepositoryImpl<EmployeeJpaRepository, EmployeeDto, Employee, String> implements IEmployeeRepository {
}
