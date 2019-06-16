package com.ilidan.tomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class SafeCountTest {

    public static void main(String[] args) {
        SafeCount safeCount = new SafeCount();
        AtomicIntegerFieldUpdater<SafeCount> updater = safeCount.getUpdater();
        ExecutorService service = Executors.newFixedThreadPool(3);

        int forLength = 10000;
        CountDownLatch latch = new CountDownLatch(forLength);
        for (int i = 0; i < forLength; i++) {
            final int num = i;
            service.execute(() -> {
                for (; ; ) {
                    int count = safeCount.getCount();
                    int nextCount = count + num;
                    if (updater.compareAndSet(safeCount, count, nextCount)) {
                        latch.countDown();
                        break;
                    }
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
        System.out.println("执行完成：count=" + safeCount.getCount());

    }

}
