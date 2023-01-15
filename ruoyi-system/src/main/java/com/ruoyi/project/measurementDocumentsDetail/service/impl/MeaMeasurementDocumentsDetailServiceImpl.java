package com.ruoyi.project.measurementDocumentsDetail.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.bill.domain.MeaContractBill;
import com.ruoyi.project.bill.mapper.MeaContractBillMapper;
import com.ruoyi.project.measurementDocumentsDetail.domain.MeaMeasurementDocumentsDetail;
import com.ruoyi.project.measurementDocumentsDetail.domain.bo.MeaMeasurementDocumentsDetailBo;
import com.ruoyi.project.measurementDocumentsDetail.domain.vo.MeaMeasurementDocumentsDetailVo;
import com.ruoyi.project.measurementDocumentsDetail.mapper.MeaMeasurementDocumentsDetailMapper;
import com.ruoyi.project.measurementDocumentsDetail.service.IMeaMeasurementDocumentsDetailService;
import io.micrometer.core.instrument.util.StringUtils;
import liquibase.pro.packaged.Q;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 台账变更/工程变更 明细Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@RequiredArgsConstructor
@Service
public class MeaMeasurementDocumentsDetailServiceImpl implements IMeaMeasurementDocumentsDetailService {

    private final MeaMeasurementDocumentsDetailMapper baseMapper;
    private final MeaContractBillMapper meaContractBillMapper;

    /**
     * 查询台账变更/工程变更 明细
     */
    @Override
    public MeaMeasurementDocumentsDetailVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询台账变更/工程变更 明细列表
     */
    @Override
    public TableDataInfo<MeaMeasurementDocumentsDetailVo> queryPageList(MeaMeasurementDocumentsDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MeaMeasurementDocumentsDetail> lqw = buildQueryWrapper(bo);
        Page<MeaMeasurementDocumentsDetailVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        if(result.getTotal()>0){
            for(MeaMeasurementDocumentsDetailVo meaMeasurementDocumentsDetailVo:result.getRecords()){
                QueryWrapper<MeaContractBill> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("zmh",meaMeasurementDocumentsDetailVo.getZmh());
                MeaContractBill meaContractBill = meaContractBillMapper.selectOne(queryWrapper);
                if(meaContractBill!=null){
                    if(meaContractBill.getHtsl()==null || meaContractBill.getShsl()==null){
                        meaMeasurementDocumentsDetailVo.setKsjsl(BigDecimal.ZERO);
                        meaMeasurementDocumentsDetailVo.setLjjlbl("0");
                    }else{
                        BigDecimal sub=meaContractBill.getHtsl().subtract(meaContractBill.getShsl());
                        if(sub.intValue()>0){
                            meaMeasurementDocumentsDetailVo.setKsjsl(sub);
                        }else {
                            meaMeasurementDocumentsDetailVo.setKsjsl(BigDecimal.ZERO);
                        }
                        if(meaContractBill.getHtsl().compareTo(BigDecimal.ZERO)==0 || meaContractBill.getShsl().compareTo(BigDecimal.ZERO)==0){
                            meaMeasurementDocumentsDetailVo.setKsjsl(BigDecimal.ZERO);
                            meaMeasurementDocumentsDetailVo.setLjjlbl("0");
                        }else {
                            BigDecimal div=meaContractBill.getShsl().divide(meaContractBill.getHtsl());
                            meaMeasurementDocumentsDetailVo.setLjjlbl(String.valueOf((div.longValue()*100)));
                        }
                    }
                    meaMeasurementDocumentsDetailVo.setMeaContractBill(meaContractBill);
                }
            }

        }
        return TableDataInfo.build(result);
    }

    /**
     * 查询台账变更/工程变更 明细列表
     */
    @Override
    public List<MeaMeasurementDocumentsDetailVo> queryList(MeaMeasurementDocumentsDetailBo bo) {
        LambdaQueryWrapper<MeaMeasurementDocumentsDetail> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaMeasurementDocumentsDetail> buildQueryWrapper(MeaMeasurementDocumentsDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaMeasurementDocumentsDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaMeasurementDocumentsDetail::getBdbh, bo.getBdbh());
        lqw.eq(StringUtils.isNotBlank(bo.getPzbh()), MeaMeasurementDocumentsDetail::getPzbh, bo.getPzbh());
        lqw.eq(StringUtils.isNotBlank(bo.getZmh()), MeaMeasurementDocumentsDetail::getZmh, bo.getZmh());
        lqw.eq(bo.getBqjlsl() != null, MeaMeasurementDocumentsDetail::getBqjlsl, bo.getBqjlsl());
        lqw.eq(StringUtils.isNotBlank(bo.getJllx()), MeaMeasurementDocumentsDetail::getJllx, bo.getJllx());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaMeasurementDocumentsDetail::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增台账变更/工程变更 明细
     */
    @Override
    public Boolean insertByBo(MeaMeasurementDocumentsDetailBo bo) {
        MeaMeasurementDocumentsDetail add = BeanUtil.toBean(bo, MeaMeasurementDocumentsDetail.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改台账变更/工程变更 明细
     */
    @Override
    public Boolean updateByBo(MeaMeasurementDocumentsDetailBo bo) {
        MeaMeasurementDocumentsDetail update = BeanUtil.toBean(bo, MeaMeasurementDocumentsDetail.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaMeasurementDocumentsDetail entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除台账变更/工程变更 明细
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
