package com.garyliang.tim.imutil;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


/**
 * Created by Android on 17/3/28.
 */

public final class UiUtil {
    /* 关闭输入法 */
    public static void hideInputWindow() {
        Runnable runnable = () -> {
            try {
                InputMethodManager inputMethodManager = (InputMethodManager) AppUtil.getTopActivity().
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                IBinder windowToken = AppUtil.getTopActivity().getCurrentFocus().getWindowToken();
                if (windowToken != null) {
                    inputMethodManager.hideSoftInputFromWindow(windowToken,InputMethodManager.HIDE_NOT_ALWAYS);
                }

            } catch (Exception e) {
                Log.d("", "关闭输入法异常");
            }
        };

        if (AppUtil.isUiThread()) {
            runnable.run();
        } else {
            AppUtil.post(runnable);
        }
    }

    /****************** 资源相关 ****************************/
    /*获取资源*/
    public static Resources getResources() {
        return AppUtil.getAppContext().getResources();
    }

    /*dip转换px*/
    public static int dip2px(int dip) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /*px转dip*/
    public static int px2dip(int px) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /* 获取文字*/
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 得到string.xml中的字符串，带点位符
     */
    public static String getString(int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }

    /*获取文字数组*/
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /*获取dimen*/
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /*获取drawable*/
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /*获取颜色*/
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    public static View inflate(int resId) {
        return inflate(resId, null);
    }

    public static View inflate(int resId, ViewGroup root) {
        return inflate(resId, root, false);
    }

    public static View inflate(int resId, ViewGroup root, boolean attachToToor) {
        return LayoutInflater.from(AppUtil.getTopActivity()).inflate(resId, root, attachToToor);
    }

    /************* 获取界面相关参数 **********/
    /*获取屏幕宽度*/
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /*获取屏幕高度*/
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    private static Toast toast;
    /*toast输出的方法，在主线程中运行，String.format("我叫%s,她叫%s", "小明","小方"）*/
    public static void toast(final String msg, final Object... params) {
        if (msg == null) {
            return;
        }
        AppUtil.getUiHandler().post(() -> {
            String v = String.format(msg, params);
            if (toast == null) {
                toast = Toast.makeText(AppUtil.getAppContext(), v, Toast.LENGTH_SHORT);
            } else {
                toast.setText(v);
            }
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        });
    }



    /**
     * 设置软件盘高度监听
     */
    public static void setOnSoftKeyBoardChangeListener(Activity activity, OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        SoftKeyBoardListener softKeyBoardListener = new SoftKeyBoardListener(activity);
        softKeyBoardListener.setOnSoftKeyBoardChangeListener(onSoftKeyBoardChangeListener);
    }

    public interface OnSoftKeyBoardChangeListener {

        void keyBoardShow(int height);
        void keyBoardHide(int height);

    }

    public static class SoftKeyBoardListener {
        private View rootView;//activity的根视图
        int rootViewVisibleHeight;//纪录根视图的显示高度
        private OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener;

        public SoftKeyBoardListener(Activity activity) {
            //获取activity的根视图
            rootView = activity.getWindow().getDecorView();

            //监听视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变
            rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    //获取当前根视图在屏幕上显示的大小
                    Rect r = new Rect();
                    rootView.getWindowVisibleDisplayFrame(r);
                    int visibleHeight = r.height();
                    if (rootViewVisibleHeight == 0) {
                        rootViewVisibleHeight = visibleHeight;
                        return;
                    }

                    //根视图显示高度没有变化，可以看作软键盘显示／隐藏状态没有改变
                    if (rootViewVisibleHeight == visibleHeight) {
                        return;
                    }

                    //根视图显示高度变小超过200，可以看作软键盘显示了
                    if (rootViewVisibleHeight - visibleHeight > 200) {
                        if (onSoftKeyBoardChangeListener != null) {
                            onSoftKeyBoardChangeListener.keyBoardShow(rootViewVisibleHeight - visibleHeight);
                        }
                        rootViewVisibleHeight = visibleHeight;
                        return;
                    }

                    //根视图显示高度变大超过200，可以看作软键盘隐藏了
                    if (visibleHeight - rootViewVisibleHeight > 200) {
                        if (onSoftKeyBoardChangeListener != null) {
                            onSoftKeyBoardChangeListener.keyBoardHide(visibleHeight - rootViewVisibleHeight);
                        }
                        rootViewVisibleHeight = visibleHeight;
                    }

                }
            });
        }

        private void setOnSoftKeyBoardChangeListener(OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
            this.onSoftKeyBoardChangeListener = onSoftKeyBoardChangeListener;
        }

    }
}
