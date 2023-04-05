package com.ruoyi.common.utils.jianguan.zjrw;

import com.ruoyi.common.core.domain.dto.ConponentScheuleDto;
import com.ruoyi.common.core.domain.entity.Conponentschedule;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/25 4:52 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class CaulateUtil {
    public  static String toAdd(String source,Long value){
        BigDecimal b1 = new BigDecimal(Double.valueOf(source));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value));
        BigDecimal result = b1.add(b2);
        result = result.setScale(10, BigDecimal.ROUND_HALF_UP);
        return result.toString();
    }

    public static ConponentScheuleDto getConponentScheuleDto(Conponentschedule conponentschedule) {
        //正常完成 1     //延期完成 2   //已开工  3 //未开工  4
        if(ObjectUtils.isEmpty(conponentschedule.getActulbegintime())){
            return new ConponentScheuleDto(conponentschedule.getId(),
                    conponentschedule.getConponentid(),conponentschedule.getMouldid(),4);
        }else if(ObjectUtils.isEmpty(conponentschedule.getActulendtime())){
            return new ConponentScheuleDto(conponentschedule.getId(),
                    conponentschedule.getConponentid(),conponentschedule.getMouldid(),3);
        }else if(conponentschedule.getActulendtime().after(conponentschedule.getPlanendtime())){
            return new ConponentScheuleDto(conponentschedule.getId(),
                    conponentschedule.getConponentid(),conponentschedule.getMouldid(),2);
        }else{
            return new ConponentScheuleDto(conponentschedule.getId(),
                    conponentschedule.getConponentid(),conponentschedule.getMouldid(),1);
        }
    }
}
