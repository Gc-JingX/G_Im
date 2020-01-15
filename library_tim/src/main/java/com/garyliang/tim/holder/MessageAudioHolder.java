package com.garyliang.tim.holder;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.garyliang.tim.R;
import com.garyliang.tim.entity.MessageInfo;
import com.garyliang.tim.entity.TimChatDtaDto;
import com.garyliang.tim.util.AudioPlayer;
import com.garyliang.tim.util.ScreenUtil;
import com.garyliang.tim.util.TUIKitConstants;
import com.garyliang.tim.util.TUIKitLog;
import com.hjq.toast.ToastUtils;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMSoundElem;

import java.io.File;

public class MessageAudioHolder extends MessageContentHolder {

    private static final int AUDIO_MIN_WIDTH = ScreenUtil.getPxByDp(60);
    private static final int AUDIO_MAX_WIDTH = ScreenUtil.getPxByDp(250);

    private static final int UNREAD = 0;
    private static final int READ = 1;

    private TextView audioTimeText;
    private ImageView audioPlayImage;
    private LinearLayout audioContentView;


    public MessageAudioHolder(View itemView, Context context) {
        super(itemView, context);

        rootView = itemView;
        mContext = context;

    }

    @Override
    protected void initView(View itemView) {
//todo
    }

    @Override
    protected void initData(Object data) {

        if (data instanceof TimChatDtaDto) {
            TimChatDtaDto chatDtaDto = (TimChatDtaDto) data;
            layoutViews(chatDtaDto.getMessage(), position);
        }
    }

    @Override
    public int getVariableLayout() {
        return R.layout.message_adapter_content_audio;
    }

    @Override
    public void initVariableViews() {
        audioTimeText = rootView.findViewById(R.id.audio_time_tv);
        audioPlayImage = rootView.findViewById(R.id.audio_play_iv);
        audioContentView = rootView.findViewById(R.id.audio_content_ll);
    }

    @Override
    public void layoutVariableViews(final MessageInfo msg, final int position) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        if (msg.isSelf()) {
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.rightMargin = 24;
            audioPlayImage.setImageResource(R.drawable.voice_msg_playing_3);
            audioContentView.removeView(audioPlayImage);
            audioContentView.addView(audioPlayImage);
            unreadAudioText.setVisibility(View.GONE);
        } else {
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.leftMargin = 24;
            // TODO 图标不对
            audioPlayImage.setImageResource(R.drawable.voice_msg_playing_3);
            audioContentView.removeView(audioPlayImage);
            audioContentView.addView(audioPlayImage, 0);
            if (msg.getCustomInt() == UNREAD) {
                LinearLayout.LayoutParams unreadParams = (LinearLayout.LayoutParams) isReadText.getLayoutParams();
                unreadParams.gravity = Gravity.CENTER_VERTICAL;
                unreadParams.leftMargin = 10;
                unreadAudioText.setVisibility(View.VISIBLE);
                unreadAudioText.setLayoutParams(unreadParams);
            } else {
                unreadAudioText.setVisibility(View.GONE);
            }
        }
        audioContentView.setLayoutParams(params);

        TIMElem elem = msg.getElement();
        if (!(elem instanceof TIMSoundElem)) {
            return;
        }
        final TIMSoundElem soundElem = (TIMSoundElem) elem;
        int duration = (int) soundElem.getDuration();
        if (duration == 0) {
            duration = 1;
        }
        if (TextUtils.isEmpty(msg.getDataPath())) {
            getSound(msg, soundElem);
        }
        ViewGroup.LayoutParams audioParams = msgContentFrame.getLayoutParams();
        audioParams.width = AUDIO_MIN_WIDTH + ScreenUtil.getPxByDp(duration * 6.0f);
        if (audioParams.width > AUDIO_MAX_WIDTH) {
            audioParams.width = AUDIO_MAX_WIDTH;
        }
        msgContentFrame.setLayoutParams(audioParams);
        audioTimeText.setText(duration + "''");
        msgContentFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AudioPlayer.getInstance().isPlaying()) {
                    AudioPlayer.getInstance().stopPlay();
                    return;
                }
                if (TextUtils.isEmpty(msg.getDataPath())) {
                    ToastUtils.show("语音文件还未下载完成");
                    return;
                }
                audioPlayImage.setImageResource(R.drawable.play_voice_message);
                final AnimationDrawable animationDrawable = (AnimationDrawable) audioPlayImage.getDrawable();
                animationDrawable.start();
                msg.setCustomInt(READ);
                unreadAudioText.setVisibility(View.GONE);
                AudioPlayer.getInstance().startPlay(msg.getDataPath(), success -> audioPlayImage.post(() -> {
                    animationDrawable.stop();
                    audioPlayImage.setImageResource(R.drawable.voice_msg_playing_3);
                }));
            }
        });
    }

    private void getSound(final MessageInfo msgInfo, TIMSoundElem soundElemEle) {
        final String path = TUIKitConstants.RECORD_DOWNLOAD_DIR + soundElemEle.getUuid();
        File file = new File(path);
        if (!file.exists()) {
            soundElemEle.getSoundToFile(path, new TIMCallBack() {
                @Override
                public void onError(int code, String desc) {
                    TUIKitLog.e("getSoundToFile failed code = ", code + ", info = " + desc);
                }

                @Override
                public void onSuccess() {
                    msgInfo.setDataPath(path);
                }
            });
        } else {
            msgInfo.setDataPath(path);
        }
    }

}
