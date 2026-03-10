package com.mcst.module.auth.orm.plus.repository.impl;

import com.mcst.easyfk.service.mybatisplus.impl.BaseMyBatisRepositoryImpl;
import com.mcst.module.auth.api.dto.RoleDto;
import com.mcst.module.auth.orm.plus.persistence.mapper.RoleMapper;
import com.mcst.module.auth.orm.plus.persistence.model.Role;
import com.mcst.module.auth.orm.repository.IRoleRepository;

/**
 * <p>
 * 角色 Repository实现类
 * </p>
 *
 * @author liuyijun
 */
public class RoleMybatisRepository extends BaseMyBatisRepositoryImpl<RoleMapper, RoleDto, Role, String> implements IRoleRepository {

}
