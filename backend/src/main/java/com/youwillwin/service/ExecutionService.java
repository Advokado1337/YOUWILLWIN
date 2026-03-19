package com.youwillwin.service;

import com.youwillwin.dto.ExecutionResult;
import com.youwillwin.dto.ExecutionResult.TestCaseResult;
import com.youwillwin.model.TestCase;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ExecutionService {

    private static final String SANDBOX_IMAGE = "youwillwin-sandbox";
    private static final int TIMEOUT_SECONDS = 10;
    private static final String MEMORY_LIMIT = "256m";

    private final CodePreparer codePreparer;
    private final OutputNormalizer outputNormalizer;

    public ExecutionService(CodePreparer codePreparer, OutputNormalizer outputNormalizer) {
        this.codePreparer = codePreparer;
        this.outputNormalizer = outputNormalizer;
    }

    public ExecutionResult execute(String code, String driverCode, List<TestCase> testCases) {
        Path tempDir = null;
        try {
            tempDir = codePreparer.prepare(code, driverCode);

            String compileError = compile(tempDir);
            if (compileError != null) {
                return new ExecutionResult("COMPILE_ERROR", List.of(), compileError);
            }

            List<TestCaseResult> results = new ArrayList<>();
            boolean allPassed = true;

            for (int i = 0; i < testCases.size(); i++) {
                TestCase tc = testCases.get(i);
                TestCaseResult result = runTestCase(tempDir, tc, i);
                results.add(result);
                if (!result.passed()) {
                    allPassed = false;
                }
            }

            String status = allPassed ? "PASS" : "FAIL";
            return new ExecutionResult(status, results, null);

        } catch (Exception e) {
            return new ExecutionResult("ERROR", List.of(), e.getMessage());
        } finally {
            codePreparer.cleanup(tempDir);
        }
    }

    private String compile(Path tempDir) throws IOException, InterruptedException {
        String hostPath = toDockerPath(tempDir);

        ProcessBuilder pb = new ProcessBuilder(
            "docker", "run", "--rm",
            "--network", "none",
            "--memory", MEMORY_LIMIT,
            "-v", hostPath + ":/code",
            SANDBOX_IMAGE,
            "javac", "/code/Solution.java"
        );
        pb.redirectErrorStream(true);

        Process process = pb.start();
        String output = new String(process.getInputStream().readAllBytes());
        boolean finished = process.waitFor(TIMEOUT_SECONDS, TimeUnit.SECONDS);

        if (!finished) {
            process.destroyForcibly();
            return "Compilation timed out";
        }

        if (process.exitValue() != 0) {
            return output;
        }
        return null;
    }

    private TestCaseResult runTestCase(Path tempDir, TestCase tc, int index)
            throws IOException, InterruptedException {

        String hostPath = toDockerPath(tempDir);

        ProcessBuilder pb = new ProcessBuilder(
            "docker", "run", "--rm", "-i",
            "--network", "none",
            "--memory", MEMORY_LIMIT,
            "--cpus", "1",
            "--stop-timeout", "5",
            "--pids-limit", "64",
            "-v", hostPath + ":/code",
            SANDBOX_IMAGE,
            "java", "-cp", "/code", "Solution"
        );

        Process process = pb.start();

        process.getOutputStream().write(tc.getInput().getBytes());
        process.getOutputStream().close();

        String stdout = new String(process.getInputStream().readAllBytes());
        String stderr = new String(process.getErrorStream().readAllBytes());
        boolean finished = process.waitFor(TIMEOUT_SECONDS, TimeUnit.SECONDS);

        String displayIn = tc.getDisplayInput() != null ? tc.getDisplayInput() : tc.getInput();
        String displayOut = tc.getDisplayOutput() != null ? tc.getDisplayOutput() : tc.getExpectedOutput();

        if (!finished) {
            process.destroyForcibly();
            return new TestCaseResult(index, false, displayIn, displayOut, "TIMEOUT");
        }

        if (process.exitValue() != 0) {
            return new TestCaseResult(index, false, displayIn, displayOut, stderr.isEmpty() ? stdout : stderr);
        }

        boolean passed = outputNormalizer.matches(stdout, tc.getExpectedOutput());
        return new TestCaseResult(index, passed, displayIn, displayOut,
                outputNormalizer.normalize(stdout));
    }

    private String toDockerPath(Path tempDir) {
        String path = tempDir.toAbsolutePath().toString();
        // Docker Desktop on Windows needs forward-slash paths like /c/Users/...
        // Convert C:\Users\... to /c/Users/...
        if (path.length() >= 2 && path.charAt(1) == ':') {
            String driveLetter = path.substring(0, 1).toLowerCase();
            path = "/" + driveLetter + path.substring(2).replace('\\', '/');
        }
        return path;
    }
}
