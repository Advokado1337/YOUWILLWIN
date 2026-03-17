package com.youwillwin.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class CodePreparer {

    private static final String IMPORTS = """
            import java.util.*;
            import java.io.*;
            import java.math.*;
            import java.util.stream.*;
            """;

    public Path prepare(String userCode, String driverCode) throws IOException {
        Path tempDir = Files.createTempDirectory("youwillwin-");

        StringBuilder sb = new StringBuilder();
        sb.append(IMPORTS);
        sb.append("\npublic class Solution {\n\n");
        sb.append(userCode);
        sb.append("\n\n");
        sb.append(driverCode);
        sb.append("\n}\n");

        Files.writeString(tempDir.resolve("Solution.java"), sb.toString());
        return tempDir;
    }

    public void cleanup(Path tempDir) {
        if (tempDir == null) return;
        try {
            Files.walk(tempDir)
                .sorted(java.util.Comparator.reverseOrder())
                .forEach(path -> {
                    try { Files.delete(path); } catch (IOException ignored) {}
                });
        } catch (IOException ignored) {}
    }
}
