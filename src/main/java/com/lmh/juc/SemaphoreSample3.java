package com.lmh.juc;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author lmh
 * @description: 一句话描述该类的功能
 * @projectName: concurrency
 * @className: SemaphoreSample1
 * @createDate: 2024/3/10 15:40
 */
public class SemaphoreSample3 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 定义5个信号量，也就是说服务器只允许5个人玩
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 22; i++) {
            final int index = i;
            threadPool.execute(() -> {
                try {
                    // 尝试获取一次信号量，5秒钟内获取到返回true，否则返回false
                    if (semaphore.tryAcquire(5, TimeUnit.SECONDS)) {
                        play();
                        semaphore.release();// 释放许可证
                    } else {
                        System.out.println(Thread.currentThread().getName() + "对不起，服务器已满，请稍后再试");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
        }
        threadPool.shutdown();
    }

    public static void play() {
        try {
            System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName() + "：获得紫禁之巅服务器进入资格");
            Thread.sleep(2000);
            System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName() +":退出服务器");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
