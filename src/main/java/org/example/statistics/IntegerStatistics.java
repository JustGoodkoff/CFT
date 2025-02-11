package org.example.statistics;

public class IntegerStatistics {
    private int count = 0;
    private int min = Integer.MAX_VALUE;
    private int max = Integer.MIN_VALUE;
    private long sum = 0;

    public void add(int value) {
        if (value < min) min = value;
        if (value > max) max = value;
        sum += value;
        count++;
    }

    public int getCount() { return count; }
    public int getMin() { return min; }
    public int getMax() { return max; }
    public long getSum() { return sum; }
    public double getAverage() { return count == 0 ? 0 : (double) sum / count; }
}