package com.garyliang.tim.holder.view.input;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.garyliang.tim.R;
import com.garyliang.tim.base.IUIKitCallBack;
import com.garyliang.tim.entity.MessageInfo;
import com.garyliang.tim.entity.MessageInfoUtil;
import com.garyliang.tim.face.Emoji;
import com.garyliang.tim.face.FaceFragment;
import com.garyliang.tim.face.FaceManager;
import com.garyliang.tim.util.AudioPlayer;
import com.garyliang.tim.util.TUIKitLog;
import com.hjq.toast.ToastUtils;

/**
 * 聊天界面，底部发送图片、拍照、摄像、文件面板
 */

public class InputLayout extends InputLayoutUI implements View.OnClickListener, TextWatcher {

    private static final String TAG = InputLayout.class.getSimpleName();
    private static final int STATE_NONE_INPUT = -1;
    private static final int STATE_SOFT_INPUT = 0;
    private static final int STATE_VOICE_INPUT = 1;
    private static final int STATE_FACE_INPUT = 2;
    private static final int STATE_ACTION_INPUT = 3;
    private FaceFragment mFaceFragment;
    private ChatInputHandler mChatInputHandler;
    private MessageHandler mMessageHandler;
    private FragmentManager mFragmentManager;
    private InputMoreFragment mInputMoreFragment;
    private boolean mSendEnable;
    private boolean mAudioCancel;
    private int mCurrentState;
    private int mLastMsgLineCount;
    private float mStartRecordY;
    private String mInputContent;

    public InputLayout(Context context) {
        super(context);
    }

