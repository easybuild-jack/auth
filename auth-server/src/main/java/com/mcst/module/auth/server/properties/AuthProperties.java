package com.mcst.module.auth.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liuyijun
 */
@ConfigurationProperties(prefix = "easyfk.config.auth")
@Data
public class AuthProperties {

    /**
     * 是否开启员工和角色的初始化
     */
    private Boolean init = Boolean.FALSE;

}


