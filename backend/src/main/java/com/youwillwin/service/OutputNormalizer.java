package com.youwillwin.service;

import org.springframework.stereotype.Component;

@Component
public class OutputNormalizer {

    public String normalize(String output) {
        if (output == null) return "";
        return output.lines()
            .map(String::stripTrailing)
            .reduce((a, b) -> a + "\n" + b)
            .orElse("")
            .stripTrailing();
    }

    public boolean matches(String actual, String expected) {
        return normalize(actual).equals(normalize(expected));
    }
}
