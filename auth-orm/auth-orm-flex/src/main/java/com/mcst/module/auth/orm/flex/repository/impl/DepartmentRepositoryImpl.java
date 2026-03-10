package com.mcst.module.auth.orm.flex.repository.impl;

import com.mcst.easyfk.service.flex.impl.BaseFlexRepositoryImpl;
import com.mcst.module.auth.api.dto.DepartmentDto;
import com.mcst.module.auth.orm.flex.persistence.mapper.DepartmentMapper;
import com.mcst.module.auth.orm.flex.persistence.model.Department;
import com.mcst.module.auth.orm.repository.IDepartmentRepository;

public class DepartmentRepositoryImpl extends BaseFlexRepositoryImpl<DepartmentMapper, DepartmentDto, Department, String> implements IDepartmentRepository {
}
