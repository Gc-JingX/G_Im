package com.garyliang.tim.imutil.config;

/**
 * Created by bin on 2017/3/25.
 */

public interface DICT {

    //基础数据类型
    abstract class BASICTYPE {
        private BASICTYPE() {
        }

        public static final String ALL = "all";             //全部
        public static final String RULE = "rule";           //业务规则
        public static final String ADDRESS = "address";     //地区
        public static final String HOSPITAL = "hospital";   //医院
        public static final String SKILL = "skill";         //擅长
        public static final String MAJOR = "major";         //专业
        public static final String DISEASE = "disease";     //病种
        public static final String DIALECT = "dialect";     //方言
    }

    //用户状态
    abstract class USERSTATUS {
        private USERSTATUS() {
        }

        public static final String SUBMITTING = "submitting";   //待提交
        public static final String APPROVING = "approving";     //审核中
        public static final String DISAPPROVE = "disapprove";   //审核错误
        public static final String ENABLE = "enable";           //审核通过
    }

    //用户类型
    abstract class USERTYPE {
        private USERTYPE() {
        }

        public static final String DOCTOR = "doctor";       //医生
        public static final String PERSON = "person";       //病人
        public static final String SELLER = "seller";       //销售
        public static final String UNKNOWN = "unknown";     //未确定
        public static final String ADMIN = "admin";     //未确定
        public static final String WAITER = "waiter";     //客服

    }

    //销售类型类型  类型：直客 ZHIKE,渠道:QUDAO,基层顾问 BASE
    abstract class SELLERTYPE {
        private SELLERTYPE() {
        }

        public static final String ZHIKE = "ZHIKE";       //直客
        public static final String QUDAO = "QUDAO";       //渠道
        public static final String BASE = "BASE";       //基层顾问

    }


    /**
     * ------------  环信部分 ------------------------
     */
    //环信扩展消息类型
    abstract class ATTRIBUTETYPE {
        private ATTRIBUTETYPE() {
        }

        //键
        public static final String IS_VOICE_CALL = "is_voice_call";     //是否为语音电话
        public static final String IS_VIDEO_CALL = "is_video_call";     //是否为视频电话

        public static final String TYPE = "type";       //消息类型
        public static final String ACTION = "action";   //消息动作
        public static final String UID = "uid";     //用户id
        public static final String GID = "gid";     //群id
        public static final String ASK_BILL_ID = "ask_bill_id";     //咨询单id
        public static final String CARD_ID = "crad_id";             //名片id
        public static final String CONSULT_ID = "consult_id";       //会诊单id
        public static final String TRAIN_ID = "train_id";           //课程id
        public static final String TRAIN_ENROLL_ID = "train_enroll_id";     //报名信息id

        public static final String GROUP_HEAD_PORTRAIT = "group_head_portrait";     //群头像
        public static final String GROUP_TITLE = "group_title";     //群名称
        public static final String GROUP_NOTICE = "group_notice";   //群公告
        public static final String GROUP_SRCID = "group_srcId";     //关联的团队id
        public static final String GROUP_USER_ID = "group_user_id";         //群成员的用户id，多个用逗号分隔，String类型
        public static final String GROUP_MEMBER_ID = "group_member_id";     //群成员信息的id，多个用逗号分隔，String类型

        public static final String IS_TEAM_OWNER = "is_team_owner";     //是否为团队创建者，int类型(0和1)
        public static final String TEAM_ID = "team_id";                 //团队id
        public static final String TEAM_MEMBER_ID = "team_member_id";       //团队成员id

        //类型
        public static final String SYS_MSG = "sys_msg";     //系统消息
        public static final String CARD = "card";           //名片消息
        public static final String SURGERY_BILL = "surgery_bill";   //手术单消息
        public static final String CONSULT = "consult";     //会诊消息

        //动作
        public static final String ADD_FRIEND = "add_friend";                       //添加好友
        public static final String CREATE_ASK_BILL = "create_ask_bill";             //新增咨询单
        public static final String CANLE_ASK_BILL = "cancel_ask_bill";               //取消咨询单
        public static final String MEMBER_JOINED = "member_joined";         //添加群成员
        public static final String MEMBER_EXITED = "member_exited";         //删除群成员
        public static final String UPDATE_GROUP = "update_group";           //更新群信息
        public static final String CONSULT_RECOMMEND = "consult_recommend";     //医生推荐会诊
        public static final String CONSULT_ADD = "consult_add";                 //大众发起会诊
        public static final String CONSULT_FINISH = "consult_finish";           //医生填写完会诊单
        public static final String CONSULT_MODIFY = "consult_modify";           //大众要求修改会诊
        public static final String CONSULT_PUBLIC = "consult_public";           //大众发布会诊
        public static final String CONSULT_START = "consult_start";             //会诊单启动


        //客服相关
        public static final String CHAT_OBJECT = "chatObject";  //(键)
        public static final int ADMIN = 0;              //(值)向管理员发消息
        public static final int CUSTOMER = 1;           //(值)向客户发消息
        public static final int CONSULT_GROUP = 2;      //(值)向会诊群发消息
    }


    abstract class PAYTYPE {
        private PAYTYPE() {
        }

        public static final int BANK_PAY = 0;       //银行转账
        public static final int ALI_PAY = 1;        //支付宝支付
        public static final int WX_PAY = 2;         //微信支付
        public static final int BALANCE_PAY = 3;    //余额支付
    }
}
