package com.mcst.module.auth.remote.config;

import com.mcst.easyfk.authority.IAuthResourceApi;
import com.mcst.module.auth.api.IDepartmentApi;
import com.mcst.module.auth.api.IEmployeeApi;
import com.mcst.module.auth.api.IRoleApi;
import com.mcst.module.auth.remote.impl.AuthResourceApiDubboImpl;
import com.mcst.module.auth.remote.impl.DepartmentApiDubboImpl;
import com.mcst.module.auth.remote.impl.EmployeeApiDubboImpl;
import com.mcst.module.auth.remote.impl.RoleApiDubboImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author liuyijun
 */
@AutoConfiguration
public class AuthApiConfig {

    @Bean
    @ConditionalOnMissingBean
    public IRoleApi roleApi() {
        return new RoleApiDubboImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IEmployeeApi employeeApi() {
        return new EmployeeApiDubboImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IDepartmentApi departmentApi() {
        return new DepartmentApiDubboImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IAuthResourceApi authResourceApi() {
        return new AuthResourceApiDubboImpl();
    }
}
