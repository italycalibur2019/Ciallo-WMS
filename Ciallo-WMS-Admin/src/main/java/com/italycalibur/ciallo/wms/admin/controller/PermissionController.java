package com.italycalibur.ciallo.wms.admin.controller;

import com.italycalibur.ciallo.wms.core.common.Result;
import com.italycalibur.ciallo.wms.core.models.entity.Permission;
import com.italycalibur.ciallo.wms.core.service.IPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 19:44:57
 */
@Tag(name = "权限管理")
@RestController
@RequestMapping("/system/dept")
@RequiredArgsConstructor
public class PermissionController {
    private final IPermissionService permissionService;

    @Operation(summary = "获取权限列表")
    @GetMapping("/list")
    @PreAuthorize("@permissionServiceImpl.hasPerm('perm:read')")
    public Result<List<Permission>> list() {
        return Result.<List<Permission>>builder().message("查询成功！").data(permissionService.list()).build();
    }

    @Operation(summary = "根据主键获取权限信息")
    @GetMapping("/get/{id}")
    @PreAuthorize("@permissionServiceImpl.hasPerm('perm:read')")
    public Result<Permission> getById(@PathVariable Long id) {
        return permissionService.getById(id) == null
                ? Result.<Permission>builder().message("查询成功！").data(permissionService.getById(id)).build()
                : Result.error("权限不存在！");
    }

    @Operation(summary = "添加权限信息")
    @PostMapping("/add")
    @PreAuthorize("@permissionServiceImpl.hasPerm('perm:create')")
    public Result<Permission> add(@RequestBody Permission dept) {
        return permissionService.save(dept)
                ? Result.<Permission>builder().message("添加成功！").data(dept).build()
                : Result.error("添加失败！");
    }

    @Operation(summary = "修改权限信息")
    @PutMapping("/update")
    @PreAuthorize("@permissionServiceImpl.hasPerm('perm:update')")
    public Result<Permission> update(@RequestBody Permission dept) {
        return permissionService.updateById(dept)
                ? Result.<Permission>builder().message("修改成功！").data(dept).build()
                : Result.error("修改失败！");
    }

    @Operation(summary = "删除权限信息")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@permissionServiceImpl.hasPerm('perm:delete')")
    public Result<Permission> delete(@PathVariable Long id) {
        return permissionService.removeById(id)
                ? Result.<Permission>builder().message("删除成功！").data(null).build()
                : Result.error("删除失败！");
    }
}
