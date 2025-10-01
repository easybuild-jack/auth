package com.mcst.module.auth.server.repository.impl;

import com.mcst.easyfk.authority.dto.AuthResourceDto;
import com.mcst.easyfk.service.mybatisplus.impl.BaseMyBatisRepositoryImpl;
import com.mcst.module.auth.server.persistence.mapper.AuthResourceMapper;
import com.mcst.module.auth.server.persistence.model.AuthResource;
import com.mcst.module.auth.server.repository.IAuthResourceRepository;

/**
 * @author liuyijun
 */
public class AuthResourceMybatisRepository extends BaseMyBatisRepositoryImpl<AuthResourceMapper, AuthResourceDto, AuthResource, String> implements IAuthResourceRepository {

}
