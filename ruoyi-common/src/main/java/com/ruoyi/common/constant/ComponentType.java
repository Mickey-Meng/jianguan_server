package com.ruoyi.common.constant;

import com.ruoyi.common.core.domain.entity.*;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author : chenzhiwei
 * @Date : Create file in 2022/3/31 17:28
 * @Version : 1.0
 * @Description : 桥梁、隧道、道路的 ComponentType值
 **/
public class ComponentType {

    public static Conponent setSuDaoConponenttype(List<String> list, Conponent conponent){
        if (list.get(9).equals("暗井、检修井")){
            conponent.setConponenttype("AJJXJ");
        } else if (list.get(9).equals("超前锚杆")){
            conponent.setConponenttype("CQMG");
        } else if (list.get(9).equals("超前小导管")){
            conponent.setConponenttype("CQXDG");
        } else if (list.get(9).equals("衬砌钢筋")){
            conponent.setConponenttype("CQGJ");
        } else if (list.get(9).equals("大管棚")){
            conponent.setConponenttype("DGP");
        } else if (list.get(9).equals("电缆沟")){
            conponent.setConponenttype("DLG");
        } else if (list.get(9).equals("洞口排水沟")){
            conponent.setConponenttype("DKPS");
        } else if (list.get(9).equals("明洞浇筑")){
            conponent.setConponenttype("MDJZ");
        } else if (list.get(9).equals("翼墙浇筑")){
            conponent.setConponenttype("YQJZ");
        } else if (list.get(9).equals("洞门和翼墙浇筑")){
            conponent.setConponenttype("DMYQJZ");
        } else if (list.get(9).equals("洞身开挖")){
            conponent.setConponenttype("DSKW");
        } else if (list.get(9).equals("防水板")){
            conponent.setConponenttype("FSB");
        } else if (list.get(9).equals("防水层")){
            conponent.setConponenttype("FSC");
        } else if (list.get(9).equals("钢架")){
            conponent.setConponenttype("SDGJ");
        } else if (list.get(9).equals("钢筋网")){
            conponent.setConponenttype("SDGJW");
        } else if (list.get(9).equals("拱圈")){
            conponent.setConponenttype("SDGQ");
        } else if (list.get(9).equals("混凝土衬砌")){
            conponent.setConponenttype("HNTCQ");
        } else if (list.get(9).equals("基层")){
            conponent.setConponenttype("SDJC");
        } else if (list.get(9).equals("截水沟")){
            conponent.setConponenttype("JIESG");
        } else if (list.get(9).equals("路面")){
            conponent.setConponenttype("SDLM");
        } else if (list.get(9).equals("路缘集水井")){
            conponent.setConponenttype("LYJSJ");
        } else if (list.get(9).equals("路缘排水沟")){
            conponent.setConponenttype("LYPSG");
        } else if (list.get(9).equals("盲管")){
            conponent.setConponenttype("SDMG");
        } else if (list.get(9).equals("锚杆")){
            conponent.setConponenttype("MG");
        } else if (list.get(9).equals("面层")){
            conponent.setConponenttype("SDMC");
        } else if (list.get(9).equals("明洞防水层")){
            conponent.setConponenttype("MDFS");
        } else if (list.get(9).equals("明洞回填")){
            conponent.setConponenttype("MDHT");
        } else if (list.get(9).equals("排水管")){
            conponent.setConponenttype("PSGUAN");
        } else if (list.get(9).equals("喷射混凝土")){
            conponent.setConponenttype("PSHNT");
        } else if (list.get(9).equals("隧道总体")){
            conponent.setConponenttype("SDZT");
        } else if (list.get(9).equals("涂料")){
            conponent.setConponenttype("TL");
        } else if (list.get(9).equals("仰拱")){
            conponent.setConponenttype("SDYG");
        } else if (list.get(9).equals("仰拱回填")){
            conponent.setConponenttype("YGHT");
        } else if (list.get(9).equals("止水带")){
            conponent.setConponenttype("ZSD");
        }
        return conponent;
    }

