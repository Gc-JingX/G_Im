package com.garyliang.tim.entity;

public class ScheduleDoctorDto {

    /**
     * code : 40001
     * message : 获取成功
     * data : {"id":42,"weekDay":5,"doctorId":2784,"status":0,"doctorName":"丁丁医生2","nickName":null,"imUserName":"z24h3830011507","imUserPassword":"z24h3830011507","code":null,"hospitalName":"福建省人民医院","fee":"0.01","logo":"Fj-prqoNWrt6sswoTNsH-BoMoq7E","fullLogo":"http://dev.image.healtt.com/Fj-prqoNWrt6sswoTNsH-BoMoq7E","departments":null,"userCode":"30011507"}
     */

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
        /**
         * id : 42
         * weekDay : 5
         * doctorId : 2784
         * status : 0
         * doctorName : 丁丁医生2
         * nickName : null
         * imUserName : z24h3830011507
         * imUserPassword : z24h3830011507
         * code : null
         * hospitalName : 福建省人民医院
         * fee : 0.01
         * logo : Fj-prqoNWrt6sswoTNsH-BoMoq7E
         * fullLogo : http://dev.image.healtt.com/Fj-prqoNWrt6sswoTNsH-BoMoq7E
         * departments : null
         * userCode : 30011507
         */

        private long id;
        private int weekDay;
        private long doctorId;
        private int status;
        private String doctorName;
        private String nickName;
        private String imUserName;
        private String imUserPassword;
        private String code;
        private String hospitalName;
        private String fee;
        private String logo;
        private String fullLogo;
        private String departments;
        private String userCode;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getWeekDay() {
            return weekDay;
        }

        public void setWeekDay(int weekDay) {
            this.weekDay = weekDay;
        }

        public long getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(long doctorId) {
            this.doctorId = doctorId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDoctorName() {
            return doctorName == null ? "" : doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getNickName() {
            return nickName == null ? "" : nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getImUserName() {
            return imUserName == null ? "" : imUserName;
        }

        public void setImUserName(String imUserName) {
            this.imUserName = imUserName;
        }

        public String getImUserPassword() {
            return imUserPassword == null ? "" : imUserPassword;
        }

        public void setImUserPassword(String imUserPassword) {
            this.imUserPassword = imUserPassword;
        }

        public String getCode() {
            return code == null ? "" : code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getHospitalName() {
            return hospitalName == null ? "" : hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getFee() {
            return fee == null ? "" : fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getLogo() {
            return logo == null ? "" : logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getFullLogo() {
            return fullLogo == null ? "" : fullLogo;
        }

        public void setFullLogo(String fullLogo) {
            this.fullLogo = fullLogo;
        }

        public String getDepartments() {
            return departments == null ? "" : departments;
        }

        public void setDepartments(String departments) {
            this.departments = departments;
        }

        public String getUserCode() {
            return userCode == null ? "" : userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }
    }
}
