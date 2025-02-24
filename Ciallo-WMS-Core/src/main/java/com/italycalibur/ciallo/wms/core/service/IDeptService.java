package com.italycalibur.ciallo.wms.core.service;

import com.italycalibur.ciallo.wms.core.dto.DeptTree;
import com.italycalibur.ciallo.wms.core.models.entity.Dept;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:12
 */
public interface IDeptService extends BaseService<Dept> {

    List<DeptTree> deptTree();

}
