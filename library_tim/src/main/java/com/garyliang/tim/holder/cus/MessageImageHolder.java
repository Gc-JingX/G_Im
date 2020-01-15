package com.garyliang.tim.holder.cus;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.garyliang.tim.R;
import com.garyliang.tim.entity.TimChartHisDto;
import com.garyliang.tim.holder.MessageBaseHolder;
import com.garyliang.tim.holder.Properties;
import com.garyliang.tim.holder.imageengine.impl.GlideEngine;

import java.util.ArrayList;
import java.util.List;

public class MessageImageHolder {

    private static final int DEFAULT_MAX_SIZE = 540;
    private static final int DEFAULT_RADIUS = 10;
    private final List<String> downloadEles = new ArrayList<>();
    private ImageView contentImage;
    private ImageView videoPlayBtn;
    private TextView videoDurationText;
    private boolean mClicking;


    protected View rootView;
    protected Properties properties = Properties.getInstance();

    public MessageImageHolder(View itemView) {
        rootView = itemView;

        initVariableViews();
    }

    public void initVariableViews() {
        contentImage = rootView.findViewById(R.id.content_image_iv);
        videoPlayBtn = rootView.findViewById(R.id.video_play_btn);
        videoDurationText = rootView.findViewById(R.id.video_duration_tv);
        contentImage.setLayoutParams(getImageParams(contentImage.getLayoutParams()));

    }

    private ViewGroup.LayoutParams getImageParams(ViewGroup.LayoutParams params) {

        params.width = DEFAULT_MAX_SIZE * 960 / 1280;
        params.height = DEFAULT_MAX_SIZE;
        return params;
    }

    public void layoutVariableViews(TimChartHisDto.DataBean.ResultsBean msg, long fromUserId, int position, MessageBaseHolder.OnCusItemClickListener cusItemClickListener) {
        videoPlayBtn.setVisibility(View.GONE);
        videoDurationText.setVisibility(View.GONE);
        resetParentLayout();

        contentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cusItemClickListener != null) {
                    cusItemClickListener.onUserIconClick(contentImage, msg);
                }
            }
        });
        GlideEngine.loadCornerImage(contentImage, msg.getChatMessages(), null, DEFAULT_RADIUS);
    }

    private void resetParentLayout() {
        ((FrameLayout) contentImage.getParent().getParent()).setPadding(17, 0, 13, 0);
    }
}
