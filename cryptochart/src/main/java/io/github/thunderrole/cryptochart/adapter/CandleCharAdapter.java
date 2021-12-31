package io.github.thunderrole.cryptochart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.thunderrole.cryptochart.R;
import io.github.thunderrole.cryptochart.itemview.BarChartItem;
import io.github.thunderrole.cryptochart.itemview.CandleChartItem;
import io.github.thunderrole.cryptochart.model.ChartEntry;
import io.github.thunderrole.cryptochart.model.Point;
import io.github.thunderrole.cryptochart.utils.EntryUtils;

/**
 * 功能描述：
 *
 * @date 2021/12/31
 */
public class CandleCharAdapter extends BaseAdapter<CandleCharAdapter.CandleHolder> {

    @Override
    protected CandleHolder createHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_candle, parent, false);
        return new CandleHolder(view);
    }

    @Override
    protected void bindView(CandleHolder holder, int position) {
        List<ChartEntry> entries = mYAxis.getVisibleEntry();
        ChartEntry maxHigh = EntryUtils.finMaxPrice(entries);
        ChartEntry minLow = EntryUtils.finMaxPrice(entries);

        //TODO 查找区间最大最小值
        float scale = 0.5f;
        if (maxHigh != null && minLow != null) {
            float diff = maxHigh.getHigh() - minLow.getLow();
            scale = holder.candleItem.getHeight() / diff;
        }

        Point entry = mPoints.get(position);
        holder.candleItem.setPoint(entry, scale * mScale, minLow);
    }

    protected class CandleHolder extends RecyclerView.ViewHolder {
        private CandleChartItem candleItem;

        public CandleHolder(@NonNull View itemView) {
            super(itemView);
            candleItem = itemView.findViewById(R.id.cci_candle);
        }
    }

}
