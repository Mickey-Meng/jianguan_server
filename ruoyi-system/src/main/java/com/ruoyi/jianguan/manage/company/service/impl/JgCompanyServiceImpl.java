package com.ruoyi.jianguan.manage.company.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.jianguan.manage.company.domain.JgCompany;
import com.ruoyi.jianguan.manage.company.domain.bo.JgCompanyBo;
import com.ruoyi.jianguan.manage.company.domain.vo.JgCompanyVo;
import com.ruoyi.jianguan.manage.company.mapper.JgCompanyMapper;
import com.ruoyi.jianguan.manage.company.service.IJgCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 公司信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-16
 */
@RequiredArgsConstructor
@Service
public class JgCompanyServiceImpl implements IJgCompanyService {

    private final JgCompanyMapper baseMapper;

    /**
     * 查询公司信息
     */
    @Override
    public JgCompanyVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询公司信息列表
     */
    @Override
    public TableDataInfo<JgCompanyVo> queryPageList(JgCompanyBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<JgCompany> lqw = buildQueryWrapper(bo);
        Page<JgCompanyVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询公司信息列表
     */
    @Override
    public List<JgCompanyVo> queryList(JgCompanyBo bo) {
        LambdaQueryWrapper<JgCompany> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<JgCompany> buildQueryWrapper(JgCompanyBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<JgCompany> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getCompanyName()), JgCompany::getCompanyName, bo.getCompanyName());
        lqw.eq(StringUtils.isNotBlank(bo.getCompanyCode()), JgCompany::getCompanyCode, bo.getCompanyCode());
        lqw.like(StringUtils.isNotBlank(bo.getTypeName()), JgCompany::getTypeName, bo.getTypeName());
        lqw.eq(StringUtils.isNotBlank(bo.getTypeCode()), JgCompany::getTypeCode, bo.getTypeCode());
        lqw.eq(StringUtils.isNotBlank(bo.getLegalPerson()), JgCompany::getLegalPerson, bo.getLegalPerson());
        lqw.eq(StringUtils.isNotBlank(bo.getLegalPhone()), JgCompany::getLegalPhone, bo.getLegalPhone());
        lqw.eq(StringUtils.isNotBlank(bo.getBaseData()), JgCompany::getBaseData, bo.getBaseData());
        lqw.eq(StringUtils.isNotBlank(bo.getDutyNum()), JgCompany::getDutyNum, bo.getDutyNum());
        lqw.eq(StringUtils.isNotBlank(bo.getEnclosure()), JgCompany::getEnclosure, bo.getEnclosure());
        return lqw;
    }

    /**
     * 新增公司信息
     */
    @Override
    public Boolean insertByBo(JgCompanyBo bo) {
        JgCompany add = BeanUtil.toBean(bo, JgCompany.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改公司信息
     */
    @Override
    public Boolean updateByBo(JgCompanyBo bo) {
        JgCompany update = BeanUtil.toBean(bo, JgCompany.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(JgCompany entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除公司信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
