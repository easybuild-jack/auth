package com.mcst.module.auth.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liu yijun
 */
@Data
@Accessors(chain = true)
public class RoleGrantVO implements Serializable {

    @NotBlank(message = "角色ID不可为空")
    @Schema(description = "角色ID", name = "id")
    private String id;
    @NotBlank(message = "资源IDS不可为空")
    @Schema(description = "资源IDS", name = "resourceIds")
    private String resourceIds;

    @Schema(description = "业务类型", name = "type")
    private String type;
}
