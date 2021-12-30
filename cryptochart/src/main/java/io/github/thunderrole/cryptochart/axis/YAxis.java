package io.github.thunderrole.cryptochart.axis;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

import io.github.thunderrole.cryptochart.model.ChartEntry;
import io.github.thunderrole.cryptochart.utils.LogUtils;

/**
 * 功能描述：
 *
 * @date 2021/12/30
 */
public class YAxis extends View implements BaseAxis{
    private List<ChartEntry> mEntries;

    public YAxis(Context context) {
        this(context,null);
    }

    public YAxis(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public YAxis(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public float setVisibleEntry(List<ChartEntry> entries) {
        mEntries = entries;
        return 0;
    }

    @Override
    public List<ChartEntry> getVisibleEntry() {
        return mEntries;
    }

    @Override
    public void setFormat(String format) {

    }

    @Override
    public void setScaleNumber(int number) {

    }
}
