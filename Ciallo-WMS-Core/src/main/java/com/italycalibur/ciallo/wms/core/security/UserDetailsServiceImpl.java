package com.italycalibur.ciallo.wms.core.security;

import com.italycalibur.ciallo.wms.core.models.entity.Role;
import com.italycalibur.ciallo.wms.core.models.entity.User;
import com.italycalibur.ciallo.wms.core.models.mapper.RoleMapper;
import com.italycalibur.ciallo.wms.core.models.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-23 15:59:02
 * @description: 自定义 UserDetailsService
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userMapper.findByUsernameWithRoles(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }else {
            for (Role role : user.getRoles()) {
                Role realRole = roleMapper.findByRoleNameWithPermissions(role.getRoleName());
                role.setPermissions(realRole.getPermissions());
            }
        }
        return user;
    }
}
