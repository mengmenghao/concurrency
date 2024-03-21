package com.lmh.thread;

import java.util.Random;

/**
 * @author lmh
 * @description: 使用继承Thread的方式实现多线程
 * @projectName: concurrency
 * @className: Match1
 * @createDate: 2024/3/6 18:49
 */
public class Match1 {
    public static void main(String[] args) {
        Runner liuxiang = new Runner();// 常见一个新的线程
        liuxiang.setName("刘翔");// 设置线程名称

        Runner laoqi = new Runner();
        laoqi.setName("老齐");

        Runner lufei = new Runner();
        lufei.setName("路飞");

        liuxiang.start(); // 启动线程
        laoqi.start();
        lufei.start();
    }
}

class Runner extends Thread {
    @Override
    public void run() {
        Integer speed = new Random().nextInt(100);
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000); // 当前线程休眠1s
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // this.getName() 打印当前线程的名字
            System.out.println(this.getName() + "已前进" +(i*speed) + "米("+speed+"米/秒)");
        }
    }
}
