package com.garyliang.tim.holder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garyliang.tim.R;
import com.garyliang.tim.base.TimBaseAdapter;
import com.garyliang.tim.base.TimBaseHolder;
import com.garyliang.tim.entity.GuidanceMessageDto;

import static com.garyliang.tim.holder.adapter.ChildChatAdpter.ChildTxtHolder.getInstance;

/**
 * <pre>
 *     author : GaryLiang
 *     e-mail : 595184932@qq.com
 *     time   : 2019/9/3
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class ChildChatAdpter extends TimBaseAdapter<GuidanceMessageDto.DataBean.ResultsBean, TimBaseHolder<GuidanceMessageDto.DataBean.ResultsBean>> {
    private Context context;

    public ChildChatAdpter(Context context) {
        this.context = context;

    }

    @Override
    protected TimBaseHolder<GuidanceMessageDto.DataBean.ResultsBean> initHolder(ViewGroup parent, int viewType) {
        return getInstance(parent, context);
    }

    // 图片

    public static class ChildTxtHolder extends TimBaseHolder<GuidanceMessageDto.DataBean.ResultsBean> {

        private Context context;
        private TextView txt;

        public static ChildTxtHolder getInstance(ViewGroup parent, Context context) {
            View item = LayoutInflater.from(context).inflate(R.layout.message__item_first_attribute, parent, false);
            return new ChildTxtHolder(item, context);
        }

        public ChildTxtHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
        }

        @Override
        protected void initView(View itemView) {
            txt = itemView.findViewById(R.id.tv_Attribute_text);
        }

        @Override
        protected void initData(GuidanceMessageDto.DataBean.ResultsBean data) {

            txt.setText(data.getTitle());
        }
    }
}
