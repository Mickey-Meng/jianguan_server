package com.ruoyi.project.ledger.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.project.approval.domain.MeaLedgerApproval;
import com.ruoyi.project.approval.mapper.MeaLedgerApprovalMapper;
import com.ruoyi.project.bill.domain.MeaContractBill;
import com.ruoyi.project.bill.domain.bo.MeaContractBillBo;
import com.ruoyi.project.bill.domain.vo.MeaContractBillVo;
import com.ruoyi.project.bill.mapper.MeaContractBillMapper;
import com.ruoyi.project.bill.service.IMeaContractBillService;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdown;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdownDetail;
import com.ruoyi.project.ledger.domain.bo.MeaLedgerBreakdownDetailBo;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownDetailInfoVo;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownDetailVo;
import com.ruoyi.project.ledger.mapper.MeaLedgerBreakdownDetailMapper;
import com.ruoyi.project.ledger.mapper.MeaLedgerBreakdownMapper;
import com.ruoyi.project.ledger.service.IMeaLedgerBreakdownDetailService;
import com.ruoyi.project.ledgerChangeDetail.domain.bo.MeaLedgerChangeDetailBo;
import com.ruoyi.project.ledgerChangeDetail.domain.vo.MeaLedgerChangeDetailVo;
import com.ruoyi.project.ledgerChangeDetail.service.IMeaLedgerChangeDetailService;
import com.ruoyi.project.measurementDocumentsDetail.domain.bo.MeaMeasurementDocumentsDetailBo;
import com.ruoyi.project.measurementDocumentsDetail.domain.vo.MeaMeasurementDocumentsDetailVo;
import com.ruoyi.project.measurementDocumentsDetail.service.IMeaMeasurementDocumentsDetailService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 台账分解明细Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-04s
 */
@RequiredArgsConstructor
@Service
public class MeaLedgerBreakdownDetailServiceImpl implements IMeaLedgerBreakdownDetailService {

    private final MeaLedgerBreakdownDetailMapper meaLedgerBreakdownDetailMapper;

    private final IMeaLedgerChangeDetailService iMeaLedgerChangeDetailService;

    private final IMeaMeasurementDocumentsDetailService iMeaMeasurementDocumentsDetailService;

    private final MeaLedgerBreakdownMapper meaLedgerBreakdownMapper;

    private final MeaLedgerApprovalMapper meaLedgerApprovalMapper;

    private final MeaContractBillMapper meaContractBillMapper;

    private final IMeaContractBillService iMeaContractBillService;
    private static final  String _QUERY_TYPE_DEFAULT = "r";

    /**
     * 查询台账分解明细
     */
    @Override
    public MeaLedgerBreakdownDetailVo queryById(String id){
        return meaLedgerBreakdownDetailMapper.selectVoById(id);
    }

