package io.github.thunderrole.cryptochart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.thunderrole.cryptochart.R;
import io.github.thunderrole.cryptochart.itemview.CandleChartItem;
import io.github.thunderrole.cryptochart.model.ChartConstants;
import io.github.thunderrole.cryptochart.model.ChartEntry;
import io.github.thunderrole.cryptochart.model.LinkChartEntry;
import io.github.thunderrole.cryptochart.utils.EntryUtils;
import io.github.thunderrole.cryptochart.utils.LogUtils;

/**
 * 功能描述：
 *
 * @date 2021/12/31
 */
public class CandleCharAdapter extends BaseAdapter<CandleCharAdapter.CandleHolder> {

    private OnClickListener mListener;

    public CandleCharAdapter(float height) {
        super(height);
    }

    @Override
    protected CandleHolder createHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_candle, parent, false);
        return new CandleHolder(view);
    }

    @Override
    protected void bindView(CandleHolder holder, int position) {
        List<ChartEntry> entries = mYAxis.getVisibleEntry();
        ChartEntry maxHigh = EntryUtils.findMaxPrice(entries);
        ChartEntry minLow = EntryUtils.findMinPrice(entries);

        //TODO 查找区间最大最小值
        if (maxHigh != null && minLow != null) {
            float diff = maxHigh.getHigh() - minLow.getLow();
            mScale = mHeight/ diff;
        }

        LinkChartEntry entry = mLinkChartEntries.get(position);

        if (maxHigh == entry.getEntry()){
            holder.candleItem.setExtremumType(ChartConstants.MAX_VALUE_TYPE);
        }else if(minLow == entry.getEntry()){
            holder.candleItem.setExtremumType(ChartConstants.MIN_VALUE_TYPE);
        }else {
            holder.candleItem.setExtremumType(ChartConstants.NORMAL_VALUE_TYPE);
        }

        holder.candleItem.setPoint(entry, mScale, mScaleFactor, minLow);

    }

    protected class CandleHolder extends RecyclerView.ViewHolder {
        private CandleChartItem candleItem;

        public CandleHolder(@NonNull View itemView) {
            super(itemView);
            candleItem = itemView.findViewById(R.id.cci_candle);
        }
    }



}
