package com.italycalibur.ciallo.wms.core.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.italycalibur.ciallo.wms.core.dto.DeptTree;
import com.italycalibur.ciallo.wms.core.models.entity.Dept;

import java.util.List;

/**
 * <p>
 * 部门表 服务类基类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:12
 */
public interface IDeptService extends IService<Dept> {

    List<DeptTree> deptTree();

}
