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
import io.github.thunderrole.cryptochart.utils.UIUtils;

/**
 * 功能描述：
 *
 * @date 2021/12/30
 */
public class XAxis extends View implements BaseAxis {
    private int mScaleNums = 5;
    private Paint mPaint;
    private List<ChartEntry> mEntries;
    private float mBottomHeight = 30;

    public XAxis(Context context) {
        this(context,null);
    }

    public XAxis(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XAxis(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(UIUtils.dp2px(context,1f));
        mPaint.setColor(Color.BLACK);
    }

    @Override
    public float setVisibleEntry(List<ChartEntry> entries) {
        mEntries = entries;
        postInvalidate();
        return 0;
    }

    @Override
    public List<ChartEntry> getVisibleEntry() {
        return null;
    }

    @Override
    public void setFormat(String format) {

    }

    @Override
    public void setScaleNumber(int number) {
        mScaleNums = number;
        postInvalidate();
    }

    public float getBottomHeight(){
        return mBottomHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int height = getHeight();
        int width = getWidth();
        int yPosition = (int) (height - mBottomHeight);
        canvas.drawLine(0.0f,
                (float) yPosition,
                (float)width,
                (float)yPosition,mPaint);
    }
}
