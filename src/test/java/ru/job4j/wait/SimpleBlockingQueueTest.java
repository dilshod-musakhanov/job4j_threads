package ru.job4j.wait;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleBlockingQueueTest {

    @Test
    public void whenAdd() throws InterruptedException {
        SimpleBlockingQueue sbq = new SimpleBlockingQueue<Integer>(3);
        Thread producer1 = new Thread(() -> {
            try {
                sbq.offer(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread producer2 = new Thread(() -> {
            try {
                sbq.offer(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer1.start();
        producer2.start();
        producer1.join();
        producer2.join();
        assertThat(sbq.poll()).isEqualTo(1);
        assertThat(sbq.poll()).isEqualTo(2);
    }

    @Test
    public void whenAddThenRemove() throws InterruptedException {
        SimpleBlockingQueue sbq = new SimpleBlockingQueue<Integer>(2);
        Thread producer1 = new Thread(() -> {
            try {
                sbq.offer(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread producer2 = new Thread(() -> {
            try {
                sbq.offer(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consumer1 = new Thread(() -> {
            try {
                sbq.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer1.start();
        producer2.start();
        consumer1.start();
        producer1.join();
        producer2.join();
        consumer1.join();
        assertThat(sbq.poll()).isEqualTo(2);
    }

}