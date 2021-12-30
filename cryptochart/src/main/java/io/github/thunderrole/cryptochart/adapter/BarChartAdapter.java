package io.github.thunderrole.cryptochart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.thunderrole.cryptochart.R;
import io.github.thunderrole.cryptochart.itemview.BarChartItem;
import io.github.thunderrole.cryptochart.model.ChartEntry;
import io.github.thunderrole.cryptochart.utils.EntryUtils;
import io.github.thunderrole.cryptochart.utils.LogUtils;

/**
 * 功能描述：
 *
 * @date 2021/12/30
 */
public class BarChartAdapter extends BaseAdapter<BarChartAdapter.BarHolder>{

    @Override
    protected BarHolder createHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base, parent, false);
        return new BarHolder(view);
    }

    @Override
    protected void bindView(BarHolder holder, int position) {
        List<ChartEntry> entries = mYAxis.getVisibleEntry();
        ChartEntry maxHigh = EntryUtils.findMaxValue(entries);
        ChartEntry minLow = EntryUtils.findMinValue(entries);
        if (maxHigh == null){
            maxHigh = EntryUtils.findMaxValue(mList);
        }
        if (minLow == null){
            minLow = EntryUtils.findMinValue(mList);
        }
        float scale = 10f;
//        if (maxHigh != null && minLow != null){
//            float diff = maxHigh.getValue() - minLow.getValue();
//            scale = holder.barItem.getHeight() / diff;
//        }

        ChartEntry entry = mList.get(position);
        holder.barItem.setEntry(entry,scale);

    }

    class BarHolder extends RecyclerView.ViewHolder{
        private BarChartItem barItem;

        public BarHolder(@NonNull View itemView) {
            super(itemView);
            barItem = itemView.findViewById(R.id.bci_bar);
        }
    }

}
