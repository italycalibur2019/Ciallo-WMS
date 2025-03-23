package com.italycalibur.ciallo.wms.core.listener;

import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.italycalibur.ciallo.wms.core.common.BaseUploadDTO;
import com.italycalibur.ciallo.wms.core.models.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: fastexcel 数据监听器
 * @author dhr
 * @date 2025-03-07 11:24:41
 * @version 1.0
 */
@Getter
@Slf4j
@RequiredArgsConstructor
public abstract class BaseExcelListener<E extends BaseEntity, M extends BaseMapper<E>, T extends BaseUploadDTO> extends AnalysisEventListener<T> {
    private final M mapper;

    // 定义一个数据列表，用于存储读取到的每一行数据
    private final List<E> dataList = new ArrayList<>();

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        try {
            // 校验字段合法性
            t.validate();
            // 处理数据
            E entity = handleData(t, analysisContext);
            // 添加读取到的每一行数据到数据列表
            dataList.add(entity);
        } catch (Exception e) {
            log.error("数据校验失败: {}", e.getMessage());
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 处理数据完成，执行后续操作
        insertData(dataList);
        log.info("读取完成，共读取了 {} 条数据", dataList.size());

    }

    public abstract E handleData(T t, AnalysisContext analysisContext);

    public abstract void insertData(List<E> dataList);
}
