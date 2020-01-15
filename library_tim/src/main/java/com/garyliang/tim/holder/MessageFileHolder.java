package com.garyliang.tim.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.garyliang.tim.R;
import com.garyliang.tim.entity.MessageInfo;
import com.garyliang.tim.entity.TimChatDtaDto;
import com.garyliang.tim.util.FileUtil;
import com.hjq.toast.ToastUtils;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMFileElem;

public class MessageFileHolder extends MessageContentHolder {

    private TextView fileNameText;
    private TextView fileSizeText;
    private TextView fileStatusText;
    private ImageView fileIconImage;

    public MessageFileHolder(View itemView, Context context) {
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
        return R.layout.message_adapter_content_file;
    }

    @Override
    public void initVariableViews() {
        fileNameText = rootView.findViewById(R.id.file_name_tv);
        fileSizeText = rootView.findViewById(R.id.file_size_tv);
        fileStatusText = rootView.findViewById(R.id.file_status_tv);
        fileIconImage = rootView.findViewById(R.id.file_icon_iv);
    }

    @Override
    public void layoutVariableViews(final MessageInfo msg, final int position) {
        TIMElem elem = msg.getElement();
        if (!(elem instanceof TIMFileElem)) {
            return;
        }
        final TIMFileElem fileElem = (TIMFileElem) elem;
        final String path = msg.getDataPath();
        fileNameText.setText(fileElem.getFileName());
        String size = FileUtil.formetFileSize(fileElem.getFileSize());
        fileSizeText.setText(size);
        msgContentFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("文件路径:" + path);
            }
        });
        if (msg.getStatus() == MessageInfo.MSG_STATUS_SEND_SUCCESS || msg.getStatus() == MessageInfo.MSG_STATUS_NORMAL) {
            fileStatusText.setText(R.string.sended);
        } else if (msg.getStatus() == MessageInfo.MSG_STATUS_DOWNLOADING) {
            fileStatusText.setText(R.string.downloading);
        } else if (msg.getStatus() == MessageInfo.MSG_STATUS_DOWNLOADED) {
            fileStatusText.setText(R.string.downloaded);
        } else if (msg.getStatus() == MessageInfo.MSG_STATUS_UN_DOWNLOAD) {
            fileStatusText.setText(R.string.un_download);
            msgContentFrame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    msg.setStatus(MessageInfo.MSG_STATUS_DOWNLOADING);
                    sendingProgress.setVisibility(View.VISIBLE);
                    fileStatusText.setText(R.string.downloading);
                    fileElem.getToFile(path, new TIMCallBack() {
                        @Override
                        public void onError(int code, String desc) {
                            ToastUtils.show("getToFile fail:" + code + "=" + desc);
                            fileStatusText.setText(R.string.un_download);
                            sendingProgress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onSuccess() {
                            msg.setDataPath(path);
                            fileStatusText.setText(R.string.downloaded);
                            msg.setStatus(MessageInfo.MSG_STATUS_DOWNLOADED);
                            sendingProgress.setVisibility(View.GONE);
                            msgContentFrame.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ToastUtils.show("文件路径:" + path);

                                }
                            });
                        }
                    });
                }
            });
        }
    }

}
