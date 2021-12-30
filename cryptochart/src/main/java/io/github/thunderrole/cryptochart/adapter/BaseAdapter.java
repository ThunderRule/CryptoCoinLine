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
import io.github.thunderrole.cryptochart.utils.LogUtils;

/**
 * 功能描述：
 *
 * @date 2021/12/29
 */
public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<ChartEntry> mList = new ArrayList<>();
    protected YAxis mYAxis;
    protected BaseAxis mXAxis;


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return createHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        bindView(holder, position);
    }

    protected abstract VH createHolder(@NonNull ViewGroup parent, int viewType);

    protected abstract void bindView(VH holder, int position);

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

        notifyDataSetChanged();
    }

    public <D extends ChartEntry> void addFrontData(List<D> list){
        mList.addAll(0,list);
        notifyDataSetChanged();
    }

    public <D extends ChartEntry> void addLastData(List<D> list){
        int oldSize = mList.size();
        mList.addAll(list);
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
    }

}
