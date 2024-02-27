package com.ruoyi.jianguan.business.onlineForms.domain;

import com.google.common.collect.Lists;
import com.ruoyi.common.enums.BimFlowKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Getter
@AllArgsConstructor
public enum TemplateCode {

    工序_JS_钢筋安装现场检测记录表("浙路(JS)107钢筋安装现场检测记录表.xlsx", "HTM_GX_GJJG_JS107"),
    工序_JS_结构物钢筋施工原始记录表("浙路(JS)604结构物钢筋施工原始记录表.xlsx", "HTM_GX_GJSG_JS604"),
    工序_JS_802钢筋加工及安装工程现场质量检验报告单("浙路(JS)802钢筋加工及安装工程现场质量检验报告单(一)(钢筋安装).xlsx", "HTM_GX_GJJG_JS802"),
    评定_ZP_106_1钢筋安装分项工程质量检验评定表附表("浙路(ZP)106-1钢筋安装分项工程质量检验评定表附表.xlsx", "HTM_PD_GJ_106_1"),
    评定_ZP_106_802钢筋安装分项工程质量检验评定表("浙路(ZP)106(802)钢筋安装分项工程质量检验评定表.xlsx", "HTM_PD_GJ_106_802"),

    工序_JS_107涵台现场检测记录表_混凝土("浙路(JS)107涵台现场检测记录表(混凝土).xlsx", "FXGC_GX_HTJC_HNT_JS107"),
    工序_JS_902涵台工程现场质量检验报告单("浙路(ZJ)902涵台工程现场质量检验报告单.xlsx","FXGC_GX_HTJC_JYBG_JS902"),
    评定_ZP_106_1涵台分项工程质量检验评定表附表("浙路(ZP)106-1涵台分项工程质量检验评定表附表.xlsx", "FXGC_PD_HTJC_106_1"),
    评定_ZP_106_902涵台分项工程质量检验评定表("浙路(ZP)106(902)涵台分项工程质量检验评定表.xlsx", "FXGC_PD_HTJC_106_902");

    private String name;
    private String code;

    public static final List<TemplateCode> LIST = Lists.newArrayList();

    static {
        LIST.addAll(Arrays.asList(TemplateCode.values()));
    }

    @SneakyThrows(Exception.class)
    public static TemplateCode getEnum(String name) {
        for (TemplateCode itemEnum : LIST) {
            if (itemEnum.getName().equals(name)) {
                return itemEnum;
            }
        }
        log.error("未找到指定的模板key枚举！");
        return null;
    }
}
