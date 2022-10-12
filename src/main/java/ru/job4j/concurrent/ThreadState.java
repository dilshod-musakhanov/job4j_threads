package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        boolean stop = true;
        Thread first = new Thread(
                () -> { }
        );
        Thread second = new Thread(
                () -> { }
        );
        System.out.println(first.getState());
        System.out.println(second.getState());
        first.start();
        second.start();
        while (stop) {
            System.out.println(first.getName());
            System.out.println(second.getName());
            if (first.getState() == Thread.State.TERMINATED && second.getState() == Thread.State.TERMINATED) {
                stop = false;
                System.out.println("Both threads got terminated now");
            }
        }
      }
}
