package com.lmh.juc;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author lmh
 * @description: 一句话描述该类的功能
 * @projectName: concurrency
 * @className: SemaphoreSample1
 * @createDate: 2024/3/10 15:40
 */
public class SemaphoreSample1 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 定义5个信号量，也就是说服务器只允许5个人玩
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 22; i++) {
            final int index = i;
            threadPool.execute(() -> {
                try {
                    semaphore.acquire(); // 获取许可证
                    play();
                    semaphore.release();// 释放许可证
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
