package com.italycalibur.ciallo.wms.core.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 暴露Spring Security的认证接口给knife4j
 * @author dhr
 * @date 2025-02-24 14:38:52
 * @version 1.0
 */
@Tag(name = "认证相关接口")
@RestController
public class LoginController {
    @PostMapping("/auth/login")
    public void login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {}

    @GetMapping("/auth/logout")
    public void logout() {}
}
