package ru.job4j.thread;

import java.net.URL;

public class Validator {

    public void validateArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Please provide both parameters");
        }
        validateURL(args[0]);
        if (0 >= Integer.parseInt(args[1])) {
            throw new IllegalArgumentException("Please provide a valid number");
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
