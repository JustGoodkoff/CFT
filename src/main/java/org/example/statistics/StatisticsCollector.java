package org.example.statistics;

public class StatisticsCollector {
    private final IntegerStatistics integerStats = new IntegerStatistics();
    private final FloatStatistics floatStats = new FloatStatistics();
    private final StringStatistics stringStats = new StringStatistics();

    public void addInteger(String value) {
        int num = Integer.parseInt(value);
        integerStats.add(num);
    }

    public void addFloat(String value) {
        double num = Double.parseDouble(value);
        floatStats.add(num);
    }

    public void addString(String value) {
        stringStats.add(value);
    }

    public IntegerStatistics getIntegerStats() { return integerStats; }
    public FloatStatistics getFloatStats() { return floatStats; }
    public StringStatistics getStringStats() { return stringStats; }

}
