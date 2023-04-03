package com.ruoyi.common.enums;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @mm
 * bim 流程枚举类
 *
 * @author qiaoxulin
 */
@Slf4j
@Getter
@AllArgsConstructor
public enum BimFlowKey {


    /**
     * 质量检测
     */
    质量检测("质量检测", "zhiliangjiance"),
    /**
     * 隐蔽工程验收记录
     */
    隐蔽工程验收记录("隐蔽工程验收记录", "yinbigongchengguanli"),
    /**
     * 施工专业分包合同
     */
    施工专业分包合同("施工专业分包合同", "shigongfenbaohetong"),
    /**
     * 劳务分包合同
     */
    劳务分包合同("劳务分包合同", "laowufenbaohetong"),
    /**
     * 进退场管理
     */
    进退场管理("进退场管理", "jintuichangguanli"),
    /**
     * 往来款
     */
    往来款管理("往来款管理", "wanglaikuanguanli"),
    /**
     * 施工技术交底
     */
    施工技术交底("施工技术交底", "shigongjishujiaodi"),
    /**
     * 施工方案
     */
    施工方案("施工方案", "shigongfangan"),
    /**
     * 施工技术交底
     */
    设备进场报验("设备进场报验", "shebeijinchangbaoyan"),
    /**
     * 设备退场报验
     */
    设备退场报验("设备退场报验", "shebeituichangbaoyan"),
    /**
     * 项目开工申请
     */
    项目开工申请("项目开工申请", "xiangmukaigongshenqing"),
    /**
     * 分项开工申请
     */
    分项开工申请("分项开工申请", "fenxiangkaigongshenqing"),
    /**
     * 监理旁站
     */
    监理旁站("监理旁站", "jianlipangzhan"),
    /**
     * 监理巡视
     */
    监理巡视("监理巡视", "jianlixunshi"),
    /**
     * 监理通知
     */
    监理通知("监理通知", "jianlitongzhi"),
    /**
     * 质量活动
     */
    质量活动("质量活动", "zhilianghuodong"),
    /**
     * 首件认可
     */
    首件认可("首件认可", "shoujianrenke"),
    /**
     * 质量简报
     */
    质量简报("质量简报", "zhiliangjianbao"),
    /**
     * 监理指令
     */
    监理指令("监理指令", "jianlizhiling"),

    /**
     * 计量审批
     */
    计量审批("计量审批", "jiliangshenpi");

    private String key;
    private String name;


    public static final List<BimFlowKey> LIST = Lists.newArrayList();

    static {
        LIST.addAll(Arrays.asList(BimFlowKey.values()));
    }

    @SneakyThrows(Exception.class)
    public static BimFlowKey getEnum(String name) {
        for (BimFlowKey itemEnum : LIST) {
            if (itemEnum.getName().equals(name)) {
                return itemEnum;
            }
        }
        log.error("未找到指定的流程key枚举！");
        return null;
    }
}