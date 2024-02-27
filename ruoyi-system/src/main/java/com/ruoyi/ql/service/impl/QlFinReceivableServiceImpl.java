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
import com.ruoyi.ql.domain.QlPaymentWarehousingRel;
import com.ruoyi.ql.domain.QlReceivableOutboundRel;
import com.ruoyi.ql.domain.bo.QlOutboundBo;
import com.ruoyi.ql.domain.bo.QlPaymentWarehousingRelBo;
import com.ruoyi.ql.domain.bo.QlReceivableOutboundRelBo;
import com.ruoyi.ql.domain.vo.*;
import com.ruoyi.ql.mapper.QlPaymentWarehousingRelMapper;
import com.ruoyi.ql.mapper.QlReceivableOutboundRelMapper;
import com.ruoyi.ql.service.IQlOutboundService;
import com.ruoyi.ql.service.IQlPaymentWarehousingRelService;
import com.ruoyi.ql.service.IQlReceivableOutboundRelService;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.domain.bo.SysNoticeBo;
import com.ruoyi.system.service.ISysNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.ql.domain.bo.QlFinReceivableBo;
import com.ruoyi.ql.domain.QlFinReceivable;
import com.ruoyi.ql.mapper.QlFinReceivableMapper;
import com.ruoyi.ql.service.IQlFinReceivableService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 收款记录Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-05
 */
@RequiredArgsConstructor
@Service
public class QlFinReceivableServiceImpl implements IQlFinReceivableService {

    private final QlFinReceivableMapper baseMapper;

    private final IQlReceivableOutboundRelService receivableOutboundRelService;

    private final QlReceivableOutboundRelMapper receivableOutboundRelMapper;

    private final IQlOutboundService iQlOutboundService;

    private final ISysNoticeService iSysNoticeService;

    /**
     * 查询收款记录
     */
    @Override
    public QlFinReceivableVo queryById(Long id){
        QlFinReceivableVo qlFinReceivableVo = baseMapper.selectVoById(id);
        QlReceivableOutboundRelBo qlReceivableOutboundRelBo = new QlReceivableOutboundRelBo();
        qlReceivableOutboundRelBo.setReceivableId(qlFinReceivableVo.getId());
        List<QlReceivableOutboundRelVo> qlReceivableOutboundRelVos = receivableOutboundRelService.queryList(qlReceivableOutboundRelBo);
        qlFinReceivableVo.setReceivableOutboundRels(qlReceivableOutboundRelVos);
        return qlFinReceivableVo;
    }

    /**
     * 查询收款记录列表
     */
    @Override
    public TableDataInfo<QlFinReceivableVo> queryPageList(QlFinReceivableBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlFinReceivable> lqw = buildQueryWrapper(bo);
        Page<QlFinReceivableVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        if(ObjectUtil.isNull(result) || CollUtil.isEmpty(result.getRecords())) {
            return TableDataInfo.build(result);
        }
        findReceivableOutboundRels(result.getRecords());
        return TableDataInfo.build(result);
    }

    private void findReceivableOutboundRels(List<QlFinReceivableVo> finReceivableVos) {
        List<Long> receivableIds = finReceivableVos.stream().map(QlFinReceivableVo::getId).collect(Collectors.toList());
        QlReceivableOutboundRelBo receivableOutboundRelBo = new QlReceivableOutboundRelBo();
        receivableOutboundRelBo.setReceivableIds(receivableIds);
        List<QlReceivableOutboundRelVo> receivableOutboundRelVos = receivableOutboundRelService.queryList(receivableOutboundRelBo);
        if (CollUtil.isEmpty(receivableOutboundRelVos)) {
            return;
        }
        Map<Long, List<QlReceivableOutboundRelVo>> receivableOutboundRelMap = receivableOutboundRelVos.stream().collect(Collectors.groupingBy(QlReceivableOutboundRelVo::getReceivableId));
        for (QlFinReceivableVo finReceivableVo : finReceivableVos) {
            if (!receivableOutboundRelMap.containsKey(finReceivableVo.getId())) {
                continue;
            }
            finReceivableVo.setReceivableOutboundRels(receivableOutboundRelMap.get(finReceivableVo.getId()));
        }
    }

    /**
     * 查询收款记录列表
     */
    @Override
    public List<QlFinReceivableVo> queryList(QlFinReceivableBo bo) {
        LambdaQueryWrapper<QlFinReceivable> lqw = buildQueryWrapper(bo);
        List<QlFinReceivableVo> qlFinReceivableVos = baseMapper.selectVoList(lqw);
        if(CollUtil.isEmpty(qlFinReceivableVos)) {
            return qlFinReceivableVos;
        }
        findReceivableOutboundRels(qlFinReceivableVos);
        return qlFinReceivableVos;
    }

