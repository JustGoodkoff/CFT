package org.example.statistics;

public class StringStatistics {
    private int count = 0;
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = 0;

    public void add(String value) {
        count++;
        int len = value.length();
        if (len < minLength) minLength = len;
        if (len > maxLength) maxLength = len;
    }

    public int getCount() { return count; }
    public int getMinLength() { return minLength; }
    public int getMaxLength() { return maxLength; }
}