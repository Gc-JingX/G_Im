package com.garyliang.tim.helper;


import android.content.Context;

import com.garyliang.tim.TUIKit;
import com.garyliang.tim.config.GeneralConfig;
import com.garyliang.tim.config.TUIKitConfigs;
import com.garyliang.tim.face.CustomFace;
import com.garyliang.tim.face.CustomFaceConfig;
import com.garyliang.tim.face.CustomFaceGroup;

public class ConfigHelper {

    public ConfigHelper() {
//todo
    }

    public TUIKitConfigs getConfigs(Context context) {
        GeneralConfig config = new GeneralConfig();
        // 显示对方是否已读的view将会展示
        config.setShowRead(false);
        config.setAppCacheDir(context.getFilesDir().getPath());
        TUIKit.getConfigs().setGeneralConfig(config);
        return TUIKit.getConfigs();
    }

    private CustomFaceConfig initCustomFaceConfig() {

        CustomFaceConfig config = new CustomFaceConfig();
        CustomFaceGroup faceConfigs = new CustomFaceGroup();
        faceConfigs.setPageColumnCount(5);
        faceConfigs.setPageRowCount(2);
        faceConfigs.setFaceGroupId(1);
        faceConfigs.setFaceIconPath("4350/tt01@2x.png");
        faceConfigs.setFaceIconName("4350");
        for (int i = 0; i <= 16; i++) {
            CustomFace customFace = new CustomFace();
            String index = "" + i;
            if (i < 10)
                index = "0" + i;
            customFace.setAssetPath("4350/tt" + index + "@2x.png");
            customFace.setFaceName("tt" + index + "@2x");
            customFace.setFaceWidth(170);
            customFace.setFaceHeight(170);
            faceConfigs.addCustomFace(customFace);
        }
        config.addFaceGroup(faceConfigs);

        return config;
    }
}
