package com.garyliang.tim.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class TimDensityUtil {
    private TimDensityUtil() {
    }

    // 根据手机的分辨率将dp的单位转成px(像素)
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    // 根据手机的分辨率将px(像素)的单位转成dp
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    // 将px值转换为sp值
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    // 将sp值转换为px值
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    // 屏幕宽度（像素）
    public static int getWindowWidth(Activity context) {
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    // 屏幕高度（像素）
    public static int getWindowHeight(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;
    }

    public static int pxToDp(float px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, Resources.getSystem().getDisplayMetrics());
    }

    public static int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static int dpToSp(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static float getDisplayDensity(Context context) {
        if (context == null) {
            return -1;
        }
        return context.getResources().getDisplayMetrics().density;
    }

    public static float getDisplayDensityDPI(Context context) {
        if (context == null) {
            return -1;
        }
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * dp转px
     */
    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getDisplayMetrics());
    }

    /**
     * sp转px
     */
    public static int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getDisplayMetrics());
    }

    /**
     * px转dp
     */
    public static float px2dp(float pxVal) {
        final float scale = getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     */
    public static float px2sp(float pxVal) {
        return (pxVal / getDisplayMetrics().scaledDensity);
    }


    /**
     * 获取屏幕像素宽度的 ratio 百分比
     *
     * @return 百分比
     */
    public static int proportion(float ratio) {
        if (ratio > 1) {
            return (int) (ratio + 0.5f);
        }
        return (int) (getDisplayMetrics().widthPixels * ratio + 0.5f);
    }

    /**
     * 获取屏幕像素宽度的 ratio 百分比（offset 屏幕像素偏移量）
     *
     * @param offset 用于布局没有占满屏幕（带边框），
     *               那么 offset = 0 - 边框
     * @return proportion
     */
    public static int proportion(float ratio, int offset) {
        if (ratio > 1) {
            return (int) (ratio + 0.5f + offset);
        }
        return (int) ((getDisplayMetrics().widthPixels + offset) * ratio + 0.5f);
    }

    /**
     * 获取 DisplayMetrics
     */
    public static DisplayMetrics getDisplayMetrics() {
        return Resources.getSystem().getDisplayMetrics();
    }

    /**
     * 获取屏幕像素宽度
     *
     * @return 像素宽度
     */
    public static int getWidthPixels() {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕像素高度
     *
     * @return 像素高度
     */
    public static int getHeightPixels() {
        return getDisplayMetrics().heightPixels;
    }

}
