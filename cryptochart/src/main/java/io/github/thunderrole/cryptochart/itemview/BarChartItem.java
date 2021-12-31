package io.github.thunderrole.cryptochart.itemview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import io.github.thunderrole.cryptochart.model.ChartEntry;
import io.github.thunderrole.cryptochart.model.Point;
import io.github.thunderrole.cryptochart.utils.UIUtils;

/**
 * 功能描述：
 *
 * @date 2021/12/29
 */
public class BarChartItem extends BaseChartItem {
    private Paint mPaint;

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
        mPaint.setStrokeWidth(UIUtils.dp2px(context,2f));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        float realHeight = 0;

        mPaint.setColor(Color.RED);
        ChartEntry entry = mPoint.getEntry();
        if (mPoint != null) {
            realHeight = height - entry.getValue() * mScale - UIUtils.dp2px(getContext(), 30f);
        }
        //TODO 柱子间隔缩放
        canvas.drawRect(5, realHeight, width - 5, height, mPaint);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("" + entry.getValue(), width / 2, realHeight - 8, mPaint);

        ChartEntry preEntry = mPoint.getPreEntry();
        ChartEntry afterEntry = mPoint.getAfterEntry();

        int mid = width / 2;
        mPaint.setColor(Color.GREEN);
        if (preEntry == null) {
            float diff = afterEntry.getValue() - entry.getValue();
            canvas.drawLine(mid, realHeight, width, realHeight - diff * mScale / 2, mPaint);
        } else if (afterEntry == null) {
            float diff = preEntry.getValue() - entry.getValue();
            canvas.drawLine(0, realHeight - diff * mScale / 2, mid, realHeight, mPaint);
        } else {
            float preDiff = preEntry.getValue() - entry.getValue();
            float backDiff = afterEntry.getValue() - entry.getValue();
            canvas.drawLine(0, realHeight - preDiff * mScale / 2, mid, realHeight, mPaint);
            canvas.drawLine(mid, realHeight, width, realHeight - backDiff * mScale / 2, mPaint);
        }
    }

}
