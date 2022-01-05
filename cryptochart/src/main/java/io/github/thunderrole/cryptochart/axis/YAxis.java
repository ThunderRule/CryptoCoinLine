package io.github.thunderrole.cryptochart.axis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

import io.github.thunderrole.cryptochart.model.ChartEntry;
import io.github.thunderrole.cryptochart.utils.EntryUtils;
import io.github.thunderrole.cryptochart.utils.LogUtils;
import io.github.thunderrole.cryptochart.utils.UIUtils;

/**
 * 功能描述：
 *
 * @date 2021/12/30
 */
public class YAxis extends View implements BaseAxis{
    private int mLableNum = 4;
    private List<ChartEntry> mEntries;
    private Paint mPaint;

    private float mBottomHeight = 30;

    public YAxis(Context context) {
        this(context,null);
    }

    public YAxis(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public YAxis(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setTextSize(20f);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    public void setVisibleEntry(List<ChartEntry> entries) {
        mEntries = entries;
        invalidate();
    }

    @Override
    public List<ChartEntry> getVisibleEntry() {
        return mEntries;
    }

    @Override
    public void setFormat(String format) {

    }

    @Override
    public void setLableNum(int number) {
        mLableNum = number;
    }

    @Override
    public int getLableNum() {
        return mLableNum;
    }

    @Override
    public void changeData(List<ChartEntry> entries) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mEntries != null && mEntries.size() != 0){
            ChartEntry maxPrice = EntryUtils.findMaxPrice(mEntries);
            ChartEntry minPrice = EntryUtils.findMinPrice(mEntries);

            float nextY = 0;
            float scaleInterval = (getHeight()- mBottomHeight) / mLableNum;
            float nextPrice = maxPrice.getHigh();
            float priceInterval = (maxPrice.getHigh() - minPrice.getLow())/mLableNum;
            LogUtils.d("maxHigh = "+maxPrice.getHigh()+", minLow = "+minPrice.getLow());

            for (int i = 0; i < mLableNum + 1; i++) {
                if (i== 0){
                    canvas.drawText(""+nextPrice,1,nextY+20,mPaint);
                }else if (i == mLableNum){
                    canvas.drawText(""+nextPrice,1,nextY,mPaint);
                }else {
                    canvas.drawText(""+nextPrice,1,nextY,mPaint);
                }
                nextPrice -= priceInterval;
                nextY += scaleInterval;
            }

        }
    }
}
