package com.italycalibur.ciallo.wms.core.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.italycalibur.ciallo.wms.core.dto.DeptTree;
import com.italycalibur.ciallo.wms.core.models.entity.Dept;
import com.italycalibur.ciallo.wms.core.models.mapper.DeptMapper;
import com.italycalibur.ciallo.wms.core.service.system.IDeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门表 服务实现类基类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:12
 */
@Service
public abstract class BaseDeptService extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Override
    public List<DeptTree> deptTree() {
        // 获取所有部门数据
        List<Dept> deptList = this.list();

        // 创建ID->节点的映射表（使用LinkedHashMap保持顺序）
        Map<Long, DeptTree> nodeMap = deptList.stream()
                .filter(dept -> dept.getId() != 0L)
                .collect(Collectors.toMap(
                        Dept::getId,
                        dept -> {
                            DeptTree node = new DeptTree();
                            BeanUtils.copyProperties(dept, node);
                            return node;
                        },
                        (existing, replacement) -> existing,  // 处理重复ID冲突
                        LinkedHashMap::new
                ));

        // 构建树形结构
        List<DeptTree> roots = new ArrayList<>();
        nodeMap.forEach((id, node) -> {
            Long parentId = node.getParentId();
            if (!nodeMap.containsKey(parentId)) {
                roots.add(node);
            } else {
                DeptTree parent = nodeMap.get(parentId);
                if (parent != null) {
                    parent.getChildren().add(node);
                }
            }
        });

        return roots;
    }
}
