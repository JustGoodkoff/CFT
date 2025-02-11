package org.example.statistics;

public class FloatStatistics {
    private int count = 0;
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    private double sum = 0;

    public void add(double value) {
        count++;
        if (value < min) min = value;
        if (value > max) max = value;
        sum += value;
    }

    public int getCount() { return count; }
    public double getMin() { return min; }
    public double getMax() { return max; }
    public double getSum() { return sum; }
    public double getAverage() { return count == 0 ? 0 : sum / count; }
}