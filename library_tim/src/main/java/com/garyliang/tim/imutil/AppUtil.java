package com.garyliang.tim.imutil;

import android.app.ActivityManager;
import android.content.Context;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * App工具类
 * 注意：
 * 1.应在BaseApplication的onCreate()里调用AppUtil.init(this)初始化
 * 2.应在BaseActivity的onCreate()/onPause()调用AppUtil.setTopActivity(this)设置topActivity
 */
public final class AppUtil {
    private static Context appContext;
    private static List<WeakReference<AppCompatActivity>> activitys;   //保存当前打开的界面的集合
    private static List<Fragment> fragments;   //保存当前打开的Fragment的集合
    private static final Handler uiHandler = new Handler(Looper.getMainLooper());
    private static final long UI_THREAD_ID = Looper.getMainLooper().getThread().getId();


    private AppUtil() {
    }


    private static final class WorkerHandlerHolder {
        private static final Handler INSTANCE;

        static {
            HandlerThread thread = new HandlerThread("worker.handler.thread");
            thread.start();
            INSTANCE = new Handler(thread.getLooper());
        }
    }

    public static Context getAppContext() {
        return appContext;
    }

    /*设置Application，此方法应在BaseApplication的onCreate()里调用*/
    public static void init(Context appContext) {
        AppUtil.appContext = appContext;
        activitys = new ArrayList<>();
        fragments = new ArrayList<>();
    }

    /*************
     * activity相关
     *******************/
    /*获取最上层的activity*/
    public static AppCompatActivity getTopActivity() {
        if ((activitys.isEmpty())) {
            return null;
        }
        if (activitys.get(activitys.size() - 1).get() == null) {
            return null;
        }
        return activitys.isEmpty() ? null : activitys.get(activitys.size() - 1).get();
    }

    /*将打开的activity保存到集合中*/
    public static void addActivity(WeakReference<AppCompatActivity> activity) {
        activitys.add(activity);
    }

    /*移除集合中已保存的activity，在界面销毁时调用*/
    public static void removeActivity(WeakReference<AppCompatActivity> activity) {
        activitys.remove(activity);
    }

    /*关闭最上方的n个界面*/
    public static void removeTopActivitys(int count) {
        for (int i = 0; i < count; i++) {
            if (activitys.size() >= i + 1 && activitys.get(activitys.size() - 1 - i).get() != null) {

                activitys.get(activitys.size() - 1 - i).get().finish();
            }
        }
    }

    /*关闭所有已经打开的界面，清空集合*/
    public static void clearActivitys() {
        if (activitys.isEmpty()) {
            return;
        }
        for (WeakReference<AppCompatActivity> activity : activitys) {
            if (activity.get() != null) {
                activity.get().finish();
            }
        }
        activitys.clear();
    }

    public static void clearActivitys2() {
        if (activitys.isEmpty()) {
            return;
        }
        for (WeakReference<AppCompatActivity> activity : activitys) {
            if (activity.get() != null && !activity.get().getClass().toString().contains("com.healtt.zh.MainActivity")) {

                activity.get().finish();
            }
        }
        Iterator<WeakReference<AppCompatActivity>> iterator = activitys.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().get() != null && !iterator.next().get().getClass().toString().contains("com.healtt.zh.MainActivity")) {
                iterator.remove();
            }
        }
    }

    public static void clearActivitys3(Class classA) {
        if (activitys.isEmpty()) {
            return;
        }
        for (WeakReference<AppCompatActivity> activity : activitys) {
            if (activity.get() != null && !activity.get().getClass().equals(classA)) {

                activity.get().finish();
            }
        }
        Iterator<WeakReference<AppCompatActivity>> iterator = activitys.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().get() != null && !iterator.next().get().getClass().equals(classA)) {
                iterator.remove();
            }
        }
    }

    public static void removeOne(Class classA) {
        try {
            if (activitys.isEmpty()) {
                return;
            }
            for (WeakReference<AppCompatActivity> activity : activitys) {
                if (activity.get() != null && activity.get().getClass().equals(classA)) {
                    activity.get().finish();
                }
            }

        } catch (Exception e) {
            Log.e("xx", e.toString());

        }
    }

    public static List<WeakReference<AppCompatActivity>> getActivitys() {
        if (activitys == null) {
            return new ArrayList<>();
        }
        return activitys;
    }


    /*获取所有打开的fragment*/
    public static List<Fragment> getFragments() {
        return fragments;
    }

    /*将打开的activity保存到集合中*/
    public static void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    /*移除集合中已保存的activity，在界面销毁时调用*/
    public static void removeFragment(Fragment fragment) {
        fragments.remove(fragment);
    }

    /***************
     * 线程操作相关
     **********************/
    /*获取UI线程的handler*/
    public static Handler getUiHandler() {
        return uiHandler;
    }

    /*获取工作线程的handler*/
    public static Handler getWorkerHandler() {
        return WorkerHandlerHolder.INSTANCE;
    }

    /*判断当前线程是否为ui线程*/
    public static boolean isUiThread() {
        return isUiThread(Thread.currentThread().getId());
    }

    public static boolean isUiThread(long threadId) {
        return threadId == UI_THREAD_ID;
    }


    /* 在主线程执行runnable */
    public static boolean post(Runnable runnable) {
        return getUiHandler().post(runnable);
    }

    /* 延时在主线程执行runnable */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getUiHandler().postDelayed(runnable, delayMillis);
    }

    /*主线程运行*/
    public static void post(ArgsRunnable run) {
        getUiHandler().post(run);
    }

    public abstract class ArgsRunnable implements Runnable {
        private Object[] args;

        public ArgsRunnable(Object... args) {
            this.args = args;
        }

        @Override
        public void run() {
            run(args);
        }

        public abstract void run(Object... args);
    }

    /******************
     * 进程相关
     **********************/
    //获取当前进程名称
    public static String getProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                return process.processName;
            }
        }
        return null;
    }

    //判断是否为主进程
    public static boolean isMainProcess(Context context) {
        return context.getPackageName().equals(getProcessName(context));
    }

    //判断当前是否在前台
    public static boolean isAppIsInBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (!appProcess.processName.equals(context.getPackageName())) {
                continue;
            }
            return appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
        }
        return true;
    }

    /************
     * 系统服务相关
     ****************************/

    public static <T> T getSystemService(String name) {
        return (T) appContext.getSystemService(name);
    }

    public static ConnectivityManager getConnectivityManager() {
        return getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static LocationManager getLocationManager() {
        return getSystemService(Context.LOCATION_SERVICE);
    }

    public static AudioManager getAudioManager() {
        return getSystemService(Context.AUDIO_SERVICE);
    }

    //网络是否可用
    public static boolean isNetworkAvailable() {
        NetworkInfo info = getConnectivityManager().getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    public static boolean isWifiAvailable() {
        NetworkInfo info = getConnectivityManager().getNetworkInfo(
                ConnectivityManager.TYPE_WIFI);
        return info != null && info.isConnected();
    }

    public static boolean isMobileNetworkAvailable() {
        NetworkInfo info = getConnectivityManager().getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE);
        return info != null && info.isConnected();
    }
}
