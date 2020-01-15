package com.garyliang.tim.holder.cus;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.garyliang.tim.R;
import com.garyliang.tim.entity.TimChartHisDto;
import com.garyliang.tim.holder.MessageBaseHolder;

public abstract class MessageCusEmptyHolder extends MessageBaseHolder {

    protected TextView chatTimeText;
    protected FrameLayout msgContentFrame;

    public MessageCusEmptyHolder(View itemView, Context context) {
        super(itemView, context);
        rootView = itemView;
        mContext = context;

        chatTimeText = itemView.findViewById(R.id.chat_time_tv);
        msgContentFrame = itemView.findViewById(R.id.msg_content_fl);
    }


    public void layoutViews(final TimChartHisDto.DataBean.ResultsBean msg, final int position) {

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
        chatTimeText.setText(msg.getSendTime());

    }

}
