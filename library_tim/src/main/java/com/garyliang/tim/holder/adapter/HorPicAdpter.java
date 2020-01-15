package com.garyliang.tim.holder.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.garyliang.tim.R;
import com.garyliang.tim.base.TimBaseAdapter;
import com.garyliang.tim.base.TimBaseHolder;
import com.garyliang.tim.holder.MessageBaseHolder;
import com.garyliang.tim.holder.imageengine.impl.GlideEngine;

import java.util.ArrayList;
import java.util.List;

import static com.garyliang.tim.holder.adapter.HorPicAdpter.PicMsgHolder.getInstance;

/**
 * <pre>
 *     author : GaryLiang
 *     e-mail : 595184932@qq.com
 *     time   : 2019/9/3
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class HorPicAdpter extends TimBaseAdapter<String, TimBaseHolder<String>> {
    private Context context;
    private MessageBaseHolder.OnCusItemClickListener cusItemClickListener;

    public HorPicAdpter(Context context, MessageBaseHolder.OnCusItemClickListener cusItemClickListener) {
        this.context = context;
        this.cusItemClickListener = cusItemClickListener;

    }


    private List<Uri> convert(List<String> data) {
        List<Uri> list = new ArrayList<>();
        for (String d : data) list.add(Uri.parse(d));
        return list;
    }

    @Override
    protected TimBaseHolder<String> initHolder(ViewGroup parent, int viewType) {
        return getInstance(parent, context, cusItemClickListener);
    }

    // 图片

    public static class PicMsgHolder extends TimBaseHolder<String> {

        ImageView imageView;
        private Context context;
        private MessageBaseHolder.OnCusItemClickListener cusItemClickListener;

        public static PicMsgHolder getInstance(ViewGroup parent, Context context, MessageBaseHolder.OnCusItemClickListener cusItemClickListener) {
            View item = LayoutInflater.from(context).inflate(R.layout.message_item_horistonal, parent, false);
            return new PicMsgHolder(item, context, cusItemClickListener);
        }

        public PicMsgHolder(View itemView, Context context, MessageBaseHolder.OnCusItemClickListener cusItemClickListener) {
            super(itemView);
            this.context = context;
            this.cusItemClickListener = cusItemClickListener;
        }

        @Override
        protected void initView(View itemView) {
            imageView = itemView.findViewById(R.id.iv);
        }

        @Override
        protected void initData(String data) {
            GlideEngine.loadCornerImage(imageView, data, null, 10);
            imageView.setOnClickListener(v -> {
                if (cusItemClickListener != null) {
                    cusItemClickListener.onPicClick(imageView, position, data);
                }
            });
        }
    }
}
