package org.example.statistics;

public class StatisticsPrinter {
    public static void print(StatisticsCollector stats, StatisticsType statsType) {
        IntegerStatistics intStats = stats.getIntegerStats();
        FloatStatistics floatStats = stats.getFloatStats();
        StringStatistics stringStats = stats.getStringStats();

        if (statsType == StatisticsType.SHORT) {
            printShort(intStats, floatStats, stringStats);
        } else {
            printFull(intStats, floatStats, stringStats);
        }
    }

    private static void printShort(IntegerStatistics intStats, FloatStatistics floatStats, StringStatistics stringStats) {
        if (intStats.getCount() > 0) {
            System.out.println("Integers: count=" + intStats.getCount());
        }
        if (floatStats.getCount() > 0) {
            System.out.println("Floats: count=" + floatStats.getCount());
        }
        if (stringStats.getCount() > 0) {
            System.out.println("Strings: count=" + stringStats.getCount());
        }
    }

    private static void printFull(IntegerStatistics intStats, FloatStatistics floatStats, StringStatistics stringStats) {
        if (intStats.getCount() > 0) {
            System.out.printf("Integers: count=%d, min=%d, max=%d, sum=%d, average=%.2f%n",
                    intStats.getCount(),
                    intStats.getMin(),
                    intStats.getMax(),
                    intStats.getSum(),
                    intStats.getAverage());
        }
        if (floatStats.getCount() > 0) {
            System.out.printf("Floats: count=%d, min=%.2f, max=%.2f, sum=%.2f, average=%.2f%n",
                    floatStats.getCount(),
                    floatStats.getMin(),
                    floatStats.getMax(),
                    floatStats.getSum(),
                    floatStats.getAverage());
        }
        if (stringStats.getCount() > 0) {
            System.out.printf("Strings: count=%d, min_length=%d, max_length=%d%n",
                    stringStats.getCount(),
                    stringStats.getMinLength(),
                    stringStats.getMaxLength());
        }
    }
}