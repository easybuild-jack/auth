package com.mcst.module.auth.server.config;

import com.mcst.easyfk.authority.IAuthResourceApi;
import com.mcst.module.auth.api.IDepartmentApi;
import com.mcst.module.auth.api.IEmployeeApi;
import com.mcst.module.auth.api.IRoleApi;
import com.mcst.module.auth.server.impl.AuthResourceApiServerImpl;
import com.mcst.module.auth.server.impl.DepartmentApiServerImpl;
import com.mcst.module.auth.server.impl.EmployeeApiServerImpl;
import com.mcst.module.auth.server.impl.RoleApiServerImpl;
import com.mcst.module.auth.server.properties.AuthProperties;
import com.mcst.module.auth.server.properties.EmpPwdProperties;
import com.mcst.module.auth.server.repository.*;
import com.mcst.module.auth.server.repository.impl.*;
import com.mcst.module.auth.server.service.IAuthResourceService;
import com.mcst.module.auth.server.service.IDepartmentService;
import com.mcst.module.auth.server.service.IEmployeeService;
import com.mcst.module.auth.server.service.IRoleService;
import com.mcst.module.auth.server.service.impl.AuthResourceServiceImpl;
import com.mcst.module.auth.server.service.impl.DepartmentServiceImpl;
import com.mcst.module.auth.server.service.impl.EmployeeServiceImpl;
import com.mcst.module.auth.server.service.impl.RoleServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author liuyijun
 */
@AutoConfiguration
@EnableConfigurationProperties({AuthProperties.class, EmpPwdProperties.class})
public class AuthServerConfig {
    @Bean
    @ConditionalOnMissingBean
    public IRoleApi roleApi() {
        return new RoleApiServerImpl();

    }

    @Bean
    @ConditionalOnMissingBean
    public IEmployeeApi employeeApi() {
        return new EmployeeApiServerImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IDepartmentApi departmentApi() {
        return new DepartmentApiServerImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IRoleService roleService() {
        return new RoleServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IRoleRepository roleRepository() {
        return new RoleMybatisRepository();
    }

    @Bean
    @ConditionalOnMissingBean
    public IRoleResourceRepository roleResourceRepository() {
        return new RoleResourceMybatisRepository();
    }

    @Bean
    @ConditionalOnMissingBean
    public IEmployeeService employeeService() {
        return new EmployeeServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IEmployeeRepository employeeRepository() {
        return new EmployeeMybatisRepository();
    }

    @Bean
    @ConditionalOnMissingBean
    public IDepartmentService departmentService() {
        return new DepartmentServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IDepartmentRepository departmentRepository() {
        return new DepartmentMybatisRepository();
    }

    @Bean
    @ConditionalOnMissingBean
    public IAuthResourceRepository authResourceRepository() {
        return new AuthResourceMybatisRepository();
    }


    @Bean
    @ConditionalOnMissingBean
    public IAuthResourceService authResourceService() {
        return new AuthResourceServiceImpl();
    }


    @Bean
    @ConditionalOnMissingBean
    public IAuthResourceApi authResourceApi() {
        return new AuthResourceApiServerImpl();
    }
}


