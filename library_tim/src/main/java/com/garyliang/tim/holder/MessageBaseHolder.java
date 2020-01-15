package com.garyliang.tim.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.garyliang.tim.R;
import com.garyliang.tim.TUIKit;
import com.garyliang.tim.base.TimBaseAdapter;
import com.garyliang.tim.base.TimBaseHolder;
import com.garyliang.tim.entity.MessageInfo;
import com.garyliang.tim.entity.TimChartHisDto;
import com.garyliang.tim.holder.cus.MessageCusEmptyHolder;
import com.garyliang.tim.holder.cus.MessageMineHolder;

public abstract class MessageBaseHolder extends TimBaseHolder {

    protected TimBaseAdapter mAdapter;
    protected Properties properties = Properties.getInstance();
    protected View rootView;
    protected Context mContext;

    public MessageBaseHolder(View itemView, Context context) {
        super(itemView);

        rootView = itemView;
        mContext = context;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mAdapter = (TimBaseAdapter) adapter;
    }


    public abstract void layoutViews(final MessageInfo msg, final int position);

    public static class Factory {
        private Factory() {
        }

        public static TimBaseHolder getInstance(Context context, ViewGroup parent, RecyclerView.Adapter adapter, int viewType, long fromUserId, final int workType) {

            LayoutInflater inflater = LayoutInflater.from(TUIKit.getAppContext());
            TimBaseHolder holder = null;
            View view = null;


            // 加群消息等holder
            if (viewType >= MessageInfo.MSG_TYPE_TIPS) {
                view = inflater.inflate(R.layout.message_adapter_item_empty, parent, false);
                holder = new MessageTipsHolder(view, context);
            }

            // 具体消息holder
            view = inflater.inflate(R.layout.message_adapter_item_content, parent, false);
            switch (viewType) {
                case MessageInfo.MSG_TYPE_TEXT:
                    holder = new MessageTextHolder(view, context);
                    ((MessageEmptyHolder) holder).setAdapter(adapter);
                    break;
                case MessageInfo.MSG_TYPE_IMAGE:
                case MessageInfo.MSG_TYPE_VIDEO:
                case MessageInfo.MSG_TYPE_CUSTOM_FACE:
                    holder = new MessageImageHolder(view, context);
                    ((MessageEmptyHolder) holder).setAdapter(adapter);
                    break;
                case MessageInfo.MSG_TYPE_AUDIO:
                    holder = new MessageAudioHolder(view, context);
                    ((MessageEmptyHolder) holder).setAdapter(adapter);
                    break;
                case MessageInfo.MSG_TYPE_FILE:
                    holder = new MessageFileHolder(view, context);
                    ((MessageEmptyHolder) holder).setAdapter(adapter);
                    break;
                case MessageInfo.MSG_TYPE_CUSTOM:
                    holder = new MessageCustomHolder(view, context);
                    ((MessageEmptyHolder) holder).setAdapter(adapter);
                    break;
                case MessageInfo.MSG_TYPE_MIME:
                    holder = new MessageMineHolder(context, view, fromUserId, workType);
                    ((MessageCusEmptyHolder) holder).setAdapter(adapter);
                    break;
                default:
                    break;
            }


            return holder;
        }
    }

    public interface OnItemClickListener {
        void onPicClick(View view, int position, MessageInfo messageInfo);

        void onUserIconClick(View view, int position, MessageInfo messageInfo);

        void onClick(int position, Object messageInfo);
    }

    public interface OnCusItemClickListener {
        void onPicClick(View view, int position, String url);

        void onUserIconClick(View view, TimChartHisDto.DataBean.ResultsBean messageInfo);
    }

    private OnItemClickListener itemClickListener;
    private OnCusItemClickListener cusItemClickListener;

    public OnItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public OnCusItemClickListener getCusItemClickListener() {
        return cusItemClickListener;
    }

    public void setCusItemClickListener(OnCusItemClickListener cusItemClickListener) {
        this.cusItemClickListener = cusItemClickListener;
    }
}
