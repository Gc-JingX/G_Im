package com.garyliang.tim.helper;

import com.garyliang.tim.holder.view.input.InputLayout;
import com.garyliang.tim.holder.view.inputmore.InputMoreActionUnit;

public class ChatLayoutHelper {


    private static ChatLayoutHelper mChatLayoutHelper = null;

    private ChatLayoutHelper() {
    }

    public static ChatLayoutHelper getInstance() {
        synchronized (ChatLayoutHelper.class) {
            if (mChatLayoutHelper == null) {
                mChatLayoutHelper = new ChatLayoutHelper();
            }
        }
        return mChatLayoutHelper;
    }

    public void customizeChatLayout(final InputLayout inputLayout, int[] txt, int[] icon, UserSelectListener listener) {

        inputLayout.disableCaptureAction(true);
        inputLayout.disableSendFileAction(true);
        inputLayout.disableSendPhotoAction(true);
        inputLayout.disableVideoRecordAction(true);

        for (int i = 0; i < txt.length; i++) {
            InputMoreActionUnit unit = new InputMoreActionUnit();
            unit.setIconResId(icon[i]);
            unit.setTitleId(txt[i]);
            int finalI = i;
            unit.setOnClickListener(v -> {

                if (listener != null) {
                    listener.onClickView(finalI);
                }

            });
            inputLayout.addAction(unit);
        }

    }

    private UserSelectListener userSelectListener;

    public UserSelectListener getUserSelectListener() {
        return userSelectListener;
    }

    public void setUserSelectListener(UserSelectListener userSelectListener) {
        this.userSelectListener = userSelectListener;
    }

    public interface UserSelectListener {
        void onClickView(int position);
    }


}
