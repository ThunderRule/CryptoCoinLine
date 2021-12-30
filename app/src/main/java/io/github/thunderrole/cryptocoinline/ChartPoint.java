package io.github.thunderrole.cryptocoinline;

import io.github.thunderrole.cryptochart.model.ChartEntry;

/**
 * 功能描述：
 *
 * @date 2021/12/30
 */
public class ChartPoint implements ChartEntry {
    private float open;
    private float close;
    private float high;
    private float low;
    private float value;
    private long date;

    public ChartPoint(float open, float close, float high, float low, float value, long date) {
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.value = value;
        this.date = date;
    }

    @Override
    public float getOpen() {
        return open;
    }

    @Override
    public float getClose() {
        return close;
    }

    @Override
    public float getHigh() {
        return high;
    }

    @Override
    public float getLow() {
        return low;
    }

    @Override
    public float getValue() {
        return value;
    }

    @Override
    public long getDate() {
        return date;
    }
}
