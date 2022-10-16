package ru.job4j.io;

import java.io.File;
import java.util.function.Predicate;

public interface Content<T> {
    void acceptFile(File file);
    String getFileContent(Predicate<T> filter);
}
