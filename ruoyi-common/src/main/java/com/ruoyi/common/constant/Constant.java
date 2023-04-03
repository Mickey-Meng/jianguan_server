package com.ruoyi.common.constant;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.common.core.domain.entity.CheckModel;
import com.ruoyi.common.core.domain.entity.ZjYcdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/17 3:07 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description: 数据常量定义
 **/
public class Constant {

    /** 参数错误 */
    public static final int ERROR_PARAM_CODE = 400;
    /** 登陆错误 */
    public static final int UN_AUTHORIZED = 401;
    /** 操作失败 */
    public static final int FAIL = 401;
    /** 非预期性内部错误 */
    public static final int EXCEPTION = 500;
    /** 业务异常 */
    public static final int ERROR_BUSINESS_CODE = 200;
    /** 数据源类型 */
    public static final String DATA_TYPE = "dataType";
    /** 返回数据格式,json */
    public static final String CONTENT_TYPE = "application/json; charset=utf-8";
    /** user信息 */
    public static final String USER_INFO = "user_info";
    /** token */
    public static final String AUTHORIZATION_TOKEN = "Authorization";
    /** agent */
    public static final String USER_AGENT = "user_agent";
    /** user_session_id */
    public static final String USER_SESSION_ID = "uid";

    public static final String USER_TOKEN="token";

    public static final String AUTHORIZATION = "userToken";

    public static final String HEADER_IP = "user_ip";

    public static final String ANDROID_AGENT = "Android";

    public static final String TOKEN_PIC_CODE = "verifyCodeToken";

    public static final ArrayList<String> BASECODE = Lists.newArrayList();

    public static Map<String, ZjYcdata>  zjYcdataMap = Maps.newHashMap();
    public static final Map<String,Float> BASICPRODUCECHE=new HashMap<String,Float>(){
        {
            put("工区",0.1f);
            put("项目",0.2f);
            put("构件类型",0.3f);
            put("墩号/孔号",0.4f);
            put("构件编码",0.5f);
            put("计划开始时间",100000.5f);
            put("计划完成时间",110000.5f);
            put("实际开始时间",120000.5f);
            put("实际完成时间",130000.5f);
            put("形象开始时间",140000.5f);
            put("形象完成时间",150000.5f);
            put("完成状态",160000.5f);
            put("工作量",170000.5f);
            put("是否完成",180000.5f);
        }
    };

    public static Map<String, Map<String,Float>> PRODUCECACHEPRO =Maps.newHashMap() ;


    public static Map<Integer, List<CheckModel>> CHECKCACHE=Maps.newHashMap();


    public static Map<String, ArrayList<String>> PRODUCECACHE =Maps.newHashMap() ;
    public static Map<String, ArrayList<Integer>> PRODUCECACHELIST =Maps.newHashMap();

    private Constant() {}
    //每个设备的值，一天的数据
    public static Map<String,Map<String,Map<String,Integer>>> ENVIREMENTDATA = Maps.newHashMap();




}
