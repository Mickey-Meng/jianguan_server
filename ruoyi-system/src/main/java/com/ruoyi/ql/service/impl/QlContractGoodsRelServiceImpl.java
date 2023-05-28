package com.ruoyi.ql.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.ql.domain.QlContractGoodsRel;
import com.ruoyi.ql.domain.bo.QlContractGoodsRelBo;
import com.ruoyi.ql.domain.bo.QlShopGoodsBo;
import com.ruoyi.ql.domain.vo.QlContractGoodsRelVo;
import com.ruoyi.ql.domain.vo.QlShopGoodsVo;
import com.ruoyi.ql.mapper.QlContractGoodsRelMapper;
import com.ruoyi.ql.service.IQlContractGoodsRelService;
import com.ruoyi.ql.service.IQlShopGoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 合同与商品关系Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-16
 */
@RequiredArgsConstructor
@Service
public class QlContractGoodsRelServiceImpl implements IQlContractGoodsRelService {

    private final QlContractGoodsRelMapper baseMapper;

    private final IQlShopGoodsService shopGoodsService;

    /**
     * 查询合同与商品关系
     */
    @Override
    public QlContractGoodsRelVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询合同与商品关系列表
     */
    @Override
    public TableDataInfo<QlContractGoodsRelVo> queryPageList(QlContractGoodsRelBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlContractGoodsRel> lqw = buildQueryWrapper(bo);
        Page<QlContractGoodsRelVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        if(ObjectUtil.isNull(result) || CollUtil.isEmpty(result.getRecords())) {
            return TableDataInfo.build(result);    
        }

        // TODO: 2023/5/18 增加批量查询
        for (QlContractGoodsRelVo contractGoodsRelVo : result.getRecords()) {
            QlShopGoodsBo qlShopGoodsBo = new QlShopGoodsBo();
            qlShopGoodsBo.setId(contractGoodsRelVo.getGoodsId());
            List<QlShopGoodsVo> qlShopGoodsVos = shopGoodsService.queryList(qlShopGoodsBo);
            if (CollUtil.isEmpty(qlShopGoodsVos)) {
                continue;
            }
            QlShopGoodsVo qlShopGoodsVo = qlShopGoodsVos.get(0);
            contractGoodsRelVo.setGoodsSearchstandard(qlShopGoodsVo.getGoodsSearchstandard());
            contractGoodsRelVo.setGoodsUnit(qlShopGoodsVo.getGoodsUnit());
        }

        return TableDataInfo.build(result);
    }

    /**
     * 查询合同与商品关系列表
     */
    @Override
    public List<QlContractGoodsRelVo> queryList(QlContractGoodsRelBo bo) {
        LambdaQueryWrapper<QlContractGoodsRel> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlContractGoodsRel> buildQueryWrapper(QlContractGoodsRelBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlContractGoodsRel> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getGoodsId() != null, QlContractGoodsRel::getGoodsId, bo.getGoodsId());
        lqw.like(StringUtils.isNotBlank(bo.getGoodsName()), QlContractGoodsRel::getGoodsName, bo.getGoodsName());
        lqw.eq(bo.getPrice() != null, QlContractGoodsRel::getPrice, bo.getPrice());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsNum()), QlContractGoodsRel::getGoodsNum, bo.getGoodsNum());
        lqw.eq(StringUtils.isNotBlank(bo.getContractType()), QlContractGoodsRel::getContractType, bo.getContractType());
        lqw.in(CollUtil.isNotEmpty(bo.getContractIds()), QlContractGoodsRel::getContractId, bo.getContractIds());
        lqw.eq(ObjectUtil.isNotNull(bo.getContractId()), QlContractGoodsRel::getContractId, bo.getContractId());
        return lqw;
    }

    /**
     * 新增合同与商品关系
     */
    @Override
    public Boolean insertByBo(QlContractGoodsRelBo bo) {
        QlContractGoodsRel add = BeanUtil.toBean(bo, QlContractGoodsRel.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改合同与商品关系
     */
    @Override
    public Boolean updateByBo(QlContractGoodsRelBo bo) {
        QlContractGoodsRel update = BeanUtil.toBean(bo, QlContractGoodsRel.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlContractGoodsRel entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除合同与商品关系
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}