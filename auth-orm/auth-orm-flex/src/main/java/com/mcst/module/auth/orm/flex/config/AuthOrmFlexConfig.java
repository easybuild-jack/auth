package com.mcst.module.auth.orm.flex.config;

import com.mcst.module.auth.orm.flex.repository.impl.*;
import com.mcst.module.auth.orm.repository.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties
public class AuthOrmFlexConfig {
    @Bean
    @ConditionalOnMissingBean
    public IRoleRepository roleRepository() {
        return new RoleRepositoryImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IEmployeeRepository employeeRepository() {
        return new EmployeeRepositoryImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IDepartmentRepository departmentRepository() {
        return new DepartmentRepositoryImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IRoleResourceRepository roleResourceRepository() {
        return new RoleResourceRepositoryImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IAuthResourceRepository authResourceRepository() {
        return new AuthResourceRepositoryImpl();
    }
}
