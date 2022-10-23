package ru.job4j.pool;

import ru.job4j.wait.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final static int SIZE = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool(int totalTasks) {
        this.tasks = new SimpleBlockingQueue<>(totalTasks);
        init();
    }

    private void init() {
        for (int i = 0; i < SIZE; i++) {
            threads.add(new Thread(
                    () -> {
                        while (!Thread.interrupted()) {
                            try {
                                Runnable task = tasks.poll();
                                task.run();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
            ));
        }
        threads.forEach(Thread::start);
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

}

