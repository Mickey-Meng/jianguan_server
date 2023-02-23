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
import com.ruoyi.project.approval.domain.MeaLedgerApproval;
import com.ruoyi.project.approval.mapper.MeaLedgerApprovalMapper;
import com.ruoyi.project.bill.domain.MeaContractBill;
import com.ruoyi.project.bill.mapper.MeaContractBillMapper;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdown;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdownDetail;
import com.ruoyi.project.ledger.domain.bo.MeaLedgerBreakdownDetailBo;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownDetailInfoVo;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownDetailVo;
import com.ruoyi.project.ledger.mapper.MeaLedgerBreakdownDetailMapper;
import com.ruoyi.project.ledger.mapper.MeaLedgerBreakdownMapper;
import com.ruoyi.project.ledger.service.IMeaLedgerBreakdownDetailService;
import io.micrometer.core.instrument.util.StringUtils;
import liquibase.pro.packaged.Q;
import liquibase.pro.packaged.W;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    private final MeaLedgerBreakdownDetailMapper baseMapper;

    private final MeaLedgerBreakdownMapper meaLedgerBreakdownMapper;

    private final MeaLedgerApprovalMapper meaLedgerApprovalMapper;

    private final MeaContractBillMapper meaContractBillMapper;

    private static final  String _QUERY_TYPE_DEFAULT = "r";

    /**
     * 查询台账分解明细
     */
    @Override
    public MeaLedgerBreakdownDetailVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询台账分解明细列表
     */
    @Override
    public TableDataInfo<MeaLedgerBreakdownDetailVo> queryPageList(MeaLedgerBreakdownDetailBo bo,String queryType, PageQuery pageQuery) {
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
        LambdaQueryWrapper<MeaLedgerBreakdownDetail> lqw = buildQueryWrapper(bo,queryType);
        Page<MeaLedgerBreakdownDetailVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询台账分解明细列表
     */
    @Override
    public List<MeaLedgerBreakdownDetailVo> queryList(MeaLedgerBreakdownDetailBo bo) {
        LambdaQueryWrapper<MeaLedgerBreakdownDetail> lqw = buildQueryWrapper(bo,"r");
        return baseMapper.selectVoList(lqw);
    }

    @Override
    public List<MeaLedgerBreakdownDetailVo> getLeafList(String reviewCode) {
        Map<String, Object> params = new HashMap<>();
        LambdaQueryWrapper<MeaLedgerBreakdownDetail> lqw = Wrappers.lambdaQuery();
        lqw.gt(MeaLedgerBreakdownDetail::getFjsl, 0);
        lqw.eq(MeaLedgerBreakdownDetail::getReviewCode, reviewCode);
        return baseMapper.selectVoList(lqw);
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
//        lqw.eq(StringUtils.isNotBlank(bo.getReviewCode()), MeaLedgerBreakdownDetail::getReviewCode, bo.getReviewCode());
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
        if(StrUtil.isNotBlank(bo.getReviewCode())){
            if(bo.getReviewCode().equals("2")){
                QueryWrapper<MeaLedgerApproval> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("review_code","2");
                List<MeaLedgerApproval> meaLedgerApprovals = meaLedgerApprovalMapper.selectList(queryWrapper);
                if(CollUtil.isNotEmpty(meaLedgerApprovals)){
                    List<String> collect = meaLedgerApprovals.stream().map(MeaLedgerApproval::getTzfjbh).collect(Collectors.toList());
                    lqw.in(MeaLedgerBreakdownDetail::getTzfjbh,collect);
                }
            }
        }
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
        add.setReviewCode("1");
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    @Override
    public Boolean insertByListBo(List<MeaLedgerBreakdownDetailBo> bo) {

        bo.forEach((e) -> {
            e.setSjsl(e.getHtsl());
        });

        List<MeaLedgerBreakdownDetail> meaLedgerBreakdownDetailList = BeanUtil.copyToList(bo, MeaLedgerBreakdownDetail.class);
//        meaLedgerBreakdownDetailList.forEach(meaLedgerBreakdownDetail ->  );
        validEntityBeforeSave(meaLedgerBreakdownDetailList.get(0));

        boolean b = baseMapper.insertBatch(meaLedgerBreakdownDetailList);
        return  b;
    }

    /**
     * 修改台账分解明细
     */
    @Override
    public Boolean updateByBo(MeaLedgerBreakdownDetailBo bo) {
        MeaLedgerBreakdownDetail update = BeanUtil.toBean(bo, MeaLedgerBreakdownDetail.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
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
        List<String> childrenCode = getChildrenCode(meaLedgerBreakdown.getTzfjbhParent(), meaLedgerBreakdowns, list);
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
        return baseMapper.deleteBatchIds(ids) > 0;
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
        List<MeaLedgerBreakdownDetailInfoVo> meaLedgerBreakdownDetailInfoVos=new ArrayList<>();
        LambdaQueryWrapper<MeaLedgerBreakdownDetail> lqw = buildQueryWrapper(bo,_QUERY_TYPE_DEFAULT);
        Page<MeaLedgerBreakdownDetailVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        Map<String, List<MeaLedgerBreakdownDetailVo>> stringListMap=new LinkedHashMap<>();
        if(CollUtil.isNotEmpty(result.getRecords())){
            List<MeaLedgerBreakdownDetailVo> records = result.getRecords();
            QueryWrapper<MeaContractBill> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("status","0");
            List<MeaContractBill> meaLedgerBreakdowns = meaContractBillMapper.selectList(queryWrapper);
            for(MeaLedgerBreakdownDetailVo meaLedgerBreakdownDetailVo:records){
                QueryWrapper<MeaContractBill> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("zmh",meaLedgerBreakdownDetailVo.getZmh());
                queryWrapper1.eq("status","0");
                queryWrapper1.eq("is_change","0");// add by yangaogao 20220223  新增变更清单，因为一个子目号可能对应多条数据了，但查询仅是为了获取其中一些数据，跟是否变更无关，因此取那条合同清单即可。
                MeaContractBill meaLedgerBreakdown = meaContractBillMapper.selectOne(queryWrapper1);
                if(meaLedgerBreakdown==null){
                    continue;
                }
                MeaContractBill maximumParent2 = getMaximumParent2(meaLedgerBreakdowns, meaLedgerBreakdown);
                if(maximumParent2!=null){
                    String key=maximumParent2.getZmh()+"-"+maximumParent2.getZmmc();
                    if(stringListMap.containsKey(key)){
                        List<MeaLedgerBreakdownDetailVo> meaLedgerBreakdownDetailVos = stringListMap.get(key);
                        meaLedgerBreakdownDetailVos.add(meaLedgerBreakdownDetailVo);
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
        page.setTotal(result.getTotal());
        page.setRecords(meaLedgerBreakdownDetailInfoVos);
        page.setPages(result.getPages());
        return TableDataInfo.build(page);
    }
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
