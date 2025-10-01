package com.mcst.module.auth.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liuyijun
 */
@ConfigurationProperties(prefix = "easyfk.config.pwd")
@Data
public class EmpPwdProperties {

    /**
     * 是否定时更新登录密码
     */
    private Boolean update = Boolean.FALSE;

}


