package com.italycalibur.ciallo.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.italycalibur.ciallo.wms.admin.dto.UserDTO;
import com.italycalibur.ciallo.wms.admin.service.UserService;
import com.italycalibur.ciallo.wms.admin.vo.UserVO;
import com.italycalibur.ciallo.wms.core.common.PageData;
import com.italycalibur.ciallo.wms.core.common.PageQueryRequest;
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

    @Override
    public PageData<UserVO> queryPage(PageQueryRequest<UserDTO> request) {
        // 构建分页参数
        Page<User> page = new Page<>(request.getPageNum(), request.getPageSize());

        // 构建查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        // 动态条件处理（根据实际业务需求扩展）
        if (request.getConditions() != null) {
             if (request.getConditions().getUsername() != null) {
                 queryWrapper.like("username", request.getConditions().getUsername());
             }
        }

        // 动态排序处理
        request.dynamicSortingProcessing(queryWrapper);

        Page<User> userPage = baseMapper.selectPage(page, queryWrapper);
        return PageData.of(userPage.getRecords().stream().map(user -> {
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
        }).toList(), userPage.getTotal(),  userPage.getSize());
    }
}
