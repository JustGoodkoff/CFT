package org.example;

import org.example.statistics.StatisticsPrinter;

public class Main {
    public static void main(String[] args) {
        try {
//            CommandLineArguments cliArgs = CLIParser.parse(args);

            FileFilter.filter(args);

//            CommandLineArguments cliArgs = CLIParser.parse(args);
//            DataProcessor processor = new DataProcessor(cliArgs);
//            processor.processFiles(cliArgs.getInputFiles());
//
//            StatisticsPrinter.print(
//                    processor.getStatistics(),
//                    cliArgs.getStatsType()
//            );

//        } catch (ParseException e) {
//            System.err.println("Error parsing arguments: " + e.getMessage());
//            System.exit(1);

        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}