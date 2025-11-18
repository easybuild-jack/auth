package com.mcst.module.auth.server.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.service.mybatisplus.persistence.BaseMyBatisPlusEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author liuyijun
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("auth_resource")
public class AuthResource extends BaseMyBatisPlusEntity<AuthResource> {

    @TableId(type = IdType.ASSIGN_UUID)
    @PrimaryKey
    private String resourceId;

    /**
     * 名称
     */
    private String name;

    /**
     * icon
     */
    private String icon;

    /**
     * 资源路径
     */
    private String url;
    /**
     * 父资源ID
     */
    private String pid;
    /**
     * 父资源名称
     */
    private String pname;
    /**
     * 标题
     */
    private String title;

    /**
     * 资源类型，menu 菜单，action 功能
     */
    private String category;
    /**
     * 备注
     */
    private String remark;
    /**
     * 排序
     */
    private Integer sort;

    /**
     * 前端路由地址
     */
    private String path;
    /**
     * 功能代码
     */
    private String actionCode;

    /**
     * 资源等级，0：无需验证，1：需要登录，2：需要权限
     */
    private Integer resourceLevel;

    /**
     * 无权限时的提示
     */
    private String unPassMsg;

    /**
     * 根目录标识，0：否，1：是
     */
    private Integer rootStatus;

    /**
     * 分组
     */
    private String groupCode;

    /**
     * 类型标识 例如：system等，表示该资源属于系统/企业等，用于处理各个平台端资源归类
     */
    private String typeFlag;

    /**
     * 默认标识 system 等，表示该资源是否是系统/企业等各平台端的默认资源
     */
    private String defaultFlag;

}
