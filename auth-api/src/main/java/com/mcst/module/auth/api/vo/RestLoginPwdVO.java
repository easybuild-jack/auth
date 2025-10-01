package com.mcst.module.auth.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liu yijun
 * @version 1.0
 */

@Data
@Accessors(chain = true)
public class RestLoginPwdVO implements Serializable {

    @NotBlank
    @Schema(description = "账号信息", name = "account")
    private String account;

    @NotBlank
    @Schema(description = "原始密码，密文", name = "oldPwd")
    private String oldPwd;
    @NotBlank
    @Schema(description = "新密码，密文", name = "newPwd")
    private String newPwd;
}