    public static Conponent setQLConponenttype(List<String> list, Conponent conponent){
        if (list.get(10).equals("薄壁墩")){
            conponent.setConponenttype("BBD");
        } else if (list.get(10).equals("侧墙")){
            conponent.setConponenttype("CQ");
        } else if (list.get(10).equals("承台")){
            conponent.setConponenttype("CT");
        } else if (list.get(10).equals("挡块")){
            conponent.setConponenttype("DK");
        } else if (list.get(10).equals("地系梁")){
            conponent.setConponenttype("DXL");
        } else if (list.get(10).equals("墩顶现浇")){
            conponent.setConponenttype("DDXJ");
        } else if (list.get(10).equals("墩顶现浇连续段")){
            conponent.setConponenttype("DDXD");
        } else if (list.get(10).equals("墩柱")){
            conponent.setConponenttype("DZ");
        } else if (list.get(10).equals("耳背墙")){
            conponent.setConponenttype("EBQ");
        } else if (list.get(10).equals("防抛网")){
            conponent.setConponenttype("FPW");
        } else if (list.get(10).equals("防撞护栏")){
            conponent.setConponenttype("FZHL");
        } else if (list.get(10).equals("盖梁")){
            conponent.setConponenttype("GL");
        } else if (list.get(10).equals("横隔板")){
            conponent.setConponenttype("HGB");
        } else if (list.get(10).equals("扩大基础")){
            conponent.setConponenttype("KD");
        } else if (list.get(10).equals("梁板安装")){
            conponent.setConponenttype("LBAZ");
        } else if (list.get(10).equals("梁板预制")){
            conponent.setConponenttype("LBYZ");
        } else if (list.get(10).equals("桥梁总体")){
            conponent.setConponenttype("QLZT");
        } else if (list.get(10).equals("桥面防水")){
            conponent.setConponenttype("QMFS");
        } else if (list.get(10).equals("桥面铺装")){
            conponent.setConponenttype("QMPZ");
        } else if (list.get(10).equals("桥台")){
            conponent.setConponenttype("QT");
        } else if (list.get(10).equals("桥台桥头搭板")){
            conponent.setConponenttype("QTQTDB");
        } else if (list.get(10).equals("桥头搭板")){
            conponent.setConponenttype("QTDB");
        } else if (list.get(10).equals("桥台搭板")){
            conponent.setConponenttype("TAIDB");
        } else if (list.get(10).equals("伸缩缝")){
            conponent.setConponenttype("SSF");
        } else if (list.get(10).equals("伸缩缝安装")){
            conponent.setConponenttype("SSFAZ");
        } else if (list.get(10).equals("湿接缝")){
            conponent.setConponenttype("SJF");
        } else if (list.get(10).equals("台背填土")){
            conponent.setConponenttype("TBTT");
        } else if (list.get(10).equals("台帽")){
            conponent.setConponenttype("TM");
        } else if (list.get(10).equals("台身")){
            conponent.setConponenttype("TS");
        } else if (list.get(10).equals("支座安装")){
            conponent.setConponenttype("ZZAZ");
        } else if (list.get(10).equals("支座垫石")){
            conponent.setConponenttype("ZZDS");
        } else if (list.get(10).equals("柱系梁")){
            conponent.setConponenttype("ZXL");
        } else if (list.get(10).equals("桩基")){
            conponent.setConponenttype("ZJ");
        } else if (list.get(10).equals("锥坡")){
            conponent.setConponenttype("ZP");
        }
        return conponent;
    }

