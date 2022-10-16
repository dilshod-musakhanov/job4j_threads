package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteContent {
    private final File file;

    public WriteContent(File file) {
        this.file = file;
    }

    public synchronized void writeContent(String content) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i++) {
                out.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
