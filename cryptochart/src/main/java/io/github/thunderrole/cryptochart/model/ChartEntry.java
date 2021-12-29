package io.github.thunderrole.cryptochart.model;

/**
 * 功能描述：
 *
 * @date 2021/12/29
 */
public interface ChartEntry {
    float getOpen();
    float getClose();
    float getHigh();
    float getLow();
    float getValue();
    long getDate();
}
