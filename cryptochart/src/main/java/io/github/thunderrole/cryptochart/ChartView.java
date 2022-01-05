package io.github.thunderrole.cryptochart;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.thunderrole.cryptochart.adapter.BarChartAdapter;
import io.github.thunderrole.cryptochart.adapter.BaseAdapter;
import io.github.thunderrole.cryptochart.adapter.CandleCharAdapter;
import io.github.thunderrole.cryptochart.axis.XAxis;
import io.github.thunderrole.cryptochart.axis.YAxis;
import io.github.thunderrole.cryptochart.model.ChartEntry;
import io.github.thunderrole.cryptochart.model.ChartStyle;
import io.github.thunderrole.cryptochart.model.Point;
import io.github.thunderrole.cryptochart.utils.LogUtils;
import io.github.thunderrole.cryptochart.utils.UIUtils;

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
    private List<ChartEntry> mList = new ArrayList<>();
    private int mChartType;

    private ScaleGestureDetector mGesture;
    private float mScaleFactor = 0.5f;

    public ChartView(@NonNull Context context) {
        this(context, null);
    }

    public ChartView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
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

        mGesture = new ScaleGestureDetector(context, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                mScaleFactor *= detector.getScaleFactor();
                if (mScaleFactor <= 0.05f) {
                    mScaleFactor = 0.05f;
                    return false;
                } else if (mScaleFactor >= 1f) {
                    mScaleFactor = 1f;
                    return false;
                }

                if (mAdapter != null) {
                    mAdapter.setScaleFactor(mScaleFactor);
                }
                return true;
            }
        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        ViewGroup.LayoutParams params = mRecyclerView.getLayoutParams();
        params.height = (int) (height - mXAxis.getBottomHeight());
        mRecyclerView.setLayoutParams(params);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGesture.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getPointerCount() > 1) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        switch (mChartType) {
            case ChartStyle.BAR_CHART:
                mAdapter = new BarChartAdapter(getMeasuredHeight() - UIUtils.dp2px(getContext(), 30f));
                break;
            case ChartStyle.CANDLE_CHART:
                mAdapter = new CandleCharAdapter(getMeasuredHeight() - UIUtils.dp2px(getContext(), 30f));
                break;
            default:
        }
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setYAxis(mYAxis);
        mAdapter.setData(mList);
        mYAxis.setVisibleEntry(getVisibleEntry());
        mRecyclerView.scrollToPosition(mList.size() - 1);

        mAdapter.setOnClickListener(new BaseAdapter.OnClickListener() {
            @Override
            public void onClick(ChartEntry entry) {
                switch (mChartType) {
                    case ChartStyle.CANDLE_CHART:
                        Toast.makeText(getContext(), entry.getDate() + "", Toast.LENGTH_LONG).show();
                        break;
                    default:
                }
            }
        });
    }

    public <D extends ChartEntry> void setData(int type, List<D> list) {
        mChartType = type;
        mList.clear();
        mList.addAll(list);
    }


    public void setXLableNum(int number) {
        mXAxis.setLableNum(number);
    }

    public void setYLableNum(int number) {
        mXAxis.setLableNum(number);
    }

    /**
     * 获取屏幕中显示的数据集
     *
     * @return
     */
    private List<ChartEntry> getVisibleEntry() {
        int firstPosition = mLayoutManaget.findFirstVisibleItemPosition();
        int lastPosition = mLayoutManaget.findLastVisibleItemPosition();
        LogUtils.d("firstP = " + firstPosition + ", lastP = " + lastPosition);
        List<ChartEntry> list = mAdapter.getData();
        if (firstPosition != -1 && lastPosition != -1) {
            if (list.size() > lastPosition + 1) {
                return list.subList(firstPosition, lastPosition + 1);
            } else {
                return list;
            }
        } else {
            return list;
        }
    }

    private void listenRecyclerScroll() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    mAdapter.notifyDataSetChanged();
                }
                LogUtils.d("滚动状态：" + newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LogUtils.d("滚动结果：" + dx);
                List<ChartEntry> visibleEntry = getVisibleEntry();
//                mXAxis.setVisibleEntry(visibleEntry);
                mYAxis.setVisibleEntry(visibleEntry);

                int lableNum = mXAxis.getLableNum();
                ArrayList<ChartEntry> lableEntry = new ArrayList<>();
                int interval = getWidth() / lableNum;
                int nextInterval = 0;
                for (int i = 0; i < lableNum + 1; i++) {
                    View view = recyclerView.findChildViewUnder(nextInterval, 30);
                    RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(view);
                    int position = holder.getAdapterPosition();
                    ChartEntry entry = mList.get(position);
                    lableEntry.add(entry);
                    nextInterval += interval;
                }
                mXAxis.changeData(lableEntry);
            }
        });

    }

}
