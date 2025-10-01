package com.mcst.module.auth.server.repository.impl;

import com.mcst.easyfk.service.mybatisplus.impl.BaseMyBatisRepositoryImpl;
import com.mcst.module.auth.api.dto.RoleDto;
import com.mcst.module.auth.server.persistence.mapper.RoleMapper;
import com.mcst.module.auth.server.persistence.model.Role;
import com.mcst.module.auth.server.repository.IRoleRepository;

/**
 * <p>
 * 角色 Repository实现类
 * </p>
 *
 * @author liuyijun
 */
public class RoleMybatisRepository extends BaseMyBatisRepositoryImpl<RoleMapper, RoleDto, Role, String> implements IRoleRepository {

}