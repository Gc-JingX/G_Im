package com.garyliang.tim.holder.cus;

import android.content.Context;
import android.view.View;

import com.garyliang.tim.R;
import com.garyliang.tim.entity.MessageInfo;
import com.garyliang.tim.entity.TimChartHisDto;
import com.garyliang.tim.entity.TimChatDtaDto;
import com.garyliang.tim.util.TUIKitLog;


public class MessageMineHolder extends MessageCusBaseHolder {

    protected Context mContext;

    public MessageMineHolder(Context context, View itemView, long fromUserId, int workType) {
        super(itemView, fromUserId, workType, context);
        this.mContext = context;
    }

    @Override
    protected void initView(View itemView) {
//todo
    }

    @Override
    protected void initData(Object data) {

        if (data instanceof TimChatDtaDto) {
            TimChatDtaDto chatDtaDto = (TimChatDtaDto) data;
            layoutViews(chatDtaDto.getChatHisDto(), position);
        }
    }

    @Override
    public void layoutViews(final TimChartHisDto.DataBean.ResultsBean msg, final int position) {
        super.layoutViews(msg, position);
        TUIKitLog.e("xx", "msg getChatMessages " + msg.getChatMessages());
        TUIKitLog.e("xx", "msg getMessagesType  " + msg.getMessagesType());

        if (msgContentFrame.getChildCount() != 0) {
            msgContentFrame.removeAllViews();
        }
        switch (msg.getMessagesType()) {
            case "txt"://文本
                View.inflate(rootView.getContext(), R.layout.message_adapter_content_text, msgContentFrame);
                new MessageCusTextHolder(rootView).layoutVariableViews(msg, fromUserId);
                break;
            case "img"://图片

                View.inflate(rootView.getContext(), R.layout.message_adapter_content_image, msgContentFrame);
                new MessageImageHolder(rootView).layoutVariableViews(msg, fromUserId, position, getCusItemClickListener());
                break;
            case "audio"://语音

                View.inflate(rootView.getContext(), R.layout.message_adapter_content_audio, msgContentFrame);
                new MessageAudioHolder(rootView).layoutVariableViews(msg, fromUserId, msgContentFrame);
                break;
            case "newOrder"://新消息模板

                View.inflate(rootView.getContext(), R.layout.message_adapter_item_user_content, msgContentFrame);
                new MessageOrderHolder(rootView).layoutVariableViews(mContext, msg, fromUserId, workType, position, getCusItemClickListener());
                break;
            default:

                break;
        }

    }


    @Override
    public void layoutViews(MessageInfo msg, int position) {

//todo
    }
}
