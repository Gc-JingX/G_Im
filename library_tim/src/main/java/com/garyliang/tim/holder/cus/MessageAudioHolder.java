package com.garyliang.tim.holder.cus;

import android.graphics.drawable.AnimationDrawable;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.garyliang.tim.R;
import com.garyliang.tim.entity.TimChartHisDto;
import com.garyliang.tim.holder.Properties;
import com.garyliang.tim.util.AudioPlayer;
import com.garyliang.tim.util.ScreenUtil;
import com.garyliang.tim.util.TUIKitLog;
import com.hjq.toast.ToastUtils;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.conversation.Msg;
import com.tencent.imsdk.conversation.ProgressInfo;

import java.io.File;

public class MessageAudioHolder {

    private static final int AUDIO_MIN_WIDTH = ScreenUtil.getPxByDp(60);
    private static final int AUDIO_MAX_WIDTH = ScreenUtil.getPxByDp(250);
    private static final String STR_LIN = "/";


    private TextView audioTimeText;
    private ImageView audioPlayImage;
    private LinearLayout audioContentView;


    protected View rootView;
    protected Properties properties = Properties.getInstance();

    public MessageAudioHolder(View itemView) {
        rootView = itemView;

        initVariableViews();
    }


    public void initVariableViews() {
        audioTimeText = rootView.findViewById(R.id.audio_time_tv);
        audioPlayImage = rootView.findViewById(R.id.audio_play_iv);
        audioContentView = rootView.findViewById(R.id.audio_content_ll);
    }

    public void layoutVariableViews(TimChartHisDto.DataBean.ResultsBean msg, long fromUserId, FrameLayout msgContentFrame) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);


        if (fromUserId == msg.getFromUserId()) {
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.rightMargin = 24;
            audioPlayImage.setImageResource(R.drawable.voice_msg_playing_3);
            audioContentView.removeView(audioPlayImage);
            audioContentView.addView(audioPlayImage);
        } else {
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.leftMargin = 24;
            // TODO 图标不对
            audioPlayImage.setImageResource(R.drawable.voice_msg_playing_3);
            audioContentView.removeView(audioPlayImage);
            audioContentView.addView(audioPlayImage, 0);

        }
        audioContentView.setLayoutParams(params);
        getSound(msg);
        int duration = msg.getFileLength();
        if (duration == 0) {
            duration = 1;
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
                AudioPlayer.getInstance().startPlay(msg.getDataPath(), success -> audioPlayImage.post(() -> {
                    animationDrawable.stop();
                    audioPlayImage.setImageResource(R.drawable.voice_msg_playing_3);
                }));
            }
        });
    }

    public String getFileName(String pathandname) {
        int start = pathandname.lastIndexOf(STR_LIN);
        if (start != -1) {
            return pathandname.substring(start + 1);
        } else {
            return null;
        }
    }

    private void getSound(final TimChartHisDto.DataBean.ResultsBean msg) {
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/A_dingdingDataFolder/voice";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        String fileName = getFileName(msg.getChatMessages());
        File file2 = new File(filePath + STR_LIN + fileName);
        String playUrl = file2.getAbsolutePath();
        if (!file2.exists()) {
            Msg.downloadToFile(3, msg.getChatMessages(), filePath, new TIMValueCallBack<ProgressInfo>() {
                @Override
                public void onError(int i, String s) {
                    TUIKitLog.e("TIMValueCallBack", "onError " + s);

                }

                @Override
                public void onSuccess(ProgressInfo progressInfo) {

                    TUIKitLog.e("TIMValueCallBack", "progressInfo " + progressInfo.getCurrentSize() + STR_LIN + progressInfo.getTotalSize());
                }
            }, new TIMCallBack() {
                @Override
                public void onError(int i, String s) {

                    TUIKitLog.e("TIMCallBack", "onError " + s);
                }

                @Override
                public void onSuccess() {
                    TUIKitLog.e("TIMCallBack", "onSuccess");
                    msg.setDataPath(playUrl);

                }
            });

        } else {

            msg.setDataPath(playUrl);
        }
    }

}
