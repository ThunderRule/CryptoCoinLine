package io.github.thunderrole.cryptochart.utils;

import java.util.ArrayList;
import java.util.List;

import io.github.thunderrole.cryptochart.model.ChartEntry;
import io.github.thunderrole.cryptochart.model.LinkChartEntry;

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

    public static ChartEntry findMaxPrice(List<ChartEntry> list){
        float max = 0;
        ChartEntry maxEntry = null;
        if (list != null){
            for (ChartEntry entry : list) {
                float high = entry.getHigh();
                if (high > max){
                    max = high;
                    maxEntry = entry;
                }
            }
        }
        return maxEntry;
    }

    public static ChartEntry findMinPrice(List<ChartEntry> list){
        float min = Float.MAX_VALUE;
        ChartEntry minEntry = null;
        if (list != null){
            for (ChartEntry entry : list) {
                float low = entry.getLow();
                if (low < min){
                    min = low;
                    minEntry = entry;
                }
            }
        }
        return minEntry;
    }

    public static List<LinkChartEntry> createPoints(List<ChartEntry> list){
        ArrayList<LinkChartEntry> linkChartEntries = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0){
                linkChartEntries.add(new LinkChartEntry(list.get(i),null,list.get(i+1)));
            }else if (i < list.size()-1){
                linkChartEntries.add(new LinkChartEntry(list.get(i),list.get(i-1),list.get(i+1)));
            }else {
                linkChartEntries.add(new LinkChartEntry(list.get(i),list.get(i-1),null));
            }
        }
        return linkChartEntries;
    }
}
