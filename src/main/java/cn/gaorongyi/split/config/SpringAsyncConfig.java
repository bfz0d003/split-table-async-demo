package cn.gaorongyi.split.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Spring线程池
 *
 * @author Loeis
 */
@Configuration
@EnableAsync
public class SpringAsyncConfig {
    public static final String THREAD_NAME_PREFIX = "SPLIT_DEMO_THREAD_";
    /**
     * 线程池维护线程的最少数量
     */
    private static final int CORE_POOL_SIZE = 10;
    /**
     * 线程池维护线程的最大数量
     */
    private static final int MAX_POOL_SIZE = 50;
    /**
     * 缓存队列
     */
    private static final int QUEUE_CAPACITY = 8;
    /**
     * 允许的空闲时间
     */
    private static final int KEEP_ALIVE = 60;

    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        //对拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setKeepAliveSeconds(KEEP_ALIVE);
        executor.initialize();
        return executor;
    }
}