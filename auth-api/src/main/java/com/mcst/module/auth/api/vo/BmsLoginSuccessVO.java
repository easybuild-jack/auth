package com.mcst.module.auth.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liuyijun BMS管理系统登录成功后返回的数据封装对象
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@Schema(description = "登录成功后返回前端的数据")
public class BmsLoginSuccessVO implements Serializable {

    @Schema(description = "Token信息", name = "accessToken")
    private String accessToken;

    @Schema(description = "当前登录用户头像信息", name = "headImage")
    private String headImage;

}
