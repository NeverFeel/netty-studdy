package com.ilidan.tomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class SafeCount {

    private AtomicIntegerFieldUpdater<SafeCount> updater =
            AtomicIntegerFieldUpdater.newUpdater(SafeCount.class, "count");
    private volatile int count = 0;

    public AtomicIntegerFieldUpdater<SafeCount> getUpdater() {
        return updater;
    }

    public int getCount() {
        return count;
    }
}
