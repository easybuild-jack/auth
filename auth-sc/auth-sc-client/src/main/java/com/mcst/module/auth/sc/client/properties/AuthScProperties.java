package com.mcst.module.auth.sc.client.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liuyijun
 */
@ConfigurationProperties(prefix = "easyfk.config.remote.auth")
@Data
public class AuthScProperties {
    /**
     * Auth模块的ServiceId配置
     */
    private String serviceId;
    /**
     * path 前缀地址，如：/remote 或/api 等，默认/remote
     */
    private String basePath;
}