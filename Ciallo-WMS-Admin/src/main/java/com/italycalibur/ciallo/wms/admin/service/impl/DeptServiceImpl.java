package com.italycalibur.ciallo.wms.admin.service.impl;

import com.italycalibur.ciallo.wms.admin.service.DeptService;
import com.italycalibur.ciallo.wms.admin.vo.DeptVO;
import com.italycalibur.ciallo.wms.core.models.entity.Dept;
import com.italycalibur.ciallo.wms.core.service.system.impl.BaseDeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 部门表 服务实现类
 * @author dhr
 * @date 2025-02-25 08:41:42
 * @version 1.0
 */
@Service
public class DeptServiceImpl extends BaseDeptService implements DeptService {

    @Override
    public List<DeptVO> listAll() {
        List<Dept> list = this.list();
        return list.stream()
                .map(dept -> {
                    DeptVO vo = new DeptVO();
                    BeanUtils.copyProperties(dept, vo);
                    Dept parentDept = this.getById(dept.getParentId());
                    if (parentDept != null) {
                        vo.setParentCompanyName(parentDept.getDeptName());
                    }
                    vo.setDeptTypeDesc("C".equals(dept.getDeptType()) ? "公司" : "部门");
                    return vo;
                })
                .toList();
    }

    @Override
    public DeptVO findDeptById(Long id) {
        Dept dept = this.getById(id);
        if (dept != null) {
            DeptVO vo = new DeptVO();
            BeanUtils.copyProperties(dept, vo);
            Dept parentDept = this.getById(dept.getParentId());
            if (parentDept != null) {
                vo.setParentCompanyName(parentDept.getDeptName());
            }
            vo.setDeptTypeDesc("C".equals(dept.getDeptType()) ? "公司" : "部门");
            return vo;
        }
        return null;
    }
}
