package org.example;


import org.example.statistics.StatisticsType;

import java.util.List;

public record CommandLineArguments(String outputPath, String prefix, boolean appendMode, StatisticsType statsType,
                                   List<String> inputFiles) {
}