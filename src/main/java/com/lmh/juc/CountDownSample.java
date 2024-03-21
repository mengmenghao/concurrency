package com.lmh.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lmh
 * @description: 一句话描述该类的功能
 * @projectName: concurrency
 * @className: CountDownSample
 * @createDate: 2024/3/10 15:25
 */
public class CountDownSample {
    private static int count = 0;

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        for (int i = 0; i <= 10000; i++) {
            final int index = i;
            threadPool.execute(() -> {
                synchronized (CountDownSample.class) {
                    try {
                        count = count + index;
                    } finally {
                        countDownLatch.countDown(); // 计数器减一
                    }

                }
            });
        }
        //try {
        //    Thread.sleep(1000);
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //}
        try {
            countDownLatch.await(); // 阻塞当前线程，直到countDownLatch=0的时候再继续往下走
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(count);
        threadPool.shutdown();
    }
}
