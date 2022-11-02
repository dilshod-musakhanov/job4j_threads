package ru.job4j.pools;

import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {

    private final static int INDEX_NOT_FOUND = -1;
    private final static int MIN_SIZE = 10;
    private final T[] array;
    private final T object;
    private final int from;
    private final int to;

    public ParallelIndexSearch(T[] array, T object, int from, int to) {
        this.array = array;
        this.object = object;
        this.from = from;
        this.to = to;
    }

    private int findIndex() {
        int res = INDEX_NOT_FOUND;
        for (int i = from; i < to; i++) {
            if (Objects.equals(array[i], object)) {
                res = i;
                break;
            }
        }
        return res;
    }

    @Override
    protected Integer compute() {
        if (to - from < MIN_SIZE) {
            return findIndex();
        }
        int mid = (from + to) / 2;
        ParallelIndexSearch<T> leftSearch = new ParallelIndexSearch<>(array, object, from, mid);
        ParallelIndexSearch<T> rightSearch = new ParallelIndexSearch<>(array, object, mid + 1, to);
        leftSearch.fork();
        rightSearch.fork();
        int leftResult = leftSearch.join();
        int rightResult = rightSearch.join();
        if (leftResult != INDEX_NOT_FOUND) {
            return leftResult;
        }
        if (rightResult != INDEX_NOT_FOUND) {
            return rightResult;
        }
        return INDEX_NOT_FOUND;
    }

    public static <T> Integer find(T[] array, T object) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ParallelIndexSearch<T> search = new ParallelIndexSearch<>(array, object, 0, array.length - 1);
        return forkJoinPool.invoke(search);
    }

}
