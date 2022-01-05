package io.github.thunderrole.cryptochart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 功能描述：
 *
 * @date 2021/12/31
 */
public class ScaledLayoutManager extends RecyclerView.LayoutManager {
    private int mHotizontalOffset;
    private int mFirstVisiPos;
    private int mLastVisiPos;
    private int mSumDx = 0;
    private int mTotalHeight = 0;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
            return;
        }
        if (getChildCount() == 0 && state.isPreLayout()) {
            return;
        }

        detachAndScrapAttachedViews(recycler);
        mHotizontalOffset = 0;
        mFirstVisiPos = 0;
        mLastVisiPos = getItemCount();

        fill(recycler, state);
    }

    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int rightOffset = 0;
        int minPos = mFirstVisiPos;
        mLastVisiPos = getItemCount() - 1;
        for (int i = 0; i <= getItemCount(); i++) {
            View child = recycler.getViewForPosition(i);
            addView(child);
            measureChildWithMargins(child, 0, 0);
            int width = getDecoratedMeasuredWidth(child);
            int height = getDecoratedMeasuredHeight(child);
            layoutDecorated(child, rightOffset, 0, rightOffset + width, height);
            rightOffset+= width;
        }

        mTotalHeight = Math.max(rightOffset, getHotizontalSpace());
    }

    private int getHotizontalSpace(){
        return getWidth() - getPaddingLeft() - getPaddingRight() - getPaddingStart() - getPaddingEnd();
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int travel = dx;
        if (mSumDx + dx < 0){
            travel = -mSumDx;
        }else if(mSumDx +dx >mTotalHeight - getHotizontalSpace()){
            travel = mTotalHeight - getHotizontalSpace() - mSumDx;
        }
        mSumDx += travel;
        offsetChildrenHorizontal(-travel);
        return dx;
    }
}
