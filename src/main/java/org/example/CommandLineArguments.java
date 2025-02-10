package org.example;


import java.util.List;

public class CommandLineArguments {
    private final String outputPath;
    private final String prefix;
    private final boolean appendMode;
    private final StatisticsType statsType;
    private final List<String> inputFiles;

    public CommandLineArguments(
            String outputPath,
            String prefix,
            boolean appendMode,
            StatisticsType statsType,
            List<String> inputFiles
    ) {
        this.outputPath = outputPath;
        this.prefix = prefix;
        this.appendMode = appendMode;
        this.statsType = statsType;
        this.inputFiles = inputFiles;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isAppendMode() {
        return appendMode;
    }

    public StatisticsType getStatsType() {
        return statsType;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }
}