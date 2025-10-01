package com.mcst.module.auth.server.repository.impl;

import com.mcst.easyfk.service.mybatisplus.impl.BaseMyBatisRepositoryImpl;
import com.mcst.module.auth.api.dto.EmployeeDto;
import com.mcst.module.auth.server.persistence.mapper.EmployeeMapper;
import com.mcst.module.auth.server.persistence.model.Employee;
import com.mcst.module.auth.server.repository.IEmployeeRepository;

/**
 * <p>
 * 员工 Repository实现类
 * </p>
 *
 * @author liuyijun
 */
public class EmployeeMybatisRepository extends BaseMyBatisRepositoryImpl<EmployeeMapper, EmployeeDto, Employee, String> implements IEmployeeRepository {

}