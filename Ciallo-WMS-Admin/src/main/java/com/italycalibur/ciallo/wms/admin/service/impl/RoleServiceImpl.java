package com.italycalibur.ciallo.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.italycalibur.ciallo.wms.admin.dto.RoleDTO;
import com.italycalibur.ciallo.wms.admin.service.RoleService;
import com.italycalibur.ciallo.wms.admin.vo.RoleVO;
import com.italycalibur.ciallo.wms.core.common.PageData;
import com.italycalibur.ciallo.wms.core.common.PageQueryRequest;
import com.italycalibur.ciallo.wms.core.models.entity.Role;
import com.italycalibur.ciallo.wms.core.service.system.impl.BaseRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 角色表 服务实现类
 * @author dhr
 * @date 2025-02-25 08:43:39
 * @version 1.0
 */
@Service
public class RoleServiceImpl extends BaseRoleService implements RoleService {
    @Override
    public List<RoleVO> listAll() {
        List<Role> roleList = this.list();
        return roleList.stream()
                .map(role -> {
                    RoleVO vo = new RoleVO();
                    BeanUtils.copyProperties(role, vo);
                    return vo;
                })
                .toList();
    }

    @Override
    public RoleVO findRoleById(Long id) {
        Role role = this.getById(id);
        if (role != null) {
            RoleVO vo = new RoleVO();
            BeanUtils.copyProperties(role, vo);
            return vo;
        }
        return null;
    }

    @Override
    public PageData<RoleVO> queryPage(PageQueryRequest<RoleDTO> request) {
        Page<Role> page = new Page<>(request.getPageNum(), request.getPageSize());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (request.getConditions() != null) {
            if (request.getConditions().getRoleName() != null) {
                queryWrapper.like("role_name", request.getConditions().getRoleName());
            }
            if (request.getConditions().getRoleDesc() != null) {
                queryWrapper.like("role_desc", request.getConditions().getRoleDesc());
            }
        }
        request.dynamicSortingProcessing(queryWrapper);
        Page<Role> rolePage = baseMapper.selectPage(page, queryWrapper);
        return PageData.of(rolePage.getRecords().stream().map(role -> {
            RoleVO vo = new RoleVO();
            BeanUtils.copyProperties(role, vo);
            return vo;
        }).toList(), rolePage.getTotal(), rolePage.getSize());
    }
}
