package com.mcst.module.auth.server.util;


import com.mcst.easyfk.authority.constant.ResourceSecurityLevel;
import com.mcst.easyfk.authority.dto.AuthResourceDto;
import com.mcst.easyfk.core.utils.common.EmptyUtil;

/**
 * @author liuyijun
 */
public class ResourceSecurityLevelUtil {

    public static ResourceSecurityLevel getResourceLevel(AuthResourceDto resource) {
        if (EmptyUtil.isNotEmpty(resource)) {
            if (EmptyUtil.isNotEmpty(resource.getResourceLevel())) {
                return switch (resource.getResourceLevel()) {
                    case 2 -> ResourceSecurityLevel.AUTHORITY;
                    case 1 -> ResourceSecurityLevel.LOGIN;
                    default -> ResourceSecurityLevel.UNIMPEDED;
                };
            }
        }
        return ResourceSecurityLevel.UNIMPEDED;
    }
}