    private LambdaQueryWrapper<QlFinReceivable> buildQueryWrapper(QlFinReceivableBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlFinReceivable> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getContractId()), QlFinReceivable::getContractId, bo.getContractId());
        lqw.eq(StringUtils.isNotBlank(bo.getContractCode()), QlFinReceivable::getContractCode, bo.getContractCode());
        lqw.like(StringUtils.isNotBlank(bo.getContractName()), QlFinReceivable::getContractName, bo.getContractName());
        lqw.eq(StringUtils.isNotBlank(bo.getCustomerId()), QlFinReceivable::getCustomerId, bo.getCustomerId());
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), QlFinReceivable::getCustomerName, bo.getCustomerName());
        lqw.eq(bo.getReceivableAmount() != null, QlFinReceivable::getReceivableAmount, bo.getReceivableAmount());
        lqw.eq(bo.getReceivableDate() != null, QlFinReceivable::getReceivableDate, bo.getReceivableDate());
        lqw.eq(StringUtils.isNotBlank(bo.getReceivableSummary()), QlFinReceivable::getReceivableSummary, bo.getReceivableSummary());
        lqw.eq(StringUtils.isNotBlank(bo.getAccountNo()), QlFinReceivable::getAccountNo, bo.getAccountNo());
        lqw.like(StringUtils.isNotBlank(bo.getBankName()), QlFinReceivable::getBankName, bo.getBankName());
        lqw.eq(StringUtils.isNotBlank(bo.getInvoiceNo()), QlFinReceivable::getInvoiceNo, bo.getInvoiceNo());
        lqw.eq(StringUtils.isNotBlank(bo.getFj()), QlFinReceivable::getFj, bo.getFj());
        return lqw;
    }

    /**
     * 新增收款记录
     */
    @Override
    public Boolean insertByBo(QlFinReceivableBo bo) {
        QlFinReceivable add = BeanUtil.toBean(bo, QlFinReceivable.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        List<QlReceivableOutboundRelBo> receivableOutboundRels = bo.getReceivableOutboundRels();
        for (QlReceivableOutboundRelBo receivableOutboundRelBo : receivableOutboundRels) {
            receivableOutboundRelBo.setReceivableId(add.getId());
            receivableOutboundRelService.insertByBo(receivableOutboundRelBo);
        }
        List<String> outboundCodes = receivableOutboundRels.stream().map(QlReceivableOutboundRelBo::getOutboundCode).collect(Collectors.toList());
        QlOutboundBo qlOutboundBo = new QlOutboundBo();
        qlOutboundBo.setOutboundCodes(outboundCodes);
        List<QlOutboundVo> qlOutboundVos = iQlOutboundService.queryList(qlOutboundBo);
        if (CollUtil.isEmpty(qlOutboundVos)) {
            return null;
        }
        List<Long> businessIds = qlOutboundVos.stream().map(QlOutboundVo::getId).collect(Collectors.toList());
        SysNoticeBo notice = new SysNoticeBo();
        notice.setBusinessIds(businessIds);
        notice.setBusinessType("outbound");
        List<SysNotice> sysNotices = iSysNoticeService.selectNoticeList(notice);
        if (CollUtil.isEmpty(sysNotices)) {
            return true;
        }
        iSysNoticeService.deleteNoticeByIds(sysNotices.stream().map(SysNotice::getNoticeId).toArray(Long[]::new));
        return flag;
    }

    /**
     * 修改收款记录
     */
    @Override
    public Boolean updateByBo(QlFinReceivableBo bo) {
        QlFinReceivable update = BeanUtil.toBean(bo, QlFinReceivable.class);
        BigDecimal amount = bo.getReceivableOutboundRels().stream().map(QlReceivableOutboundRelBo::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        update.setReceivableAmount(amount);
        validEntityBeforeSave(update);
        LambdaQueryWrapper<QlReceivableOutboundRel> ql = new LambdaQueryWrapper<>();
        ql.eq(QlReceivableOutboundRel::getReceivableId, bo.getId());
        List<QlReceivableOutboundRel> receivableOutboundRels = receivableOutboundRelMapper.selectList(ql);
        if(CollUtil.isNotEmpty(receivableOutboundRels)) {
            receivableOutboundRelMapper.deleteBatchIds(receivableOutboundRels);
        }
        for (QlReceivableOutboundRelBo receivableOutboundRelBo : bo.getReceivableOutboundRels()) {
            receivableOutboundRelBo.setId(null);
            receivableOutboundRelBo.setReceivableId(bo.getId());
            receivableOutboundRelService.insertByBo(receivableOutboundRelBo);
        }
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlFinReceivable entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除收款记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        //TODO 20230525  需要删除对应的收款明细表
        boolean deleteResult = baseMapper.deleteBatchIds(ids) > 0;
        if (deleteResult) {
            List<Long> idList = ids.stream().distinct().collect(Collectors.toList());
            if (CollUtil.isEmpty(idList)) {
                return true;
            }
            QlReceivableOutboundRelBo qlReceivableOutboundRelBo = new QlReceivableOutboundRelBo();
            qlReceivableOutboundRelBo.setReceivableIds(idList);
            List<QlReceivableOutboundRelVo> qlReceivableOutboundRelVos = receivableOutboundRelService.queryList(qlReceivableOutboundRelBo);
            if(CollUtil.isEmpty(qlReceivableOutboundRelVos)) {
                return true;
            }
            receivableOutboundRelService.deleteWithValidByIds(qlReceivableOutboundRelVos.stream().map(QlReceivableOutboundRelVo::getId).distinct().collect(Collectors.toList()), true);
        }
        return deleteResult;
    }
}
