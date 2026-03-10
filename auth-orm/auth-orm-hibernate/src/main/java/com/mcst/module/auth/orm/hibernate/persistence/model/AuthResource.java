package com.mcst.module.auth.orm.hibernate.persistence.model;

import com.mcst.easyfk.authority.dto.AuthResourceDto;
import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.service.hibernate.persistence.BaseHibernateEntity;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.UuidGenerator;

/**
 * @author liuyijun
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "auth_resource")
@AutoMapper(target = AuthResourceDto.class)
public class AuthResource extends BaseHibernateEntity {

    @PrimaryKey
    @Id
    @UuidGenerator
    @Column(name = "resource_id")
    private String resourceId;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * icon
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 资源路径
     */
    @Column(name = "url")
    private String url;
    /**
     * 父资源ID
     */
    @Column(name = "pid")
    private String pid;
    /**
     * 父资源名称
     */
    @Column(name = "pname")
    private String pname;
    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 资源类型，menu 菜单，action 功能
     */
    @Column(name = "category")
    private String category;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 前端路由地址
     */
    @Column(name = "path")
    private String path;
    /**
     * 功能代码
     */
    @Column(name = "action_code")
    private String actionCode;

    /**
     * 资源等级，0：无需验证，1：需要登录，2：需要权限
     */
    @Column(name = "resource_level")
    private Integer resourceLevel;

    /**
     * 无权限时的提示
     */
    @Column(name = "un_pass_msg")
    private String unPassMsg;

    /**
     * 根目录标识，0：否，1：是
     */
    @Column(name = "root_status")
    private Integer rootStatus;

    /**
     * 分组
     */
    @Column(name = "group_code")
    private String groupCode;

    /**
     * 类型标识 例如：system等，表示该资源属于系统/企业等，用于处理各个平台端资源归类
     */
    @Column(name = "type_flag")
    private String typeFlag;

    /**
     * 默认标识 system 等，表示该资源是否是系统/企业等各平台端的默认资源
     */
    @Column(name = "default_flag")
    private String defaultFlag;

}
