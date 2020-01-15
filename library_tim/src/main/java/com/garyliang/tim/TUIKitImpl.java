package com.garyliang.tim;

import android.content.Context;
import android.text.TextUtils;

import com.garyliang.tim.base.IMEventListener;
import com.garyliang.tim.base.IUIKitCallBack;
import com.garyliang.tim.config.GeneralConfig;
import com.garyliang.tim.config.TUIKitConfigs;
import com.garyliang.tim.face.FaceManager;
import com.garyliang.tim.util.BackgroundTasks;
import com.garyliang.tim.util.FileUtil;
import com.garyliang.tim.util.TUIKitLog;
import com.tencent.imsdk.BuildConfig;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class TUIKitImpl {
    private TUIKitImpl() {
    }

    private static final String TAG = "TUIKit";

    private static Context sAppContext;
    private static TUIKitConfigs sConfigs;
    private static List<IMEventListener> sIMEventListeners = new ArrayList<>();

    /**
     * TUIKit的初始化函数
     *
     * @param context  应用的上下文，一般为对应应用的ApplicationContext
     * @param sdkAppID 您在腾讯云注册应用时分配的sdkAppID
     * @param configs  TUIKit的相关配置项，一般使用默认即可，需特殊配置参考API文档
     */
    public static void init(Context context, int sdkAppID, TUIKitConfigs configs) {
        TUIKitLog.e(TAG, "init tuikit version: " + BuildConfig.VERSION_NAME);
        sAppContext = context;
        sConfigs = configs;
        if (sConfigs.getGeneralConfig() == null) {
            GeneralConfig generalConfig = new GeneralConfig();
            sConfigs.setGeneralConfig(generalConfig);
        }
        String dir = sConfigs.getGeneralConfig().getAppCacheDir();
        if (TextUtils.isEmpty(dir)) {
            TUIKitLog.e(TAG, "appCacheDir is empty, use default dir");
            sConfigs.getGeneralConfig().setAppCacheDir(context.getFilesDir().getPath());
        } else {
            File file = new File(dir);
            if (file.exists()) {
                if (file.isFile()) {
                    TUIKitLog.e(TAG, "appCacheDir is a file, use default dir");
                    sConfigs.getGeneralConfig().setAppCacheDir(context.getFilesDir().getPath());
                } else if (!file.canWrite()) {
                    TUIKitLog.e(TAG, "appCacheDir can not write, use default dir");
                    sConfigs.getGeneralConfig().setAppCacheDir(context.getFilesDir().getPath());
                }
            } else {
                boolean ret = file.mkdirs();
                if (!ret) {
                    TUIKitLog.e(TAG, "appCacheDir is invalid, use default dir");
                    sConfigs.getGeneralConfig().setAppCacheDir(context.getFilesDir().getPath());
                }
            }
        }
        initIM(context, sdkAppID);

        BackgroundTasks.initInstance();
        FileUtil.initPath(); // 取决于app什么时候获取到权限，即使在application中初始化，首次安装时，存在获取不到权限，建议app端在activity中再初始化一次，确保文件目录完整创建
        FaceManager.loadFaceFiles();
    }

    public static void login(String userid, String usersig, final IUIKitCallBack callback) {
        TIMManager.getInstance().login(userid, usersig, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                callback.onError(TAG, code, desc);
            }

            @Override
            public void onSuccess() {
                callback.onSuccess(null);
            }
        });
    }

    private static void initIM(Context context, int sdkAppID) {
        TIMSdkConfig sdkConfig = sConfigs.getSdkConfig();
        if (sdkConfig == null) {
            sdkConfig = new TIMSdkConfig(sdkAppID);
            sConfigs.setSdkConfig(sdkConfig);
        }
        GeneralConfig generalConfig = sConfigs.getGeneralConfig();
        sdkConfig.setLogLevel(generalConfig.getLogLevel());
        sdkConfig.enableLogPrint(generalConfig.isLogPrint());
        TIMManager.getInstance().init(context, sdkConfig);

        TIMUserConfig userConfig = new TIMUserConfig();
        userConfig.setReadReceiptEnabled(true);

        //设置用户状态变更事件监听器
        userConfig.setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {
                for (IMEventListener l : sIMEventListeners) {
                    l.onForceOffline();
                }
            }

            @Override
            public void onUserSigExpired() {
                for (IMEventListener l : sIMEventListeners) {
                    l.onUserSigExpired();
                }
            }
        });
        //设置连接状态事件监听器
        userConfig.setConnectionListener(new TIMConnListener() {
            @Override
            public void onConnected() {
                for (IMEventListener l : sIMEventListeners) {
                    l.onConnected();
                }
            }

            @Override
            public void onDisconnected(int code, String desc) {
                for (IMEventListener l : sIMEventListeners) {
                    l.onDisconnected(code, desc);
                }
            }

            @Override
            public void onWifiNeedAuth(String name) {
                for (IMEventListener l : sIMEventListeners) {
                    l.onWifiNeedAuth(name);
                }
            }
        });
//设置群组事件监听器
        userConfig.setGroupEventListener(elem -> {
            for (IMEventListener l : sIMEventListeners) {
                l.onGroupTipsEvent(elem);
            }
        });


        TIMManager.getInstance().addMessageListener(msgs -> {
            for (IMEventListener l : sIMEventListeners) {
                l.onNewMessages(msgs);
            }
            return false;
        });

        TIMManager.getInstance().setUserConfig(userConfig);

    }


    public static Context getAppContext() {
        return sAppContext;
    }

    public static TUIKitConfigs getConfigs() {
        if (sConfigs == null) {
            sConfigs = TUIKitConfigs.getConfigs();
        }
        return sConfigs;
    }

    public static void addIMEventListener(IMEventListener listener) {
        if (listener != null && !sIMEventListeners.contains(listener)) {
            sIMEventListeners.add(listener);
        }
    }

    public static void removeIMEventListener(IMEventListener listener) {
        if (listener == null) {
            sIMEventListeners.clear();
        } else {
            sIMEventListeners.remove(listener);
        }
    }
}
