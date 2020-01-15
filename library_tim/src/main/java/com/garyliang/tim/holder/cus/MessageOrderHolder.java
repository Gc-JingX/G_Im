package com.garyliang.tim.holder.cus;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.garyliang.tim.R;
import com.garyliang.tim.entity.TimChartHisDto;
import com.garyliang.tim.holder.MessageBaseHolder;
import com.garyliang.tim.holder.Properties;
import com.garyliang.tim.holder.adapter.HorPicAdpter;
import com.garyliang.tim.holder.view.TimSmartText;
import com.garyliang.tim.util.TimDensityUtil;

public class MessageOrderHolder {

    private TextView tvTitle;
    private TimSmartText tvContentDescribe;
    private RecyclerView picRv;
    private String[] showTitle = {"导诊分析订单", "肿瘤多学科联合会诊(MDT)", "第一次提问", "图文问诊订单"};

    protected View rootView;
    protected Properties properties = Properties.getInstance();

    public MessageOrderHolder(View itemView) {
        rootView = itemView;

        initVariableViews();
    }


    public void initVariableViews() {

        tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        tvContentDescribe = (TimSmartText) rootView.findViewById(R.id.tv_content_describe);
        picRv = (RecyclerView) rootView.findViewById(R.id.pic_rv);

    }

    public void layoutVariableViews(Context context, TimChartHisDto.DataBean.ResultsBean msg, long fromUserId, int workType, int position, MessageBaseHolder.OnCusItemClickListener cusItemClickListener) {
        tvTitle.setText(showTitle[workType - 1]);
        TimChartHisDto.DataBean.ResultsBean.ImMsgTempBean imMsgTempBean = msg.getImMsgTemplateDTO();
        tvContentDescribe.reset();
        setUserText("就诊人", (imMsgTempBean.getPatientName() + "/" + (imMsgTempBean.getPatientSex() == 1 ? "男" : "女") + "/" + imMsgTempBean.getPatientAge() + "岁"));
        setUserText("病灶种类", imMsgTempBean.getCustomDisease());
        setUserText("患病时长", imMsgTempBean.getIllnessTime());
        setUserText("病情描述", imMsgTempBean.getDiseaseInfo());
        if (!TextUtils.isEmpty(imMsgTempBean.getHopeHelp())) {
            setUserText("希望得到的帮助", imMsgTempBean.getHopeHelp());
        }
        if (!imMsgTempBean.getPictureUrl().isEmpty()) {

            tvContentDescribe.addPiece(new TimSmartText.Piece.Builder("图片资料")
                    .textColor(Color.parseColor("#6F7A77"))
                    .textSize(TimDensityUtil.dpToSp(15))
                    .build());

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            picRv.setLayoutManager(linearLayoutManager);
            HorPicAdpter horizontalAdapter = new HorPicAdpter(context,cusItemClickListener);
            picRv.setAdapter(horizontalAdapter);
            horizontalAdapter.setDatas(imMsgTempBean.getPictureUrl());

        } else {
            picRv.setVisibility(View.GONE);
        }
        // Display the final, styled text
        tvContentDescribe.display();
    }

    private void setUserText(String showTip, String txt) {
        tvContentDescribe.addPiece(new TimSmartText.Piece.Builder(showTip + "\n")
                .textColor(Color.parseColor("#6F7A77"))
                .textSize(TimDensityUtil.dpToSp(15))
                .build());
        tvContentDescribe.addPiece(new TimSmartText.Piece.Builder(txt + "\n\n")
                .textColor(Color.parseColor("#414A4C"))
                .textSize(TimDensityUtil.dpToSp(15))
                .build());
    }
}
