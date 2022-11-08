package ru.job4j.pools;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public String toString() {
            return "Sums{"
                    + "rowSum=" + rowSum
                    + ", colSum=" + colSum
                    + '}';
        }

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
            int rowEl = 0;
            int colEl = 0;
            for (int j = 0; j < matrix.length; j++) {
                rowEl += matrix[index][j];
                colEl += matrix[j][index];
            }
            return new Sums(rowEl, colEl);
        }

        public static void main(String[] args) throws ExecutionException, InterruptedException {
            int[][] m = new int[][] {{1, 2}, {3, 4}};
            System.out.println(Arrays.toString(Sums.sum(m)));
            System.out.println(Arrays.toString(Sums.asyncSum(m)));
        }
    }
}
