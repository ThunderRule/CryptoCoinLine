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
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,7.04212f,1640836923));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,8.04212f,1640750523));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,2.04212f,1640664123));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,1.04212f,1640577723));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,10.04212f,1640491323));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,20.04212f,1640404923));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,5.04212f,1640318523));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,2.04212f,1640232123));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,9.04212f,1640145723));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,7.04212f,1640059323));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,8.04212f,1639972923));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,2.04212f,1639886523));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,1.04212f,1639800123));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,4.04212f,1639713723));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,6.04212f,1639627323));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,7.04212f,1639540923));
        points.add(new ChartPoint(465058.8f,46509.2f,46537.5f,46497.5f,17.04212f,1639454523));

        ChartView chart = findViewById(R.id.cv);
        chart.setData(ChartStyle.BAR_CHART,points);

    }
}