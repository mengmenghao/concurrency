package com.lmh.juc;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lmh
 * @description: 可调度线程池
 * @projectName: concurrency
 * @className: ThreadPoolSample1
 * @createDate: 2024/3/10 14:49
 */
public class ThreadPoolSample4 {
    public static void main(String[] args) {
        // 调度器对象
        // 创建一个可调度线程池
        // ExecutorService用于管理线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
       /* // 延迟3秒执行一次run方法
        scheduledThreadPool.schedule(() -> {
            System.out.println("延迟3秒执行");
        }, 3, TimeUnit.SECONDS);*/

        // Timer ,项目实际开发中scheduledThreadPool与Timer都不会用到，因为有成熟的调度框架Quartz，或者Spring自带调度，
        // 成熟的调度框架支持cron表达式
        scheduledThreadPool.scheduleWithFixedDelay(()->{
            System.out.println(LocalDateTime.now() + "延迟1秒执行，每三秒执行一次");
        },1,3,TimeUnit.SECONDS);

        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scheduledThreadPool.shutdown();
    }
}
