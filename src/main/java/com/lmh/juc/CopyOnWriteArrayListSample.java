package com.lmh.juc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author lmh
 * @description: 一句话描述该类的功能
 * @projectName: concurrency
 * @className: CopyOnWriteArrayListSample
 * @createDate: 2024/3/23 22:38
 */
public class CopyOnWriteArrayListSample {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            list.remove(next);
        }
        System.out.println(list);
    }
}
