package com.garyliang.tim.holder.view.input.impl;

import android.view.View;
import android.widget.EditText;

import com.garyliang.tim.holder.view.input.BaseInputFragment;
import com.garyliang.tim.holder.view.inputmore.InputMoreActionUnit;

public interface IInputLayout {

    /**
     * disable 语音输入后，会隐藏按钮
     *
     * @param disable
     */
    void disableAudioInput(boolean disable);

    /**
     * disable 表情输入后，会隐藏按钮
     *
     * @param disable
     */
    void disableEmojiInput(boolean disable);

    /**
     * disable 更多功能后，会隐藏按钮
     *
     * @param disable
     */
    void disableMoreInput(boolean disable);

    /**
     * 替换点击“+”弹出的面板
     *
     * @param fragment
     */
    void replaceMoreInput(BaseInputFragment fragment);

    /**
     * 替换点击“+”响应的事件
     *
     * @param listener
     */
    void replaceMoreInput(View.OnClickListener listener);

    /**
     * disable 发送图片后，会隐藏更多面板上的按钮
     *
     * @param disable
     */
    void disableSendPhotoAction(boolean disable);

    /**
     * disable 拍照后，会隐藏更多面板上的按钮
     *
     * @param disable
     */
    void disableCaptureAction(boolean disable);

    /**
     * disable 录像后，会隐藏更多面板上的按钮
     *
     * @param disable
     */
    void disableVideoRecordAction(boolean disable);

    /**
     * disable 发送文件后，会隐藏更多面板上的按钮
     *
     * @param disable
     */
    void disableSendFileAction(boolean disable);

    /**
     * 增加更多面板上的事件单元
     *
     */
    void addAction(InputMoreActionUnit action);

    /**
     * 获取输入框View
     *
     * @return 输入框EditText
     */
    EditText getInputText();
}
