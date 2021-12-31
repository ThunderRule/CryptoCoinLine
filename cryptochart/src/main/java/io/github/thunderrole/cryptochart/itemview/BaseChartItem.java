package io.github.thunderrole.cryptochart.itemview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import io.github.thunderrole.cryptochart.model.Point;
import io.github.thunderrole.cryptochart.utils.UIUtils;

/**
 * 功能描述：
 *
 * @date 2021/12/29
 */
public class BaseChartItem extends View {

    protected Point mPoint;
    protected float mScale = 1.0f;


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
        int width = (int) (UIUtils.dp2px(getContext(),10f) * mScale);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int spec = MeasureSpec.makeMeasureSpec(width, mode);
        setMeasuredDimension(spec,heightMeasureSpec);
    }

    public void setPoint(Point point,float scale){
        mPoint = point;
        mScale = scale;
        invalidate();
    }
}
