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
                System.out.println(Thread.currentThread().getName() + " is waiting");
                wait();
            }
            if (queue.size() < numberOfElements) {
                queue.offer(value);
                System.out.println(Thread.currentThread().getName() + " offer() - " + value);
                notifyAll();
            }
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (null == queue.peek()) {
                System.out.println(Thread.currentThread().getName() + " is waiting");
                wait();
            }
            T value = queue.poll();
            System.out.println(Thread.currentThread().getName() + " poll() - " + value);
            notifyAll();
            return value;
        }
    }

}
