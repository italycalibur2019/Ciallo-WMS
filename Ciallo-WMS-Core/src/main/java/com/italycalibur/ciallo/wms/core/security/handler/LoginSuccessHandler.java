package com.italycalibur.ciallo.wms.core.security.handler;

import com.italycalibur.ciallo.wms.core.common.Result;
import com.italycalibur.ciallo.wms.core.models.entity.User;
import com.italycalibur.ciallo.wms.core.utils.JwtUtils;
import com.italycalibur.ciallo.wms.core.vo.AuthenticationVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-14 00:18:23
 * @description: 自定义登录成功处理器
 */
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        User userDetails = (User) authentication.getPrincipal();

        // 生成JWT令牌
        String accessToken = jwtUtils.generateToken(userDetails);
        String refreshToken = jwtUtils.generateToken(accessToken);

        // 构造统一响应格式
        response.setContentType("application/json;charset=UTF-8");
        AuthenticationVO vo = new AuthenticationVO(userDetails.getUsername(), accessToken, refreshToken);
        response.getWriter().write(Result.ok(vo).asJsonString());
    }
}
