package org.example.statistics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class IntegerStatistics {
    private long count = 0;
    private BigInteger min = null;
    private BigInteger max = null;
    private BigInteger sum = BigInteger.ZERO;

    public void add(BigInteger value) {
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
    public BigInteger getMin() { return min; }
    public BigInteger getMax() { return max; }
    public BigInteger getSum() { return sum; }
    public BigDecimal getAverage() { return count == 0 ? BigDecimal.ZERO
            : new BigDecimal(sum).divide(new BigDecimal(count), 10, RoundingMode.HALF_UP); }
}