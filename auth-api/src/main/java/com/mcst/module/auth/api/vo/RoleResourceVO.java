package com.mcst.module.auth.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author liu yijun
 */
@Data
@Accessors(chain = true)
public class RoleResourceVO implements Serializable {

    @Schema(description = "子元素", name = "children")
    private List<RoleResourceVO> children;
    @Schema(description = "名称", name = "name")
    private String name;
    @Schema(description = "ID", name = "id")
    private String id;
    @Schema(description = "是否拥有权限", name = "checked")
    private Boolean checked;
}
