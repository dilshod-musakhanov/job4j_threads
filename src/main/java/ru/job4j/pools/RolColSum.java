package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        Sums[] res = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            res[i] = getSums(i, matrix);
        }
        return res;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] res = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int index = i;
            res[i] = CompletableFuture.supplyAsync(() ->
                    getSums(index, matrix)).get();
        }
        return res;
    }

    public static Sums getSums(int index, int[][] matrix) {
        int row = 0;
        int column = 0;
        for (int j = 0; j < matrix.length; j++) {
            row += matrix[index][j];
            column += matrix[j][index];
        }
        return new Sums(row, column);
    }
}
