package ru.job4j.io;

import java.io.*;

public class ParseFile {
    private final File file;
    private Content content;

    public ParseFile(File file, Content content) {
        this.file = file;
        this.content = content;
    }

    public String getContent() {
        content.acceptFile(file);
        return content.getFileContent(ch -> true);
    }
}
