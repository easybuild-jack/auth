package com.mcst.module.auth.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liuyijun
 */
@Data
@Accessors(chain = true)
public class BmsLoginRequest implements Serializable {

    private String loginName;

    private String password;

}
