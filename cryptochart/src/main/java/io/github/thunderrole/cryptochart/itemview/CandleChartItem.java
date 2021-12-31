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
 * @date 2021/12/31
 */
public class CandleChartItem extends BaseChartItem {
    private Paint mPaint;
    private ChartEntry minEntry;

    public CandleChartItem(Context context) {
        this(context, null);
    }

    public CandleChartItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CandleChartItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(UIUtils.dp2px(context, 1f));
    }

    public void setPoint(Point point, float scale, ChartEntry min) {
        minEntry = min;
        setPoint(point, scale);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ChartEntry entry = mPoint.getEntry();

        int height = getHeight();
        int width = getWidth();
        int mid = width / 2;
        float candleHeight = Math.abs(entry.getClose() - entry.getOpen()) * mScale;
        float top = 0;

        float openY = calculateDiff(entry.getOpen());
        float closeY = calculateDiff(entry.getClose());
        float highY = height - calculateDiff(entry.getHigh());
        float lowY = height - calculateDiff(entry.getLow());
        if (entry.getClose() > entry.getOpen()) {
            mPaint.setColor(Color.GREEN);
            top = height - closeY;
//            canvas.drawLine(mid, highY, mid, closeY, mPaint);
//            canvas.drawLine(mid, openY, mid, lowY, mPaint);
        } else {
            mPaint.setColor(Color.RED);
//            canvas.drawLine(mid, highY, mid, openY, mPaint);
//            canvas.drawLine(mid, closeY , mid, lowY, mPaint);
        }

        canvas.drawRect(5, top, width - 5, top + candleHeight, mPaint);

    }

    private float calculateDiff(float value) {
        if (minEntry != null) {
            return (value - minEntry.getLow()) * mScale;
        }
        return 0;
    }
}
