package com.mcst.module.auth.api.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.mcst.easyfk.core.dto.request.BasicParam;

/**
* <p>
* 角色数据参数对象
* </p>
*
* @author liu yijun
* @since 2024-12-31
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description="角色数据参数对象")
public class RoleParam extends BasicParam {



    /**
    * 角色ID
    */
    @Schema(description= "角色ID")
    private String roleId;

    /**
    * 名称
    */
    @Schema(description= "名称")
    private String roleName;

    /**
    * 禁用状态 0可用，1禁用
    */
    @Schema(description= "禁用状态 0可用，1禁用")
    private Integer forbiddenFlag;

    /**
    * 备注信息
    */
    @Schema(description= "备注信息")
    private String remark;

    /**
    * 上级ID
    */
    @Schema(description= "上级ID")
    private String parentId;

    /**
    * 上级名称
    */
    @Schema(description= "上级名称")
    private String parentName;

    /**
    * 超级管理员标识，0：非，1：是
    */
    @Schema(description= "超级管理员标识，0：非，1：是")
    private Integer supperStatus;

    /**
    * 平台类型
    */
    @Schema(description= "平台类型")
    private String type;

    /**
    * 所属SaasID
    */
    @Schema(description= "所属SaasID")
    private String saasId;

    /**
    * 所属Saas名称
    */
    @Schema(description= "所属Saas名称")
    private String saasName;

    /**
    * 业务类型
    */
    @Schema(description= "业务类型")
    private String bizType;













}
