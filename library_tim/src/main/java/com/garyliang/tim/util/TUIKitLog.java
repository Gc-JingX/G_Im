package com.garyliang.tim.util;

import android.util.Log;


import com.garyliang.tim.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @autor feijin_lgc
 * <p>
 * create at 2017/9/9 12:01
 */
public class TUIKitLog {
    private TUIKitLog() {
    }

    public static final boolean IS_DEBUG = BuildConfig.DEBUG;
    private static final String TAG = "lgc";
    /**
     * logcat在实现上对于message的内存分配大概,2k左右, 所以超过的内容都直接被丢弃,设置文本长度超过LOG_MAXLENGTH分多条打印
     */
    private static final int LOG_MAXLENGTH = 2048;

    // 记录上次的logLocation
    private static String lastLogMethod = "";
    private static String jumpKeyWord = "   ---------☞ ";


    public static void i(String msg) {
        if (IS_DEBUG)
            Log.i(TAG, logContent(msg) + logLocation(0));
    }

    public static void d(String msg) {
        if (IS_DEBUG)
            Log.d(TAG, logContent(msg) + logLocation(0));
    }

    public static void e(String msg) {
        if (IS_DEBUG)
            Log.e(TAG, logContent(msg) + logLocation(0));
    }

    public static void v(String msg) {
        if (IS_DEBUG)
            Log.v(TAG, logContent(msg) + logLocation(0));
    }

    public static void w(String msg) {
        if (IS_DEBUG)
            Log.w(TAG, logContent(msg) + logLocation(0));
    }

    public static void i(String tag, String msg) {
        if (IS_DEBUG)
            Log.i(tag, logContent(msg) + logLocation(0));
    }

    public static void d(String tag, String msg) {
        if (IS_DEBUG)
            Log.d(tag, logContent(msg) + logLocation(0));
    }

    public static void e(String tag, String msg) {
        if (IS_DEBUG)
            Log.e(tag, logContent(msg) + logLocation(0));
    }

    public static void v(String tag, String msg) {
        if (IS_DEBUG)
            Log.v(tag, logContent(msg) + logLocation(0));
    }

    public static void w(String tag, String msg) {
        if (IS_DEBUG)
            Log.w(tag, logContent(msg) + logLocation(0));
    }

    /**
     * 打印异常
     *
     * @param msg
     * @param e
     */
    public static void e(String msg, Exception e) {
        if (IS_DEBUG) {
            Log.e(TAG, msg + logLocation(0), e);
        }
    }

    /**
     * 打印json格式文本
     *
     * @param json
     */
    public static void json(String json) {
        if (IS_DEBUG) {
            json("", json);
        }
    }

    /**
     * 打印json格式文本
     *
     * @param prefix 前缀文本
     * @param json
     */
    public static void json(String prefix, String json) {
        if (IS_DEBUG) {
            String text = prefix + fomatJson(json);
            Log.e(TAG, logContent(text) + "  " + logLocation(0));
        }
    }

    /**
     * json格式化
     *
     * @param jsonStr
     * @return
     */
    private static String fomatJson(String jsonStr) {
        try {
            jsonStr = jsonStr.trim();
            if (jsonStr.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(jsonStr);
                return jsonObject.toString(2);
            }
            if (jsonStr.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(jsonStr);
                return jsonArray.toString(2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Json格式有误: " + jsonStr;
    }

    /**
     * 打印内容
     *
     * @param text
     * @return
     */
    private static String logContent(String text) {
        if (text.length() < 50) {// 内容长度不超过50，前面加空格加到50
            int minLeng = 50 - text.length();
            if (minLeng > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < minLeng; i++) {
                    stringBuilder.append(" ");
                }
                text = text + stringBuilder.toString();
            }
        } else if (text.length() > LOG_MAXLENGTH) {// 内容超过logcat单条打印上限，分批打印
            int logTime = text.length() / LOG_MAXLENGTH;
            for (int i = 0; i < logTime; i++) {
                String leng = text.substring(i * LOG_MAXLENGTH, (i + 1)
                        * LOG_MAXLENGTH);
                // 提示
                if (i == 0) {
                    Log.i(TAG, "打印分" + logTime + "条显示 :" + leng);
                } else {
                    Log.i(TAG, "接上条↑" + leng);
                }

            }
            text = "接上条↑"
                    + text.substring(logTime * LOG_MAXLENGTH, text.length());
        }
        return text;
    }


    /**
     * 定位打印的方法
     *
     * @return
     */
    private static StringBuilder logLocation(int index) {
        StringBuilder stringBuilder = new StringBuilder();
        if (getLogStackTrace(index) == null) {
            return stringBuilder;
        }
        try {
            StackTraceElement logStackTrace = getLogStackTrace(index);
            if (logStackTrace == null) {
                return stringBuilder;
            }
            stringBuilder.append(jumpKeyWord).append(" (").append(logStackTrace.getFileName())
                    .append(":").append(logStackTrace.getLineNumber() + ")").append(logStackTrace.getMethodName())
                    .append("():");
            if (stringBuilder.toString().equals(lastLogMethod)) {
                stringBuilder = new StringBuilder("");
            } else {
                lastLogMethod = stringBuilder.toString();
            }
        } catch (Exception e) {
            Log.e("xx", e.toString());

        }
        return stringBuilder;
    }

    /**
     * 获取调用打印方法的栈 index 调用打印i/e/json的index为0
     *
     * @return
     */
    private static StackTraceElement getLogStackTrace(int index) {
        StackTraceElement[] stackTraces = Thread.currentThread()
                .getStackTrace();
        StackTraceElement logTackTraces = stackTraces[0];
        for (int i = 0; i < stackTraces.length; i++) {
            StackTraceElement stackTrace = stackTraces[i];
            if (stackTrace.getClassName().equals(TUIKitLog.class.getName())) {
                logTackTraces = stackTraces[i + 3 + index];
                break;
            }
        }
        return logTackTraces;
    }

}
