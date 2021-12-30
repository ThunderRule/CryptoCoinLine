package io.github.thunderrole.cryptochart.itemview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import io.github.thunderrole.cryptochart.model.ChartEntry;
import io.github.thunderrole.cryptochart.utils.LogUtils;
import io.github.thunderrole.cryptochart.utils.UIUtils;

/**
 * 功能描述：
 *
 * @date 2021/12/29
 */
public class BarChartItem extends View {
    private Paint mPaint;
    private ChartEntry mEntry;
    private float mScale = 1.0f;

    public BarChartItem(Context context) {
        this(context, null);
    }

    public BarChartItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarChartItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setEntry(ChartEntry entry,float scale){
        mEntry = entry;
        mScale = scale;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        float realHeight = 0;

        if (mEntry != null){
            realHeight = height-mEntry.getValue()*mScale - UIUtils.dp2px(getContext(),30f);
        }
        canvas.drawRect(5,realHeight,width-5,height,mPaint);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(""+mEntry.getValue(),width/2,realHeight-8,mPaint);

    }

}
