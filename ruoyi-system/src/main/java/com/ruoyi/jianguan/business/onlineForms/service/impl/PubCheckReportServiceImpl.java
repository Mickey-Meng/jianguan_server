package com.ruoyi.jianguan.business.onlineForms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.common.core.domain.entity.Conponent;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.poi.LuckySheetUtil;
import com.ruoyi.jianguan.business.onlineForms.domain.PubProduceDocument;
import com.ruoyi.jianguan.business.onlineForms.domain.vo.PubProduceDocumentVo;
import com.ruoyi.jianguan.business.onlineForms.mapper.PubProduceDocumentMapper;
import com.ruoyi.jianguan.business.onlineForms.service.IOnlineFormsService;
import com.ruoyi.jianguan.common.dao.ConponentDAO;
import com.ruoyi.system.domain.SysOss;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jianguan.business.onlineForms.domain.bo.PubCheckReportBo;
import com.ruoyi.jianguan.business.onlineForms.domain.vo.PubCheckReportVo;
import com.ruoyi.jianguan.business.onlineForms.domain.PubCheckReport;
import com.ruoyi.jianguan.business.onlineForms.mapper.PubCheckReportMapper;
import com.ruoyi.jianguan.business.onlineForms.service.IPubCheckReportService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 评定填报关联信息Service业务层处理
 *
 * @author mickey
 * @date 2024-01-16
 */
@RequiredArgsConstructor
@Service
public class PubCheckReportServiceImpl implements IPubCheckReportService {

    private final PubCheckReportMapper baseMapper;
    private final PubProduceDocumentMapper pubProduceDocumentMapper;
    private final IOnlineFormsService onlineFormsService;
    @Autowired
    private ConponentDAO conponentDAO;

    /**
     * 查询评定填报关联信息
     */
    @Override
    public PubCheckReportVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询评定填报关联信息列表
     */
    @Override
    public TableDataInfo<PubCheckReportVo> queryPageList(PubCheckReportBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PubCheckReport> lqw = buildQueryWrapper(bo);
        Page<PubCheckReportVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询评定填报关联信息列表
     */
    @Override
    public List<PubCheckReportVo> queryList(PubCheckReportBo bo) {
        LambdaQueryWrapper<PubCheckReport> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PubCheckReport> buildQueryWrapper(PubCheckReportBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PubCheckReport> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCheckComponentId() != null, PubCheckReport::getCheckComponentId, bo.getCheckComponentId());
        lqw.eq(bo.getCheckProduceId() != null, PubCheckReport::getCheckProduceId, bo.getCheckProduceId());
        lqw.eq(bo.getReportComponentId() != null, PubCheckReport::getReportComponentId, bo.getReportComponentId());
        lqw.eq(bo.getReportProduceId() != null, PubCheckReport::getReportProduceId, bo.getReportProduceId());
        lqw.eq(bo.getScxmStatus() != null, PubCheckReport::getScxmStatus, bo.getScxmStatus());
        lqw.eq(bo.getWgzlStatus() != null, PubCheckReport::getWgzlStatus, bo.getWgzlStatus());
        lqw.eq(bo.getZlwzxStatus() != null, PubCheckReport::getZlwzxStatus, bo.getZlwzxStatus());
        lqw.eq(bo.getCheckResult() != null, PubCheckReport::getCheckResult, bo.getCheckResult());
        lqw.eq(bo.getStatus() != null, PubCheckReport::getStatus, bo.getStatus());
        lqw.eq(bo.getReportTime() != null, PubCheckReport::getReportTime, bo.getReportTime());
        return lqw;
    }

    /**
     * 新增评定填报关联信息
     */
    @Override
    public Boolean insertByBo(PubCheckReportBo bo) {
        PubCheckReport add = BeanUtil.toBean(bo, PubCheckReport.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改评定填报关联信息
     */
    @Override
    public Boolean updateByBo(PubCheckReportBo bo) {
        PubCheckReport update = BeanUtil.toBean(bo, PubCheckReport.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PubCheckReport entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除评定填报关联信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 校验主键是否唯一评定填报关联信息信息
     */
    @Override
    public Boolean checkUniqueByPrimaryKey(Long id) {
        int count = baseMapper.selectCountByPrimaryKey(id);
        return count <= 1;
    }

    @Override
    public PubCheckReportVo loadOnlineCheckReport(Integer componentId) throws IOException {
        // 查询评定、报验关联数据
        PubCheckReport pubCheckReport = baseMapper.selectVoOne(new LambdaQueryWrapper<PubCheckReport>()
                .eq(!Objects.isNull(componentId), PubCheckReport::getCheckComponentId, componentId), PubCheckReport.class);

        if (Objects.isNull(pubCheckReport)) {
            // 没查到，则新增相关信息
            pubCheckReport = new PubCheckReport();

            baseMapper.insertOrUpdate(pubCheckReport);
        } else {
            // 查到了，则继续相关数据更新
            Map documentQueryMap = Maps.newHashMap();
            documentQueryMap.put("produce_id",pubCheckReport.getReportProduceId());
            List<PubProduceDocument> pubProduceDocumentList = pubProduceDocumentMapper.selectByMap(documentQueryMap);
            pubProduceDocumentList = pubProduceDocumentList.stream().sorted(Comparator.comparingLong(PubProduceDocument::getId)).collect(Collectors.toList());

            Conponent component = conponentDAO.selectByPrimaryKey(componentId);
            switch (component.getConponenttype()) {
                case "HTMPD_GJ":
                    // 钢筋评定
                    int[] rowNumbers = new int[]{12, 13};
                    List<List<String>> contentsDataList = Lists.newArrayList();
                    for (PubProduceDocument pubProduceDocument : pubProduceDocumentList) {
                        contentsDataList.addAll(onlineFormsService.getWorkbookContents(pubProduceDocument, rowNumbers));
                    }
                    System.err.println("=========================" + contentsDataList.size() + "=========================");
                   break;
                case "HTMPD_FXGC":
                    // 分项工程评定
                    break;
                default:
                    System.out.println("评定构件类型不匹配");
            }
        }
        return BeanUtil.toBean(pubCheckReport, PubCheckReportVo.class);
    }


}
