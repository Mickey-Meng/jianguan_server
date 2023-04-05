package com.ruoyi.common.utils.jianguan.zjrw;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : chenzhiwei
 * @Date : Create file in 2022/3/31 11:06
 * @Version : 1.0
 * @Description :
 **/
public class StringUtils {

    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String returnProcessName(String flowKey){
        switch (flowKey){
            case "sgdwhtrybs":
                return "施工单位合同人员报审";

            case "jldwhtrybs":
                return "监理单位合同人员报审";

            case "qzdwhtrybs":
                return "全咨单位合同人员报审";

            case "sgdwryqj":
                return "施工单位人员请假";

            case "jldwryqj":
                return "监理单位人员请假";

            case "qzdwryqj":
                return "全咨单位人员请假";

            case "sgdwrybg":
                return "施工单位人员变更";

            case "jldwrybg":
                return "监理单位人员变更";

            case "qzdwrybg":
                return "全咨单位人员变更";
            default :
                return "";
        }
    }
}
