package org.example.cli;


import org.apache.commons.cli.*;
import org.example.processing.TestFileGenerator;
import org.example.statistics.StatisticsType;

import java.util.List;

public class CLIParser {
    public static CommandLineArguments parse(String[] args) {
        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().argName("path").desc("Output directory").build());
        options.addOption(Option.builder("p").hasArg().argName("prefix").desc("File prefix").build());
        options.addOption(Option.builder("a").desc("Append mode").build());
        options.addOption(Option.builder("s").desc("Short statistics").build());
        options.addOption(Option.builder("f").desc("Full statistics").build());

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("Ошибка: система не смогла обработать аргументы командной строки");
            System.err.println("Проверьте корректность аргументов командной строки");
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("FileFilter", options);
            System.exit(1);
        }

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

        outputPath = replaceForbiddenCharacters(outputPath, "PATH");
        prefix = replaceForbiddenCharacters(prefix, "PREFIX");

        List<String> inputFiles = cmd.getArgList();
        if (inputFiles.isEmpty()) {
            try {
                String filename = TestFileGenerator.createTestFile();
                inputFiles.add(filename);
                System.out.println("""
                        Пользователь не ввел пути к файлам со входными данными.
                        Программа создала файл с тестовыми данными для демонстрации работы программы.
                        """);
            } catch (RuntimeException e) {
                System.err.println("""
                        Ошибка:
                        Пользователь не ввел пути к файлам со входными данными.
                        Программе не удалось создать файл с тестовыми данными для демонстрации работы программы.
                        """);
                System.exit(1);
            }
        }
        return new CommandLineArguments(outputPath, prefix, appendMode, statsType, inputFiles);
    }

    private static String replaceForbiddenCharacters(String input, String type) {
        String prefixForbiddenCharacters = "[/\\\\:*?\"<>|]";
        String pathForbiddenCharacters = "[\\\\:*?\"<>|]";
        return switch (type) {
            case "PATH" -> (input.startsWith("/") ? "." + input : input).replaceAll(pathForbiddenCharacters, "");
            case "PREFIX" -> input.replaceAll(prefixForbiddenCharacters, "");
            default -> input;
        };

    }

}