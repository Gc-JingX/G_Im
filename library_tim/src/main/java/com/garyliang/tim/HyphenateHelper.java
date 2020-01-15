package com.garyliang.tim;

import android.content.Context;

import com.garyliang.tim.helper.ConfigHelper;
import com.garyliang.tim.imutil.AppUtil;
import com.garyliang.tim.util.TUIKitLog;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.session.SessionWrapper;

/**
 * <pre>
 *     author : GaryLiang
 *     e-mail : 595184932@qq.com
 *     time   : 2019/8/30
 *     desc   :  工具类
 *     version: 1.0
 * </pre>
 */

public class HyphenateHelper {

    private Context context;
    private static HyphenateHelper instance = null;


    private HyphenateHelper() {
    }

    public static synchronized HyphenateHelper getInstance() {
        if (instance == null) {
            instance = new HyphenateHelper();
        }
        return instance;
    }


    /**
     * 初始化环信SDK
     */
    public void init(Context context) {
        this.context = context;
        AppUtil.init(context);

//        //初始化事件监听
        //判断是否是在主线程
        if (SessionWrapper.isMainProcess(context)) {
            /**
             * TUIKit的初始化函数
             *
             * @param context  应用的上下文，一般为对应应用的ApplicationContext
             * @param sdkAppID 您在腾讯云注册应用时分配的sdkAppID
             * @param configs  TUIKit的相关配置项，一般使用默认即可，需特殊配置参考API文档
             */
            TUIKit.init(context, GenerateTestUserSig.SDKAPPID, new ConfigHelper().getConfigs(context));

        }
    }


    /**
     * 登录环信
     */
    public void loginIM(String userid, String usersig) {
        usersig = GenerateTestUserSig.genTestUserSig("111111");
        userid = "111111";
        TIMManager.getInstance().login(userid, usersig, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                TUIKitLog.e("loginIM", "onError 登录识别 " + desc);
            }

            @Override
            public void onSuccess() {
                TUIKitLog.e("loginIM", "onSuccess 登录成功 ");
            }
        });
    }


    /**
     * 退出登录
     */
    public void logout() {
        TIMManager.getInstance().logout(new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                TUIKitLog.e("logout", "onError " + s);
            }

            @Override
            public void onSuccess() {
                TUIKitLog.e("logout", "onSuccess 退出成功 ");

            }
        });
    }


}