    public static Conponent setLMLJConponenttype(List<String> list, Conponent conponent){
        if (list.get(9).equals("八字墙")){
            conponent.setConponenttype("BZQ");
        } else if (list.get(9).equals("薄壁墩")){
            conponent.setConponenttype("BBD");
        } else if (list.get(9).equals("边沟")){
            conponent.setConponenttype("BG");
        } else if (list.get(9).equals("承台")){
            conponent.setConponenttype("CT");
        } else if (list.get(9).equals("挡块")){
            conponent.setConponenttype("DK");
        } else if (list.get(9).equals("挡墙")){
            conponent.setConponenttype("DQ");
        } else if (list.get(9).equals("倒虹吸")){
            conponent.setConponenttype("DHX");
        } else if (list.get(9).equals("地系梁")){
            conponent.setConponenttype("DXL");
        } else if (list.get(9).equals("底基层")){
            conponent.setConponenttype("DJC");
        } else if (list.get(9).equals("垫层")){
            conponent.setConponenttype("DIANC");
        } else if (list.get(9).equals("跌水井")){
            conponent.setConponenttype("DSJ");
        } else if (list.get(9).equals("洞内铺装")){
            conponent.setConponenttype("DNPZ");
        } else if (list.get(9).equals("陡坡路堤")){
            conponent.setConponenttype("DPLD");
        } else if (list.get(9).equals("渡槽")){
            conponent.setConponenttype("DC");
        } else if (list.get(9).equals("渡槽防水")){
            conponent.setConponenttype("DCFS");
        } else if (list.get(9).equals("渡槽现浇")){
            conponent.setConponenttype("DCXJ");
        } else if (list.get(9).equals("墩柱")){
            conponent.setConponenttype("DZ");
        } else if (list.get(9).equals("防撞护栏安装")){
            conponent.setConponenttype("FZHLAZ");
        } else if (list.get(9).equals("盖板安装")){
            conponent.setConponenttype("GBAZ");
        } else if (list.get(9).equals("盖板预制")){
            conponent.setConponenttype("GBYZ");
        } else if (list.get(9).equals("盖梁")){
            conponent.setConponenttype("GL");
        } else if (list.get(9).equals("沟渠")){
            conponent.setConponenttype("GQ");
        } else if (list.get(9).equals("骨架植草")){
            conponent.setConponenttype("GJZC");
        } else if (list.get(9).equals("管节预制")){
            conponent.setConponenttype("GJYZ");
        } else if (list.get(9).equals("过路涵")){
            conponent.setConponenttype("GLH");
        } else if (list.get(9).equals("涵背回填")){
            conponent.setConponenttype("HBHT");
        } else if (list.get(9).equals("涵台")){
            conponent.setConponenttype("HT");
        } else if (list.get(9).equals("横向排水管")){
            conponent.setConponenttype("HXPSG");
        } else if (list.get(9).equals("护栏施工")){
            conponent.setConponenttype("HLSG");
        } else if (list.get(9).equals("换填")){
            conponent.setConponenttype("HT");
        } else if (list.get(9).equals("混凝土涵管安装")){
            conponent.setConponenttype("HGAZ");
        } else if (list.get(9).equals("基层")){
            conponent.setConponenttype("SDJC");
        } else if (list.get(9).equals("急流槽")){
            conponent.setConponenttype("JLC");
        } else if (list.get(9).equals("集水井")){
            conponent.setConponenttype("JSG");
        } else if (list.get(9).equals("检修坡道")){
            conponent.setConponenttype("JXPD");
        } else if (list.get(9).equals("阶梯")){
            conponent.setConponenttype("JT");
        } else if (list.get(9).equals("截水沟")){
            conponent.setConponenttype("JIESG");
        } else if (list.get(9).equals("截水墙")){
            conponent.setConponenttype("JSQ");
        } else if (list.get(9).equals("扩大基础")){
            conponent.setConponenttype("KD");
        } else if (list.get(9).equals("梁板安装")){
            conponent.setConponenttype("LBAZ");
        } else if (list.get(9).equals("流水槽")){
            conponent.setConponenttype("LSC");
        } else if (list.get(9).equals("路基填筑")){
            conponent.setConponenttype("LJTZ");
        } else if (list.get(9).equals("路基挖方")){
            conponent.setConponenttype("LJWF");
        } else if (list.get(9).equals("路肩")){
            conponent.setConponenttype("LJ");
        } else if (list.get(9).equals("路缘石")){
            conponent.setConponenttype("LYS");
        } else if (list.get(9).equals("面层")){
            conponent.setConponenttype("SDMC");
        } else if (list.get(9).equals("明涵搭板")){
            conponent.setConponenttype("MHDB");
        } else if (list.get(9).equals("明涵铺装")){
            conponent.setConponenttype("MHPZ");
        } else if (list.get(9).equals("排水沟")){
            conponent.setConponenttype("PSGOU");
        } else if (list.get(9).equals("片石混凝土挡土墙")){
            conponent.setConponenttype("PSDTQ");
        } else if (list.get(9).equals("桥梁总体")){
            conponent.setConponenttype("QLZT");
        } else if (list.get(9).equals("桥面防水")){
            conponent.setConponenttype("QMFS");
        } else if (list.get(9).equals("桥面铺装")){
            conponent.setConponenttype("QMPZ");
        } else if (list.get(9).equals("桥台")){
            conponent.setConponenttype("QT");
        } else if (list.get(9).equals("桥头搭板")){
            conponent.setConponenttype("QTDB");
        } else if (list.get(9).equals("清表")){
            conponent.setConponenttype("QB");
        } else if (list.get(9).equals("伸缩缝安装")){
            conponent.setConponenttype("SSFAZ");
        } else if (list.get(9).equals("渗沟")){
            conponent.setConponenttype("SG");
        } else if (list.get(9).equals("湿接缝浇筑")){
            conponent.setConponenttype("SJFJZ");
        } else if (list.get(9).equals("台背回填")){
            conponent.setConponenttype("TBHT");
        } else if (list.get(9).equals("台背填土")){
            conponent.setConponenttype("TBTT");
        } else if (list.get(9).equals("台帽")){
            conponent.setConponenttype("TM");
        } else if (list.get(9).equals("台身")){
            conponent.setConponenttype("TS");
        } else if (list.get(9).equals("梯道桥")){
            conponent.setConponenttype("TDQ");
        } else if (list.get(9).equals("填方护坡")){
            conponent.setConponenttype("TFHP");
        } else if (list.get(9).equals("填挖交界")){
            conponent.setConponenttype("TWJJ");
        } else if (list.get(9).equals("透水性材料回填")){
            conponent.setConponenttype("CLHT");
        } else if (list.get(9).equals("土工格栅")){
            conponent.setConponenttype("TGGS");
        } else if (list.get(9).equals("挖方护坡")){
            conponent.setConponenttype("WFHP");
        } else if (list.get(9).equals("箱涵浇筑")){
            conponent.setConponenttype("XHJZ");
        } else if (list.get(9).equals("一字墙")){
            conponent.setConponenttype("YZQ");
        } else if (list.get(9).equals("预制安装梁、板")){
            conponent.setConponenttype("YZLB");
        } else if (list.get(9).equals("支座安装")){
            conponent.setConponenttype("ZZAZ");
        } else if (list.get(9).equals("支座垫石")){
            conponent.setConponenttype("ZZDS");
        } else if (list.get(9).equals("桩基")){
            conponent.setConponenttype("ZJ");
        } else if (list.get(9).equals("装饰装修")){
            conponent.setConponenttype("ZSZX");
        } else if (list.get(9).equals("锥坡")){
            conponent.setConponenttype("ZP");
        } else if (list.get(9).equals("总体")){
            conponent.setConponenttype("LMZT");
        }
        return conponent;
    }

