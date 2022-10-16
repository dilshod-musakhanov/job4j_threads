package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ReadContent implements Content<Character> {
    private File file;

    @Override
    public void acceptFile(File file) {
        this.file = file;
    }

    @Override
    public String getFileContent(Predicate<Character> filter) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) > 0) {
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
