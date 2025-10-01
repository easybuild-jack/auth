package com.mcst.module.auth.server.repository.impl;

import com.mcst.easyfk.service.mybatisplus.impl.BaseMyBatisRepositoryImpl;
import com.mcst.module.auth.api.dto.DepartmentDto;
import com.mcst.module.auth.server.persistence.mapper.DepartmentMapper;
import com.mcst.module.auth.server.persistence.model.Department;
import com.mcst.module.auth.server.repository.IDepartmentRepository;

/**
 * <p>
 * 部门 Repository实现类
 * </p>
 *
 * @author liuyijun
 */
public class DepartmentMybatisRepository extends BaseMyBatisRepositoryImpl<DepartmentMapper, DepartmentDto, Department, String> implements IDepartmentRepository {

}