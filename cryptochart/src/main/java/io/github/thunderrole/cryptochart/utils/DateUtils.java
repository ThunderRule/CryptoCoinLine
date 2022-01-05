package io.github.thunderrole.cryptochart.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能描述：
 *
 * @date 2022/1/5
 */
public class DateUtils {
    private static DateUtils mIntance = null;

    private SimpleDateFormat mmdd = new SimpleDateFormat("MM/dd HH:mm");


    private DateUtils(){

    }

    public static DateUtils getInstance(){
        if (mIntance == null){
            mIntance = new DateUtils();
        }
        return mIntance;
    }

    public String long2Format(long date){
        String format = mmdd.format(new Date(date));
        return format;
    }
}