    public static ZjConponentProducetime setConponentTypeName(ZjConponentProducetime producetime, String type) {
        if (type.equals("BBD")){
            producetime.setConponenttypename("薄壁墩");
        } else if (type.equals("CQ")){
            producetime.setConponenttypename("侧墙");
        } else if (type.equals("CT")){
            producetime.setConponenttypename("承台");
        } else if (type.equals("DK")){
            producetime.setConponenttypename("挡块");
        } else if (type.equals("DXL")){
            producetime.setConponenttypename("地系梁");
        } else if (type.equals("DDXJ")){
            producetime.setConponenttypename("墩顶现浇");
        } else if (type.equals("DDXD")){
            producetime.setConponenttypename("墩顶现浇连续段");
        } else if (type.equals("DZ")){
            producetime.setConponenttypename("墩柱");
        } else if (type.equals("EBQ")){
            producetime.setConponenttypename("耳背墙");
        } else if (type.equals("FPW")){
            producetime.setConponenttypename("防抛网");
        } else if (type.equals("FZHL")){
            producetime.setConponenttypename("防撞护栏");
        } else if (type.equals("GL")){
            producetime.setConponenttypename("盖梁");
        } else if (type.equals("HGB")){
            producetime.setConponenttypename("横隔板");
        } else if (type.equals("KD")){
            producetime.setConponenttypename("扩大基础");
        } else if (type.equals("LBAZ")){
            producetime.setConponenttypename("梁板安装");
        } else if (type.equals("LBYZ")){
            producetime.setConponenttypename("梁板预制");
        } else if (type.equals("QLZT")){
            producetime.setConponenttypename("桥梁总体");
        } else if (type.equals("QMFS")){
            producetime.setConponenttypename("桥面防水");
        } else if (type.equals("QMPZ")){
            producetime.setConponenttypename("桥面铺装");
        } else if (type.equals("QT")){
            producetime.setConponenttypename("桥台");
        } else if (type.equals("QTQTDB")){
            producetime.setConponenttypename("桥台桥头搭板");
        } else if (type.equals("QTDB")){
            producetime.setConponenttypename("桥头搭板");
        } else if (type.equals("TAIDB")){
            producetime.setConponenttypename("桥台搭板");
        } else if (type.equals("SSF")){
            producetime.setConponenttypename("伸缩缝");
        } else if (type.equals("SSFAZ")){
            producetime.setConponenttypename("伸缩缝安装");
        } else if (type.equals("SJF")){
            producetime.setConponenttypename("湿接缝");
        } else if (type.equals("TBTT")){
            producetime.setConponenttypename("台背填土");
        } else if (type.equals("TM")){
            producetime.setConponenttypename("台帽");
        } else if (type.equals("TS")){
            producetime.setConponenttypename("台身");
        } else if (type.equals("ZZAZ")){
            producetime.setConponenttypename("支座安装");
        } else if (type.equals("ZZDS")){
            producetime.setConponenttypename("支座垫石");
        } else if (type.equals("ZXL")){
            producetime.setConponenttypename("柱系梁");
        } else if (type.equals("ZJ")){
            producetime.setConponenttypename("桩基");
        } else if (type.equals("ZP")){
            producetime.setConponenttypename("锥坡");
        }
        return producetime;
    }

