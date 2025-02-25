package com.italycalibur.ciallo.wms.admin.service.impl;

import com.italycalibur.ciallo.wms.admin.service.UserService;
import com.italycalibur.ciallo.wms.admin.vo.UserVO;
import com.italycalibur.ciallo.wms.core.models.entity.Dept;
import com.italycalibur.ciallo.wms.core.models.entity.User;
import com.italycalibur.ciallo.wms.core.service.system.impl.BaseDeptService;
import com.italycalibur.ciallo.wms.core.service.system.impl.BaseUserService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 用户表 服务实现类
 * @author dhr
 * @date 2025-02-25 08:42:19
 * @version 1.0
 */
@Service
public class UserServiceImpl extends BaseUserService implements UserService {
    @Resource
    private BaseDeptService baseDeptService;

    @Override
    public List<UserVO> listAll() {
        List<User> list = this.list();
        if (list != null) {
            return list.stream().map(user -> {
                UserVO vo = new UserVO();
                BeanUtils.copyProperties(user, vo);
                Dept dept = baseDeptService.getById(user.getDeptId());
                if (dept != null) {
                    vo.setDeptName(dept.getDeptName());
                }
                Dept company = baseDeptService.getById(user.getCompanyId());
                if (company != null) {
                    vo.setCompanyName(company.getDeptName());
                }
                return vo;
            }).toList();
        }
        return List.of();
    }

    @Override
    public UserVO findUserById(Long id) {
        User user = this.getById(id);
        if (user != null) {
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(user, vo);
            Dept dept = baseDeptService.getById(user.getDeptId());
            if (dept != null) {
                vo.setDeptName(dept.getDeptName());
            }
            Dept company = baseDeptService.getById(user.getCompanyId());
            if (company != null) {
                vo.setCompanyName(company.getDeptName());
            }
            return vo;
        }
        return null;
    }
}
