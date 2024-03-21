package com.lmh.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lmh
 * @description: 可缓存线程池
 * @projectName: concurrency
 * @className: ThreadPoolSample1
 * @createDate: 2024/3/10 14:49
 */
public class ThreadPoolSample1 {
    public static void main(String[] args) {
        // 调度器对象
        // 创建一个可缓存线程池
        // ExecutorService用于管理线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 可缓存线程池的特点是，无限大，如果线程池中没有可用线程则创建，有空闲线程则利用起来
        for (int i = 0; i < 1000; i++) {
            final int index = i;
            executorService.execute(() -> {
                System.out.println("当前线程：" + Thread.currentThread().getName()+"，index:" + index);
            });
        }
        try {
            Thread.sleep(1000); // 给线程足够的运行时间
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // shutdown()代表关闭线程池（等待所有线程完成）
        // shutdownNow()代表关立即闭线程池，不等待线程，不推荐使用（强制关闭）
        executorService.shutdown();

    }
}
