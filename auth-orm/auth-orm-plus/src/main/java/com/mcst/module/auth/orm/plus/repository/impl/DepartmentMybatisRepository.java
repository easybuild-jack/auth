package com.mcst.module.auth.orm.plus.repository.impl;

import com.mcst.easyfk.service.mybatisplus.impl.BaseMyBatisRepositoryImpl;
import com.mcst.module.auth.api.dto.DepartmentDto;
import com.mcst.module.auth.orm.plus.persistence.mapper.DepartmentMapper;
import com.mcst.module.auth.orm.plus.persistence.model.Department;
import com.mcst.module.auth.orm.repository.IDepartmentRepository;

/**
 * <p>
 * 部门 Repository实现类
 * </p>
 *
 * @author liuyijun
 */
public class DepartmentMybatisRepository extends BaseMyBatisRepositoryImpl<DepartmentMapper, DepartmentDto, Department, String> implements IDepartmentRepository {

}
