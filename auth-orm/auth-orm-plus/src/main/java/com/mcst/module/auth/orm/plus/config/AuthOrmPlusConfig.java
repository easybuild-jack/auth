package com.mcst.module.auth.orm.plus.config;

import com.mcst.module.auth.orm.plus.repository.impl.*;
import com.mcst.module.auth.orm.repository.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties
public class AuthOrmPlusConfig {
    @Bean
    @ConditionalOnMissingBean
    public IRoleRepository roleRepository() {
        return new RoleMybatisRepository();
    }

    @Bean
    @ConditionalOnMissingBean
    public IEmployeeRepository employeeRepository() {
        return new EmployeeMybatisRepository();
    }

    @Bean
    @ConditionalOnMissingBean
    public IDepartmentRepository departmentRepository() {
        return new DepartmentMybatisRepository();
    }

    @Bean
    @ConditionalOnMissingBean
    public IRoleResourceRepository roleResourceRepository() {
        return new RoleResourceMybatisRepository();
    }

    @Bean
    @ConditionalOnMissingBean
    public IAuthResourceRepository authResourceRepository() {
        return new AuthResourceMybatisRepository();
    }
}
