package com.garyliang.tim.holder.view.input;

import com.garyliang.tim.base.TimBaseFragment;
import com.garyliang.tim.holder.view.input.impl.IChatLayout;

public class BaseInputFragment extends TimBaseFragment {

    private IChatLayout mChatLayout;

    public IChatLayout getChatLayout() {
        return mChatLayout;
    }

    public BaseInputFragment setChatLayout(IChatLayout layout) {
        mChatLayout = layout;
        return this;
    }
}
