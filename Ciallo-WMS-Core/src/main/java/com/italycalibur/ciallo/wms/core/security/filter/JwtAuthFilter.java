package com.italycalibur.ciallo.wms.core.security.filter;

import com.italycalibur.ciallo.wms.core.security.UserDetailsServiceImpl;
import com.italycalibur.ciallo.wms.core.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-23 16:27:45
 * @description: TODO
 */
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    // JWT工具类
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 1. 从请求头提取Token
            String token = jwtUtils.getTokenFromRequest(request);
            if (token != null && jwtUtils.validateToken(token)) {

                // 2. 解析用户名
                String username = jwtUtils.getUsernameFromToken(token);

                // 3. 加载用户信息
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // 4. 构建认证对象
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // 5. 存储到SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            // Token无效时直接返回401错误
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
