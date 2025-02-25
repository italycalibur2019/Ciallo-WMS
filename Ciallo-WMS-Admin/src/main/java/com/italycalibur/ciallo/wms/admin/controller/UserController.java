package com.italycalibur.ciallo.wms.admin.controller;

import com.italycalibur.ciallo.wms.admin.dto.UserDTO;
import com.italycalibur.ciallo.wms.core.common.Result;
import com.italycalibur.ciallo.wms.core.models.entity.User;
import com.italycalibur.ciallo.wms.admin.service.UserService;
import com.italycalibur.ciallo.wms.core.utils.MD5Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 19:44:57
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/system/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "获取用户列表")
    @GetMapping("/list")
    @PreAuthorize("@ps.hasPerm('user:read')")
    public Result<List<User>> list() {
        return Result.<List<User>>builder().message("查询成功！").data(userService.list()).build();
    }

    @Operation(summary = "根据主键获取用户信息")
    @GetMapping("/get/{id}")
    @PreAuthorize("@ps.hasPerm('user:read')")
    public Result<User> getById(@PathVariable Long id) {
        return userService.getById(id) == null
                ? Result.<User>builder().message("查询成功！").data(userService.getById(id)).build()
                : Result.error("用户不存在！");
    }

    @Operation(summary = "添加用户信息")
    @PostMapping("/add")
    @PreAuthorize("@ps.hasPerm('user:create')")
    public Result<User> add(@Valid @RequestBody UserDTO user) {
        user.setPassword(MD5Utils.md5Encode(user.getRawPassword()));
        return userService.save(user)
                ? Result.<User>builder().message("添加成功！").data(user).build()
                : Result.error("添加失败！");
    }

    @Operation(summary = "修改用户信息")
    @PutMapping("/update")
    @PreAuthorize("@ps.hasPerm('user:update')")
    public Result<User> update(@Valid @RequestBody UserDTO user) {
        user.setPassword(MD5Utils.md5Encode(user.getRawPassword()));
        return userService.updateById(user)
                ? Result.<User>builder().message("修改成功！").data(user).build()
                : Result.error("修改失败！");
    }

    @Operation(summary = "删除用户信息")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@ps.hasPerm('user:delete')")
    public Result<User> delete(@PathVariable Long id) {
        return userService.removeById(id)
                ? Result.<User>builder().message("删除成功！").data(null).build()
                : Result.error("删除失败！");
    }

    @Operation(summary = "给用户分配角色")
    @PutMapping("/assignRole/{userId}")
    @PreAuthorize("@ps.hasPerm('user:update')")
    public Result<User> assignRole(@PathVariable Long userId, @RequestBody List<Long> roleIds) {
        return userService.assignRole(userId, roleIds)
                ? Result.<User>builder().message("分配成功！").data(null).build()
                : Result.error("分配失败！");
    }
}
