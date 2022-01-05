package io.github.thunderrole.cryptochart.utils;

import io.github.thunderrole.cryptochart.model.ChartEntry;
import io.github.thunderrole.cryptochart.model.Point;

/**
 * 功能描述：
 *
 * @date 2022/1/5
 */
public class DrawUtils {
    private static DrawUtils mInstance = null;

    private DrawUtils() {

    }

    public static DrawUtils getInstance() {
        if (mInstance == null) {
            mInstance = new DrawUtils();
        }
        return mInstance;
    }

    public double[] controlPoints(Point p1, Point p2, Point p3) {
        float t = 0.16f;
        if (p1 != null || p2 != null || p3 != null) {
            double d01 = Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
            double d12 = Math.sqrt(Math.pow(p3.getX() - p2.getX(), 2) + Math.pow(p3.getY() - p2.getY(), 2));
            double fa =  t * d01 / (d01 + d12);
            double fb = t * d12 / (d01 + d12);
            double c1x = p2.getX() - fa * (p3.getX() - p1.getX());
            double c1y = p2.getY() - fa * (p3.getY() - p1.getY());
            double c2x = p2.getX() + fb * (p3.getX() - p1.getX());
            double c2y = p2.getY() + fb * (p3.getY() - p1.getY());
            return new double[]{c1x, c1y, c2x, c2y};
        }
        return null;
    }

}
