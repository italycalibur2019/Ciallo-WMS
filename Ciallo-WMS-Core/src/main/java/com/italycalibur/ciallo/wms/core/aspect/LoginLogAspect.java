package com.italycalibur.ciallo.wms.core.aspect;

import com.italycalibur.ciallo.wms.core.models.entity.LoginLog;
import com.italycalibur.ciallo.wms.core.models.entity.User;
import com.italycalibur.ciallo.wms.core.service.log.ILoginLogService;
import com.italycalibur.ciallo.wms.core.utils.IPUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 日志切面，用于记录登录日志
 * @author dhr
 * @date 2025-02-27 08:27:55
 * @version 1.0
 */
@Aspect
@Component
@RequiredArgsConstructor
public class LoginLogAspect {
    private final ILoginLogService logService; // 假设存在日志服务

    @Around("execution(* com.italycalibur.ciallo.wms.core.security.handler.LoginSuccessHandler.onAuthenticationSuccess(..))")
    public Object logLoginInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        Authentication authentication = (Authentication) joinPoint.getArgs()[2];

        User user = (User) authentication.getPrincipal();

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed(); // 执行原方法
        long duration = System.currentTimeMillis() - startTime;

        // 记录登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(user.getUsername());
        loginLog.setIp(IPUtils.getIpAddr(request));
        loginLog.setLoginTime(new Date());
        loginLog.setDuration(duration);
        logService.save(loginLog);

        return result;
    }
}
