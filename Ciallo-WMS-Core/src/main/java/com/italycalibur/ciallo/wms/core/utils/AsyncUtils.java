package com.italycalibur.ciallo.wms.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @description: 异步执行工具
 * @author dhr
 * @date 2025-03-07 10:30:32
 * @version 1.0
 */
@Component
@Slf4j
public class AsyncUtils {
    @Async
    public void execute(Runnable runnable, String name) {
        log.info("开始执行异步任务，任务名称：{}", name);
        long begin = System.currentTimeMillis();
        try {
            runnable.run();
        } catch (Exception e) {
            log.error("异步任务执行异常，任务名称：{}，异常信息：{}", name, e.getMessage());
        }
        long end = System.currentTimeMillis();
        log.info("异步任务执行完成，任务名称：{}，耗时：{}ms", name, end - begin);
    }
}
