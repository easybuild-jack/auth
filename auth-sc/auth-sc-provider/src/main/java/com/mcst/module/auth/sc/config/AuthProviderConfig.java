package com.mcst.module.auth.sc.config;

import com.mcst.module.auth.sc.client.api.remote.IDepartmentScRemote;
import com.mcst.module.auth.sc.client.api.remote.IEmployeeScRemote;
import com.mcst.module.auth.sc.client.api.remote.IRoleScRemote;
import com.mcst.module.auth.sc.provider.AuthResourceRemoteImpl;
import com.mcst.module.auth.sc.provider.DepartmentScRemoteImpl;
import com.mcst.module.auth.sc.provider.EmployeeScRemoteImpl;
import com.mcst.module.auth.sc.provider.RoleScRemoteImpl;
import com.mcst.module.resource.sc.client.api.remote.IAuthResourceRemote;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liu yijun
 * @version 1.0
 */
@Configuration
public class AuthProviderConfig {

    @Bean
    @ConditionalOnMissingBean
    public IDepartmentScRemote departmentScRemote() {
        return new DepartmentScRemoteImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IEmployeeScRemote employeeScRemote() {
        return new EmployeeScRemoteImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IRoleScRemote roleScRemote() {
        return new RoleScRemoteImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IAuthResourceRemote authResourceRemote() {
        return new AuthResourceRemoteImpl();
    }
}
