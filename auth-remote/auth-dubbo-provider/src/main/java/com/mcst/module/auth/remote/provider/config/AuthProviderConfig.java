package com.mcst.module.auth.remote.provider.config;

import com.mcst.module.auth.remote.IDepartmentRemote;
import com.mcst.module.auth.remote.IEmployeeRemote;
import com.mcst.module.auth.remote.IRoleRemote;
import com.mcst.module.auth.remote.provider.impl.AuthResourceRemoteImpl;
import com.mcst.module.auth.remote.provider.impl.DepartmentRemoteImpl;
import com.mcst.module.auth.remote.provider.impl.EmployeeRemoteImpl;
import com.mcst.module.auth.remote.provider.impl.RoleRemoteImpl;
import com.mcst.module.auth.remote.IAuthResourceRemote;
import org.apache.dubbo.config.ServiceConfig;
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
    public ServiceConfig<IEmployeeRemote> employeeRemoteServiceConfig(EmployeeRemoteImpl employeeRemoteImpl) {
        ServiceConfig<IEmployeeRemote> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(IEmployeeRemote.class);
        serviceConfig.setRef(employeeRemoteImpl);
        serviceConfig.export();
        return serviceConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public ServiceConfig<IRoleRemote> roleRemoteServiceConfig(RoleRemoteImpl roleRemoteImpl) {
        ServiceConfig<IRoleRemote> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(IRoleRemote.class);
        serviceConfig.setRef(roleRemoteImpl);
        serviceConfig.export();
        return serviceConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public ServiceConfig<IDepartmentRemote> departmentRemoteServiceConfig(DepartmentRemoteImpl departmentRemoteImpl) {
        ServiceConfig<IDepartmentRemote> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(IDepartmentRemote.class);
        serviceConfig.setRef(departmentRemoteImpl);
        serviceConfig.export();
        return serviceConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public ServiceConfig<IAuthResourceRemote> authResourceRemoteServiceConfig(AuthResourceRemoteImpl authResourceRemoteImpl) {
        ServiceConfig<IAuthResourceRemote> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(IAuthResourceRemote.class);
        serviceConfig.setRef(authResourceRemoteImpl);
        serviceConfig.export();
        return serviceConfig;
    }
}
