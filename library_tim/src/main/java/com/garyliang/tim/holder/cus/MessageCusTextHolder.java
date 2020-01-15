package com.garyliang.tim.holder.cus;

import android.view.View;
import android.widget.TextView;

import com.garyliang.tim.R;
import com.garyliang.tim.entity.TimChartHisDto;
import com.garyliang.tim.face.FaceManager;
import com.garyliang.tim.holder.Properties;


public class MessageCusTextHolder {

    private TextView msgBodyText;
    protected View rootView;
    protected Properties properties = Properties.getInstance();

    public MessageCusTextHolder(View itemView) {
        rootView = itemView;

        initVariableViews();
    }


    public void initVariableViews() {
        msgBodyText = rootView.findViewById(R.id.msg_body_tv);
    }

    public void layoutVariableViews(TimChartHisDto.DataBean.ResultsBean msg, long fromUserId) {
        msgBodyText.setVisibility(View.VISIBLE);
        FaceManager.handlerEmojiText(msgBodyText, msg.getChatMessages(), false);
        if (properties.getChatContextFontSize() != 0) {
            msgBodyText.setTextSize(properties.getChatContextFontSize());
        }
        if (fromUserId == msg.getFromUserId()) {
            if (properties.getRightChatContentFontColor() != 0) {
                msgBodyText.setTextColor(properties.getRightChatContentFontColor());
            }
        } else {
            if (properties.getLeftChatContentFontColor() != 0) {
                msgBodyText.setTextColor(properties.getLeftChatContentFontColor());
            }
        }
    }

}
