package com.garyliang.tim.entity;

import java.util.ArrayList;
import java.util.List;

public class TimChartHisDto {


    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        private int pageNo;
        private int pageSize;
        private int totalRecord;
        private int totalPage;
        private Object params;
        private OtherBean other;
        private List<ResultsBean> results;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalRecord() {
            return totalRecord;
        }

        public void setTotalRecord(int totalRecord) {
            this.totalRecord = totalRecord;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public Object getParams() {
            return params;
        }

        public void setParams(Object params) {
            this.params = params;
        }

        public OtherBean getOther() {
            return other;
        }

        public void setOther(OtherBean other) {
            this.other = other;
        }

        public List<ResultsBean> getResults() {
            if (results == null) {
                return new ArrayList<>();
            }
            return results;
        }

        public void setResults(List<ResultsBean> results) {
            this.results = results;
        }


        public static class OtherBean {

            private List<AskAndAnswerListBean> AskAndAnswerList;

            public List<AskAndAnswerListBean> getAskAndAnswerList() {
                if (AskAndAnswerList == null) {
                    return new ArrayList<>();
                }
                return AskAndAnswerList;
            }

            public void setAskAndAnswerList(List<AskAndAnswerListBean> askAndAnswerList) {
                AskAndAnswerList = askAndAnswerList;
            }

            public static class AskAndAnswerListBean {

                private long orderParentId;
                private String orderCode;
                private long orderId;
                private int seq;
                private String diseaseInfo;
                private String hopeHelp;
                private int orderStatus;
                private Object caseFolderInfoInModelList;
                private List<AnswerInfoDTOListBean> answerInfoDTOList;

                public long getOrderParentId() {
                    return orderParentId;
                }

                public void setOrderParentId(long orderParentId) {
                    this.orderParentId = orderParentId;
                }

                public String getOrderCode() {
                    return orderCode == null ? "" : orderCode;
                }

                public void setOrderCode(String orderCode) {
                    this.orderCode = orderCode;
                }

                public long getOrderId() {
                    return orderId;
                }

                public void setOrderId(long orderId) {
                    this.orderId = orderId;
                }

                public int getSeq() {
                    return seq;
                }

                public void setSeq(int seq) {
                    this.seq = seq;
                }

                public String getDiseaseInfo() {
                    return diseaseInfo == null ? "" : diseaseInfo;
                }

                public void setDiseaseInfo(String diseaseInfo) {
                    this.diseaseInfo = diseaseInfo;
                }

                public String getHopeHelp() {
                    return hopeHelp == null ? "" : hopeHelp;
                }

                public void setHopeHelp(String hopeHelp) {
                    this.hopeHelp = hopeHelp;
                }

                public int getOrderStatus() {
                    return orderStatus;
                }

                public void setOrderStatus(int orderStatus) {
                    this.orderStatus = orderStatus;
                }

                public Object getCaseFolderInfoInModelList() {
                    return caseFolderInfoInModelList;
                }

                public void setCaseFolderInfoInModelList(Object caseFolderInfoInModelList) {
                    this.caseFolderInfoInModelList = caseFolderInfoInModelList;
                }

                public List<AnswerInfoDTOListBean> getAnswerInfoDTOList() {
                    if (answerInfoDTOList == null) {
                        return new ArrayList<>();
                    }
                    return answerInfoDTOList;
                }

                public void setAnswerInfoDTOList(List<AnswerInfoDTOListBean> answerInfoDTOList) {
                    this.answerInfoDTOList = answerInfoDTOList;
                }

                public static class AnswerInfoDTOListBean {
                    /**
                     * answerId : 450
                     * answerContent : 1212
                     * answerTime : 2019-11-06 17:03:59
                     * answerTimeString : 2019-11-06 17:03:59.0
                     * doctorId : 2812
                     * doctorName : 蔡医生
                     * doctorPortrait : http://dev.image.healtt.com/FgcEMtsRycBLopUGZsdDZH-J-zMD
                     */

                    private long answerId;
                    private String answerContent;
                    private String answerTime;
                    private String answerTimeString;
                    private String doctorId;
                    private String doctorName;
                    private String doctorPortrait;

                    public long getAnswerId() {
                        return answerId;
                    }

                    public void setAnswerId(long answerId) {
                        this.answerId = answerId;
                    }

                    public String getAnswerContent() {
                        return answerContent == null ? "" : answerContent;
                    }

                    public void setAnswerContent(String answerContent) {
                        this.answerContent = answerContent;
                    }

                    public String getAnswerTime() {
                        return answerTime == null ? "" : answerTime;
                    }

                    public void setAnswerTime(String answerTime) {
                        this.answerTime = answerTime;
                    }

                    public String getAnswerTimeString() {
                        return answerTimeString == null ? "" : answerTimeString;
                    }

                    public void setAnswerTimeString(String answerTimeString) {
                        this.answerTimeString = answerTimeString;
                    }

                    public String getDoctorId() {
                        return doctorId == null ? "" : doctorId;
                    }

                    public void setDoctorId(String doctorId) {
                        this.doctorId = doctorId;
                    }

                    public String getDoctorName() {
                        return doctorName == null ? "" : doctorName;
                    }

                    public void setDoctorName(String doctorName) {
                        this.doctorName = doctorName;
                    }

                    public String getDoctorPortrait() {
                        return doctorPortrait == null ? "" : doctorPortrait;
                    }

                    public void setDoctorPortrait(String doctorPortrait) {
                        this.doctorPortrait = doctorPortrait;
                    }
                }
            }
        }

        public static class ResultsBean {
            /**
             * msgId : 202
             * fromImUserName : 20tqby30011791
             * fromPortraitUrl : http://dev.image.healtt.com/Fvnx_MvBqqTFHuzUeAU0kLTvL5TI
             * fromUserName : jjkii
             * fromUserId : 3074
             * chatMessages : http://dev.image.healtt.com/Ft_zu89FH9mp7c92WTFPVgXxDMeq
             * messagesType : img
             * fileLength : 0
             * sendTime : 2019-09-27 15:32:03
             * chatType : groupchat
             * toId : 94374106038273
             */

            private long msgId;
            private String fromImUserName;
            private String fromPortraitUrl;
            private String fromUserName;
            private long fromUserId;
            private String chatMessages;
            private String messagesType;
            private int fileLength;
            private String sendTime;
            private String chatType;
            private String toId;
            private ImMsgTempBean imMsgTemplateDTO;

            private long orderParentId;
            private String orderCode;
            private long orderId;
            private int seq;
            private String diseaseInfo;
            private String hopeHelp;
            private int orderStatus;
            private Object caseFolderInfoInModelList;
            private List<AnswerInfoDTOListBean> answerInfoDTOList;

            private String dataPath;

            public String getDataPath() {
                return dataPath == null ? "" : dataPath;
            }

            public void setDataPath(String dataPath) {
                this.dataPath = dataPath;
            }

            public ImMsgTempBean getImMsgTemplateDTO() {
                return imMsgTemplateDTO;
            }

            public static class ImMsgTempBean {

                /**
                 * orderCode : TW201910241852313440718
                 * orderId : 1590
                 * doctorId : 2812
                 * orderStatus : 2
                 * patientId : 7
                 * patientName : 货物
                 * patientSex : 1
                 * patientAge : 39
                 * customDisease : 肾癌
                 * diseaseStr : 肾癌
                 * illnessTime : 一个月
                 * optTime : 1571914381000
                 * diseaseInfo : 兔兔
                 * hopeHelp : 拉近了距离
                 * msgTemplateTitle : 图文问诊订单
                 * pictureUrl : ["FoeY2q0t0PTqrSBFIu8Nj2Vyc38f"]
                 */

                private String orderCode;
                private long orderId;
                private long doctorId;
                private int orderStatus;
                private long patientId;
                private String patientName;
                private int patientSex;
                private int patientAge;
                private String customDisease;
                private String diseaseStr;
                private String illnessTime;
                private long optTime;
                private String diseaseInfo;
                private String hopeHelp;
                private String msgTemplateTitle;
                private List<String> pictureUrl;

                public String getOrderCode() {
                    return orderCode == null ? "" : orderCode;
                }

                public void setOrderCode(String orderCode) {
                    this.orderCode = orderCode;
                }

                public long getOrderId() {
                    return orderId;
                }

                public void setOrderId(long orderId) {
                    this.orderId = orderId;
                }

                public long getDoctorId() {
                    return doctorId;
                }

                public void setDoctorId(long doctorId) {
                    this.doctorId = doctorId;
                }

                public int getOrderStatus() {
                    return orderStatus;
                }

                public void setOrderStatus(int orderStatus) {
                    this.orderStatus = orderStatus;
                }

                public long getPatientId() {
                    return patientId;
                }

                public void setPatientId(long patientId) {
                    this.patientId = patientId;
                }

                public String getPatientName() {
                    return patientName == null ? "" : patientName;
                }

                public void setPatientName(String patientName) {
                    this.patientName = patientName;
                }

                public int getPatientSex() {
                    return patientSex;
                }

                public void setPatientSex(int patientSex) {
                    this.patientSex = patientSex;
                }

                public int getPatientAge() {
                    return patientAge;
                }

                public void setPatientAge(int patientAge) {
                    this.patientAge = patientAge;
                }

                public String getCustomDisease() {
                    return customDisease == null ? "" : customDisease;
                }

                public void setCustomDisease(String customDisease) {
                    this.customDisease = customDisease;
                }

                public String getDiseaseStr() {
                    return diseaseStr == null ? "" : diseaseStr;
                }

                public void setDiseaseStr(String diseaseStr) {
                    this.diseaseStr = diseaseStr;
                }

                public String getIllnessTime() {
                    return illnessTime == null ? "" : illnessTime;
                }

                public void setIllnessTime(String illnessTime) {
                    this.illnessTime = illnessTime;
                }

                public long getOptTime() {
                    return optTime;
                }

                public void setOptTime(long optTime) {
                    this.optTime = optTime;
                }

                public String getDiseaseInfo() {
                    return diseaseInfo == null ? "" : diseaseInfo;
                }

                public void setDiseaseInfo(String diseaseInfo) {
                    this.diseaseInfo = diseaseInfo;
                }

                public String getHopeHelp() {
                    return hopeHelp == null ? "" : hopeHelp;
                }

                public void setHopeHelp(String hopeHelp) {
                    this.hopeHelp = hopeHelp;
                }

                public String getMsgTemplateTitle() {
                    return msgTemplateTitle == null ? "" : msgTemplateTitle;
                }

                public void setMsgTemplateTitle(String msgTemplateTitle) {
                    this.msgTemplateTitle = msgTemplateTitle;
                }

                public List<String> getPictureUrl() {
                    if (pictureUrl == null) {
                        return new ArrayList<>();
                    }
                    return pictureUrl;
                }

                public void setPictureUrl(List<String> pictureUrl) {
                    this.pictureUrl = pictureUrl;
                }
            }

            public static class AnswerInfoDTOListBean {
                /**
                 * answerId : 450
                 * answerContent : 1212
                 * answerTime : 2019-11-06 17:03:59
                 * answerTimeString : 2019-11-06 17:03:59.0
                 * doctorId : 2812
                 * doctorName : 蔡医生
                 * doctorPortrait : http://dev.image.healtt.com/FgcEMtsRycBLopUGZsdDZH-J-zMD
                 */

                private long answerId;
                private String answerContent;
                private String answerTime;
                private String answerTimeString;
                private String doctorId;
                private String doctorName;
                private String doctorPortrait;

                public long getAnswerId() {
                    return answerId;
                }

                public void setAnswerId(long answerId) {
                    this.answerId = answerId;
                }

                public String getAnswerContent() {
                    return answerContent == null ? "" : answerContent;
                }

                public void setAnswerContent(String answerContent) {
                    this.answerContent = answerContent;
                }

                public String getAnswerTime() {
                    return answerTime == null ? "" : answerTime;
                }

                public void setAnswerTime(String answerTime) {
                    this.answerTime = answerTime;
                }

                public String getAnswerTimeString() {
                    return answerTimeString == null ? "" : answerTimeString;
                }

                public void setAnswerTimeString(String answerTimeString) {
                    this.answerTimeString = answerTimeString;
                }

                public String getDoctorId() {
                    return doctorId == null ? "" : doctorId;
                }

                public void setDoctorId(String doctorId) {
                    this.doctorId = doctorId;
                }

                public String getDoctorName() {
                    return doctorName == null ? "" : doctorName;
                }

                public void setDoctorName(String doctorName) {
                    this.doctorName = doctorName;
                }

                public String getDoctorPortrait() {
                    return doctorPortrait == null ? "" : doctorPortrait;
                }

                public void setDoctorPortrait(String doctorPortrait) {
                    this.doctorPortrait = doctorPortrait;
                }
            }

            public long getMsgId() {
                return msgId;
            }

            public void setMsgId(long msgId) {
                this.msgId = msgId;
            }

            public String getFromImUserName() {
                return fromImUserName == null ? "" : fromImUserName;
            }

            public void setFromImUserName(String fromImUserName) {
                this.fromImUserName = fromImUserName;
            }

            public String getFromPortraitUrl() {
                return fromPortraitUrl == null ? "" : fromPortraitUrl;
            }

            public void setFromPortraitUrl(String fromPortraitUrl) {
                this.fromPortraitUrl = fromPortraitUrl;
            }

            public String getFromUserName() {
                return fromUserName == null ? "" : fromUserName;
            }

            public void setFromUserName(String fromUserName) {
                this.fromUserName = fromUserName;
            }

            public long getFromUserId() {
                return fromUserId;
            }

            public void setFromUserId(long fromUserId) {
                this.fromUserId = fromUserId;
            }

            public String getChatMessages() {
                return chatMessages == null ? "" : chatMessages;
            }

            public void setChatMessages(String chatMessages) {
                this.chatMessages = chatMessages;
            }

            public String getMessagesType() {
                return messagesType == null ? "" : messagesType;
            }

            public void setMessagesType(String messagesType) {
                this.messagesType = messagesType;
            }

            public int getFileLength() {
                return fileLength;
            }

            public void setFileLength(int fileLength) {
                this.fileLength = fileLength;
            }

            public String getSendTime() {
                return sendTime == null ? "" : sendTime;
            }

            public void setSendTime(String sendTime) {
                this.sendTime = sendTime;
            }

            public String getChatType() {
                return chatType == null ? "" : chatType;
            }

            public void setChatType(String chatType) {
                this.chatType = chatType;
            }

            public String getToId() {
                return toId == null ? "" : toId;
            }

            public void setToId(String toId) {
                this.toId = toId;
            }

            public void setImMsgTemplateDTO(ImMsgTempBean imMsgTemplateDTO) {
                this.imMsgTemplateDTO = imMsgTemplateDTO;
            }

            public long getOrderParentId() {
                return orderParentId;
            }

            public void setOrderParentId(long orderParentId) {
                this.orderParentId = orderParentId;
            }

            public String getOrderCode() {
                return orderCode == null ? "" : orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }

            public long getOrderId() {
                return orderId;
            }

            public void setOrderId(long orderId) {
                this.orderId = orderId;
            }

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public String getDiseaseInfo() {
                return diseaseInfo == null ? "" : diseaseInfo;
            }

            public void setDiseaseInfo(String diseaseInfo) {
                this.diseaseInfo = diseaseInfo;
            }

            public String getHopeHelp() {
                return hopeHelp == null ? "" : hopeHelp;
            }

            public void setHopeHelp(String hopeHelp) {
                this.hopeHelp = hopeHelp;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
            }

            public Object getCaseFolderInfoInModelList() {
                return caseFolderInfoInModelList;
            }

            public void setCaseFolderInfoInModelList(Object caseFolderInfoInModelList) {
                this.caseFolderInfoInModelList = caseFolderInfoInModelList;
            }

            public List<AnswerInfoDTOListBean> getAnswerInfoDTOList() {
                if (answerInfoDTOList == null) {
                    return new ArrayList<>();
                }
                return answerInfoDTOList;
            }

            public void setAnswerInfoDTOList(List<AnswerInfoDTOListBean> answerInfoDTOList) {
                this.answerInfoDTOList = answerInfoDTOList;
            }
        }
    }
}
