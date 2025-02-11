package org.example;


import org.apache.commons.cli.*;
import org.example.statistics.StatisticsType;

import java.text.ParseException;
import java.util.List;

public class CLIParser {
    public static CommandLineArguments parse(String[] args) throws org.apache.commons.cli.ParseException {
        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().argName("path").desc("Output directory").build());
        options.addOption(Option.builder("p").hasArg().argName("prefix").desc("File prefix").build());
        options.addOption(Option.builder("a").desc("Append mode").build());
        options.addOption(Option.builder("s").desc("Short statistics").build());
        options.addOption(Option.builder("f").desc("Full statistics").build());

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        boolean isShort = cmd.hasOption("s");
        boolean isFull = cmd.hasOption("f");
        if (isShort && isFull) {
            isShort = false;

        }
        if (!isShort && !isFull) {
            isShort = true;
        }

        StatisticsType statsType = isShort ? StatisticsType.SHORT : StatisticsType.FULL;

        String outputPath = cmd.getOptionValue("o", ".");
        String prefix = cmd.getOptionValue("p", "");
        boolean appendMode = cmd.hasOption("a");

        List<String> inputFiles = cmd.getArgList();
        if (inputFiles.isEmpty()) {
//            throw new ParseException("No input files specified", 1);
        }

        return new CommandLineArguments(outputPath, prefix, appendMode, statsType, inputFiles);
    }
}