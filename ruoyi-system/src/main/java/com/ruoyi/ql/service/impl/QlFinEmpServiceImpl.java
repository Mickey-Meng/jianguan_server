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
import com.ruoyi.ql.domain.bo.QlFinEmpBo;
import com.ruoyi.ql.domain.vo.QlFinEmpVo;
import com.ruoyi.ql.domain.QlFinEmp;
import com.ruoyi.ql.mapper.QlFinEmpMapper;
import com.ruoyi.ql.service.IQlFinEmpService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 员工信息管理Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlFinEmpServiceImpl implements IQlFinEmpService {

    private final QlFinEmpMapper baseMapper;

    /**
     * 查询员工信息管理
     */
    @Override
    public QlFinEmpVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询员工信息管理列表
     */
    @Override
    public TableDataInfo<QlFinEmpVo> queryPageList(QlFinEmpBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlFinEmp> lqw = buildQueryWrapper(bo);
        Page<QlFinEmpVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询员工信息管理列表
     */
    @Override
    public List<QlFinEmpVo> queryList(QlFinEmpBo bo) {
        LambdaQueryWrapper<QlFinEmp> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlFinEmp> buildQueryWrapper(QlFinEmpBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlFinEmp> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getEmpName()), QlFinEmp::getEmpName, bo.getEmpName());
        lqw.eq(StringUtils.isNotBlank(bo.getEmpId()), QlFinEmp::getEmpId, bo.getEmpId());
        return lqw;
    }

    /**
     * 新增员工信息管理
     */
    @Override
    public Boolean insertByBo(QlFinEmpBo bo) {
        QlFinEmp add = BeanUtil.toBean(bo, QlFinEmp.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改员工信息管理
     */
    @Override
    public Boolean updateByBo(QlFinEmpBo bo) {
        QlFinEmp update = BeanUtil.toBean(bo, QlFinEmp.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlFinEmp entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除员工信息管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
