package com.lmh.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lmh
 * @description: CyclicBarrier循环屏障
 * @projectName: concurrency
 * @className: CyclicBarrierSample
 * @createDate: 2024/3/20 21:01
 */
public class CyclicBarrierSample {
    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            executorService.execute(() -> {
                go();
            });
        }

        executorService.shutdown();
    }

    private static void go(){
        System.out.println(Thread.currentThread().getName()+"：准备就绪");
        try {
            barrier.await();// 设置屏障点，当累计5个线程都准备好厚，才运行后面的代码
            System.out.println(Thread.currentThread().getName()+"：开始执行");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
