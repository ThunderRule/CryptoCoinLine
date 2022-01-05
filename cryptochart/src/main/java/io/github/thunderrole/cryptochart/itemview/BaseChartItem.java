package io.github.thunderrole.cryptochart.itemview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import io.github.thunderrole.cryptochart.model.LinkChartEntry;
import io.github.thunderrole.cryptochart.utils.UIUtils;

/**
 * 功能描述：
 *
 * @date 2021/12/29
 */
public class BaseChartItem extends View {

    protected LinkChartEntry mLinkChartEntry;
    protected float mScale = 1.0f;
    protected float mFingerScale = 0.5f;


    public BaseChartItem(Context context) {
        super(context);
    }

    public BaseChartItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseChartItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = (int) (UIUtils.dp2px(getContext(),40f) * mFingerScale);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int spec = MeasureSpec.makeMeasureSpec(width, mode);
        setMeasuredDimension(spec,heightMeasureSpec);
    }

    public void setPoint(LinkChartEntry linkChartEntry, float scale, float fingerScale){
        mLinkChartEntry = linkChartEntry;
        mScale = scale;
        mFingerScale = fingerScale;
        invalidate();
    }
}
