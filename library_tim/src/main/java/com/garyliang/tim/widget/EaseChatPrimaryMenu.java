package com.garyliang.tim.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.garyliang.tim.R;


/**
 * primary menu
 */
public class EaseChatPrimaryMenu extends EaseChatPrimaryMenuBase implements OnClickListener {
    private EditText editText;
    private View buttonSetModeKeyboard;
    private RelativeLayout edittextLayout;
    private View buttonSetModeVoice;
    private TextView buttonSend;
    private View buttonPressToSpeak;
    private ImageView faceNormal;
    private Button buttonMore;
    private boolean ctrlPress = false;
    private boolean easyInputMenu = false; //简易输入菜单标示
    private boolean easyInputMojiaMenu = false; //简易带表情输入菜单标示

    public EaseChatPrimaryMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public EaseChatPrimaryMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EaseChatPrimaryMenu(Context context) {
        super(context);
        init(context);
    }

    //设置简易菜单显示，仅允许文字输入
    public void setEasyKeyboardWithMoja() {
        easyInputMojiaMenu = true;
        edittextLayout.setVisibility(View.VISIBLE);
        buttonSetModeVoice.setVisibility(View.GONE);
        buttonSetModeKeyboard.setVisibility(View.GONE);

        buttonSend.setVisibility(View.GONE);
        buttonMore.setVisibility(View.GONE);
        buttonPressToSpeak.setVisibility(View.GONE);
        faceNormal.setVisibility(View.VISIBLE);

    }

    //设置简易菜单显示，仅允许文字输入
    public void setEasyKeyboard() {
        easyInputMenu = true;
        edittextLayout.setVisibility(View.VISIBLE);
        buttonSetModeVoice.setVisibility(View.GONE);
        buttonSetModeKeyboard.setVisibility(View.GONE);

        buttonSend.setVisibility(View.VISIBLE);
        buttonMore.setVisibility(View.GONE);
        buttonPressToSpeak.setVisibility(View.GONE);
        faceNormal.setVisibility(View.GONE);

    }

    //设置图文聊天菜单显示
    public void setOnlyImageKeyboard() {
        easyInputMenu = false;
        edittextLayout.setVisibility(View.VISIBLE);
        buttonSetModeVoice.setVisibility(View.GONE);
        buttonSetModeKeyboard.setVisibility(View.GONE);

        buttonSend.setVisibility(View.GONE);
        buttonMore.setVisibility(View.VISIBLE);
        buttonPressToSpeak.setVisibility(View.GONE);
        faceNormal.setVisibility(View.VISIBLE);

        buttonSetModeVoice.setVisibility(VISIBLE);
    }

    private void init(final Context context) {
        LayoutInflater.from(context).inflate(R.layout.ease_widget_chat_primary_menu, this);
        editText = (EditText) findViewById(R.id.et_sendmessage);
        buttonSetModeKeyboard = findViewById(R.id.btn_set_mode_keyboard);
        edittextLayout = (RelativeLayout) findViewById(R.id.edittext_layout);
        buttonSetModeVoice = findViewById(R.id.btn_set_mode_voice);
        buttonSend = (TextView) findViewById(R.id.btn_send);
        buttonPressToSpeak = findViewById(R.id.btn_press_to_speak);
        faceNormal = (ImageView) findViewById(R.id.iv_face_normal);
        buttonMore = (Button) findViewById(R.id.btn_more);
//        edittextLayout.setBackgroundResource(R.drawable.ease_input_bar_bg_normal);

        buttonSend.setOnClickListener(this);
        buttonSetModeKeyboard.setOnClickListener(this);
        buttonSetModeVoice.setOnClickListener(this);
        buttonMore.setOnClickListener(this);
        faceNormal.setOnClickListener(this);

        editText.setOnClickListener(this);
        editText.requestFocus();


//        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
//
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    edittextLayout.setBackgroundResource(R.drawable.ease_input_bar_bg_active);
//                } else {
//                    edittextLayout.setBackgroundResource(R.drawable.ease_input_bar_bg_normal);
//                }
//
//            }
//        });
        // listen the text change
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    buttonMore.setVisibility(View.GONE);
                    buttonSend.setVisibility(View.VISIBLE);
                } else {
                    buttonMore.setVisibility(View.VISIBLE);
                    buttonSend.setVisibility(View.GONE);
                    //简易菜单，不显示表情按钮和拍照或其他
                    if (easyInputMenu) {
                        faceNormal.setVisibility(View.GONE);
                        buttonMore.setVisibility(View.GONE);
                        buttonSend.setVisibility(View.VISIBLE);
                    } else if (easyInputMojiaMenu) {

                        buttonMore.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//todo
            }

            @Override
            public void afterTextChanged(Editable s) {
//todo


            }
        });

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                // test on Mac virtual machine: ctrl map to KEYCODE_UNKNOWN
                if (keyCode == KeyEvent.KEYCODE_UNKNOWN) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        ctrlPress = true;
                    } else if (event.getAction() == KeyEvent.ACTION_UP) {
                        ctrlPress = false;
                    }
                }
                return false;
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND ||
                        (event.getKeyCode() == KeyEvent.KEYCODE_ENTER &&
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                ctrlPress)) {
                    String s = editText.getText().toString();
                    editText.setText("");
                    listener.onSendBtnClicked(s);
                    return true;
                } else {
                    return false;
                }
            }
        });


        buttonPressToSpeak.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (listener != null) {
                    return listener.onPressToSpeakBtnTouch(v, event);
                }
                return false;
            }
        });
    }

    /**
     * set recorder view when speak icon is touched
     *
     * @param voiceRecorderView
     */
