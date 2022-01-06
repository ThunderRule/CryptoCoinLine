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
import io.github.thunderrole.cryptochart.utils.DateUtils;
import io.github.thunderrole.cryptochart.utils.UIUtils;

/**
 * 功能描述：
 *
 * @date 2021/12/30
 */
public class XAxis extends View implements BaseAxis {
    private int mLableNum = 5;
    private Paint mPaint;
    private List<ChartEntry> mLableEntries;
    private float mBottomHeight = 30;

    public XAxis(Context context) {
        this(context, null);
    }

    public XAxis(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XAxis(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(UIUtils.dp2px(context, 1f));
        mPaint.setColor(Color.LTGRAY);
    }

    @Override
    public void setVisibleEntry(List<ChartEntry> entries) {
    }

    @Override
    public List<ChartEntry> getVisibleEntry() {
        return null;
    }

    @Override
    public void setFormat(String format) {

    }

    @Override
    public void setLableNum(int number) {
        mLableNum = number;
        postInvalidate();
    }

    @Override
    public int getLableNum() {
        return mLableNum;
    }

    @Override
    public void changeData(List<ChartEntry> entries) {
        mLableEntries = entries;
        invalidate();
    }

    public float getBottomHeight() {
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
                (float) width,
                (float) yPosition, mPaint);

        int interval = getWidth() / mLableNum;
        int nextLineX = 0;
        for (int i = 0; i < mLableNum+1; i++) {
            canvas.drawLine(nextLineX,0,nextLineX,getHeight(),mPaint);

            if (i == 0){
                mPaint.setTextAlign(Paint.Align.LEFT);
            }else if (i == mLableNum){
                mPaint.setTextAlign(Paint.Align.RIGHT);
            }else {
                mPaint.setTextAlign(Paint.Align.CENTER);
            }

            if (mLableEntries != null && mLableEntries.size() == mLableNum+1){
                ChartEntry entry = mLableEntries.get(i);
                mPaint.setTextSize(20f);
                canvas.drawText(DateUtils.getInstance().long2Format(entry.getDate()), nextLineX,yPosition+20,mPaint);
//                canvas.drawText(entry.getDate()+"", nextLineX,yPosition+20,mPaint);
            }
            nextLineX += interval;
        }



    }
}
