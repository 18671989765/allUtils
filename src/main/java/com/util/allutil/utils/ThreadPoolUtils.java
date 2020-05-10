package com.util.allutil.utils;

import java.util.concurrent.*;

/**
 * 手动实现线程池
 */
public class ThreadPoolUtils {

    /**
     * 1	corePoolSize	int	核心线程池大小
     * 2	maximumPoolSize	int	最大线程池大小
     * 3	keepAliveTime	long	线程最大空闲时间
     * 4	unit	TimeUnit	时间单位
     * 5	workQueue	BlockingQueue<Runnable>	线程等待队列
     * 6	threadFactory	ThreadFactory	线程创建工厂
     * 7	handler	RejectedExecutionHandler	拒绝策略
     */
    public static ExecutorService getExecutorServiceInstance() {

        ExecutorService executorService = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread();
                thread.setName("线程名称-----:" + r.getClass().getName());
                return thread;
            }
            //丢弃策略是直接抛出异常
        }, new ThreadPoolExecutor.AbortPolicy());

        return executorService;
    }
}
