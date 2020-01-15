package com.garyliang.tim.entity;

import java.util.ArrayList;
import java.util.List;

public class GuidanceMessageDto {
    /**
     * code : 40001
     * message : 分页获取导诊台模块消息成功!
     * data : {"pageNo":1,"pageSize":20,"totalRecord":6,"totalPage":1,"results":[{"id":2,"title":"我想向医生咨询肿瘤治疗建议","type":"text","content":null},{"id":6,"title":"我想让医生查看我的检查报告并给出专业治疗建议","type":"text","content":null},{"id":9,"title":"向李永强医生提问","type":"url","content":"http://devm.healtt.com/m/doctor/869"},{"id":1,"title":"我想向医生提问-未指定医生","type":"url","content":"https://devm.healtt.com/m/qa/orderWithoutDoctor"},{"id":8,"title":"呼叫人工服务","type":"text","content":null},{"id":19,"title":"导诊购买","type":"event","content":null}],"params":{},"other":{}}
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
         * pageNo : 1
         * pageSize : 20
         * totalRecord : 6
         * totalPage : 1
         * results : [{"id":2,"title":"我想向医生咨询肿瘤治疗建议","type":"text","content":null},{"id":6,"title":"我想让医生查看我的检查报告并给出专业治疗建议","type":"text","content":null},{"id":9,"title":"向李永强医生提问","type":"url","content":"http://devm.healtt.com/m/doctor/869"},{"id":1,"title":"我想向医生提问-未指定医生","type":"url","content":"https://devm.healtt.com/m/qa/orderWithoutDoctor"},{"id":8,"title":"呼叫人工服务","type":"text","content":null},{"id":19,"title":"导诊购买","type":"event","content":null}]
         * params : {}
         * other : {}
         */

        private int pageNo;
        private int pageSize;
        private int totalRecord;
        private int totalPage;
        private Object params;
        private Object other;
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

        public Object getOther() {
            return other;
        }

        public void setOther(Object other) {
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


        public static class ResultsBean {
            /**
             * id : 2
             * title : 我想向医生咨询肿瘤治疗建议
             * type : text
             * content : null
             */

            private int id;
            private String title;
            private String type;
            private String content;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title == null ? "" : title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type == null ? "" : type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content == null ? "" : content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }

}
