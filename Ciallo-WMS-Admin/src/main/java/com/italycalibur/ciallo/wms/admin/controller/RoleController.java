package com.italycalibur.ciallo.wms.admin.controller;

import com.italycalibur.ciallo.wms.admin.dto.RoleDTO;
import com.italycalibur.ciallo.wms.core.common.Result;
import com.italycalibur.ciallo.wms.core.models.entity.Role;
import com.italycalibur.ciallo.wms.core.models.entity.User;
import com.italycalibur.ciallo.wms.admin.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 19:44:57
 */
@Tag(name = "角色管理")
@RestController
@RequestMapping("/system/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @Operation(summary = "获取角色列表")
    @GetMapping("/list")
    @PreAuthorize("@ps.hasPerm('role:read')")
    public Result<List<Role>> list() {
        return Result.<List<Role>>builder().message("查询成功！").data(roleService.list()).build();
    }

    @Operation(summary = "根据主键获取角色信息")
    @GetMapping("/get/{id}")
    @PreAuthorize("@ps.hasPerm('role:read')")
    public Result<Role> getById(@PathVariable Long id) {
        return roleService.getById(id) == null
                ? Result.<Role>builder().message("查询成功！").data(roleService.getById(id)).build()
                : Result.error("角色不存在！");
    }

    @Operation(summary = "添加角色信息")
    @PostMapping("/add")
    @PreAuthorize("@ps.hasPerm('role:create')")
    public Result<Role> add(@Valid @RequestBody RoleDTO role) {
        return roleService.save(role)
                ? Result.<Role>builder().message("添加成功！").data(role).build()
                : Result.error("添加失败！");
    }

    @Operation(summary = "修改角色信息")
    @PutMapping("/update")
    @PreAuthorize("@ps.hasPerm('role:update')")
    public Result<Role> update(@Valid @RequestBody RoleDTO role) {
        return roleService.updateById(role)
                ? Result.<Role>builder().message("修改成功！").data(role).build()
                : Result.error("修改失败！");
    }

    @Operation(summary = "删除角色信息")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@ps.hasPerm('role:delete')")
    public Result<Role> delete(@PathVariable Long id) {
        return roleService.removeById(id)
                ? Result.<Role>builder().message("删除成功！").data(null).build()
                : Result.error("删除失败！");
    }

    @Operation(summary = "给角色分配权限")
    @PutMapping("/assignPerm/{roleId}")
    @PreAuthorize("@ps.hasPerm('role:update')")
    public Result<User> assignPerm(@PathVariable Long roleId, @RequestBody List<Long> permIds) {
        return roleService.assignPerm(roleId, permIds)
                ? Result.<User>builder().message("分配成功！").data(null).build()
                : Result.error("分配失败！");
    }
}
