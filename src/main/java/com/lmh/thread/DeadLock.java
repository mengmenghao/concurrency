package com.lmh.thread;

/**
 * @author lmh
 * @description: 一句话描述该类的功能
 * @projectName: concurrency
 * @className: DeadLock
 * @createDate: 2024/3/6 21:01
 */
public class DeadLock {
    private static String fileA = "A文件";

    private static String fileB = "B文件";

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (fileA) {
                System.out.println(Thread.currentThread().getName() + "获取了文件A锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fileB) {
                    System.out.println(Thread.currentThread().getName() + "获取了文件B锁");
                    System.out.println();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (fileB) {
                System.out.println(Thread.currentThread().getName() + "获取了文件B锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fileA) {
                    System.out.println(Thread.currentThread().getName() + "获取了文件A锁");
                    System.out.println();
                }
            }
        }).start();
    }
}
