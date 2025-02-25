package com.italycalibur.ciallo.wms.admin.controller;

import com.italycalibur.ciallo.wms.admin.dto.DeptDTO;
import com.italycalibur.ciallo.wms.admin.vo.DeptVO;
import com.italycalibur.ciallo.wms.core.common.Result;
import com.italycalibur.ciallo.wms.core.dto.DeptTree;
import com.italycalibur.ciallo.wms.admin.service.DeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 19:44:57
 */
@Tag(name = "部门管理")
@RestController
@RequestMapping("/system/dept")
@RequiredArgsConstructor
public class DeptController {
    private final DeptService deptService;

    @Operation(summary = "获取部门列表")
    @GetMapping("/list")
    @PreAuthorize("@ps.hasPerm('dept:read')")
    public Result<List<DeptVO>> list() {
        return Result.<List<DeptVO>>builder().message("查询成功！").data(deptService.listAll()).build();
    }

    @Operation(summary = "根据主键获取部门信息")
    @GetMapping("/get/{id}")
    @PreAuthorize("@ps.hasPerm('dept:read')")
    public Result<DeptVO> getById(@PathVariable Long id) {
        return deptService.getById(id) == null
                ? Result.<DeptVO>builder().message("查询成功！").data(deptService.findDeptById(id)).build()
                : Result.error("部门不存在！");
    }

    @Operation(summary = "添加部门信息")
    @PostMapping("/add")
    @PreAuthorize("@ps.hasPerm('dept:create')")
    public Result<DeptVO> add(@Valid @RequestBody DeptDTO dept) {
        return deptService.save(dept)
                ? Result.<DeptVO>builder().message("添加成功！").data(deptService.findDeptById(dept.getId())).build()
                : Result.error("添加失败！");
    }

    @Operation(summary = "修改部门信息")
    @PutMapping("/update")
    @PreAuthorize("@ps.hasPerm('dept:update')")
    public Result<DeptVO> update(@Valid @RequestBody DeptDTO dept) {
        return deptService.updateById(dept)
                ? Result.<DeptVO>builder().message("修改成功！").data(deptService.findDeptById(dept.getId())).build()
                : Result.error("修改失败！");
    }

    @Operation(summary = "删除部门信息")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@ps.hasPerm('dept:delete')")
    public Result<Void> delete(@PathVariable Long id) {
        return deptService.removeById(id)
                ? Result.<Void>builder().message("删除成功！").data(null).build()
                : Result.error("删除失败！");
    }

    @Operation(summary = "获取部门树形结构")
    @GetMapping("/deptTree")
    @PreAuthorize("@ps.hasPerm('dept:read')")
    public Result<List<DeptTree>> deptTree() {
        return Result.<List<DeptTree>>builder().message("查询成功！").data(deptService.deptTree()).build();
    }
}
