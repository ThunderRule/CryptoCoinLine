package io.github.thunderrole.cryptochart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.thunderrole.cryptochart.adapter.BarChartAdapter;
import io.github.thunderrole.cryptochart.adapter.BaseAdapter;
import io.github.thunderrole.cryptochart.axis.BaseAxis;
import io.github.thunderrole.cryptochart.axis.XAxis;
import io.github.thunderrole.cryptochart.axis.YAxis;
import io.github.thunderrole.cryptochart.model.ChartEntry;
import io.github.thunderrole.cryptochart.model.ChartStyle;
import io.github.thunderrole.cryptochart.utils.LogUtils;

/**
 * 功能描述：
 *
 * @date 2021/12/29
 */
public class ChartView extends FrameLayout {
    private static final String TAG = "ChartView";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManaget;
    private XAxis mXAxis;
    private YAxis mYAxis;
    private BaseAdapter<? extends RecyclerView.ViewHolder> mAdapter;

    public ChartView(@NonNull Context context) {
        this(context,null);
    }

    public ChartView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ChartView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mXAxis = new XAxis(context);
        mYAxis = new YAxis(context);

        mRecyclerView = new RecyclerView(context);
        mLayoutManaget = new LinearLayoutManager(context);
        mLayoutManaget.setOrientation(RecyclerView.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManaget);


        listenRecyclerScroll();

        addView(mXAxis);
        addView(mRecyclerView);
        addView(mYAxis);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        ViewGroup.LayoutParams params = mRecyclerView.getLayoutParams();
        params.height = (int) (height - mXAxis.getBottomHeight());
        mRecyclerView.setLayoutParams(params);
    }

    public <D extends ChartEntry> void setData(int type, List<D> list){
        switch (type){
            case ChartStyle.BAR_CHART:
                mAdapter = new BarChartAdapter();
                mAdapter.setYAxis(mYAxis);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.setData(list);
                mYAxis.setVisibleEntry(getVisibleEntry());
                break;
            default:
        }
    }

    private float caculateItemWith(){
        View child = mRecyclerView.getChildAt(0);
        if (child != null){
            return child.getWidth();
        }else {
            return -1;
        }
    }

    public void setXScaleNumber(int number){
        mXAxis.setScaleNumber(number);
    }
    public void setYScaleNumber(int number){
        mXAxis.setScaleNumber(number);
    }

    /**
     * 获取屏幕中显示的数据集
     * @return
     */
    private List<ChartEntry> getVisibleEntry(){
        int firstPosition = mLayoutManaget.findFirstVisibleItemPosition();
        int lastPosition = mLayoutManaget.findLastVisibleItemPosition();
        List<ChartEntry> list = mAdapter.getData();
        if (firstPosition == -1 && lastPosition == -1){
            int count = (int) (getWidth() / caculateItemWith());
            if (list.size() > count){
                return list.subList(0,count);
            }else {
                return list;
            }
        }else{
            if (list.size() > lastPosition){
                return list.subList(firstPosition,lastPosition);
            }else {
                return list;
            }
        }
    }

    private void listenRecyclerScroll(){
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                List<ChartEntry> visibleEntry = getVisibleEntry();
                mXAxis.setVisibleEntry(visibleEntry);
                mYAxis.setVisibleEntry(visibleEntry);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

}
