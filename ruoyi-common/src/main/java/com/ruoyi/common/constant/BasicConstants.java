package com.ruoyi.common.constant;

/**
 * 描述：全局常量
 *
 * @author Nanci
 * @since 2020-09-18 16:08:22
 */
public class BasicConstants {

    //1 则商户扫码后，手机端仅展示公众号、2 表示仅展示小程序，3 表示公众号和小程序都展示。如果为未指定，则默认小程序和公众号都展示。第三方平台开发者可以使用本字段来控制授权的帐号类型。
    public static final Integer AUTH_TYPE_OFFICIAL_ACCOUNT = new Integer(1);
    public static final Integer AUTH_TYPE_APPLETS = new Integer(2);
    public static final Integer AUTH_TYPE_ALL = new Integer(3);

    // 1.有效
    public static final Integer VALID = new Integer(1);
    // 0.无效
    public static final Integer INVALID = new Integer(0);

    // 微信授权成功/审核通过 0
    public static final String SUCCESS = "0";
    // 微信授权失败/审核拒绝 1
    public static final String FAIL = "1";

    // 三方接口请求方式 POST
    public static final String POST = "POST";
    // 三方接口请求方式 GET
    public static final String GET = "GET";

    // 第三方平台appId
    public static final String APPID = "wx5b50e905298b396e";
    // 重定向页面
    public static final String REDIRECTURL = "http://basic.rhpass.com/notice/$APPID$/callback";
    // 发送微信模板消息
    public static final String SEND_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";


    // 第三方平台appSecret
    public static final String APPSECRET = "779622be0904b1e768cf448d0cf91843";
    // 第三方平台token
    public static final String TOKEN = "rht_basic_wechat_token";
    // 第三方平台加密key
    public static final String KEY = "cuAihCz53DZRjZwbsGcZJ2Ai6AtT142uphtJMsk7iQ1";


    //三方平台获取token接口
    public static final String GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
    //三方平台获取预授权码接口
    public static final String GET_PRE_AUTH_CODE = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=COMPONENT_ACCESS_TOKEN";
    //三方平台授权地址
    public static String GET_AUTH_ADDRESS = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=APPID&pre_auth_code=CODE&redirect_uri=REDIRECT";
    //三方平台使用授权码获取授权信息
    public static final String GET_AUTH_INFO = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=COMPONENT_ACCESS_TOKEN";
    //三方平台获取/刷新接口调用令牌
    public static final String REFUSH_TOKEN = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=COMPONENT_ACCESS_TOKEN";
    //三方平台获取授权方的帐号基本信息
    public static final String GET_ACCOUNT_INFO = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=COMPONENT_ACCESS_TOKEN";

    //用户授权给公众号--获取code
    //public static final String GET_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE&component_appid=component_appid#wechat_redirect";
    //用户授权给公众号--通过 code 换取 access_token
    //public static final String GET_ACCESS = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=APPID&code=CODE&grant_type=authorization_code&component_appid=COMPONENT_APPID&component_access_token=COMPONENT_ACCESS_TOKEN";

    // 公众号的appId
    public static final String GONGZHONGHAO_APP_ID = "wx012fa7157e44a70a";
    // 公众号的appSecret
    public static final String GONGZHONGHAO_SECRET = "5062318242af67fc061a2599e8d45ec1";
    // 微信授权地址
    public static final String GONGZHONGHAO_OUTH_ADDRESS = "https://open.weixin.qq.com/connect/oauth2/authorize?";
    // 公众号发送模板消息
    public static final String SEND_TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    // 绑定结果提醒
    public static final String BINGING_RESULT_TEMPLATE_ID = "BVEk5wHIOucXyjLcRufOQjAS6wwR3T8EGl6h3E13PDc";

    // 网页扫码登录
    public static final String SCAN_CODE_LOGIN = "https://open.weixin.qq.com/connect/qrconnect?";
    // 网站应用的appId
    public static final String WEB_APP_ID = "wx012fa7157e44a70a";
    // 网站应用的appSecret
    public static final String WEB_SECRET = "5062318242af67fc061a2599e8d45ec1";

    public static final String BUSSINESS_MODEL_KEYS = "businessModelKeyList";

    public static final String BUSSINESS_MODEL = "businessModel";

    public static final String IM_PREFIX = "im_business_";

    public static final String IM_MODEL_PREFIX = "im_model_";

    public static final String IM_USER = "imUser";

    public static final String IM_SYSTEM_ON_LINE_USER = "im_system_on_line_user_";


}

