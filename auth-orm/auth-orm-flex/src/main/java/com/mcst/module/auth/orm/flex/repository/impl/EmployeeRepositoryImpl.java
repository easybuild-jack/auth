package com.mcst.module.auth.orm.flex.repository.impl;

import com.mcst.easyfk.service.flex.impl.BaseFlexRepositoryImpl;
import com.mcst.module.auth.api.dto.EmployeeDto;
import com.mcst.module.auth.orm.flex.persistence.mapper.EmployeeMapper;
import com.mcst.module.auth.orm.flex.persistence.model.Employee;
import com.mcst.module.auth.orm.repository.IEmployeeRepository;

public class EmployeeRepositoryImpl extends BaseFlexRepositoryImpl<EmployeeMapper, EmployeeDto, Employee, String> implements IEmployeeRepository {
}