    public static ZjConponentProducetime setLMDLTypeName(ZjConponentProducetime producetime, String type) {
        if (type.equals("BBD")){
            producetime.setConponenttypename("薄壁墩");
        } else if (type.equals("BG")){
            producetime.setConponenttypename("边沟");
        } else if (type.equals("BZQ")){
            producetime.setConponenttypename("八字墙");
        } else if (type.equals("CLHT")){
            producetime.setConponenttypename("透水性材料回填");
        } else if (type.equals("CT")){
            producetime.setConponenttypename("承台");
        } else if (type.equals("DC")){
            producetime.setConponenttypename("渡槽");
        } else if (type.equals("DCFS")){
            producetime.setConponenttypename("渡槽防水");
        } else if (type.equals("DCXJ")){
            producetime.setConponenttypename("渡槽现浇");
        } else if (type.equals("DHX")){
            producetime.setConponenttypename("倒虹吸");
        } else if (type.equals("DIANC")){
            producetime.setConponenttypename("垫层");
        } else if (type.equals("DJC")){
            producetime.setConponenttypename("底基层");
        } else if (type.equals("DK")){
            producetime.setConponenttypename("挡块");
        } else if (type.equals("DNPZ")){
            producetime.setConponenttypename("洞内铺装");
        } else if (type.equals("DPLD")){
            producetime.setConponenttypename("陡坡路堤");
        } else if (type.equals("DQ")){
            producetime.setConponenttypename("挡墙");
        } else if (type.equals("DSJ")){
            producetime.setConponenttypename("跌水井");
        } else if (type.equals("DXL")){
            producetime.setConponenttypename("地系梁");
        } else if (type.equals("DZ")){
            producetime.setConponenttypename("墩柱");
        } else if (type.equals("FZHLAZ")){
            producetime.setConponenttypename("防撞护栏安装");
        } else if (type.equals("GBAZ")){
            producetime.setConponenttypename("盖板安装");
        } else if (type.equals("GBYZ")){
            producetime.setConponenttypename("盖板预制");
        } else if (type.equals("GJYZ")){
            producetime.setConponenttypename("管节预制");
        } else if (type.equals("GJZC")){
            producetime.setConponenttypename("骨架植草");
        } else if (type.equals("GL")){
            producetime.setConponenttypename("盖梁");
        } else if (type.equals("GLH")){
            producetime.setConponenttypename("过路涵");
        } else if (type.equals("GQ")){
            producetime.setConponenttypename("沟渠");
        } else if (type.equals("HBHT")){
            producetime.setConponenttypename("涵背回填");
        } else if (type.equals("HGAZ")){
            producetime.setConponenttypename("混凝土涵管安装");
        } else if (type.equals("HLSG")){
            producetime.setConponenttypename("护栏施工");
        } else if (type.equals("HUANT")){
            producetime.setConponenttypename("换填");
        } else if (type.equals("HT")){
            producetime.setConponenttypename("涵台");
        } else if (type.equals("HXPSG")){
            producetime.setConponenttypename("横向排水管");
        } else if (type.equals("JIESG")){
            producetime.setConponenttypename("截水沟");
        } else if (type.equals("JLC")){
            producetime.setConponenttypename("急流槽");
        } else if (type.equals("JSG")){
            producetime.setConponenttypename("集水井");
        } else if (type.equals("JSQ")){
            producetime.setConponenttypename("侧墙");
        } else if (type.equals("JT")){
            producetime.setConponenttypename("阶梯");
        } else if (type.equals("JXPD")){
            producetime.setConponenttypename("检修坡道");
        } else if (type.equals("KD")){
            producetime.setConponenttypename("扩大基础");
        } else if (type.equals("LJ")){
            producetime.setConponenttypename("路肩");
        } else if (type.equals("LJTZ")){
            producetime.setConponenttypename("路基填筑");
        } else if (type.equals("LJWF")){
            producetime.setConponenttypename("路基挖方");
        } else if (type.equals("LMZT")){
            producetime.setConponenttypename("总体");
        } else if (type.equals("LSC")){
            producetime.setConponenttypename("流水槽");
        } else if (type.equals("LYS")){
            producetime.setConponenttypename("路缘石");
        } else if (type.equals("MHDB")){
            producetime.setConponenttypename("明涵搭板");
        } else if (type.equals("MHPZ")){
            producetime.setConponenttypename("明涵铺装");
        } else if (type.equals("PSDTQ")){
            producetime.setConponenttypename("片石混凝土挡土墙");
        } else if (type.equals("PSGOU")){
            producetime.setConponenttypename("排水沟");
        } else if (type.equals("QB")){
            producetime.setConponenttypename("清表");
        } else if (type.equals("QLZT")){
            producetime.setConponenttypename("桥梁总体");
        } else if (type.equals("QMFS")){
            producetime.setConponenttypename("桥面防水");
        } else if (type.equals("QMPZ")){
            producetime.setConponenttypename("桥面铺装");
        } else if (type.equals("QT")){
            producetime.setConponenttypename("桥台");
        } else if (type.equals("QTDB")){
            producetime.setConponenttypename("桥头搭板");
        } else if (type.equals("SDJC")){
            producetime.setConponenttypename("基层");
        } else if (type.equals("SDMC")){
            producetime.setConponenttypename("面层");
        } else if (type.equals("SG")){
            producetime.setConponenttypename("渗沟");
        } else if (type.equals("SJFJZ")){
            producetime.setConponenttypename("湿接缝浇筑");
        } else if (type.equals("SSFAZ")){
            producetime.setConponenttypename("伸缩缝安装");
        } else if (type.equals("TBHT")){
            producetime.setConponenttypename("台背回填");
        } else if (type.equals("TBTT")){
            producetime.setConponenttypename("台背填土");
        } else if (type.equals("TDQ")){
            producetime.setConponenttypename("梯道桥");
        } else if (type.equals("TFHP")){
            producetime.setConponenttypename("填方护坡");
        } else if (type.equals("TGGS")){
            producetime.setConponenttypename("土工格栅");
        } else if (type.equals("TM")){
            producetime.setConponenttypename("台帽");
        } else if (type.equals("TS")){
            producetime.setConponenttypename("台身钢筋安装");
        } else if (type.equals("TWJJ")){
            producetime.setConponenttypename("填挖交界");
        } else if (type.equals("WFHP")){
            producetime.setConponenttypename("挖方护坡");
        } else if (type.equals("XHJZ")){
            producetime.setConponenttypename("箱涵浇筑");
        } else if (type.equals("YZLB")){
            producetime.setConponenttypename("预制安装梁、板");
        } else if (type.equals("YZQ")){
            producetime.setConponenttypename("一字墙");
        } else if (type.equals("ZJ")){
            producetime.setConponenttypename("桩基");
        } else if (type.equals("ZP")){
            producetime.setConponenttypename("锥坡");
        } else if (type.equals("ZSZX")){
            producetime.setConponenttypename("装饰装修");
        } else if (type.equals("ZZAZ")){
            producetime.setConponenttypename("支座安装");
        } else if (type.equals("ZZDS")){
            producetime.setConponenttypename("支座垫石");
        }
        return producetime;
    }

