package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private static final int BUFFER_IN_BYTES = 1024;
    private static final int OFF = 0;
    private static final int OUT_OF = -1;
    private static final int MILLISECONDS = 1000;
    private final String url;
    private final int speed;
    private final String fileName;

    public Wget(String url, int speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[BUFFER_IN_BYTES];
            int bytesRead;
            int downloadData = 0;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, OFF, BUFFER_IN_BYTES)) != OUT_OF) {
                fileOutputStream.write(dataBuffer, OFF, bytesRead);
                downloadData += bytesRead;
                if (downloadData >= speed) {
                    long finish = System.currentTimeMillis();
                    long period = finish - start;
                    if (period < MILLISECONDS) {
                        Thread.sleep(MILLISECONDS - period);
                    }
                    downloadData = 0;
                    start = System.currentTimeMillis();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Validator val = new Validator();
        val.validateArgs(args);
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(args[0], speed, args[2]));
        wget.start();
        wget.join();
    }
}
