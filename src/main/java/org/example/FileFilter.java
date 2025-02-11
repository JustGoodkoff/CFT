package org.example;

import org.apache.commons.cli.ParseException;
import org.example.statistics.StatisticsPrinter;

public class FileFilter {

    public static void filter(String[] args) throws ParseException {
        CommandLineArguments cliArgs = CLIParser.parse(args);
        DataProcessor processor = new DataProcessor(cliArgs);
        processor.processFiles(cliArgs.getInputFiles());

        StatisticsPrinter.print(
                processor.getStatistics(),
                cliArgs.getStatsType()
        );
    }
}
