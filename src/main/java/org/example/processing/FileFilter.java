package org.example.processing;

import org.example.cli.CLIParser;
import org.example.cli.CommandLineArguments;
import org.example.statistics.StatisticsPrinter;

public class FileFilter {

    public static void filter(String[] args){
        CommandLineArguments cliArgs = CLIParser.parse(args);
        DataProcessor processor = new DataProcessor(cliArgs);
        processor.processFiles(cliArgs.inputFiles());

        StatisticsPrinter.print(
                processor.getStatistics(),
                cliArgs.statsType()
        );
    }
}
