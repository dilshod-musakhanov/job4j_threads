package ru.job4j.cas;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CASCountTest {

    @Test
    public void whenMultiThreadsIncrements() {
        CASCount casCount = new CASCount();
        List<Thread> threadList = List.of(
                new Thread(casCount::increment),
                new Thread(casCount::increment),
                new Thread(casCount::increment),
                new Thread(casCount::increment)
        );
        threadList.forEach(Thread::start);
        for (Thread th : threadList) {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertThat(casCount.get()).isEqualTo(threadList.size());
    }

}