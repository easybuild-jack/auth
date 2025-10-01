package com.mcst.module.auth.server.util;

import com.mcst.easyfk.authority.dto.AuthResourceDto;
import com.mcst.easyfk.core.utils.common.EmptyUtil;
import com.mcst.module.auth.api.vo.RoleResourceVO;

import java.util.ArrayList;
import java.util.List;

public class RoleGrantUtil {
    public static void createTreeNode(List<AuthResourceDto> parentResources, List<RoleResourceVO> tree) {
        if (EmptyUtil.isNotEmpty(parentResources)) {
            //提取根资源
            for (AuthResourceDto parentResource : parentResources) {
                if (EmptyUtil.isEmpty(parentResource.getPid())) {
                    RoleResourceVO treeNode = new RoleResourceVO().setName(parentResource.getTitle()).setId(parentResource.getResourceId());
                    tree.add(treeNode);
                }
            }
            for (RoleResourceVO treeNode : tree) {
                List<RoleResourceVO> children = getChildrenNode(treeNode.getId(), parentResources);
                if (EmptyUtil.isNotEmpty(children)) {
                    treeNode.setChildren(children);
                }
            }

        }
    }

    public static List<RoleResourceVO> getChildrenNode(String pid, List<AuthResourceDto> oriResource) {
        if (EmptyUtil.isNotEmpty(oriResource)) {
            List<RoleResourceVO> childrenNode = new ArrayList<>();
            for (AuthResourceDto resource : oriResource) {
                RoleResourceVO treeNode = new RoleResourceVO().setId(resource.getResourceId()).setName(resource.getTitle());
                if (EmptyUtil.isNotEmpty(resource.getPid()) && resource.getPid().equals(pid)) {
                    List<RoleResourceVO> childrenChildrenNode = getChildrenNode(resource.getResourceId(), oriResource);
                    if (EmptyUtil.isNotEmpty(childrenChildrenNode)) {
                        treeNode.setChildren(childrenChildrenNode);
                    }
                    childrenNode.add(treeNode);
                }
            }
            return childrenNode;
        }
        return null;
    }

    public static void checkSelected(List<RoleResourceVO> tree, String resourceIds) {
        if (EmptyUtil.isNotEmpty(tree)) {
            for (RoleResourceVO treeNode : tree) {
                if (EmptyUtil.isNotEmpty(resourceIds)) {
                    for (String resourceId : resourceIds.split(",")) {
                        if (resourceId.equals(treeNode.getId())) {
                            treeNode.setChecked(true);
                        }
                    }
                }
                if (EmptyUtil.isNotEmpty(treeNode.getChildren())) {
                    checkSelected(treeNode.getChildren(), resourceIds);
                }
            }
        }
    }
}
