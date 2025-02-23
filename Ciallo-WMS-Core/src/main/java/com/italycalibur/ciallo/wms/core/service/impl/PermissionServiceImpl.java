package com.italycalibur.ciallo.wms.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.italycalibur.ciallo.wms.core.models.entity.Permission;
import com.italycalibur.ciallo.wms.core.models.mapper.PermissionMapper;
import com.italycalibur.ciallo.wms.core.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
@Slf4j
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public boolean hasPerm(String permKey) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            log.warn("当前用户未登录");
            return false;
        }else {
            log.info("当前用户：{}", auth.getName());
            log.info("当前用户权限：{}", auth.getAuthorities());
            return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(permKey));
        }
    }
}
