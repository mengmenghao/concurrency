package com.lmh.thread;

import java.util.Random;

/**
 * @author lmh
 * @description: 使用实现Rannable接口的方式实现多线程
 * @projectName: concurrency
 * @className: Match2
 * @createDate: 2024/3/6 19:00
 */
public class Match2 {
    public static void main(String[] args) {
        Runner2 liuxiang = new Runner2();
        Thread thread1 = new Thread(liuxiang, "刘翔");

        Thread thread2 = new Thread(new Runner2(),"老七");

        Runner2 lufei = new Runner2();
        Thread thread3 = new Thread(lufei, "路飞");

        thread1.start();
        thread2.start();
        thread3.start();
    }


}
class Runner2 implements Runnable {

    @Override
    public void run() {
        Integer speed = new Random().nextInt(100);
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000); // 当前线程休眠1s
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Thread.currentThread().getName() 打印当前线程的名字
            System.out.println(Thread.currentThread().getName() + "已前进" +(i*speed) + "米("+speed+"米/秒)");
        }
    }
}