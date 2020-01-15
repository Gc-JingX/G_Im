package com.garyliang.tim.entity;

import java.util.ArrayList;
import java.util.List;

public class EMMessageAttributeDTO {
    private int code ;    //数据使用标示，code = 1 导诊医生信息，code = 2 导诊台模块消息
    private List<GuidanceMessageDto.DataBean.ResultsBean> firstMessageExpandDTOs;
    private ScheduleDoctorDto.DataBean firstDoctorDOT;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<GuidanceMessageDto.DataBean.ResultsBean> getFirstMessageExpandDTOs() {
        if (firstMessageExpandDTOs == null) {
            return new ArrayList<>();
        }
        return firstMessageExpandDTOs;
    }

    public void setFirstMessageExpandDTOs(List<GuidanceMessageDto.DataBean.ResultsBean> firstMessageExpandDTOs) {
        this.firstMessageExpandDTOs = firstMessageExpandDTOs;
    }

    public ScheduleDoctorDto.DataBean getFirstDoctorDOT() {
        return firstDoctorDOT;
    }

    public void setFirstDoctorDOT(ScheduleDoctorDto.DataBean firstDoctorDOT) {
        this.firstDoctorDOT = firstDoctorDOT;
    }
}
