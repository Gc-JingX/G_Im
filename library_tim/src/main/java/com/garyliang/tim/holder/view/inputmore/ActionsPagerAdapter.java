package com.garyliang.tim.holder.view.inputmore;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.garyliang.tim.R;

import java.util.ArrayList;
import java.util.List;


public class ActionsPagerAdapter extends PagerAdapter {

    private int itemCountPerGridView = 4;
    private final Context mContext;
    private final List<InputMoreActionUnit> mInputMoreList;
    private final ViewPager mViewPager;
    private final int mGridViewCount;
    private int actionHeight;

    public ActionsPagerAdapter(ViewPager mViewPager, List<InputMoreActionUnit> mInputMoreList) {
        this.mContext = mViewPager.getContext();
        this.mInputMoreList = new ArrayList<>(mInputMoreList);
        this.mViewPager = mViewPager;
        this.mGridViewCount = (mInputMoreList.size() + itemCountPerGridView - 1) / itemCountPerGridView;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int end = (position + 1) * itemCountPerGridView > mInputMoreList.size() ? mInputMoreList
                .size() : (position + 1) * itemCountPerGridView;
        List<InputMoreActionUnit> subBaseActions = mInputMoreList.subList(position
                * itemCountPerGridView, end);

        GridView gridView = new GridView(mContext);
        gridView.setAdapter(new ActionsGridViewAdapter(mContext, subBaseActions));
        if (mInputMoreList.size() >= 4) {
            gridView.setNumColumns(4);

            container.post(() -> {
                ViewGroup.LayoutParams layoutParams = mViewPager.getLayoutParams();
                layoutParams.height = actionHeight;
                mViewPager.setLayoutParams(layoutParams);
            });
        } else {
            gridView.setNumColumns(mInputMoreList.size());

            container.post(() -> {
                ViewGroup.LayoutParams layoutParams = mViewPager.getLayoutParams();
                layoutParams.height = actionHeight;
                mViewPager.setLayoutParams(layoutParams);
            });
        }
        gridView.setSelector(R.color.transparent);
        gridView.setHorizontalSpacing(0);
        gridView.setVerticalSpacing(0);
        gridView.setGravity(Gravity.CENTER);
        gridView.setTag(Integer.valueOf(position));
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = ((Integer) parent.getTag()) * itemCountPerGridView + position;
                mInputMoreList.get(index).getOnClickListener().onClick(view);
            }
        });

        container.addView(gridView);
        return gridView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return mGridViewCount;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
