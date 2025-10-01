package com.mcst.module.auth.sc.client.config;

import com.mcst.module.auth.api.IDepartmentApi;
import com.mcst.module.auth.api.IEmployeeApi;
import com.mcst.module.auth.api.IRoleApi;
import com.mcst.module.auth.sc.client.impl.DepartmentApiScImpl;
import com.mcst.module.auth.sc.client.impl.EmployeeApiScImpl;
import com.mcst.module.auth.sc.client.impl.RoleApiScImpl;
import com.mcst.module.auth.sc.client.properties.AuthScProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuyijun
 */
@Configuration
@EnableConfigurationProperties(AuthScProperties.class)
public class AuthScConfig {

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

}