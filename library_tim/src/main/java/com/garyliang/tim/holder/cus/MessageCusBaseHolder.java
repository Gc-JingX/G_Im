package com.garyliang.tim.holder.cus;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.garyliang.tim.R;
import com.garyliang.tim.entity.TimChartHisDto;
import com.garyliang.tim.holder.view.UserIconView;

import java.util.ArrayList;
import java.util.List;

public abstract class MessageCusBaseHolder extends MessageCusEmptyHolder {

    protected UserIconView leftUserIcon;
    protected UserIconView rightUserIcon;
    protected TextView usernameText;
    protected LinearLayout msgContentLinear;
    protected ProgressBar sendingProgress;
    protected ImageView statusImage;
    protected TextView isReadText;
    protected TextView unreadAudioText;

    protected long fromUserId;
    protected int workType;


    public MessageCusBaseHolder(View itemView, long fromUserId, int workType, Context context) {
        super(itemView, context);
        rootView = itemView;

        this.fromUserId = fromUserId;
        this.workType = workType;

        leftUserIcon = itemView.findViewById(R.id.left_user_icon_view);
        rightUserIcon = itemView.findViewById(R.id.right_user_icon_view);
        usernameText = itemView.findViewById(R.id.user_name_tv);
        msgContentLinear = itemView.findViewById(R.id.msg_content_ll);
        statusImage = itemView.findViewById(R.id.message_status_iv);
        sendingProgress = itemView.findViewById(R.id.message_sending_pb);
        isReadText = itemView.findViewById(R.id.is_read_tv);
        unreadAudioText = itemView.findViewById(R.id.audio_unread);
    }

    @Override
    public void layoutViews(final TimChartHisDto.DataBean.ResultsBean msg, final int position) {


        usernameText.setText(msg.getFromUserName());
        //// 头像设置
        List<Object> urllist = new ArrayList<>();
        urllist.add(msg.getFromPortraitUrl());
        if (fromUserId == msg.getFromUserId()) {
            leftUserIcon.setVisibility(View.GONE);
            rightUserIcon.setVisibility(View.VISIBLE);
            rightUserIcon.setIconUrls(urllist);
        } else {
            leftUserIcon.setVisibility(View.VISIBLE);
            rightUserIcon.setVisibility(View.GONE);
            leftUserIcon.setIconUrls(urllist);
        }
        if (properties.getAvatar() != 0) {
            leftUserIcon.setDefaultImageResId(properties.getAvatar());
            rightUserIcon.setDefaultImageResId(properties.getAvatar());
        } else {
            leftUserIcon.setDefaultImageResId(R.drawable.default_head);
            rightUserIcon.setDefaultImageResId(R.drawable.default_head);
        }
        if (properties.getAvatarRadius() != 0) {
            leftUserIcon.setRadius(properties.getAvatarRadius());
            rightUserIcon.setRadius(properties.getAvatarRadius());
        } else {
            leftUserIcon.setRadius(5);
            rightUserIcon.setRadius(5);
        }
        if (properties.getAvatarSize() != null && properties.getAvatarSize().length == 2) {
            ViewGroup.LayoutParams params = leftUserIcon.getLayoutParams();
            params.width = properties.getAvatarSize()[0];
            params.height = properties.getAvatarSize()[1];
            leftUserIcon.setLayoutParams(params);

            params = rightUserIcon.getLayoutParams();
            params.width = properties.getAvatarSize()[0];
            params.height = properties.getAvatarSize()[1];
            rightUserIcon.setLayoutParams(params);
        }

        //// 用户昵称设置
        if (fromUserId == msg.getFromUserId()) { // 默认不显示自己的昵称
            if (properties.getRightNameVisibility() == 0) {
                usernameText.setVisibility(View.GONE);
            } else {
                usernameText.setVisibility(properties.getRightNameVisibility());
            }
        } else {
            if (properties.getLeftNameVisibility() == 0) {
                usernameText.setVisibility(View.VISIBLE);
            } else {
                usernameText.setVisibility(properties.getLeftNameVisibility());
            }
        }
        if (properties.getNameFontColor() != 0) {
            usernameText.setTextColor(properties.getNameFontColor());
        }
        if (properties.getNameFontSize() != 0) {
            usernameText.setTextSize(properties.getNameFontSize());
        }


        //// 左右边的消息需要调整一下内容的位置
        if (fromUserId == msg.getFromUserId()) {
            msgContentLinear.removeView(msgContentFrame);
            msgContentLinear.addView(msgContentFrame);
        } else {
            msgContentLinear.removeView(msgContentFrame);
            msgContentLinear.addView(msgContentFrame, 0);
        }
        msgContentLinear.setVisibility(View.VISIBLE);

        //// 音频已读
        unreadAudioText.setVisibility(View.GONE);
    }
}
