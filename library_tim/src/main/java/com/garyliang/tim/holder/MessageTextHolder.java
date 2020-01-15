package com.garyliang.tim.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.garyliang.tim.R;
import com.garyliang.tim.entity.MessageInfo;
import com.garyliang.tim.entity.TimChatDtaDto;
import com.garyliang.tim.face.FaceManager;
import com.garyliang.tim.util.TUIKitLog;


public class MessageTextHolder extends MessageContentHolder {

    private TextView msgBodyText;


    public MessageTextHolder(View itemView, Context context) {
        super(itemView, context);

        rootView = itemView;
        mContext = context;
    }

    @Override
    protected void initView(View itemView) {
//todo
    }

    @Override
    protected void initData(Object data) {
        TUIKitLog.e("xx", "initData " + data);
        if (data instanceof TimChatDtaDto) {
            TUIKitLog.e("xx");
            TimChatDtaDto chatDtaDto = (TimChatDtaDto) data;
            layoutViews(chatDtaDto.getMessage(), position);
        }
    }

    @Override
    public int getVariableLayout() {
        return R.layout.message_adapter_content_text;
    }

    @Override
    public void initVariableViews() {
        msgBodyText = rootView.findViewById(R.id.msg_body_tv);
    }

    @Override
    public void layoutVariableViews(MessageInfo msg, int position) {
        msgBodyText.setVisibility(View.VISIBLE);
        FaceManager.handlerEmojiText(msgBodyText, msg.getExtra().toString(), false);
        if (properties.getChatContextFontSize() != 0) {
            msgBodyText.setTextSize(properties.getChatContextFontSize());
        }
        if (msg.isSelf()) {
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
