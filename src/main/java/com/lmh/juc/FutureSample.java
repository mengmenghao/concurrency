package com.lmh.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author lmh
 * @description: 一句话描述该类的功能
 * @projectName: concurrency
 * @className: FutureSample
 * @createDate: 2024/3/23 22:28
 */
public class FutureSample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 2; i <= 10000; i++) {
            Computor computor = new Computor();
            computor.setNum(i);
            // Future是对用于计算的线程进行监听，因为计算时在其他线程中执行的，所以这个返回结果的过程是异步的
            Future<Boolean> result = executorService.submit(computor);// 将computor对象提交给线程池，如果有空闲线程立即执行里面的call方法
            try {
                Boolean r = result.get(); // 用于获取返回值，如果线程内部的call没有执行完成，则进入等待状态，直到计算完成
                if (r) {
                    System.out.println(computor.getNum());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        executorService.shutdown();
    }
}

class Computor implements Callable<Boolean> {
    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public Boolean call() throws Exception {
        boolean isPrime = true;
        for (Integer i = 2; i < num; i++) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
}
