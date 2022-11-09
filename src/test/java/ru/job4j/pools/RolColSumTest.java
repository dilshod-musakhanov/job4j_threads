package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

public class RolColSumTest {

    @Test
    public void whenSum() {
        int[][] matrix = new int[][] {{1, 2}, {3, 4}};
        Sums[] res = {new Sums(3, 4), new Sums(7, 6)};
        assertThat(RolColSum.sum(matrix)).isEqualTo(res);
    }

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][] {{1, 2}, {3, 4}};
        Sums[] res = {new Sums(3, 4), new Sums(7, 6)};
        assertThat(RolColSum.asyncSum(matrix)).isEqualTo(res);
    }
}