    public static ZjConponentProducetime setSDTypeName(ZjConponentProducetime producetime, String type) {
        if (type.equals("暗井、检修井")){
            producetime.setConponenttypename("AJJXJ");
        } else if (type.equals("超前锚杆")){
            producetime.setConponenttypename("CQMG");
        } else if (type.equals("超前小导管")){
            producetime.setConponenttypename("CQXDG");
        } else if (type.equals("衬砌钢筋")){
            producetime.setConponenttypename("CQGJ");
        } else if (type.equals("大管棚")){
            producetime.setConponenttypename("DGP");
        } else if (type.equals("电缆沟")){
            producetime.setConponenttypename("DLG");
        } else if (type.equals("洞口排水沟")){
            producetime.setConponenttypename("DKPS");
        } else if (type.equals("明洞浇筑")){
            producetime.setConponenttypename("MDJZ");
        } else if (type.equals("翼墙浇筑")){
            producetime.setConponenttypename("YQJZ");
        } else if (type.equals("洞门和翼墙浇筑")){
            producetime.setConponenttypename("DMYQJZ");
        } else if (type.equals("洞身开挖")){
            producetime.setConponenttypename("DSKW");
        } else if (type.equals("防水板")){
            producetime.setConponenttypename("FSB");
        } else if (type.equals("防水层")){
            producetime.setConponenttypename("FSC");
        } else if (type.equals("钢架")){
            producetime.setConponenttypename("SDGJ");
        } else if (type.equals("钢筋网")){
            producetime.setConponenttypename("SDGJW");
        } else if (type.equals("拱圈")){
            producetime.setConponenttypename("SDGQ");
        } else if (type.equals("混凝土衬砌")){
            producetime.setConponenttypename("HNTCQ");
        } else if (type.equals("基层")){
            producetime.setConponenttypename("SDJC");
        } else if (type.equals("截水沟")){
            producetime.setConponenttypename("JIESG");
        } else if (type.equals("路面")){
            producetime.setConponenttypename("SDLM");
        } else if (type.equals("路缘集水井")){
            producetime.setConponenttypename("LYJSJ");
        } else if (type.equals("路缘排水沟")){
            producetime.setConponenttypename("LYPSG");
        } else if (type.equals("盲管")){
            producetime.setConponenttypename("SDMG");
        } else if (type.equals("锚杆")){
            producetime.setConponenttypename("MG");
        } else if (type.equals("SDMC")){
            producetime.setConponenttypename("面层");
        } else if (type.equals("MDFS")){
            producetime.setConponenttypename("明洞防水层");
        } else if (type.equals("MDHT")){
            producetime.setConponenttypename("明洞回填");
        } else if (type.equals("PSGUAN")){
            producetime.setConponenttypename("排水管");
        } else if (type.equals("PSHNT")){
            producetime.setConponenttypename("喷射混凝土");
        } else if (type.equals("SDZT")){
            producetime.setConponenttypename("隧道总体");
        } else if (type.equals("TL")){
            producetime.setConponenttypename("涂料");
        } else if (type.equals("SDYG")){
            producetime.setConponenttypename("仰拱");
        } else if (type.equals("YGHT")){
            producetime.setConponenttypename("仰拱回填");
        } else if (type.equals("ZSD")){
            producetime.setConponenttypename("止水带");
        }
        return producetime;
    }

