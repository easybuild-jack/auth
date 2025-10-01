package com.mcst.module.auth.server.enums;

/**
 * @author liu yijun
 * @version 1.0
 */
public enum AuthEnum {
    I18N_PATH("i18n/authMessage");

    private final String code;

    private AuthEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
