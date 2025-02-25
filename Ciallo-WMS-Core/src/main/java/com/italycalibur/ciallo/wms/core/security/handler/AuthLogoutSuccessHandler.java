package com.italycalibur.ciallo.wms.core.security.handler;

import com.italycalibur.ciallo.wms.core.common.Result;
import com.italycalibur.ciallo.wms.core.utils.JwtUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-14 00:59:56
 * @description: 自定义登出成功处理器
 */
@Component
@RequiredArgsConstructor
public class AuthLogoutSuccessHandler implements LogoutSuccessHandler {
    private final JwtUtils jwtUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {
        String token = jwtUtils.getTokenFromRequest(request);
        if (token != null) {
            jwtUtils.addBlackListToken(token);
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(Result.ok().asJsonString());
    }
}