    public InputLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void init() {

        mAudioInputSwitchButton.setOnClickListener(this);
        mEmojiInputButton.setOnClickListener(this);
        mMoreInputButton.setOnClickListener(this);
        mSendTextButton.setOnClickListener(this);
        mTextInput.addTextChangedListener(this);
        mTextInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                showSoftInput();
                return false;
            }
        });
        mTextInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return false;
            }
        });
        mTextInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return false;
            }
        });

        mSendAudioButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                TUIKitLog.i(TAG, "mSendAudioButton onTouch action:" + motionEvent.getAction());
                if (!checkPermission(AUDIO_RECORD)) {
                    TUIKitLog.i(TAG, "audio record checkPermission failed");
                    return false;
                }
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mAudioCancel = true;
                        mStartRecordY = motionEvent.getY();
                        if (mChatInputHandler != null) {
                            mChatInputHandler.onRecordStatusChanged(ChatInputHandler.RECORD_START);
                        }
                        mSendAudioButton.setText("松开结束");
                        AudioPlayer.getInstance().startRecord(InputLayout.this::recordComplete);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (motionEvent.getY() - mStartRecordY < -100) {
                            mAudioCancel = true;
                            if (mChatInputHandler != null) {
                                mChatInputHandler.onRecordStatusChanged(ChatInputHandler.RECORD_CANCEL);
                            }
                        } else {
                            if (mAudioCancel && mChatInputHandler != null) {
                                mChatInputHandler.onRecordStatusChanged(ChatInputHandler.RECORD_START);
                            }
                            mAudioCancel = false;
                        }
                        mSendAudioButton.setText("松开结束");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        mAudioCancel = motionEvent.getY() - mStartRecordY < -100;
                        if (mChatInputHandler != null) {
                            mChatInputHandler.onRecordStatusChanged(ChatInputHandler.RECORD_STOP);
                        }
                        AudioPlayer.getInstance().stopRecord();
                        mSendAudioButton.setText("按住说话");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mTextInput.removeTextChangedListener(this);
    }

    @Override
    protected void startSendPhoto() {
        TUIKitLog.i(TAG, "startSendPhoto");
        if (!checkPermission(SEND_PHOTO)) {
            TUIKitLog.i(TAG, "startSendPhoto checkPermission failed");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        mInputMoreFragment.setCallback(new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                TUIKitLog.i(TAG, "onSuccess: " + data);
                MessageInfo info = MessageInfoUtil.buildImageMessage((Uri) data, true);
                if (mMessageHandler != null) {
                    mMessageHandler.sendMessage(info);
                    hideSoftInput();
                }
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                TUIKitLog.i(TAG, "errCode: " + errCode);
                ToastUtils.show(errMsg);
            }
        });
        mInputMoreFragment.startActivityForResult(intent, InputMoreFragment.REQUEST_CODE_PHOTO);
    }

    @Override
    protected void startCapture() {
        //todo
    }

    @Override
    protected void startVideoRecord() {
        //todo
    }

    @Override
    protected void startSendFile() {
        //todo
    }

    public void setChatInputHandler(ChatInputHandler handler) {
        this.mChatInputHandler = handler;
    }


    public void setMessageHandler(MessageHandler handler) {
        this.mMessageHandler = handler;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.voice_input_switch) {
            if (mCurrentState == STATE_FACE_INPUT || mCurrentState == STATE_ACTION_INPUT) {
                mCurrentState = STATE_VOICE_INPUT;
                mInputMoreView.setVisibility(View.GONE);
                mEmojiInputButton.setImageResource(R.drawable.action_face_selector);
            } else if (mCurrentState == STATE_SOFT_INPUT) {
                mCurrentState = STATE_VOICE_INPUT;
            } else {
                mCurrentState = STATE_SOFT_INPUT;
            }
            if (mCurrentState == STATE_VOICE_INPUT) {
                mAudioInputSwitchButton.setImageResource(R.drawable.action_textinput_selector);
                mSendAudioButton.setVisibility(VISIBLE);
                mTextInput.setVisibility(GONE);
                hideSoftInput();
            } else {
                mAudioInputSwitchButton.setImageResource(R.drawable.action_audio_selector);
                mSendAudioButton.setVisibility(GONE);
                mTextInput.setVisibility(VISIBLE);
                showSoftInput();
            }
        } else if (view.getId() == R.id.face_btn) {
            if (mCurrentState == STATE_VOICE_INPUT) {
                mCurrentState = STATE_NONE_INPUT;
                mAudioInputSwitchButton.setImageResource(R.drawable.action_audio_selector);
                mSendAudioButton.setVisibility(GONE);
                mTextInput.setVisibility(VISIBLE);
            }
            if (mCurrentState == STATE_FACE_INPUT) {
                mCurrentState = STATE_NONE_INPUT;
                mInputMoreView.setVisibility(View.GONE);
                mEmojiInputButton.setImageResource(R.drawable.action_face_selector);
                mTextInput.setVisibility(VISIBLE);
            } else {
                mCurrentState = STATE_FACE_INPUT;
                mEmojiInputButton.setImageResource(R.drawable.action_textinput_selector);
                showFaceViewGroup();
            }
        } else if (view.getId() == R.id.more_btn) {//若点击右边的“+”号按钮
            hideSoftInput();
            if (mMoreInputEvent instanceof View.OnClickListener) {
                ((View.OnClickListener) mMoreInputEvent).onClick(view);
            } else if (mMoreInputEvent instanceof BaseInputFragment) {
                showCustomInputMoreFragment();
            } else {
                if (mCurrentState == STATE_ACTION_INPUT) {
                    mCurrentState = STATE_NONE_INPUT;
                    //以下是zanhanding添加的代码，用于fix有时需要两次点击加号按钮才能呼出富文本选择布局的问题
                    //判断富文本选择布局是否已经被呼出，并反转相应的状态
                    if (mInputMoreView.getVisibility() == View.VISIBLE) {
                        mInputMoreView.setVisibility(View.GONE);
                    } else {
                        mInputMoreView.setVisibility(View.VISIBLE);
                    }
                    //以上是zanhanding添加的代码，用于fix有时需要两次点击加号按钮才能呼出富文本选择布局的问题
                } else {
                    showInputMoreLayout();//显示“更多”消息发送布局
                    mCurrentState = STATE_ACTION_INPUT;
                    mAudioInputSwitchButton.setImageResource(R.drawable.action_audio_selector);
                    mEmojiInputButton.setImageResource(R.drawable.action_face_selector);
                    mSendAudioButton.setVisibility(GONE);
                    mTextInput.setVisibility(VISIBLE);
                }
            }
        } else if (view.getId() == R.id.send_btn && mSendEnable) {
            if (mMessageHandler != null) {
                mMessageHandler.sendMessage(MessageInfoUtil.buildTextMessage(mTextInput.getText().toString().trim()));
            }
            mTextInput.setText("");
        }
    }

    private void showSoftInput() {
        TUIKitLog.i(TAG, "showSoftInput");
        hideInputMoreLayout();
        mAudioInputSwitchButton.setImageResource(R.drawable.action_audio_selector);
        mEmojiInputButton.setImageResource(R.drawable.ic_input_face_normal);
        mTextInput.requestFocus();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mTextInput, 0);
        if (mChatInputHandler != null) {
            postDelayed(() -> mChatInputHandler.onInputAreaClick(), 200);
        }
    }

    public void hideSoftInput() {
        TUIKitLog.i(TAG, "hideSoftInput");
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mTextInput.getWindowToken(), 0);
        mTextInput.clearFocus();
        mInputMoreView.setVisibility(View.GONE);
    }

    private void showFaceViewGroup() {
        TUIKitLog.i(TAG, "showFaceViewGroup");
        if (mFragmentManager == null) {
            mFragmentManager = mActivity.getFragmentManager();
        }
        if (mFaceFragment == null) {
            mFaceFragment = new FaceFragment();
        }
        hideSoftInput();
        mInputMoreView.setVisibility(View.VISIBLE);
        mTextInput.requestFocus();
        mFaceFragment.setListener(new FaceFragment.OnEmojiClickListener() {
            @Override
            public void onEmojiDelete() {
                int index = mTextInput.getSelectionStart();
                Editable editable = mTextInput.getText();
                boolean isFace = false;
                if (index <= 0) {
                    return;
                }
                if (editable.charAt(index - 1) == ']') {
                    for (int i = index - 2; i >= 0; i--) {
                        if (editable.charAt(i) == '[') {
                            String faceChar = editable.subSequence(i, index).toString();
                            if (FaceManager.isFaceChar(faceChar)) {
                                editable.delete(i, index);
                                isFace = true;
                            }
                            break;
                        }
                    }
                }
                if (!isFace) {
                    editable.delete(index - 1, index);
                }
            }

            @Override
            public void onEmojiClick(Emoji emoji) {
                int index = mTextInput.getSelectionStart();
                Editable editable = mTextInput.getText();
                editable.insert(index, emoji.getFilter());
                FaceManager.handlerEmojiText(mTextInput, editable.toString(), true);
            }

            @Override
            public void onCustomFaceClick(int groupIndex, Emoji emoji) {
                mMessageHandler.sendMessage(MessageInfoUtil.buildCustomFaceMessage(groupIndex, emoji.getFilter()));
            }
        });
        mFragmentManager.beginTransaction().replace(R.id.more_groups, mFaceFragment).commitAllowingStateLoss();
        if (mChatInputHandler != null) {
            postDelayed(() -> mChatInputHandler.onInputAreaClick(), 100);
        }
    }

    private void showCustomInputMoreFragment() {
        TUIKitLog.i(TAG, "showCustomInputMoreFragment");
        if (mFragmentManager == null) {
            mFragmentManager = mActivity.getFragmentManager();
        }
        BaseInputFragment fragment = (BaseInputFragment) mMoreInputEvent;
        hideSoftInput();
        mInputMoreView.setVisibility(View.VISIBLE);
        mFragmentManager.beginTransaction().replace(R.id.more_groups, fragment).commitAllowingStateLoss();
        if (mChatInputHandler != null) {
            postDelayed(() -> mChatInputHandler.onInputAreaClick(), 100);
        }
    }

    private void showInputMoreLayout() {
        TUIKitLog.i(TAG, "showInputMoreLayout");
        if (mFragmentManager == null) {
            mFragmentManager = mActivity.getFragmentManager();
        }
        if (mInputMoreFragment == null) {
            mInputMoreFragment = new InputMoreFragment();
        }

        assembleActions();
        mInputMoreFragment.setActions(mInputMoreActionList);
        hideSoftInput();
        mInputMoreView.setVisibility(View.VISIBLE);
        mFragmentManager.beginTransaction().replace(R.id.more_groups, mInputMoreFragment).commitAllowingStateLoss();
        if (mChatInputHandler != null) {
            postDelayed(() -> mChatInputHandler.onInputAreaClick(), 100);
        }
    }

    private void hideInputMoreLayout() {
        mInputMoreView.setVisibility(View.GONE);
    }

    private void recordComplete(boolean success) {
        int duration = AudioPlayer.getInstance().getDuration();
        TUIKitLog.i(TAG, "recordComplete duration:" + duration);
        if (mChatInputHandler != null) {
            if (!success || duration == 0) {
                mChatInputHandler.onRecordStatusChanged(ChatInputHandler.RECORD_FAILED);
                return;
            }
            if (mAudioCancel) {
                mChatInputHandler.onRecordStatusChanged(ChatInputHandler.RECORD_CANCEL);
                return;
            }
            if (duration < 1000) {
                mChatInputHandler.onRecordStatusChanged(ChatInputHandler.RECORD_TOO_SHORT);
                return;
            }
            mChatInputHandler.onRecordStatusChanged(ChatInputHandler.RECORD_STOP);
        }

        if (mMessageHandler != null && success) {
            mMessageHandler.sendMessage(MessageInfoUtil.buildAudioMessage(AudioPlayer.getInstance().getPath(), duration));
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mInputContent = s.toString();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//todo
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (TextUtils.isEmpty(s.toString().trim())) {
            mSendEnable = false;
            showSendTextButton(View.GONE);
            showMoreInputButton(View.VISIBLE);
        } else {
            mSendEnable = true;
            showSendTextButton(View.VISIBLE);
            showMoreInputButton(View.GONE);
            if (mTextInput.getLineCount() != mLastMsgLineCount) {
                mLastMsgLineCount = mTextInput.getLineCount();
                if (mChatInputHandler != null) {
                    mChatInputHandler.onInputAreaClick();
                }
            }
            if (!TextUtils.equals(mInputContent, mTextInput.getText().toString())) {
                FaceManager.handlerEmojiText(mTextInput, mTextInput.getText().toString(), true);
            }
        }
    }


    public interface MessageHandler {
        void sendMessage(MessageInfo msg);
    }

    public static final class ChatInputHandler {

        public static final int RECORD_START = 1;
        public static final int RECORD_STOP = 2;
        public static final int RECORD_CANCEL = 3;
        public static final int RECORD_TOO_SHORT = 4;
        public static final int RECORD_FAILED = 5;

        void onInputAreaClick() {
            //todo
        }

        void onRecordStatusChanged(int status) {
            //todo

        }
    }

}
