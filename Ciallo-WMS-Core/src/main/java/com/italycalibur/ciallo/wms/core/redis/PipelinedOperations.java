package com.italycalibur.ciallo.wms.core.redis;

/** 
 * @description: pipelined 操作接口
 * @author dhr
 * @date 2025-02-25 15:09:55
 * @version 1.0
 */
@FunctionalInterface
public interface PipelinedOperations<E> {
    /**
     * redis的pipelined操作
     * @param executor 执行器
     */
    void operate(E executor);
}
