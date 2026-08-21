package com.mcst.module.auth.test;

import com.mcst.easyfk.authority.constant.ResourceSecurityLevel;
import com.mcst.easyfk.authority.dto.AuthResourceDto;
import com.mcst.module.auth.server.util.ResourceSecurityLevelUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author liu yijun
 * @version 1.0
 */
class TestMsg {

    @Test
    void resourceLevel2ShouldReturnAuthority() {
        AuthResourceDto resource = new AuthResourceDto().setResourceLevel(2);
        assertEquals(ResourceSecurityLevel.AUTHORITY, ResourceSecurityLevelUtil.getResourceLevel(resource));
    }

    @Test
    void resourceLevel1ShouldReturnLogin() {
        AuthResourceDto resource = new AuthResourceDto().setResourceLevel(1);
        assertEquals(ResourceSecurityLevel.LOGIN, ResourceSecurityLevelUtil.getResourceLevel(resource));
    }

    @Test
    void resourceLevel0ShouldReturnUnimpeded() {
        AuthResourceDto resource = new AuthResourceDto().setResourceLevel(0);
        assertEquals(ResourceSecurityLevel.UNIMPEDED, ResourceSecurityLevelUtil.getResourceLevel(resource));
    }

    @Test
    void nullResourceShouldReturnUnimpeded() {
        assertEquals(ResourceSecurityLevel.UNIMPEDED, ResourceSecurityLevelUtil.getResourceLevel(null));
    }
}
