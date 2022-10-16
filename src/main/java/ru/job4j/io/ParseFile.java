package ru.job4j.io;

import java.io.*;

public class ParseFile {
    private final File file;
    private ReadContent readContent;
    private WriteContent writeContent;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent() {
        readContent = new ReadContent(file);
        return readContent.readContent(ch -> true);
    }

    public synchronized String getContentWithoutUnicode() {
        readContent = new ReadContent(file);
        return readContent.readContentWithoutUnicode(ch -> ch < 0x80);
    }

    public synchronized void saveContent(String content) {
        writeContent = new WriteContent(file);
        writeContent.writeContent(content);
    }

}
