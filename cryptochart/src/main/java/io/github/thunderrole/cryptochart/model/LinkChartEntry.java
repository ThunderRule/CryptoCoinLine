package io.github.thunderrole.cryptochart.model;

import java.util.List;

/**
 * 功能描述：
 *
 * @date 2021/12/31
 */
public class LinkChartEntry {
    private ChartEntry entry;
    private ChartEntry preEntry;
    private ChartEntry afterEntry;

    public LinkChartEntry(ChartEntry entry, ChartEntry preEntry, ChartEntry afterEntry) {
        this.entry = entry;
        this.preEntry = preEntry;
        this.afterEntry = afterEntry;
    }

    public ChartEntry getEntry() {
        return entry;
    }

    public ChartEntry getPreEntry() {
        return preEntry;
    }

    public ChartEntry getAfterEntry() {
        return afterEntry;
    }
}
