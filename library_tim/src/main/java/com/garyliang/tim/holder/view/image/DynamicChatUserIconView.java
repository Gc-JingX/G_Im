package com.garyliang.tim.holder.view.image;


import com.garyliang.tim.entity.MessageInfo;
import com.garyliang.tim.util.ScreenUtil;

public abstract class DynamicChatUserIconView extends DynamicLayoutView<MessageInfo> {

    private int iconRadius = -1;

    public int getIconRadius() {
        return iconRadius;
    }

    /**
     * 设置聊天头像圆角
     *
     * @param iconRadius
     */
    public void setIconRadius(int iconRadius) {
        this.iconRadius = ScreenUtil.getPxByDp(iconRadius);
    }
}
