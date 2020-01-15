package com.garyliang.tim.holder.view.inputmore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;


import com.garyliang.tim.R;

import java.util.List;

public class InputMoreLayout extends LinearLayout {

    public InputMoreLayout(Context context) {
        super(context);
        init();
    }

    public InputMoreLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InputMoreLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.chat_inputmore_layout, this);
    }

    // 初始化更多布局adapter
    public void init(List<InputMoreActionUnit> actions) {

        final ViewPager viewPager = findViewById(R.id.viewPager);
        final ViewGroup indicator = findViewById(R.id.actions_page_indicator);

        ActionsPagerAdapter adapter = new ActionsPagerAdapter(viewPager, actions);
        viewPager.setAdapter(adapter);
        initPageListener(indicator, adapter.getCount(), viewPager);
    }

    // 初始化更多布局PageListener
    private void initPageListener(final ViewGroup indicator, final int count, final ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                setIndicator(indicator, count, position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//todo
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//todo

            }
        });

        setIndicator(indicator, count, 0);
    }

    /**
     * 设置页码
     */
    private void setIndicator(ViewGroup indicator, int total, int current) {
        if (total <= 1) {
            indicator.removeAllViews();
        } else {
            indicator.removeAllViews();
            for (int i = 0; i < total; i++) {
                ImageView imgCur = new ImageView(indicator.getContext());
                imgCur.setId(i);
                // 判断当前页码来更新
                if (i == current) {
                    imgCur.setBackgroundResource(R.drawable.page_selected);
                } else {
                    imgCur.setBackgroundResource(R.drawable.page_unselected);
                }

                indicator.addView(imgCur);
            }
        }
    }
}
