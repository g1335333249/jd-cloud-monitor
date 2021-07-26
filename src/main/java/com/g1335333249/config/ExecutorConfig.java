package com.g1335333249.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/7/12 10:05
 * @Description:
 * @Modified By:
 */
@Configuration
@Slf4j
public class ExecutorConfig {

    @Bean("threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 获取到服务器的cpu内核
        int i = Runtime.getRuntime().availableProcessors();
        log.info("CPU共{}个核心", i);
        // 核心线程数
        executor.setCorePoolSize(5);
        // 最大线程数
        executor.setMaxPoolSize(20);
        // 队列程度
        executor.setQueueCapacity(1000);
        // 线程空闲时间
        executor.setKeepAliveSeconds(1000);
        // 线程前缀名称
        executor.setThreadNamePrefix("task-async");
        // 配置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
