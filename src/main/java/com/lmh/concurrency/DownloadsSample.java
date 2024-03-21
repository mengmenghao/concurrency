package com.lmh.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author lmh
 * @description: 一句话描述该类的功能
 * @projectName: concurrency
 * @className: DownloadsSample
 * @createDate: 2024/3/6 17:57
 */
public class DownloadsSample {
    public static int users = 100; //同时模拟的并发访问用户数量
    public static int downTotal = 50000; //用户下载的真实总数
    public static int count = 0; // 计数器

    public static void main(String[] args) {
        // 调度器，JDK1.5后提供的concurrent包对于并发的支持
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 信号量，用于模拟并发的人数
        final Semaphore semaphore = new Semaphore(users);
        for (int i = 0; i < downTotal; i++) {
            executorService.execute(() -> {
                // 通过多线程模拟N个用户并发访问并下载
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown(); // 关闭调度服务
        System.out.println("下载总数："+count);
    }

    private static void add() {
        count++;
    }
}
