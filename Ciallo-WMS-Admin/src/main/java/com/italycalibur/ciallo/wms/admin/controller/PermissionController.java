package com.italycalibur.ciallo.wms.admin.controller;

import com.italycalibur.ciallo.wms.admin.dto.PermissionDTO;
import com.italycalibur.ciallo.wms.admin.vo.PermissionVO;
import com.italycalibur.ciallo.wms.core.common.Result;
import com.italycalibur.ciallo.wms.core.dto.MenuTree;
import com.italycalibur.ciallo.wms.core.enums.MenuTypeEnum;
import com.italycalibur.ciallo.wms.core.models.entity.Permission;
import com.italycalibur.ciallo.wms.admin.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@RequestMapping("/system/permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @Operation(summary = "获取权限列表")
    @GetMapping("/list")
    @PreAuthorize("@ps.hasPerm('perm:read')")
    public Result<List<PermissionVO>> list() {
        return Result.<List<PermissionVO>>builder().message("查询成功！").data(permissionService.listAll()).build();
    }

    @Operation(summary = "根据主键获取权限信息")
    @GetMapping("/get/{id}")
    @PreAuthorize("@ps.hasPerm('perm:read')")
    public Result<PermissionVO> getById(@PathVariable Long id) {
        return permissionService.getById(id) == null
                ? Result.<PermissionVO>builder().message("查询成功！").data(permissionService.findPermById(id)).build()
                : Result.error("权限不存在！");
    }

    @Operation(summary = "添加权限信息")
    @PostMapping("/add")
    @PreAuthorize("@ps.hasPerm('perm:create')")
    public Result<PermissionVO> add(@Valid @RequestBody PermissionDTO permission) {
        permission.setMenuType(MenuTypeEnum.valueOf(permission.getMenuTypeStr()));
        return permissionService.save(permission)
                ? Result.<PermissionVO>builder().message("添加成功！").data(permissionService.findPermById(permission.getId())).build()
                : Result.error("添加失败！");
    }

    @Operation(summary = "修改权限信息")
    @PutMapping("/update")
    @PreAuthorize("@ps.hasPerm('perm:update')")
    public Result<PermissionVO> update(@Valid @RequestBody PermissionDTO permission) {
        permission.setMenuType(MenuTypeEnum.valueOf(permission.getMenuTypeStr()));
        return permissionService.updateById(permission)
                ? Result.<PermissionVO>builder().message("修改成功！").data(permissionService.findPermById(permission.getId())).build()
                : Result.error("修改失败！");
    }

    @Operation(summary = "删除权限信息")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@ps.hasPerm('perm:delete')")
    public Result<Void> delete(@PathVariable Long id) {
        return permissionService.removeById(id)
                ? Result.<Void>builder().message("删除成功！").data(null).build()
                : Result.error("删除失败！");
    }

    @Operation(summary = "获取权限菜单树形结构")
    @GetMapping("/menuTree")
    @PreAuthorize("@ps.hasPerm('menu:read')")
    public Result<List<MenuTree>> menuTree() {
        return Result.<List<MenuTree>>builder().message("查询成功！").data(permissionService.menuTree()).build();
    }
}
