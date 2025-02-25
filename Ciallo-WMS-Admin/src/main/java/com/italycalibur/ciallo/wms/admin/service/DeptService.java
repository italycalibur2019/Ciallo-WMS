package com.italycalibur.ciallo.wms.admin.service;

import com.italycalibur.ciallo.wms.admin.vo.DeptVO;
import com.italycalibur.ciallo.wms.core.service.system.IDeptService;

import java.util.List;

/**
 * @author dhr
 * @version 1.0
 * @description: 部门表 服务类
 * @date 2025-02-25 08:39:09
 */
public interface DeptService extends IDeptService {
    List<DeptVO> listAll();

    DeptVO findDeptById(Long id);
}
