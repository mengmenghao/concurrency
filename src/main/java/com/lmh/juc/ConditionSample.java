package com.lmh.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lmh
 * @description: 一句话描述该类的功能
 * @projectName: concurrency
 * @className: ConditionSample
 * @createDate: 2024/3/21 20:39
 */
public class ConditionSample {
    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock(); //Condition对象必须配合Lock一起使用
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    c1.await();
                    System.out.println("粒粒皆辛苦");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    c2.await();
                    System.out.println("谁知盘中餐");
                    c1.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    c3.await();
                    System.out.println("汗滴禾下土");
                    c2.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    Thread.sleep(1000);
                    System.out.println("锄禾日当午");
                    c3.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }


            }
        }).start();






    }
}
