package ru.job4j.thread;

import java.io.File;
import java.net.URL;

public class Validator {

    public void validateArgs(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Please provide all parameters: URL SPEED TARGET_FILE");
        }
        validateURL(args[0]);
        if (0 >= Integer.parseInt(args[1])) {
            throw new IllegalArgumentException("Please provide a valid number");
        }
        if (!(new File(args[2]).exists())) {
            throw new IllegalArgumentException("File does not exist. Create a required file");
        }
    }

    public void validateURL(String url) {
        try {
            new URL(url).toURI();
        } catch (Exception e) {
            throw new IllegalArgumentException("Please provide a valid URL");
        }
    }
}
