package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger();

    public void increment() {
        int currentCount;
        do {
            currentCount = count.get();
        } while (!count.compareAndSet(currentCount, currentCount + 1));
    }

    public int get() {
        return count.get();
    }

}
