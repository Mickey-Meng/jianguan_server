package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.ql.domain.bo.QlContractInfoSaleBo;
import com.ruoyi.ql.domain.vo.QlContractInfoSaleVo;
import com.ruoyi.ql.domain.QlContractInfoSale;
import com.ruoyi.ql.mapper.QlContractInfoSaleMapper;
import com.ruoyi.ql.service.IQlContractInfoSaleService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 合同管理Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlContractInfoSaleServiceImpl implements IQlContractInfoSaleService {

    private final QlContractInfoSaleMapper baseMapper;

    /**
     * 查询合同管理
     */
    @Override
    public QlContractInfoSaleVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询合同管理列表
     */
    @Override
    public TableDataInfo<QlContractInfoSaleVo> queryPageList(QlContractInfoSaleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlContractInfoSale> lqw = buildQueryWrapper(bo);
        Page<QlContractInfoSaleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询合同管理列表
     */
    @Override
    public List<QlContractInfoSaleVo> queryList(QlContractInfoSaleBo bo) {
        LambdaQueryWrapper<QlContractInfoSale> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlContractInfoSale> buildQueryWrapper(QlContractInfoSaleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlContractInfoSale> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getContractCode()), QlContractInfoSale::getContractCode, bo.getContractCode());
        lqw.like(StringUtils.isNotBlank(bo.getContractName()), QlContractInfoSale::getContractName, bo.getContractName());
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), QlContractInfoSale::getCustomerName, bo.getCustomerName());
        lqw.eq(bo.getAmount() != null, QlContractInfoSale::getAmount, bo.getAmount());
        lqw.eq(bo.getContactDate() != null, QlContractInfoSale::getContactDate, bo.getContactDate());
        lqw.eq(bo.getRetentionDate() != null, QlContractInfoSale::getRetentionDate, bo.getRetentionDate());
        return lqw;
    }

    /**
     * 新增合同管理
     */
    @Override
    public Boolean insertByBo(QlContractInfoSaleBo bo) {
        QlContractInfoSale add = BeanUtil.toBean(bo, QlContractInfoSale.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改合同管理
     */
    @Override
    public Boolean updateByBo(QlContractInfoSaleBo bo) {
        QlContractInfoSale update = BeanUtil.toBean(bo, QlContractInfoSale.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlContractInfoSale entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除合同管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
