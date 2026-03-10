package com.mcst.module.auth.orm.plus.repository.impl;

import com.mcst.easyfk.authority.dto.AuthResourceDto;
import com.mcst.easyfk.service.mybatisplus.impl.BaseMyBatisRepositoryImpl;
import com.mcst.module.auth.orm.plus.persistence.mapper.AuthResourceMapper;
import com.mcst.module.auth.orm.plus.persistence.model.AuthResource;
import com.mcst.module.auth.orm.repository.IAuthResourceRepository;

/**
 * @author liuyijun
 */
public class AuthResourceMybatisRepository extends BaseMyBatisRepositoryImpl<AuthResourceMapper, AuthResourceDto, AuthResource, String> implements IAuthResourceRepository {

}
