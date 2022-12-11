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
import com.ruoyi.ql.domain.bo.QlBasisCustomerAccinfoBo;
import com.ruoyi.ql.domain.vo.QlBasisCustomerAccinfoVo;
import com.ruoyi.ql.domain.QlBasisCustomerAccinfo;
import com.ruoyi.ql.mapper.QlBasisCustomerAccinfoMapper;
import com.ruoyi.ql.service.IQlBasisCustomerAccinfoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 客户账户信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlBasisCustomerAccinfoServiceImpl implements IQlBasisCustomerAccinfoService {

    private final QlBasisCustomerAccinfoMapper baseMapper;

    /**
     * 查询客户账户信息
     */
    @Override
    public QlBasisCustomerAccinfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询客户账户信息列表
     */
    @Override
    public TableDataInfo<QlBasisCustomerAccinfoVo> queryPageList(QlBasisCustomerAccinfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlBasisCustomerAccinfo> lqw = buildQueryWrapper(bo);
        Page<QlBasisCustomerAccinfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询客户账户信息列表
     */
    @Override
    public List<QlBasisCustomerAccinfoVo> queryList(QlBasisCustomerAccinfoBo bo) {
        LambdaQueryWrapper<QlBasisCustomerAccinfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlBasisCustomerAccinfo> buildQueryWrapper(QlBasisCustomerAccinfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlBasisCustomerAccinfo> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), QlBasisCustomerAccinfo::getCustomerName, bo.getCustomerName());
        lqw.eq(StringUtils.isNotBlank(bo.getBankNo()), QlBasisCustomerAccinfo::getBankNo, bo.getBankNo());
        lqw.like(StringUtils.isNotBlank(bo.getBankName()), QlBasisCustomerAccinfo::getBankName, bo.getBankName());
        lqw.eq(StringUtils.isNotBlank(bo.getAccType()), QlBasisCustomerAccinfo::getAccType, bo.getAccType());
        return lqw;
    }

    /**
     * 新增客户账户信息
     */
    @Override
    public Boolean insertByBo(QlBasisCustomerAccinfoBo bo) {
        QlBasisCustomerAccinfo add = BeanUtil.toBean(bo, QlBasisCustomerAccinfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改客户账户信息
     */
    @Override
    public Boolean updateByBo(QlBasisCustomerAccinfoBo bo) {
        QlBasisCustomerAccinfo update = BeanUtil.toBean(bo, QlBasisCustomerAccinfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlBasisCustomerAccinfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除客户账户信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
