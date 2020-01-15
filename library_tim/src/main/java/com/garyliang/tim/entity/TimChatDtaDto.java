package com.garyliang.tim.entity;


public class TimChatDtaDto {
    private MessageInfo message;
    private TimChartHisDto.DataBean.ResultsBean chatHisDto;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public TimChatDtaDto(MessageInfo message, TimChartHisDto.DataBean.ResultsBean chatHisDto, int type) {
        this.message = message;
        this.chatHisDto = chatHisDto;
        this.type = type;
    }

    public TimChatDtaDto(MessageInfo message, int type) {
        this.message = message;
        this.type = type;
    }

    public TimChatDtaDto(TimChartHisDto.DataBean.ResultsBean chatHisDto, int type) {
        this.chatHisDto = chatHisDto;
        this.type = type;
    }

    public MessageInfo getMessage() {
        return message;
    }

    public void setMessage(MessageInfo message) {
        this.message = message;
    }

    public TimChartHisDto.DataBean.ResultsBean getChatHisDto() {
        return chatHisDto;
    }

    public void setChatHisDto(TimChartHisDto.DataBean.ResultsBean chatHisDto) {
        this.chatHisDto = chatHisDto;
    }


}