    public static void fillProduce(Produceandrecode produceandrecodeindex, ProduceRecord produceRecord, String type, Map<Integer, List<Produce>> collect2) {
        String status = toSatatus(produceandrecodeindex);

            // add yangaogao  20230510 没看懂以上的if有啥作用，理论上无需判断构建类型，但是目前仅支持最多四层工序

            Produce produce = collect2.get(produceandrecodeindex.getProduceid()).get(0);
            ProduceRecordDetail produceRecordDetail = new ProduceRecordDetail();
            produceRecordDetail.setProduceRangee(status);
            produceRecordDetail.setStatus(produceandrecodeindex.getCheckresult());
            produceRecordDetail.setTime(produceandrecodeindex.getStime());
            produceRecord.getProduceRecordDetails().add(produceRecordDetail);

            //TODO delete 工序明细，下面通过穷举的方式可以废弃，待前端改完，则可删除下面代码
            if(produce.getRange()==1){
                produceRecord.setOne(status);
                produceRecord.setOneTime(produceandrecodeindex.getStime());
            }else if(produce.getRange()==2){
                produceRecord.setTwo(status);
                produceRecord.setTwoTime(produceandrecodeindex.getStime());
            }else if(produce.getRange()==3){
                produceRecord.setThree(status);
                produceRecord.setThreeTime(produceandrecodeindex.getStime());
            }else if(produce.getRange()==4){
                produceRecord.setFour(status);
                produceRecord.setFourTime(produceandrecodeindex.getStime());
            }else if(produce.getRange()==5){
                produceRecord.setFive(status);
                produceRecord.setFiveTime(produceandrecodeindex.getStime());
            }
            else if(produce.getRange()==6){
                produceRecord.setThree(status);
                produceRecord.setThreeTime(produceandrecodeindex.getStime());
            }
            else if(produce.getRange()==7){
                produceRecord.setThree(status);
                produceRecord.setThreeTime(produceandrecodeindex.getStime());
            }

        //TODO delete 工序明细，下面通过穷举的方式可以废弃，待前端改完，则可删除下面代码
    }

    private static String toSatatus(Produceandrecode produceandrecodeindex) {
        if(ObjectUtils.isEmpty(produceandrecodeindex.getRecodeid()) || produceandrecodeindex.getRecodeid()==0){
            return "0_0";
        }else{
            return produceandrecodeindex.getRecodeid() + "_" + produceandrecodeindex.getCheckresult();
        }
    }
}
