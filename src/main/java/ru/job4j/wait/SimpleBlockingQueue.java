package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final int numberOfElements;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (queue.size() == numberOfElements) {
                wait();
            }
            queue.offer(value);
            notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (queue.size() == 0) {
                wait();
            }
            T value = queue.poll();
            notifyAll();
            return value;
        }
    }

}
