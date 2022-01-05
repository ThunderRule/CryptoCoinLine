package io.github.thunderrole.cryptochart.itemview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import io.github.thunderrole.cryptochart.model.ChartEntry;
import io.github.thunderrole.cryptochart.model.LinkChartEntry;
import io.github.thunderrole.cryptochart.model.Point;
import io.github.thunderrole.cryptochart.utils.DrawUtils;
import io.github.thunderrole.cryptochart.utils.UIUtils;

/**
 * 功能描述：
 *
 * @date 2021/12/31
 */
public class CandleChartItem extends BaseChartItem {
    private static final String TAG = "CandleChartItem";
    private Paint mPaint;
    private Path mPath;
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
        mPaint.setAntiAlias(true);

        mPath = new Path();
    }

    public void setPoint(LinkChartEntry linkChartEntry, float scale, float fingerScale, ChartEntry min) {
        minEntry = min;
        setPoint(linkChartEntry, scale, fingerScale);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ChartEntry entry = mLinkChartEntry.getEntry();

        int height = getHeight();
        int width = getWidth();
        int mid = width / 2;
        float candleHeight = Math.abs(entry.getClose() - entry.getOpen()) * mScale;
        float top = 0;

        float openY = height - calculateDiff(entry.getOpen());
        float closeY = height - calculateDiff(entry.getClose());
        float highY = height - calculateDiff(entry.getHigh());
        float lowY = height - calculateDiff(entry.getLow());
        if (entry.getClose() > entry.getOpen()) {
            mPaint.setColor(Color.GREEN);
            top = closeY;
        } else {
            mPaint.setColor(Color.RED);
            top = openY;
        }

        //绘制蜡烛图
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawLine(mid, highY, mid, top, mPaint);
        canvas.drawRect(5 * mFingerScale, top, width - 5 * mFingerScale, top + Math.max(UIUtils.dp2px(getContext(), 1f), candleHeight), mPaint);
        canvas.drawLine(mid, top + candleHeight, mid, lowY, mPaint);

        //绘制曲线图
//        ChartEntry preEntry = mLinkChartEntry.getPreEntry();
//        ChartEntry afterEntry = mLinkChartEntry.getAfterEntry();
//        mPaint.setColor(Color.BLACK);
//        mPaint.setStyle(Paint.Style.STROKE);
//        if (preEntry == null) {
//            float afterLowY = height - calculateDiff(afterEntry.getLow());
//            mPath.moveTo(mid, lowY);
//            mPath.cubicTo(width, lowY, width, afterLowY, mid + width, afterLowY);
//        } else if (afterEntry == null) {
//            float preLowY = height - calculateDiff(preEntry.getLow());
//            mPath.moveTo(-mid, preLowY);
//            mPath.cubicTo(0, preLowY, 0, lowY, mid, lowY);
//        } else {
//            float preLowY = height - calculateDiff(preEntry.getLow());
//            float afterLowY = height - calculateDiff(afterEntry.getLow());
//
//            mPath.moveTo(-mid, preLowY);
//            mPath.cubicTo(0, preLowY, 0, lowY, mid, lowY);
//            mPath.cubicTo(width, lowY, width, afterLowY, mid + width, afterLowY);
//            canvas.drawPath(mPath, mPaint);
//            mPath.reset();
//        }

    }

    private float calculateDiff(float value) {
        if (minEntry != null) {
            return (value - minEntry.getLow()) * mScale;
        }
        return 0;
    }
}
