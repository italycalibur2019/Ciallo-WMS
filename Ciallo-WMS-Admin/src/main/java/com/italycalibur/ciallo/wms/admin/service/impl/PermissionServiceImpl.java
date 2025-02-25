package com.italycalibur.ciallo.wms.admin.service.impl;

import com.italycalibur.ciallo.wms.admin.service.PermissionService;
import com.italycalibur.ciallo.wms.admin.vo.DeptVO;
import com.italycalibur.ciallo.wms.admin.vo.PermissionVO;
import com.italycalibur.ciallo.wms.core.models.entity.Dept;
import com.italycalibur.ciallo.wms.core.models.entity.Permission;
import com.italycalibur.ciallo.wms.core.service.system.impl.BasePermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 权限表 服务实现类
 * @author dhr
 * @date 2025-02-25 08:42:51
 * @version 1.0
 */
@Service
public class PermissionServiceImpl extends BasePermissionService implements PermissionService {
    @Override
    public List<PermissionVO> listAll() {
        List<Permission> list = this.list();
        return list.stream()
                .map(perm -> {
                    PermissionVO vo = new PermissionVO();
                    BeanUtils.copyProperties(perm, vo);
                    Permission parentPerm = this.getById(perm.getParentId());
                    if (parentPerm != null) {
                        vo.setParentMenuName(parentPerm.getPermName());
                    }
                    vo.setMenuTypeDesc(switch (perm.getMenuType()) {
                        case BUTTON:
                            yield "按钮";
                        case MAIN_MENU:
                            yield "主菜单";
                        case SUB_MENU:
                            yield "子菜单";
                        case CATALOG:
                            yield "目录";
                    });
                    return vo;
                })
                .toList();
    }

    @Override
    public PermissionVO findPermById(Long id) {
        Permission perm = this.getById(id);
        if (perm != null) {
            PermissionVO vo = new PermissionVO();
            BeanUtils.copyProperties(perm, vo);
            Permission parentPerm = this.getById(perm.getParentId());
            if (parentPerm != null) {
                vo.setParentMenuName(parentPerm.getPermName());
            }
            vo.setMenuTypeDesc(switch (perm.getMenuType()) {
                case BUTTON:
                    yield "按钮";
                case MAIN_MENU:
                    yield "主菜单";
                case SUB_MENU:
                    yield "子菜单";
                case CATALOG:
                    yield "目录";
            });
            return vo;
        }
        return null;
    }
}
