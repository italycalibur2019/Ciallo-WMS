package com.italycalibur.ciallo.wms.admin.controller;

import com.italycalibur.ciallo.wms.core.common.Result;
import com.italycalibur.ciallo.wms.core.models.entity.User;
import com.italycalibur.ciallo.wms.core.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    private final IUserService userService;

    @Operation(summary = "获取用户列表")
    @GetMapping("/list")
    @PreAuthorize("@permissionServiceImpl.hasPerm('user:read')")
    public Result<List<User>> list() {
        return Result.<List<User>>builder().message("查询成功！").data(userService.list()).build();
    }

    @Operation(summary = "根据主键获取用户信息")
    @GetMapping("/get/{id}")
    @PreAuthorize("@permissionServiceImpl.hasPerm('user:read')")
    public Result<User> getById(@PathVariable Long id) {
        return userService.getById(id) == null
                ? Result.<User>builder().message("查询成功！").data(userService.getById(id)).build()
                : Result.error("用户不存在！");
    }

    @Operation(summary = "添加用户信息")
    @PostMapping("/add")
    @PreAuthorize("@permissionServiceImpl.hasPerm('user:create')")
    public Result<User> add(@RequestBody User user) {
        return userService.save(user)
                ? Result.<User>builder().message("添加成功！").data(user).build()
                : Result.error("添加失败！");
    }

    @Operation(summary = "修改用户信息")
    @PutMapping("/update")
    @PreAuthorize("@permissionServiceImpl.hasPerm('user:update')")
    public Result<User> update(@RequestBody User user) {
        return userService.updateById(user)
                ? Result.<User>builder().message("修改成功！").data(user).build()
                : Result.error("修改失败！");
    }

    @Operation(summary = "删除用户信息")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@permissionServiceImpl.hasPerm('user:delete')")
    public Result<User> delete(@PathVariable Long id) {
        return userService.removeById(id)
                ? Result.<User>builder().message("删除成功！").data(null).build()
                : Result.error("删除失败！");
    }
}
