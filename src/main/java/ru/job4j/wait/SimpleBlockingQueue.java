package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final int numberOfElements;

    public SimpleBlockingQueue(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public void offer(T value) {
        synchronized (this) {
            while (queue.size() == numberOfElements) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(queue.offer(value));
            }
            if (queue.size() < numberOfElements) {
                queue.offer(value);
                this.notify();
            }
        }
    }

    public T poll() {
        synchronized (this) {
            while (null == queue.peek()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.notify();
            return queue.poll();

        }
    }

}
