package com.garyliang.tim.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.garyliang.tim.R;


/**
 * <pre>
 *     author : Gary.Liang
 *     e-mail : 595184932@qq.com
 *     time   : 2019/11/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class FirstEasyChatInputMenu extends LinearLayout {


    FrameLayout primaryMenuContainer;
    protected EaseChatPrimaryMenu chatPrimaryMenu;
    protected LayoutInflater layoutInflater;

    private FirstEasyChatInputMenu.ChatInputMenuListener listener;
    private Context context;
    private boolean inited;

    public FirstEasyChatInputMenu(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public FirstEasyChatInputMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FirstEasyChatInputMenu(Context context) {
        super(context);
        init(context);
    }

    //设置为简单聊天界面
    public void setEasyChat() {
        chatPrimaryMenu.setEasyKeyboard();
    }

    private void init(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.ease_widget_chat_input_menu, this);
        primaryMenuContainer = (FrameLayout) findViewById(R.id.primary_menu_container);

    }

    public void setInputTxt(String text) {
        chatPrimaryMenu.onTextInsert(text);
    }

    public EaseChatPrimaryMenu getChatPrimaryMenu() {
        return chatPrimaryMenu;
    }

    /**
     * init view
     * <p>
     * This method should be called after registerExtendMenuItem(), setCustomEmojiconMenu() and setCustomPrimaryMenu().
     */
    public void init() {
        if (inited) {
            return;
        }
        if (chatPrimaryMenu == null) {
            chatPrimaryMenu = (EaseChatPrimaryMenu) layoutInflater.inflate(R.layout.ease_layout_chat_primary_menu, null);
        }
        primaryMenuContainer.addView(chatPrimaryMenu);


        processChatMenu();

        inited = true;
    }

    /**
     * hide extend menu
     */
    public void hideExtendMenuContainer() {
        chatPrimaryMenu.onExtendMenuContainerHide();
    }


    public void setCustomPrimaryMenu(EaseChatPrimaryMenu customPrimaryMenu) {
        this.chatPrimaryMenu = customPrimaryMenu;
    }

    public EaseChatPrimaryMenuBase getPrimaryMenu() {
        return chatPrimaryMenu;
    }

    protected void processChatMenu() {
        // send message button
        chatPrimaryMenu.setChatPrimaryMenuListener(new EaseChatPrimaryMenuBase.EaseChatPrimaryMenuListener() {

            @Override
            public void onSendBtnClicked(String content) {
                if (listener != null)
                    listener.onSendMessage(content);
            }

            @Override
            public void onToggleVoiceBtnClicked() {
                //todo
            }

            @Override
            public void onToggleExtendClicked() {
                //todo
            }

            @Override
            public void onToggleEmojiconClicked() {
                //todo
            }

            @Override
            public void onEditTextClicked() {
                //todo
            }


            @Override
            public boolean onPressToSpeakBtnTouch(View v, MotionEvent event) {
                if (listener != null) {
                    return listener.onPressToSpeakBtnTouch(v, event);
                }
                return false;
            }
        });


    }


    /**
     * insert text
     *
     * @param text
     */
    public void insertText(String text) {
        getPrimaryMenu().onTextInsert(text);
    }


    /**
     * hide keyboard
     */
    private void hideKeyboard() {
        chatPrimaryMenu.hideKeyboard();
    }


    public void setChatInputMenuListener(FirstEasyChatInputMenu.ChatInputMenuListener listener) {
        this.listener = listener;
    }

    public interface ChatInputMenuListener {
        /**
         * when send message button pressed
         *
         * @param content message content
         */
        void onSendMessage(String content);

        /**
         * when big icon pressed
         *
         * @param emojicon
         */

        /**
         * when speak button is touched
         *
         * @param v
         * @param event
         * @return
         */
        boolean onPressToSpeakBtnTouch(View v, MotionEvent event);
    }

}
