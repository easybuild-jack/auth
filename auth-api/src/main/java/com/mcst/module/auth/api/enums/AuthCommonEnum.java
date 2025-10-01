package com.mcst.module.auth.api.enums;

/**
 * @author LiuYiJun
 */
public enum AuthCommonEnum {

    /**
     * 平台用户
     */
    Platform("platform"),
    /**
     * Saas用户
     */
    Saas("saas"),
    /**
     * 工作厂区字段
     */
    WorkArea("workArea"),
    /**
     * 系统管理员
     */
    Admin("系统管理员");

    private String value;

    AuthCommonEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}