    /**
     * 查询台账分解明细列表
     */
    @Override
    public TableDataInfo<MeaLedgerBreakdownDetailVo> queryPageList(MeaLedgerBreakdownDetailBo bo,String queryType, PageQuery pageQuery) {
     /*   if(StrUtil.isNotBlank(bo.getReviewCode())){
            if(bo.getReviewCode().equals("2")){
                QueryWrapper<MeaLedgerApproval> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("review_code","2");
                List<MeaLedgerApproval> meaLedgerApprovals = meaLedgerApprovalMapper.selectList(queryWrapper);
                if(CollUtil.isEmpty(meaLedgerApprovals)){
                    return TableDataInfo.build(new ArrayList<>());
                }
            }
        }*/
        LambdaQueryWrapper<MeaLedgerBreakdownDetail> lqw = buildQueryWrapper4jiliang(bo,queryType);
        Page<MeaLedgerBreakdownDetailVo> result = meaLedgerBreakdownDetailMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询台账分解明细列表
     */
    @Override
    public List<MeaLedgerBreakdownDetailVo> queryList(MeaLedgerBreakdownDetailBo bo) {
        LambdaQueryWrapper<MeaLedgerBreakdownDetail> lqw = buildQueryWrapper(bo,"r");
//        lqw.eq(MeaLedgerBreakdownDetail::getYjlsl,0);
        return meaLedgerBreakdownDetailMapper.selectVoList(lqw);
    }
    @Override
    public List<MeaLedgerBreakdownDetailVo> queryList4ledgerApproval(MeaLedgerBreakdownDetailBo bo) {
//        LambdaQueryWrapper<MeaLedgerBreakdownDetail> lqw =  Wrappers.lambdaQuery();
//        lqw.eq(MeaLedgerBreakdownDetail::getYjlsl,0).and(wq->wq.
//        gt(MeaLedgerBreakdownDetail::getFjsl, 0).or().gt(MeaLedgerBreakdownDetail::getBgfjsl, 0));
//        lqw.eq(StringUtils.isNotBlank(bo.getZmh()), MeaLedgerBreakdownDetail::getZmh, bo.getZmh());
        return meaLedgerBreakdownDetailMapper.queryList4ledgerApproval(bo.getZmh(),bo.getZmmc());
    }
    @Override
    public List<MeaLedgerBreakdownDetailVo> getLeafList(String reviewCode) {
        Map<String, Object> params = new HashMap<>();
        LambdaQueryWrapper<MeaLedgerBreakdownDetail> lqw = Wrappers.lambdaQuery();
        lqw.gt(MeaLedgerBreakdownDetail::getFjsl, 0);
        lqw.eq(MeaLedgerBreakdownDetail::getReviewCode, reviewCode);
        return meaLedgerBreakdownDetailMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaLedgerBreakdownDetail> buildQueryWrapper(MeaLedgerBreakdownDetailBo bo,String queryType) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaLedgerBreakdownDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaLedgerBreakdownDetail::getBdbh, bo.getBdbh());
        if ("e".equalsIgnoreCase(queryType)){
            lqw.eq(StringUtils.isNotBlank(bo.getTzfjbh()), MeaLedgerBreakdownDetail::getTzfjbh, bo.getTzfjbh());
        }else if("r".equalsIgnoreCase(queryType)){
            lqw.likeRight(StringUtils.isNotBlank(bo.getTzfjbh()), MeaLedgerBreakdownDetail::getTzfjbh, bo.getTzfjbh());
        }

        lqw.eq(StringUtils.isNotBlank(bo.getFjmulu()), MeaLedgerBreakdownDetail::getFjmulu, bo.getFjmulu());
        lqw.eq(StringUtils.isNotBlank(bo.getZmh()), MeaLedgerBreakdownDetail::getZmh, bo.getZmh());
        lqw.eq(StringUtils.isNotBlank(bo.getZmmc()), MeaLedgerBreakdownDetail::getZmmc, bo.getZmmc());
        lqw.eq(StringUtils.isNotBlank(bo.getDw()), MeaLedgerBreakdownDetail::getDw, bo.getDw());
        lqw.eq(bo.getHtdj() != null, MeaLedgerBreakdownDetail::getHtdj, bo.getHtdj());
        lqw.eq(bo.getSjsl() != null, MeaLedgerBreakdownDetail::getSjsl, bo.getSjsl());
        lqw.eq(bo.getFjsl() != null, MeaLedgerBreakdownDetail::getFjsl, bo.getFjsl());
        lqw.eq(bo.getBgsl() != null, MeaLedgerBreakdownDetail::getBgsl, bo.getBgsl());
        lqw.eq(bo.getBgfjsl() != null, MeaLedgerBreakdownDetail::getBgfjsl, bo.getBgfjsl());
        lqw.eq(bo.getFhsl() != null, MeaLedgerBreakdownDetail::getFhsl, bo.getFhsl());
        lqw.eq(bo.getYjlsl() != null, MeaLedgerBreakdownDetail::getYjlsl, bo.getYjlsl());
        lqw.eq(bo.getFhje() != null, MeaLedgerBreakdownDetail::getFhje, bo.getFhje());
        lqw.eq(StringUtils.isNotBlank(bo.getFjlx()), MeaLedgerBreakdownDetail::getFjlx, bo.getFjlx());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaLedgerBreakdownDetail::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getReviewCode()), MeaLedgerBreakdownDetail::getReviewCode, bo.getReviewCode());
        lqw.eq(StringUtils.isNotBlank(bo.getIsChange()), MeaLedgerBreakdownDetail::getIsChange, bo.getIsChange());
        lqw.in(CollUtil.isNotEmpty(bo.getZmhList()), MeaLedgerBreakdownDetail::getZmh, bo.getZmhList());
        // add by gaoxinlong 20230307  【增加子目号排序】
        lqw.orderByAsc(MeaLedgerBreakdownDetail::getZmh);
        return lqw;
    }



    private LambdaQueryWrapper<MeaLedgerBreakdownDetail> buildQueryWrapper4jiliang(MeaLedgerBreakdownDetailBo bo,String queryType) {
        LambdaQueryWrapper<MeaLedgerBreakdownDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaLedgerBreakdownDetail::getBdbh, bo.getBdbh());
        lqw.eq(StringUtils.isNotBlank(bo.getIsChange()), MeaLedgerBreakdownDetail::getIsChange, bo.getIsChange());
        if ("e".equalsIgnoreCase(queryType)){
            lqw.eq(StringUtils.isNotBlank(bo.getTzfjbh()), MeaLedgerBreakdownDetail::getTzfjbh, bo.getTzfjbh());
        }else if("r".equalsIgnoreCase(queryType)){
            lqw.likeRight(StringUtils.isNotBlank(bo.getTzfjbh()), MeaLedgerBreakdownDetail::getTzfjbh, bo.getTzfjbh());
        }
        lqw.eq(StringUtils.isNotBlank(bo.getReviewCode()), MeaLedgerBreakdownDetail::getReviewCode, bo.getReviewCode());
     /*   if(StrUtil.isNotBlank(bo.getReviewCode())){
            if(bo.getReviewCode().equals("2")){
                QueryWrapper<MeaLedgerApproval> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("review_code","2");
                List<MeaLedgerApproval> meaLedgerApprovals = meaLedgerApprovalMapper.selectList(queryWrapper);
                if(CollUtil.isNotEmpty(meaLedgerApprovals)){
                    List<String> collect = meaLedgerApprovals.stream().map(MeaLedgerApproval::getTzfjbh).collect(Collectors.toList());
                    lqw.in(MeaLedgerBreakdownDetail::getTzfjbh,collect);
                }
            }
        }*/
        // add by gaoxinlong 20230307  【增加子目号排序】
        lqw.orderByAsc(MeaLedgerBreakdownDetail::getZmh);
        return lqw;
    }
    /**
     * 新增台账分解明细
     */
    @Override
    public Boolean insertByBo(MeaLedgerBreakdownDetailBo bo) {
        MeaLedgerBreakdownDetail add = BeanUtil.toBean(bo, MeaLedgerBreakdownDetail.class);
        validEntityBeforeSave(add);
        add.setId(null);
        add.setReviewCode("0"); //modify yangaogao 20230731  取消台账分解审批流程，因此默认为审批通过  20230803

        boolean flag = meaLedgerBreakdownDetailMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    @Override
    public Boolean insertByListBo(List<MeaLedgerBreakdownDetailBo> meaLedgerBreakdownDetailBos) {
        meaLedgerBreakdownDetailBos.forEach((e) -> {
            e.setSjsl(e.getHtsl());
        });

        List<MeaLedgerBreakdownDetail> meaLedgerBreakdownDetailList = BeanUtil.copyToList(meaLedgerBreakdownDetailBos, MeaLedgerBreakdownDetail.class);
//        meaLedgerBreakdownDetailList.forEach(meaLedgerBreakdownDetail ->  );
        validEntityBeforeSave(meaLedgerBreakdownDetailList.get(0));

        boolean b = meaLedgerBreakdownDetailMapper.insertBatch(meaLedgerBreakdownDetailList);
        return  b;
    }


    /**
     * 分解数量是否已超过最大可分解数量
     * @param meaLedgerBreakdownDetailBos
     */
    private void validatorFjsl(List<MeaLedgerBreakdownDetailBo> meaLedgerBreakdownDetailBos) {
        // 过滤变更
        List<MeaLedgerBreakdownDetailBo> meaLedgerBreakdownDetailBoList = meaLedgerBreakdownDetailBos.stream().filter(meaLedgerBreakdownDetailBo -> "0".equals(meaLedgerBreakdownDetailBo.getIsChange())).collect(Collectors.toList());
        if(CollUtil.isEmpty(meaLedgerBreakdownDetailBoList)) {
            return;
        }
        List<String> zmhList = meaLedgerBreakdownDetailBoList.stream().map(MeaLedgerBreakdownDetailBo::getZmh).collect(Collectors.toList());

        MeaLedgerBreakdownDetailBo meaLedgerBreakdownDetailBo = new MeaLedgerBreakdownDetailBo();
        meaLedgerBreakdownDetailBo.setZmhList(zmhList);
        meaLedgerBreakdownDetailBo.setIsChange("0");
        List<MeaLedgerBreakdownDetailVo> meaLedgerBreakdownDetailVos = queryList(meaLedgerBreakdownDetailBo);
        if(CollUtil.isEmpty(meaLedgerBreakdownDetailBoList)) {
            return;
        }

        MeaContractBillBo meaContractBillBo = new MeaContractBillBo();
        meaContractBillBo.setIsChange("0");
        meaContractBillBo.setZmhList(zmhList);
        // 可分解的数量
        List<MeaContractBillVo> meaContractBillVos = iMeaContractBillService.getLeafList(meaContractBillBo);
        if(CollUtil.isEmpty(meaContractBillVos)) {
            return;
        }
        // 本次待分解的数量
        Map<String, BigDecimal> fjslMap = meaLedgerBreakdownDetailBoList.stream().collect(Collectors.toMap(MeaLedgerBreakdownDetailBo::getZmh, MeaLedgerBreakdownDetailBo::getFjsl));
        // 台账分解明细中zmh的已分解数量
        Map<String, List<MeaLedgerBreakdownDetailVo>> meaLedgerBreakdownDetailMap = meaLedgerBreakdownDetailVos
                .stream().filter(meaLedgerBreakdownDetailVo -> ObjectUtil.isNotNull(meaLedgerBreakdownDetailVo.getFjsl()))
                .collect(Collectors.groupingBy(MeaLedgerBreakdownDetailVo::getZmh));
        for (MeaContractBillVo meaContractBillVo : meaContractBillVos) {
            if (!meaLedgerBreakdownDetailMap.containsKey(meaContractBillVo.getZmh())) {
                continue;
            }
            if (!fjslMap.containsKey(meaContractBillVo.getZmh())) {
                continue;
            }

            List<MeaLedgerBreakdownDetailVo> meaLedgerBreakdownDetails = meaLedgerBreakdownDetailMap.get(meaContractBillVo.getZmh());
            // 已分解总数量
            BigDecimal fjsl = new BigDecimal("0.0");
            for (MeaLedgerBreakdownDetailVo meaLedgerBreakdownDetail : meaLedgerBreakdownDetails) {
                fjsl = fjsl.add(meaLedgerBreakdownDetail.getFjsl());
            }

            // 本次分解数量
            BigDecimal currentFjsj = fjslMap.get(meaContractBillVo.getZmh());

            // 可分解数量 - 已分解数量 = 待分解数量
            // 待分解与本次分解数量对比 本次分解数量大于待分解数量-抛异常
            BigDecimal htsl = meaContractBillVo.getHtsl();
            if(currentFjsj.compareTo(htsl.subtract(fjsl)) > 0) {
                throw new ServiceException("子目号："+meaContractBillVo.getZmh()+"的本次分解数量大于可分解数量，本次分解数量："+currentFjsj+"，可分解数量："+htsl.subtract(fjsl));
            }
        }
    }

    /**
     * 变更分解数量是否已超过最大课分解变更数量
     * @param meaLedgerBreakdownDetailBos
     */
    private void validatorBgfjsl(List<MeaLedgerBreakdownDetailBo> meaLedgerBreakdownDetailBos) {
        List<MeaLedgerBreakdownDetailBo> meaLedgerBreakdownDetailBoList = meaLedgerBreakdownDetailBos.stream().filter(meaLedgerBreakdownDetailBo -> "1".equals(meaLedgerBreakdownDetailBo.getIsChange())).collect(Collectors.toList());
        // 过滤变更
        if(CollUtil.isEmpty(meaLedgerBreakdownDetailBoList)) {
            return;
        }
        List<String> zmhList = meaLedgerBreakdownDetailBoList.stream().map(MeaLedgerBreakdownDetailBo::getZmh).collect(Collectors.toList());

        MeaLedgerBreakdownDetailBo meaLedgerBreakdownDetailBo = new MeaLedgerBreakdownDetailBo();
        meaLedgerBreakdownDetailBo.setZmhList(zmhList);
        meaLedgerBreakdownDetailBo.setIsChange("1");
        List<MeaLedgerBreakdownDetailVo> meaLedgerBreakdownDetailVos = queryList(meaLedgerBreakdownDetailBo);
        if(CollUtil.isEmpty(meaLedgerBreakdownDetailBoList)) {
            return;
        }

        MeaContractBillBo meaContractBillBo = new MeaContractBillBo();
        meaContractBillBo.setIsChange("1");
        meaContractBillBo.setZmhList(zmhList);
        // 可分解的数量
        List<MeaContractBillVo> meaContractBillVos = iMeaContractBillService.getLeafList(meaContractBillBo);
        if(CollUtil.isEmpty(meaContractBillVos)) {
            return;
        }
        // 本次待分解的数量
        Map<String, BigDecimal> fjslMap = meaLedgerBreakdownDetailBoList.stream().collect(Collectors.toMap(MeaLedgerBreakdownDetailBo::getZmh, MeaLedgerBreakdownDetailBo::getBgfjsl));
        // 过滤bgfjsl未null的数据，台账分解明细中zmh的已分解数量
        Map<String, List<MeaLedgerBreakdownDetailVo>> meaLedgerBreakdownDetailMap = meaLedgerBreakdownDetailVos
                .stream().filter(meaLedgerBreakdownDetailVo -> ObjectUtil.isNotNull(meaLedgerBreakdownDetailVo.getBgfjsl()))
                .collect(Collectors.groupingBy(MeaLedgerBreakdownDetailVo::getZmh));
        for (MeaContractBillVo meaContractBillVo : meaContractBillVos) {
            if (!meaLedgerBreakdownDetailMap.containsKey(meaContractBillVo.getZmh())) {
                continue;
            }
            if (!fjslMap.containsKey(meaContractBillVo.getZmh())) {
                continue;
            }

            List<MeaLedgerBreakdownDetailVo> meaLedgerBreakdownDetails = meaLedgerBreakdownDetailMap.get(meaContractBillVo.getZmh());
            // 已分解总数量
            BigDecimal bgfjsl = new BigDecimal("0.0");
            for (MeaLedgerBreakdownDetailVo meaLedgerBreakdownDetail : meaLedgerBreakdownDetails) {
                bgfjsl = bgfjsl.add(meaLedgerBreakdownDetail.getBgfjsl());
            }

            // 本次分解数量
            BigDecimal currentBgfjsj = fjslMap.get(meaContractBillVo.getZmh());

            // 可分解数量 - 已分解数量 = 待分解数量
            // 待分解与本次分解数量对比 本次分解数量大于待分解数量-抛异常
            BigDecimal bgsl = meaContractBillVo.getBgsl();
            if(currentBgfjsj.compareTo(bgsl.subtract(bgfjsl)) > 0) {
                throw new ServiceException("子目号："+meaContractBillVo.getZmh()+"的本次分解数量大于可分解变更数量，本次分解数量："+currentBgfjsj+"，可变更分解数量："+bgsl.subtract(bgfjsl));
            }
        }



    }


    /**
     * 修改台账分解明细
     */
    @Override
    public Boolean updateByBo(MeaLedgerBreakdownDetailBo meaLedgerBreakdownDetailBo) {
        List<MeaLedgerBreakdownDetailBo> meaLedgerBreakdownDetailBos = new ArrayList<>();
        meaLedgerBreakdownDetailBos.add(meaLedgerBreakdownDetailBo);
        // 分解数量是否已超过最大可分解数量
        validatorFjsl(meaLedgerBreakdownDetailBos);
        // 变更分解数量是否已超过最大课分解变更数量
        validatorBgfjsl(meaLedgerBreakdownDetailBos);
        MeaLedgerBreakdownDetail update = BeanUtil.toBean(meaLedgerBreakdownDetailBo, MeaLedgerBreakdownDetail.class);
        validEntityBeforeSave(update);
        return meaLedgerBreakdownDetailMapper.updateById(update) > 0;
    }

    /**
     * 批量修改台账分解明细
     */
    @Override
    public Boolean updateBatchByBOList(List<MeaLedgerBreakdownDetailBo> bos) {
        if(CollUtil.isEmpty(bos)) {
            return true;
        }
        List<MeaLedgerBreakdownDetail> meaLedgerBreakdownDetails = BeanUtil.copyToList(bos, MeaLedgerBreakdownDetail.class);
        for (MeaLedgerBreakdownDetail meaLedgerBreakdownDetail : meaLedgerBreakdownDetails) {
            validEntityBeforeSave(meaLedgerBreakdownDetail);
        }
        return meaLedgerBreakdownDetailMapper.updateBatchById(meaLedgerBreakdownDetails);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaLedgerBreakdownDetail entity){
        QueryWrapper<MeaLedgerBreakdown> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("tzfjbh",entity.getTzfjbh()).eq("status",'0');
        MeaLedgerBreakdown meaLedgerBreakdown = meaLedgerBreakdownMapper.selectOne(queryWrapper);
        QueryWrapper<MeaLedgerBreakdown> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("status",'0');
        List<MeaLedgerBreakdown> meaLedgerBreakdowns = meaLedgerBreakdownMapper.selectList(queryWrapper1);
        ArrayList<String> list = new ArrayList<>();
        List<String> childrenCode = getChildrenCode(meaLedgerBreakdown.getTzfjbh(), meaLedgerBreakdowns, list);
//        List<String> childrenCode = getChildrenCode(meaLedgerBreakdown.getTzfjbhParent(), meaLedgerBreakdowns, list);
        String level="";
        if(CollUtil.isNotEmpty(childrenCode)){
            for(String key:childrenCode){
                if(StrUtil.isEmpty(level)){
                    level=key;
                }else {
                    level=key+"/"+level;
                }
            }
        }
        entity.setFjmulu(level);

    }

    public List<String> getChildrenCode(String orgCode, List<MeaLedgerBreakdown> all, List<String> list) {
        for (MeaLedgerBreakdown d : all) {
            if (orgCode.equals(d.getTzfjbh())) {
                list.add(d.getTzfjmc());
                getChildrenCode(d.getTzfjbhParent(), all, list);
            }
        }
        return list;
    }
    /**
     * 批量删除台账分解明细
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return meaLedgerBreakdownDetailMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public TableDataInfo<MeaLedgerBreakdownDetailInfoVo> listInfo(MeaLedgerBreakdownDetailBo bo, PageQuery pageQuery) {
        if(StrUtil.isNotBlank(bo.getReviewCode())){
            if(bo.getReviewCode().equals("2")){
                QueryWrapper<MeaLedgerApproval> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("review_code","2");
                List<MeaLedgerApproval> meaLedgerApprovals = meaLedgerApprovalMapper.selectList(queryWrapper);
                if(CollUtil.isEmpty(meaLedgerApprovals)){
                    return TableDataInfo.build(new ArrayList<>());
                }
            }
        }
        QueryWrapper<MeaContractBill> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("status","0");
        queryWrapper1.eq("is_change","0");// add by yangaogao 20220223  新增变更清单，因为一个子目号可能对应多条数据了，但查询仅是为了获取其中一些数据，跟是否变更无关，因此取那条合同清单即可。
        List<MeaContractBill> meaContractBills = meaContractBillMapper.selectList(queryWrapper1);
        Map<String, MeaContractBill>  meaContractBillMap  =new HashMap<>();
        for(MeaContractBill meaContractBill:meaContractBills){
            meaContractBillMap.put(meaContractBill.getZmh(),meaContractBill);
        }

        List<MeaLedgerBreakdownDetailInfoVo> meaLedgerBreakdownDetailInfoVos=new ArrayList<>();
        LambdaQueryWrapper<MeaLedgerBreakdownDetail> lqw = buildQueryWrapper(bo,_QUERY_TYPE_DEFAULT);
        lqw.isNotNull(MeaLedgerBreakdownDetail::getFjsl).gt(MeaLedgerBreakdownDetail::getFjsl,new BigDecimal(0));

//      Page<MeaLedgerBreakdownDetailVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        List<MeaLedgerBreakdownDetailVo> meaLedgerBreakdownDetailVoList = meaLedgerBreakdownDetailMapper.selectMeaLedgerBreakdownDetails(bo.getTzfjbh());
        Map<String, List<MeaLedgerBreakdownDetailVo>> stringListMap=new LinkedHashMap<>();
        Map<String, List<MeaLedgerChangeDetailVo>> meaLedgerChangeDetailMap = new HashMap<>();
        Map<String, List<MeaMeasurementDocumentsDetailVo>> meaMeasurementDocumentsDetailVoMap = new HashMap<>();
        if(CollUtil.isNotEmpty(meaLedgerBreakdownDetailVoList)) {
            List<String> zmhList = meaLedgerBreakdownDetailVoList.stream().map(MeaLedgerBreakdownDetailVo::getZmh).distinct().collect(Collectors.toList());
            // 子目录号查询变更数量
            MeaLedgerChangeDetailBo meaLedgerChangeDetailBo = new MeaLedgerChangeDetailBo();
            meaLedgerChangeDetailBo.setZmhList(zmhList);
            List<MeaLedgerChangeDetailVo> meaLedgerChangeDetailVos = iMeaLedgerChangeDetailService.query(meaLedgerChangeDetailBo);
            meaLedgerChangeDetailMap = meaLedgerChangeDetailVos.stream().collect(Collectors.groupingBy(MeaLedgerChangeDetailVo::getZmh));
            // 子目号查询计量数量
            MeaMeasurementDocumentsDetailBo meaMeasurementDocumentsDetailBo = new MeaMeasurementDocumentsDetailBo();
            meaMeasurementDocumentsDetailBo.setZmhList(zmhList);
            List<MeaMeasurementDocumentsDetailVo> meaMeasurementDocumentsDetailVos = iMeaMeasurementDocumentsDetailService.queryList(meaMeasurementDocumentsDetailBo);
            meaMeasurementDocumentsDetailVoMap = meaMeasurementDocumentsDetailVos.stream().collect(Collectors.groupingBy(MeaMeasurementDocumentsDetailVo::getZmh));
        }


        if(CollUtil.isNotEmpty(meaLedgerBreakdownDetailVoList)){
//            List<MeaLedgerBreakdownDetailVo> records = meaLedgerBreakdownDetailVoList.getRecords();
            QueryWrapper<MeaContractBill> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("status","0");
            List<MeaContractBill> meaLedgerBreakdowns = meaContractBillMapper.selectList(queryWrapper);
            for(MeaLedgerBreakdownDetailVo meaLedgerBreakdownDetailVo:meaLedgerBreakdownDetailVoList){
               /* QueryWrapper<MeaContractBill> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("zmh",meaLedgerBreakdownDetailVo.getZmh());
                queryWrapper1.eq("status","0");
                queryWrapper1.eq("is_change","0");// add by yangaogao 20220223  新增变更清单，因为一个子目号可能对应多条数据了，但查询仅是为了获取其中一些数据，跟是否变更无关，因此取那条合同清单即可。
                MeaContractBill meaLedgerBreakdown = meaContractBillMapper.selectOne(queryWrapper1);*/
                MeaContractBill meaLedgerBreakdown = meaContractBillMap.get(meaLedgerBreakdownDetailVo.getZmh());
                if(meaLedgerBreakdown==null){
                    continue;
                }

                //获取当前分解明细对应的根部清单，因为
                MeaContractBill maximumParent2 = getMaximumParent2(meaLedgerBreakdowns, meaLedgerBreakdown);
                if(maximumParent2!=null){
                    String key=maximumParent2.getZmh()+"-"+maximumParent2.getZmmc(); //拼接子目号和字母名称，作为map的key存入缓存
                    if(stringListMap.containsKey(key)){
                        List<MeaLedgerBreakdownDetailVo> meaLedgerBreakdownDetailVos = stringListMap.get(key);
                        //判断当前台账分解清单中，子目号是否已经存入到清单根节点对应的list中，如果已经存在，则累加当前子目号对应的分解数量即可。
                        Optional<MeaLedgerBreakdownDetailVo> meaLedgerBreakdownDetailVo1 = meaLedgerBreakdownDetailVos.stream().filter(item -> item.getZmh().equals(meaLedgerBreakdownDetailVo.getZmh())).findFirst();
                        BigDecimal bgsl = new BigDecimal("0.0");
                        BigDecimal yjlsl = new BigDecimal("0.0");
                        if(meaLedgerChangeDetailMap.containsKey(meaLedgerBreakdownDetailVo.getZmh())) {
                            List<MeaLedgerChangeDetailVo> meaLedgerChangeDetails = meaLedgerChangeDetailMap.get(meaLedgerBreakdownDetailVo.getZmh());
                            // 汇总子目号的变更数据
                            for (MeaLedgerChangeDetailVo meaLedgerChangeDetail : meaLedgerChangeDetails) {
                                bgsl = bgsl.add(meaLedgerChangeDetail.getBgsl());
                            }
                        }
                        if(meaMeasurementDocumentsDetailVoMap.containsKey(meaLedgerBreakdownDetailVo.getZmh())) {
                            List<MeaMeasurementDocumentsDetailVo> meaMeasurementDocumentsDetailVos = meaMeasurementDocumentsDetailVoMap.get(meaLedgerBreakdownDetailVo.getZmh());
                            for (MeaMeasurementDocumentsDetailVo meaMeasurementDocumentsDetailVo : meaMeasurementDocumentsDetailVos) {
                                yjlsl = yjlsl.add(meaMeasurementDocumentsDetailVo.getBqjlsl());
                            }
                        }

                        if (null != meaLedgerBreakdownDetailVo1  && meaLedgerBreakdownDetailVo1.isPresent()){
                            MeaLedgerBreakdownDetailVo m = new MeaLedgerBreakdownDetailVo();
                            BeanCopyUtils.copy(meaLedgerBreakdownDetailVo1.get(),m);
                            m.setFjsl(meaLedgerBreakdownDetailVo1.get().getFjsl().add(meaLedgerBreakdownDetailVo.getFjsl()));
                            m.setBgfjsl(meaLedgerBreakdownDetailVo1.get().getBgfjsl().add(meaLedgerBreakdownDetailVo.getBgfjsl()));
                            m.setBgsl(bgsl);
                            m.setYjlsl(yjlsl);
                            meaLedgerBreakdownDetailVos.remove(meaLedgerBreakdownDetailVo1.get());
                            meaLedgerBreakdownDetailVos.add(m);
                        }else{
                            meaLedgerBreakdownDetailVos.add(meaLedgerBreakdownDetailVo);
                        }
                    }else {
                        List<MeaLedgerBreakdownDetailVo> meaLedgerBreakdownDetailVos =new ArrayList<>();
                        meaLedgerBreakdownDetailVos.add(meaLedgerBreakdownDetailVo);
                        stringListMap.put(key,meaLedgerBreakdownDetailVos);
                    }
                }
            }
            if(stringListMap.keySet().size()>0){
                for(String key:stringListMap.keySet()){
                    MeaLedgerBreakdownDetailInfoVo meaLedgerBreakdownDetailInfoVo=new MeaLedgerBreakdownDetailInfoVo();
                    String[] split = key.split("-");
                    meaLedgerBreakdownDetailInfoVo.setId(IdUtil.fastUUID());
                    meaLedgerBreakdownDetailInfoVo.setTzfjbh("第"+split[0]+"章"+split[1]);
                    meaLedgerBreakdownDetailInfoVo.setZmmc("第"+split[0]+"章"+split[1]);
                    List<MeaLedgerBreakdownDetailVo> meaLedgerBreakdownDetailVos = stringListMap.get(key);
                    meaLedgerBreakdownDetailInfoVo.setChildren(meaLedgerBreakdownDetailVos);
                    meaLedgerBreakdownDetailInfoVos.add(meaLedgerBreakdownDetailInfoVo);
                }

            }
        }
        Page<MeaLedgerBreakdownDetailInfoVo> page=new Page<>();
        page.setTotal(meaLedgerBreakdownDetailVoList.size());
        page.setRecords(meaLedgerBreakdownDetailInfoVos);
        page.setPages(0);
        return TableDataInfo.build(page);
    }
    //获取当前工程里清单对象对应的根目录
    public MeaContractBill getMaximumParent2(List<MeaContractBill> deptAll, MeaContractBill deptChild) {
        if (ObjectUtil.isNotEmpty(deptChild)) {
            String parentId = deptChild.getZmhParent();
            List<MeaContractBill> parentDepts = deptAll.stream().filter(item -> item.getZmh().equals(parentId)).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(parentDepts)) {
                MeaContractBill parentDept = parentDepts.get(0);
                MeaContractBill maximumParent2 = getMaximumParent2(deptAll, parentDept);
                if (maximumParent2 == null) {
                    return parentDept;
                }
                return maximumParent2;
            }
        }
        return null;
    }
}
