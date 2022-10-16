package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ReadContent {
    private final File file;

    public ReadContent(File file) {
        this.file = file;
    }

    public synchronized String readContent(Predicate<Character> filter) {
        return getFileContent(filter);
    }

    public synchronized String readContentWithoutUnicode(Predicate<Character> filter) {
        return getFileContent(filter);
    }

    public String getFileContent(Predicate<Character> filter) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) != -1) {
                if (filter.test((char) data)) {
                    stringBuilder.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
