package org.example.statistics;

import java.math.BigDecimal;
import java.math.BigInteger;

public class StatisticsCollector {
    private final IntegerStatistics integerStats = new IntegerStatistics();
    private final FloatStatistics floatStats = new FloatStatistics();
    private final StringStatistics stringStats = new StringStatistics();

    public void addInteger(String value) {
        BigInteger num = new BigInteger(value);
        integerStats.add(num);
    }

    public void addFloat(String value) {
        BigDecimal num = new BigDecimal(value);
        floatStats.add(num);
    }

    public void addString(String value) {
        stringStats.add(value);
    }

    public IntegerStatistics getIntegerStats() { return integerStats; }
    public FloatStatistics getFloatStats() { return floatStats; }
    public StringStatistics getStringStats() { return stringStats; }

}
