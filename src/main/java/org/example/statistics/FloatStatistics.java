package org.example.statistics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class FloatStatistics {
    private long count = 0;
    private BigDecimal min = null;
    private BigDecimal max = null;
    private BigDecimal sum = BigDecimal.ZERO;

    public void add(BigDecimal value) {
        if (count == 0) {
            max = value;
            min = value;
        }
        if (value.compareTo(min) < 0) min = value;
        if (value.compareTo(max) > 0) max = value;
        sum = sum.add(value);
        count += 1;
    }

    public long getCount() { return count; }
    public BigDecimal getMin() { return min; }
    public BigDecimal getMax() { return max; }
    public BigDecimal getSum() { return sum; }
    public BigDecimal getAverage() { return count == 0 ? BigDecimal.ZERO
            : sum.divide(new BigDecimal(count), 10, RoundingMode.UP); }
}