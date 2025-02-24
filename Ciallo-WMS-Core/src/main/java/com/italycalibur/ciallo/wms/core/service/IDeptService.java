package com.italycalibur.ciallo.wms.core.service;

import com.italycalibur.ciallo.wms.core.dto.DeptTree;
import com.italycalibur.ciallo.wms.core.models.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:12
 */
public interface IDeptService extends IService<Dept> {

    List<DeptTree> deptTree();

}
