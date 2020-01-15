package com.garyliang.tim.holder;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.garyliang.tim.R;
import com.garyliang.tim.base.TimBaseAdapter;
import com.garyliang.tim.entity.EMMessageAttributeDTO;
import com.garyliang.tim.entity.MessageInfo;
import com.garyliang.tim.entity.TimChatDtaDto;
import com.garyliang.tim.holder.adapter.ChildChatAdpter;
import com.garyliang.tim.util.TUIKitLog;
import com.google.gson.Gson;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.imsdk.TIMElem;

import java.io.UnsupportedEncodingException;


public class MessageCustomHolder extends MessageContentHolder implements ICustomMessageViewGroup {

    private MessageInfo mMessageInfo;
    private int mPosition;

    private RecyclerView recyclerView;
    private ChildChatAdpter childChatAdpter;

    public MessageCustomHolder(View itemView, Context context) {
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

        if (data instanceof TimChatDtaDto) {
            TimChatDtaDto chatDtaDto = (TimChatDtaDto) data;
            layoutViews(chatDtaDto.getMessage(), position);
        }
    }

    @Override
    public int getVariableLayout() {
        return R.layout.message_adapter_content_custom;
    }

    @Override
    public void initVariableViews() {
        recyclerView = rootView.findViewById(R.id.rv_cus);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        childChatAdpter = new ChildChatAdpter(mContext);
        recyclerView.setAdapter(childChatAdpter);
        childChatAdpter.setOnItemClickListener(new TimBaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (getItemClickListener() != null) {
                    getItemClickListener().onClick(position, childChatAdpter.getDatas().get(position));
                }
            }
        });
    }

    @Override
    public void layoutViews(MessageInfo msg, int position) {
        mMessageInfo = msg;
        mPosition = position;
        super.layoutViews(msg, position);
    }

    @Override
    public void layoutVariableViews(MessageInfo msg, int position) {

        TIMElem elem = msg.getElement();
        if (!(elem instanceof TIMCustomElem)) {
            return;
        }
        TIMCustomElem customElem = (TIMCustomElem) elem;
        try {
            String s1 = new String(customElem.getData(), "utf-8");
            TUIKitLog.e("xx", "msg " + s1);
            EMMessageAttributeDTO attributeDTO = new Gson().fromJson(s1, EMMessageAttributeDTO.class);
            childChatAdpter.setDatas(attributeDTO.getFirstMessageExpandDTOs());
        } catch (UnsupportedEncodingException e) {

            //todo
        }


    }

    private void hideAll() {
        for (int i = 0; i < ((RelativeLayout) rootView).getChildCount(); i++) {
            ((RelativeLayout) rootView).getChildAt(i).setVisibility(View.GONE);
        }
    }

    @Override
    public void addMessageItemView(View view) {
        hideAll();
        if (view != null) {
            ((RelativeLayout) rootView).removeView(view);
            ((RelativeLayout) rootView).addView(view);
        }
    }

    @Override
    public void addMessageContentView(View view) {
        // item有可能被复用，因为不能确定是否存在其他自定义view，这里把所有的view都隐藏之后重新layout
        hideAll();
        super.layoutViews(mMessageInfo, mPosition);
    }

}
