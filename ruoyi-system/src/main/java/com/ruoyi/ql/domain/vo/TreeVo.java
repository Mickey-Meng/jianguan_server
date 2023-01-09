package com.ruoyi.ql.domain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName QlShopTreeGoodsVo
 * @Description TODO
 * @Author Karl
 * @Date 2023/1/9 14:15
 * @Version 1.0
 */
@Data
public class TreeVo {

   private List<TreeVo> children = new ArrayList<>();
   private String label;
   private String value;
}
