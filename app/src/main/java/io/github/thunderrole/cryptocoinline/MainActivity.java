package io.github.thunderrole.cryptocoinline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;

import io.github.thunderrole.cryptochart.ChartView;
import io.github.thunderrole.cryptochart.adapter.BarChartAdapter;
import io.github.thunderrole.cryptochart.model.ChartStyle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ChartPoint> points = new ArrayList<>();
        points.add(new ChartPoint(48364.06f,48882.77f,48537.5f,41497.5f,7.04212f,1640836923));
        points.add(new ChartPoint(48882.77f,47639.75f,48897.5f,45497.5f,8.04212f,1640750523));
        points.add(new ChartPoint(47639.75f,46148.67f,49537.5f,41497.5f,2.04212f,1640664123));
        points.add(new ChartPoint(46148.67f,46844.86f,47537.5f,42497.5f,1.04212f,1640577723));
        points.add(new ChartPoint(46844.86f,46699.10f,48537.5f,40497.5f,10.04212f,1640491323));
        points.add(new ChartPoint(46699.10f,46916.22f,47537.5f,46497.5f,20.04212f,1640404923));
        points.add(new ChartPoint(46916.22f,48906.70f,49527.5f,46297.5f,5.04212f,1640318523));
        points.add(new ChartPoint(48906.70f,48611.49f,49577.5f,48497.5f,2.04212f,1640232123));
        points.add(new ChartPoint(48611.49f,50847.10f,49507.5f,46097.5f,9.04212f,1640145723));
        points.add(new ChartPoint(50847.10f,50846.72f,51537.5f,49497.5f,7.04212f,1640059323));
        points.add(new ChartPoint(50846.72f,50429.21f,50937.5f,48497.5f,8.04212f,1639972923));
        points.add(new ChartPoint(50429.21f,50801.07f,51537.5f,50211.5f,2.04212f,1639886523));
        points.add(new ChartPoint(50801.07f,50727.54f,51237.5f,50001.5f,1.04212f,1639800123));
        points.add(new ChartPoint(50727.54f,47556.84f,51937.5f,46497.5f,4.04212f,1639713723));
        points.add(new ChartPoint(47556.84f,46471.73f,48537.5f,42497.5f,6.04212f,1639627323));
        points.add(new ChartPoint(46471.73f,47125.87f,47907.26f,45923.13f,7.04212f,1640822400));
        points.add(new ChartPoint(47125.89f,47131.80f,47555.20f,46837.49f,17.04212f,1640908800));

        ArrayList<ChartPoint> clone = (ArrayList<ChartPoint>) points.clone();
        points.addAll(clone);

        ChartView chart = findViewById(R.id.cv);
        chart.setData(ChartStyle.CANDLE_CHART,points);

    }
}