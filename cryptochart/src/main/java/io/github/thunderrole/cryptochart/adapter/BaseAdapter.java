package io.github.thunderrole.cryptochart.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.thunderrole.cryptochart.axis.BaseAxis;
import io.github.thunderrole.cryptochart.axis.YAxis;
import io.github.thunderrole.cryptochart.model.ChartEntry;
import io.github.thunderrole.cryptochart.model.LinkChartEntry;
import io.github.thunderrole.cryptochart.utils.EntryUtils;

/**
 * 功能描述：
 *
 * @date 2021/12/29
 */
public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected float mHeight;
    protected List<ChartEntry> mList = new ArrayList<>();
    protected List<LinkChartEntry> mLinkChartEntries = new ArrayList<>();
    protected YAxis mYAxis;
    protected BaseAxis mXAxis;
    protected float mScale = 1.f;
    protected float mScaleFactor = 0.5f;
    private OnClickListener mListener;

    public BaseAdapter(float height){
        mHeight = height;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return createHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        bindView(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onClick(mList.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    protected abstract VH createHolder(@NonNull ViewGroup parent, int viewType);

    protected abstract void bindView(VH holder, int position);

    public void setScaleFactor(float scaleFactor){
        mScaleFactor = scaleFactor;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<ChartEntry> getData(){
        return mList;
    }

    public void setYAxis(YAxis axis){
        mYAxis = axis;
    }

    public <D extends ChartEntry> void setData(List<D> list){
        mList.clear();
        mList.addAll(list);
        mLinkChartEntries = EntryUtils.createPoints(mList);
        notifyDataSetChanged();
    }

    public <D extends ChartEntry> void addFrontData(List<D> list){
        mList.addAll(0,list);
        mLinkChartEntries = EntryUtils.createPoints(mList);
        notifyDataSetChanged();
    }

    public <D extends ChartEntry> void addLastData(List<D> list){
        int oldSize = mList.size();
        mList.addAll(list);
        mLinkChartEntries = EntryUtils.createPoints(mList);
        notifyItemRangeInserted(oldSize, mList.size());
    }

    public void addLastData(ChartEntry entry){
        ChartEntry lastEntry = mList.get(mList.size());
        if (entry.getDate() > lastEntry.getDate()){
            mList.add(entry);
            notifyItemInserted(mList.size());
        }else if (entry.getDate() == lastEntry.getDate()){
            mList.remove(lastEntry);
            mList.add(entry);
            notifyItemChanged(mList.size());
        }
        mLinkChartEntries = EntryUtils.createPoints(mList);
    }

    public void setOnClickListener(OnClickListener listener){
        mListener = listener;
    }

    public interface OnClickListener{
        void onClick(ChartEntry entry);
    }


}
