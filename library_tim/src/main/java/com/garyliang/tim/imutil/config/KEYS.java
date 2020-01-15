package com.garyliang.tim.imutil.config;

/**
 * Created by bin on 2017/3/25.
 * SharedPreferences缓存对应的key
 */

public interface KEYS {

    String IS_FIRST_INSTALL = "isFirstInstall";      //是否第一次安装，同意使用协议后置为true
    String IS_LOCATION_LOGIN = "isLocationLogin";     //是否已登录，在用手机验证码登录的时候标志为true

    String IS_VALID = "isValid";        //当前版本是否可用
    String CHECK_VERSION_TIME = "checkVersionTime";     //上次检查版本更新的时间


    String IS_VERIFY_PASS_DOCTOR = "isVerifyPassDoctor";     //在该手机申请认证的医生，认证通过后第一次使用App
    String LATEST_VERSION = "latestVersion";    //服务器最新版本
    String LAST_VERSION = "last_version";       //最后使用的版本

    String LATEST_RULES = "latestRules";  //服务器最新版本对应的规则

    //红点配置 defaultPriceOfAsk
    String SET_PRICE_ASK = "setPriceAsk"; //触发了设置医生咨询价格事件

    //环信聊天设置
    String STATUE_INFO_NOTIFICATION = "infoNotificationStatue"; //接收新消息通知
    String STATUE_SOUND = "soundStatue"; //声音
    String STATUE_VIBRATE = "vibrateStatue"; //振动

    String CONTSULT_MSG_DONE_LIST = "contsult_msg_done_list";   //已完成操作的会诊消息的列表(set)

    /**
     * 在CurrentSpStore中使用
     */

    String HAS_AF_MSG = "has_af_msg";   //存在新朋友消息通知
    String HAS_QA_MSG = "has_qa_msg";   //存在享听提问消息通知
    String HAS_QR_MSG = "has_qr_msg";   //存在享听回答消息通知
    String HAS_NEW_TEAM = "has_new_team";   //存在新的团队邀请

    String CM_IM_USER_NAME = "cm_im_user_name";     //客服环信账号
    String IS_INSERT_CM_MSG = "is_insert_cm_msg";   //是否插入过客服消息
    String IS_INSERT_CM_MDT_MSG = "is_insert_cm_mdt_msg";   //是否插入过客服消息

    String UPDATE_BASIC_VERSION_TIME = "update_basic_version_time";          //上次更新基础数据版本的时间
    String UPDATE_ASK_BILL_TIME = "updateAskBillTime";          //上次更新咨询单的时间
    String UPDATE_TEAM_MEMBER_TIME = "update_team_member_time";     //上次更新团队成员的时间(long)
    String DO_NOT_DISTURB_LIST = "do_not_disturb_list";     //免打扰列表(set)
    String UPDATE_TEAM_ACCOUNT_TIME = "update_team_account_time";   //上次更新团队(string)
    String UPDATE_DOCTOR_EXPERT_VERSION_TIME = "update_doctor_expert_version_time";     //上次更新专家数据版本号的时间(String)
    String DOCTOR_EXPERT_VERSION = "doctor_expert_version";     //专家数据版本号

    String IS_LOAD_QUIZ_LISTEN_LIST = "IS_LOAD_QUIZ_LISTEN_LIST";   //是否已加载过收听记录的列表（boolean）
    String IS_LOAD_SURGERY_BILL = "is_load_surgery_bill";   //是否加载过手术单
    String IS_LOAD_SURVEY_RESULT = "is_load_survey_result"; //是否加载过问卷结果
    String IS_LOAD_TRAIN_ENROLL = "is_load_train_enroll";   //是否加载过培训课程报名信息

    String CONSULT_MSG_COUNT = "consult_msg_count";     //会诊消息数量

    String APP_MDT_HELP_URL = "app_mdt_help_url";       //mdt帮助也链接
    String APP_MDT_VIDEO_URL = "app_mdt_video_url";      //mdt视频链接
    String APP_MDT_CASE_LIST_URL = "app_mdt_case_list_url";  //mdt套餐列表链接
    String APP_MDT_INTRO_URL = "app_mdt_intro_url";      //mdt介绍详情链接
    String APP_MDT_GROUP_INTRO_URL = "app_mdt_group_intro_url";      //mdt套餐组合介绍详情链接

    String APP_H5_QA_URL = "app_question_url";      //问答链接
    String APP_H5_HOME_URL = "app_home_url";      //首页链接
}
