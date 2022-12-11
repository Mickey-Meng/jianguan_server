package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.ql.domain.QlBasisCustomerAccinfo;
import com.ruoyi.ql.domain.QlFinReimbursementItem;
import com.ruoyi.ql.domain.vo.QlBasisCustomerAccinfoVo;
import com.ruoyi.ql.domain.vo.QlFinReimbursementItemVo;
import com.ruoyi.ql.mapper.QlFinReimbursementItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.ql.domain.bo.QlFinReimbursementBo;
import com.ruoyi.ql.domain.vo.QlFinReimbursementVo;
import com.ruoyi.ql.domain.QlFinReimbursement;
import com.ruoyi.ql.mapper.QlFinReimbursementMapper;
import com.ruoyi.ql.service.IQlFinReimbursementService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 费用报销Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlFinReimbursementServiceImpl implements IQlFinReimbursementService {

    private final QlFinReimbursementMapper baseMapper;
    private final QlFinReimbursementItemMapper qlFinReimbursementItemMapper;

    /**
     * 查询费用报销
     */
    @Override
    public QlFinReimbursementVo queryById(Long id){
        QlFinReimbursementVo qlFinReimbursementVo = baseMapper.selectVoById(id);


        LambdaQueryWrapper<QlFinReimbursementItem> qlFinReimbursementItemLambdaQueryWrapper = new LambdaQueryWrapper<>();
        qlFinReimbursementItemLambdaQueryWrapper.eq(QlFinReimbursementItem::getReimbursementOrderId, id);
        List<QlFinReimbursementItemVo> qlFinReimbursementItemVoList = qlFinReimbursementItemMapper.selectVoList(qlFinReimbursementItemLambdaQueryWrapper);

        qlFinReimbursementVo.setQlFinReimbursementItemVoList(qlFinReimbursementItemVoList);
        return qlFinReimbursementVo;
    }

    /**
     * 查询费用报销列表
     */
    @Override
    public TableDataInfo<QlFinReimbursementVo> queryPageList(QlFinReimbursementBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlFinReimbursement> lqw = buildQueryWrapper(bo);
        Page<QlFinReimbursementVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询费用报销列表
     */
    @Override
    public List<QlFinReimbursementVo> queryList(QlFinReimbursementBo bo) {
        LambdaQueryWrapper<QlFinReimbursement> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlFinReimbursement> buildQueryWrapper(QlFinReimbursementBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlFinReimbursement> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getReimbursementOrderId()), QlFinReimbursement::getReimbursementOrderId, bo.getReimbursementOrderId());
        lqw.eq(bo.getFinReimbursementDate() != null, QlFinReimbursement::getFinReimbursementDate, bo.getFinReimbursementDate());
        lqw.eq(bo.getFinAmount() != null, QlFinReimbursement::getFinAmount, bo.getFinAmount());
        lqw.like(StringUtils.isNotBlank(bo.getEmpName()), QlFinReimbursement::getEmpName, bo.getEmpName());
        return lqw;
    }

    /**
     * 新增费用报销
     */
    @Override
    public Boolean insertByBo(QlFinReimbursementBo bo) {
        QlFinReimbursement add = BeanUtil.toBean(bo, QlFinReimbursement.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改费用报销
     */
    @Override
    public Boolean updateByBo(QlFinReimbursementBo bo) {
        QlFinReimbursement update = BeanUtil.toBean(bo, QlFinReimbursement.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlFinReimbursement entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除费用报销
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
