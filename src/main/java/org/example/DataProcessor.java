package org.example;

import org.example.statistics.StatisticsCollector;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.*;
import java.util.List;

public class DataProcessor {
    private final Path outputPath;
    private final String prefix;
    private final boolean appendMode;
    private final StatisticsCollector statistics = new StatisticsCollector();

    private BufferedWriter integerWriter;
    private BufferedWriter floatWriter;
    private BufferedWriter stringWriter;

    public DataProcessor(CommandLineArguments cliArgs) {
        this.outputPath = Paths.get(cliArgs.getOutputPath());
        this.prefix = cliArgs.getPrefix();
        this.appendMode = cliArgs.isAppendMode();
    }

    public void processFiles(List<String> inputFiles) {
        for (String file : inputFiles) {
            processFile(file);
        }
        closeWriters();
    }

    private void processFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath + ": " + e.getMessage());
        }
    }

    private void processLine(String line) {
        if (isInteger(line)) {
            statistics.addInteger(line);
            writeLine(line, DataType.INTEGER);
        } else if (isDouble(line)) {
            statistics.addFloat(line);
            writeLine(line, DataType.FLOAT);
        } else {
            statistics.addString(line);
            writeLine(line, DataType.STRING);
        }
    }

    private boolean isInteger(String s) {
        try {
            new BigInteger(s);
//            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String s) {
        try {
            new BigDecimal(s);
//            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void writeLine(String line, DataType type) {
        BufferedWriter writer = null;
        switch (type) {
            case INTEGER:
                writer = getIntegerWriter();
                break;
            case FLOAT:
                writer = getFloatWriter();
                break;
            case STRING:
                writer = getStringWriter();
                break;
        }

        if (writer != null) {
            try {
                writer.write(line);
                writer.newLine();
            } catch (IOException e) {
                System.err.println("Error writing to " + type.name().toLowerCase() + " file: " + e.getMessage());
            }
        }
    }

    private BufferedWriter getIntegerWriter() {
        if (integerWriter == null) {
            integerWriter = createWriter(DataType.INTEGER);
        }
        return integerWriter;
    }

    private BufferedWriter getFloatWriter() {
        if (floatWriter == null) {
            floatWriter = createWriter(DataType.FLOAT);
        }
        return floatWriter;
    }

    private BufferedWriter getStringWriter() {
        if (stringWriter == null) {
            stringWriter = createWriter(DataType.STRING);
        }
        return stringWriter;
    }

    private BufferedWriter createWriter(DataType type) {
        String fileName = prefix + type.getFileName();
        Path filePath = outputPath.resolve(fileName);
        try {
            Files.createDirectories(filePath.getParent());
            boolean append = appendMode && Files.exists(filePath);
            return new BufferedWriter(new FileWriter(filePath.toFile(), append));
        } catch (IOException e) {
            System.err.println("Failed to create writer for " + type.name().toLowerCase() + ": " + e.getMessage());
            return null;
        }
    }

    private void closeWriters() {
        closeWriter(integerWriter, "integer");
        closeWriter(floatWriter, "float");
        closeWriter(stringWriter, "string");
    }

    private void closeWriter(BufferedWriter writer, String type) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                System.err.println("Error closing " + type + " writer: " + e.getMessage());
            }
        }
    }

    public StatisticsCollector getStatistics() {
        return statistics;
    }

    private enum DataType {
        INTEGER("integers.txt"),
        FLOAT("floats.txt"),
        STRING("strings.txt");

        private final String fileName;

        DataType(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }
}