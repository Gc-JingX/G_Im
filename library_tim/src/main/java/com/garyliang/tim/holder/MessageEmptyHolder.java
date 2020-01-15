package com.garyliang.tim.holder;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.garyliang.tim.R;
import com.garyliang.tim.entity.MessageInfo;
import com.garyliang.tim.util.DateTimeUtil;

import java.util.Date;

public abstract class MessageEmptyHolder extends MessageBaseHolder {

    protected TextView chatTimeText;
    protected FrameLayout msgContentFrame;

    public MessageEmptyHolder(View itemView, Context context) {
        super(itemView, context);

        rootView = itemView;
        mContext = context;

        chatTimeText = itemView.findViewById(R.id.chat_time_tv);
        msgContentFrame = itemView.findViewById(R.id.msg_content_fl);
        initVariableLayout();
    }

    public abstract int getVariableLayout();

    private void setVariableLayout(int resId) {
        if (msgContentFrame.getChildCount() == 0) {
            View.inflate(rootView.getContext(), resId, msgContentFrame);
        }
        initVariableViews();
    }

    private void initVariableLayout() {
        if (getVariableLayout() != 0) {
            setVariableLayout(getVariableLayout());
        }
    }

    public abstract void initVariableViews();

    public void layoutViews(final MessageInfo msg, final int position) {

        //// 时间线设置
        if (properties.getChatTimeBubble() != null) {
            chatTimeText.setBackground(properties.getChatTimeBubble());
        }
        if (properties.getChatTimeFontColor() != 0) {
            chatTimeText.setTextColor(properties.getChatTimeFontColor());
        }
        if (properties.getChatTimeFontSize() != 0) {
            chatTimeText.setTextSize(properties.getChatTimeFontSize());
        }

        chatTimeText.setVisibility(View.VISIBLE);
        chatTimeText.setText(DateTimeUtil.getTimeFormatText(new Date(msg.getMsgTime() * 1000)));
    }

}
