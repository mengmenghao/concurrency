package com.lmh.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lmh
 * @description: 定长线程池
 * @projectName: concurrency
 * @className: ThreadPoolSample1
 * @createDate: 2024/3/10 14:49
 */
public class ThreadPoolSample2 {
    public static void main(String[] args) {
        // 调度器对象
        // 创建一个定长线程池
        // ExecutorService用于管理线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 定长线程池的特点是，固定线程总数，空间线程用于执行任务，
        // 如果线程都在使用后续任务则处于等待状态，在线程池中的线程执行任务后再执行后续任务
        // 如果任务处于等待状态，备选的等待算法默认为FIFO(先进先出) 也可设置为LIFO（后进先出）
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
