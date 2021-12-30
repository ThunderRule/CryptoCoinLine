package io.github.thunderrole.cryptochart.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import io.github.thunderrole.cryptochart.model.ChartEntry;

/**
 * 功能描述：
 *
 * @date 2021/12/30
 */
public class LogUtils {

    public static void d(String msg){
        d("CryptoChart",msg);
    }

    public static void d(String tag,String msg){
        Log.d(tag,msg);
    }

    public static void d(ChartEntry entry){
        if (entry != null){
            StringBuilder builder = new StringBuilder();
            String string = builder.append(", high = ")
                    .append(entry.getHigh())
                    .append(", low = ")
                    .append(entry.getLow())
                    .append(", open = ")
                    .append(entry.getOpen())
                    .append(", close = ")
                    .append(entry.getOpen())
                    .append(", value = ")
                    .append(entry.getValue())
                    .append(", date = ")
                    .append(new SimpleDateFormat("yyy-MM-dd").format(new Date(entry.getDate())))
                    .append("\n")
                    .toString();
            d("Entry",string);
        }else {
            d("Entry","this entry is NULL");
        }
    }

    public static void d(List<ChartEntry> list){
        d("CryptoChart List",list);
    }

    public static void d(String tag,List<ChartEntry> list){
        if (list != null){
            StringBuilder builder = new StringBuilder();
            for (ChartEntry entry : list) {
                String string = builder.append(", high = ")
                        .append(entry.getHigh())
                        .append(", low = ")
                        .append(entry.getLow())
                        .append(", open = ")
                        .append(entry.getOpen())
                        .append(", close = ")
                        .append(entry.getOpen())
                        .append(", value = ")
                        .append(entry.getValue())
                        .append(", date = ")
                        .append(new SimpleDateFormat("yyy-MM-dd").format(new Date(entry.getDate())))
                        .append("\n")
                        .toString();
                d(tag,string);
            }
        }else {
            d(tag,"this entry list is NULL");
        }
    }
}
