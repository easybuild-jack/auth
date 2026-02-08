package com.mcst.module.resource.sc.client.config;

import com.mcst.easyfk.authority.IAuthResourceApi;
import com.mcst.module.resource.sc.client.impl.AuthResourceApiScImpl;
import com.mcst.module.resource.sc.client.properties.ResourceScProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author liuyijun
 */
@AutoConfiguration
@EnableConfigurationProperties(ResourceScProperties.class)
public class ResourceScConfig {

    @Bean
    @ConditionalOnMissingBean
    public IAuthResourceApi authResourceApi() {
        return new AuthResourceApiScImpl();
    }
}