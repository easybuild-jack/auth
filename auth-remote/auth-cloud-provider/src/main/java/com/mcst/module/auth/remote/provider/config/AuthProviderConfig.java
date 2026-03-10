package com.mcst.module.auth.remote.provider.config;

import com.mcst.module.auth.remote.IDepartmentRemote;
import com.mcst.module.auth.remote.IEmployeeRemote;
import com.mcst.module.auth.remote.IRoleRemote;
import com.mcst.module.auth.remote.provider.impl.AuthResourceRemoteImpl;
import com.mcst.module.auth.remote.provider.impl.DepartmentRemoteImpl;
import com.mcst.module.auth.remote.provider.impl.EmployeeRemoteImpl;
import com.mcst.module.auth.remote.provider.impl.RoleRemoteImpl;
import com.mcst.module.auth.remote.IAuthResourceRemote;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author liu yijun
 */
@AutoConfiguration
public class AuthProviderConfig {

    @Bean
    @ConditionalOnMissingBean
    public IDepartmentRemote departmentRemote() {
        return new DepartmentRemoteImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IEmployeeRemote employeeRemote() {
        return new EmployeeRemoteImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IRoleRemote roleRemote() {
        return new RoleRemoteImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IAuthResourceRemote authResourceRemote() {
        return new AuthResourceRemoteImpl();
    }
}
