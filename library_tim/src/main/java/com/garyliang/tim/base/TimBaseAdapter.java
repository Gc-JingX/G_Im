package com.garyliang.tim.base;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class TimBaseAdapter<T, E extends TimBaseHolder> extends RecyclerView.Adapter<E> {

    protected List<T> datas;      //展示的数据集合
    protected OnItemClickListener onItemClickListener;   //条目点击的监听事件
    protected OnItemLongClickListener onItemLongClickListener;  //条目长按的监听事件
    protected E holder;

    //设置集合的数据
    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addDatas(List<T> datas) {
        if (this.datas == null) {
            this.datas = new ArrayList<>();
        }
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public void addData(@IntRange(from = 0) int position, @NonNull Collection<? extends T> newData) {
        if (this.datas == null) {
            this.datas = new ArrayList<>();
        }
        datas.addAll(position, newData);
        notifyItemRangeInserted(position, newData.size());
        compatibilityDataSizeChanged(newData.size());
    }

    private void compatibilityDataSizeChanged(int size) {
        final int dataSize = datas == null ? 0 : datas.size();
        if (dataSize == size) {
            notifyDataSetChanged();
        }
    }

    public List<T> getDatas() {
        if (datas == null) {
            return new ArrayList<>();
        }
        return datas;
    }

    //获取指定条目的数据
    public T getItemData(int position) {
        return (datas == null || datas.size() - 1 < position) ? null : datas.get(position);
    }

    @Override
    public E onCreateViewHolder(ViewGroup parent, int viewType) {
        E holder = initHolder(parent, viewType);
        holder.setOnItemLongClickListener(onItemLongClickListener); //设置条目的长按事件
        return holder;
    }

    protected abstract E initHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(E holder, int position) {
        this.holder = holder;
        holder.setOnItemListener(onItemClickListener);      //设置条目点击监听
        holder.initData(datas.isEmpty() ? null : datas.get(position), position);      //初始化holder显示的数据
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    //设置条目点击事件
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //设置条目长按的监听事件
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onLongClick(View view, int position);
    }
}
