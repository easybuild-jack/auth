package com.mcst.module.auth.remote.config;

import com.mcst.easyfk.authority.IAuthResourceApi;
import com.mcst.module.auth.api.IDepartmentApi;
import com.mcst.module.auth.api.IEmployeeApi;
import com.mcst.module.auth.api.IRoleApi;
import com.mcst.module.auth.remote.impl.AuthResourceApiScImpl;
import com.mcst.module.auth.remote.impl.DepartmentApiScImpl;
import com.mcst.module.auth.remote.impl.EmployeeApiScImpl;
import com.mcst.module.auth.remote.impl.RoleApiScImpl;
import com.mcst.module.auth.remote.properties.AuthApiProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author liuyijun
 */
@AutoConfiguration
@EnableConfigurationProperties(AuthApiProperties.class)
public class AuthApiConfig {

    @Bean
    @ConditionalOnMissingBean
    public IRoleApi roleApi() {
        return new RoleApiScImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IEmployeeApi employeeApi() {
        return new EmployeeApiScImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IDepartmentApi departmentApi() {
        return new DepartmentApiScImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IAuthResourceApi authResourceApi() {
        return new AuthResourceApiScImpl();
    }
}