//    public void setPressToSpeakRecorderView(EaseVoiceRecorderView voiceRecorderView) {
//        EaseVoiceRecorderView voiceRecorderView1 = voiceRecorderView;
//    }

    /**
     * append emoji icon to editText
     *
     * @param emojiContent
     */
    public void onEmojiconInputEvent(CharSequence emojiContent) {
        editText.append(emojiContent);
    }

    /**
     * delete emojicon
     */
    public void onEmojiconDeleteEvent() {
        if (!TextUtils.isEmpty(editText.getText())) {
            KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
            editText.dispatchKeyEvent(event);
        }
    }

    /**
     * on clicked event
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_send) {
            if (listener != null) {
                String s = editText.getText().toString();
                editText.setText("");
                listener.onSendBtnClicked(s);
            }
        } else if (id == R.id.btn_set_mode_voice) {     //TODO:语音聊天需申请权限
            //判断是否有录音权限
            setModeVoice();
            showNormalFaceImage();
            if (listener != null)
                listener.onToggleVoiceBtnClicked();

        } else if (id == R.id.btn_set_mode_keyboard) {
            setModeKeyboard();
            showNormalFaceImage();
            if (listener != null)
                listener.onToggleVoiceBtnClicked();
        } else if (id == R.id.btn_more) {
            toggleFaceImage();
            setModeKeyboard();
            showNormalFaceImage();
            if (listener != null)
                listener.onToggleExtendClicked();


        } else if (id == R.id.et_sendmessage) {
            faceNormal.setVisibility(View.VISIBLE);
            if (listener != null) {
                listener.onEditTextClicked();
            }
            //简易菜单，不显示表情按钮和拍照或其他
            if (easyInputMenu) {
                faceNormal.setVisibility(View.GONE);
                buttonMore.setVisibility(View.GONE);
                buttonSend.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.iv_face_normal) {
            toggleFaceImage();
            setModeKeyboard();
            showNormalFaceImage();
            if (listener != null) {
                listener.onToggleEmojiconClicked();
            }
        }
    }


    /**
     * show voice icon when speak bar is touched
     */
    protected void setModeVoice() {
        hideKeyboard();
        edittextLayout.setVisibility(View.GONE);
        buttonSetModeVoice.setVisibility(View.GONE);
        buttonSetModeKeyboard.setVisibility(View.VISIBLE);
        buttonSend.setVisibility(View.GONE);
        buttonMore.setVisibility(View.VISIBLE);
        buttonPressToSpeak.setVisibility(View.VISIBLE);
        faceNormal.setVisibility(View.VISIBLE);

    }


    /**
     * show keyboard
     */
    protected void setModeKeyboard() {
        edittextLayout.setVisibility(View.VISIBLE);
        buttonSetModeKeyboard.setVisibility(View.GONE);
        buttonSetModeVoice.setVisibility(View.VISIBLE);
        // mEditTextContent.setVisibility(View.VISIBLE);
        editText.requestFocus();
        // buttonSend.setVisibility(View.VISIBLE);
        buttonPressToSpeak.setVisibility(View.GONE);
        if (TextUtils.isEmpty(editText.getText())) {
            buttonMore.setVisibility(View.VISIBLE);
            buttonSend.setVisibility(View.GONE);
        } else {
            buttonMore.setVisibility(View.GONE);
            buttonSend.setVisibility(View.VISIBLE);
        }

    }


    protected void toggleFaceImage() {
        if (faceNormal.getVisibility() != View.VISIBLE) {
            showNormalFaceImage();
        }
    }

    private void showNormalFaceImage() {
//        faceNormal.setVisibility(View.VISIBLE);
        //简易菜单，不显示表情按钮和拍照或其他
        if (easyInputMenu) {
            faceNormal.setVisibility(View.GONE);
            buttonMore.setVisibility(View.GONE);
            buttonSend.setVisibility(View.VISIBLE);
        }
    }

    private void showSelectedFaceImage() {
        faceNormal.setVisibility(View.GONE);
    }


    @Override
    public void onExtendMenuContainerHide() {
        showNormalFaceImage();
    }

    @Override
    public void onTextInsert(CharSequence text) {
        int start = editText.getSelectionStart();
        Editable editable = editText.getEditableText();
        editable.insert(start, text);
        setModeKeyboard();
    }

    @Override
    public EditText getEditText() {
        return editText;
    }

}
