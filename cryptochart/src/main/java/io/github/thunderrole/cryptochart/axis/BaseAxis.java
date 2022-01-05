package io.github.thunderrole.cryptochart.axis;

import java.util.List;

import io.github.thunderrole.cryptochart.model.ChartEntry;

/**
 * 功能描述：
 *
 * @date 2021/12/30
 */
public interface BaseAxis {

    void setVisibleEntry(List<ChartEntry> entries);

    List<ChartEntry> getVisibleEntry();

    void setFormat(String format);

    void setLableNum(int number);

    void changeData(List<ChartEntry> entries);

    int getLableNum();

}
