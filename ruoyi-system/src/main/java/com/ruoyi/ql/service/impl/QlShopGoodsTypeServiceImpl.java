package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.ql.domain.bo.QlShopGoodsTypeBo;
import com.ruoyi.ql.domain.vo.QlShopGoodsTypeVo;
import com.ruoyi.ql.domain.QlShopGoodsType;
import com.ruoyi.ql.mapper.QlShopGoodsTypeMapper;
import com.ruoyi.ql.service.IQlShopGoodsTypeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品类别Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlShopGoodsTypeServiceImpl implements IQlShopGoodsTypeService {

    private final QlShopGoodsTypeMapper baseMapper;

    /**
     * 查询商品类别
     */
    @Override
    public QlShopGoodsTypeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }


    /**
     * 查询商品类别列表
     */
    @Override
    public List<QlShopGoodsTypeVo> queryList(QlShopGoodsTypeBo bo) {
        LambdaQueryWrapper<QlShopGoodsType> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlShopGoodsType> buildQueryWrapper(QlShopGoodsTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlShopGoodsType> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getParentId() != null, QlShopGoodsType::getParentId, bo.getParentId());
        lqw.eq(StringUtils.isNotBlank(bo.getAncestors()), QlShopGoodsType::getAncestors, bo.getAncestors());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsTypeCode()), QlShopGoodsType::getGoodsTypeCode, bo.getGoodsTypeCode());
        lqw.like(StringUtils.isNotBlank(bo.getGoodsTypeName()), QlShopGoodsType::getGoodsTypeName, bo.getGoodsTypeName());
        lqw.eq(bo.getOrderNum() != null, QlShopGoodsType::getOrderNum, bo.getOrderNum());
        return lqw;
    }

    /**
     * 新增商品类别
     */
    @Override
    public Boolean insertByBo(QlShopGoodsTypeBo bo) {
        QlShopGoodsType add = BeanUtil.toBean(bo, QlShopGoodsType.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商品类别
     */
    @Override
    public Boolean updateByBo(QlShopGoodsTypeBo bo) {
        QlShopGoodsType update = BeanUtil.toBean(bo, QlShopGoodsType.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlShopGoodsType entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商品类别
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
