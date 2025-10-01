package com.mcst.module.auth.config;

import com.mcst.module.auth.controller.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liu yijun
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties
public class AuthControllerConfig {

    @Bean
    @ConditionalOnMissingBean
    public DepartmentController departmentController() {
        return new DepartmentController();
    }

    @Bean
    @ConditionalOnMissingBean
    public EmployeeController employeeController() {
        return new EmployeeController();
    }

    @Bean
    @ConditionalOnMissingBean
    public LoginController loginController() {
        return new LoginController();
    }

    @Bean
    @ConditionalOnMissingBean
    public ResourceController resourceController() {
        return new ResourceController();
    }

    @Bean
    @ConditionalOnMissingBean
    public RoleController roleController() {
        return new RoleController();
    }
}
