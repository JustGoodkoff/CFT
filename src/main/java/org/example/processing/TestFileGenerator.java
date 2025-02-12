package org.example.processing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class TestFileGenerator {
    public static String createTestFile() {
        List<String> lines = Arrays.asList(
                "123",
                "-123",
                "0",
                "qwertyuiop[]",
                "-3.14e5",
                "numbernumbernumber",
                "0123",
                "1234567890123456789012345678901234567890",
                "1234567890123456789012345678901234567890.5"
        );
        Path testFile = Paths.get("test_data.txt");
        try {
            Files.write(testFile, lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return testFile.toString();
    }
}
