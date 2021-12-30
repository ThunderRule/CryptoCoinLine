package io.github.thunderrole.cryptochart.utils;

import android.content.Context;

/**
 * 功能描述：
 *
 * @date 2021/12/30
 */
public class UIUtils {
    public static int dp2px(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }
}
