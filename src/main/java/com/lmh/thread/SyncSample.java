package com.lmh.thread;

import com.sun.source.tree.NewArrayTree;

import java.util.Random;

/**
 * @author lmh
 * @description: 一句话描述该类的功能
 * @projectName: concurrency
 * @className: SyncSample
 * @createDate: 2024/3/6 19:46
 */
public class SyncSample {
    public static void main(String[] args) {
        Couplet couplet = new Couplet();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                int r = new Random().nextInt(2);
                if (r % 2 == 0) {
                    couplet.first();
                } else {
                    couplet.second();
                }
            }).start();
        }
    }
}

class Couplet {
    Object lock = new Object(); // 锁对象
    public void first() {
        synchronized (lock) { // 同步代码块
            System.out.print("琴");
            System.out.print("瑟");
            System.out.print("琵");
            System.out.print("琶");
            System.out.println();
        }
    }

    public void second() {
        synchronized (lock) {
            System.out.print("魑");
            System.out.print("魅");
            System.out.print("魍");
            System.out.print("魉");
            System.out.println();
        }
    }
}