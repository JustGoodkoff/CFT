package org.example.statistics;

public class StringStatistics {
    private long count = 0;
    private long minLength = Integer.MAX_VALUE;
    private long maxLength = 0;

    public void add(String value) {
        count++;
        int len = value.length();
        if (len < minLength) minLength = len;
        if (len > maxLength) maxLength = len;
    }

    public long getCount() { return count; }
    public long getMinLength() { return minLength; }
    public long getMaxLength() { return maxLength; }
}