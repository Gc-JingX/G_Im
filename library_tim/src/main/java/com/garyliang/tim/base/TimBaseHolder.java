package com.garyliang.tim.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


public abstract class TimBaseHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected int position;
    protected TimBaseAdapter.OnItemClickListener onItemClickListener;
    protected TimBaseAdapter.OnItemLongClickListener onItemLongClickListener;

    public TimBaseHolder(View itemView) {
        super(itemView);
        initView(itemView);
        itemView.setOnClickListener(this);  //设置条目的点击事件
    }

    //初始化视图
    protected abstract void initView(View itemView);

    //初始化数据
    public void initData(T data, int position) {
        this.position = position;
        itemView.setOnLongClickListener(view -> {
            if (onItemLongClickListener != null) {
                onItemLongClickListener.onLongClick(itemView, position);
                return true;
            }
            return false;
        });
        initData(data);
        updateSkin();
    }

    protected abstract void initData(T data);

    //设置条目监听
    public void setOnItemListener(TimBaseAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //设置条目长按的监听事件
    public void setOnItemLongClickListener(TimBaseAdapter.OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            onItemClickListener.onClick(view, position);
        }
    }

    //设置皮肤颜色
    public void updateSkin() {
        setWhiteSkin();
    }



    //设置白色皮肤
    protected void setWhiteSkin() {

    }
}
