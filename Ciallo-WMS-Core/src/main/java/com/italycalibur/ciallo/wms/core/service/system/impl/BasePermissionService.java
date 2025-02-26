package com.italycalibur.ciallo.wms.core.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.italycalibur.ciallo.wms.core.dto.MenuTree;
import com.italycalibur.ciallo.wms.core.models.entity.Permission;
import com.italycalibur.ciallo.wms.core.models.mapper.PermissionMapper;
import com.italycalibur.ciallo.wms.core.service.system.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限表 服务实现类基类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
@Slf4j
@Service
public abstract class BasePermissionService extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public boolean hasPerm(String permKey) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            log.warn("当前用户未登录");
            return false;
        }else {
            log.debug("当前用户：{}", auth.getName());
            log.debug("当前用户权限：{}", auth.getAuthorities());
            return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(permKey));
        }
    }

    @Override
    public List<MenuTree> menuTree() {
        // 获取所有权限数据
        List<Permission> permissionList = this.list();

        // 创建ID->节点的映射表（使用LinkedHashMap保持顺序）
        Map<Long, MenuTree> nodeMap = permissionList.stream()
                .filter(perm -> perm.getId() != 0L)
                .collect(Collectors.toMap(
                        Permission::getId,
                        perm -> {
                            MenuTree node = new MenuTree();
                            BeanUtils.copyProperties(perm, node);
                            return node;
                        },
                        (existing, replacement) -> existing,  // 处理重复ID冲突
                        LinkedHashMap::new
                ));

        // 构建树形结构
        List<MenuTree> roots = new ArrayList<>();
        nodeMap.forEach((id, node) -> {
            Long parentId = node.getParentId();
            if (!nodeMap.containsKey(parentId)) {
                roots.add(node);
            } else {
                MenuTree parent = nodeMap.get(parentId);
                if (parent != null) {
                    parent.getChildren().add(node);
                }
            }
        });

        return roots;
    }
}
