package ru.job4j.cas;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;

public class CASCountTest {

    @Test
    public void whenMultiThreadsIncrements() throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread one = new Thread(casCount::increment);
        Thread two = new Thread(casCount::increment);
        Thread three = new Thread(casCount::increment);
        Thread four = new Thread(casCount::increment);
        one.start();
        two.start();
        three.start();
        four.start();
        one.join();
        two.join();
        three.join();
        four.join();
        assertThat(casCount.get()).isEqualTo(4);
    }

}