package io.github.thunderrole.cryptochart.utils;

import java.util.List;

import io.github.thunderrole.cryptochart.model.ChartEntry;

/**
 * 功能描述：
 *
 * @date 2021/12/30
 */
public class EntryUtils {

    public static ChartEntry findMaxValue(List<ChartEntry> list){
        float max = 0;
        ChartEntry maxEntry = null;
        if (list != null){
            for (ChartEntry entry : list) {
                float high = entry.getValue();
                if (high > max){
                    max = high;
                    maxEntry = entry;
                }
            }
        }
        return maxEntry;
    }

    public static ChartEntry findMinValue(List<ChartEntry> list){
        float min = Float.MAX_VALUE;
        ChartEntry minEntry = null;
        if (list != null){
            for (ChartEntry entry : list) {
                float low = entry.getValue();
                if (low < min){
                    min = low;
                    minEntry = entry;
                }
            }
        }
        return minEntry;
    }
}
