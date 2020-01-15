package com.garyliang.tim.holder.view.input.impl;

import com.garyliang.tim.entity.MessageInfo;
import com.garyliang.tim.holder.view.input.InputLayout;

public interface IChatLayout extends ILayout {

    /**
     * 获取聊天窗口 Input 区域 Layout
     *
     * @return
     */
    InputLayout getInputLayout();

    /**
     * 退出聊天，释放相关资源（一般在 activity finish 时调用）
     */
    void exitChat();

    /**
     * 初始化参数
     */
    void initDefault();

    /**
     * 加载聊天消息
     */
    void loadMessages();

    /**
     * 发送消息
     *
     * @param msg   消息
     * @param retry 是否重试
     */
    void sendMessage(MessageInfo msg, boolean retry);
}
