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
import com.ruoyi.ql.domain.bo.QlFinWagesBo;
import com.ruoyi.ql.domain.vo.QlFinWagesVo;
import com.ruoyi.ql.domain.QlFinWages;
import com.ruoyi.ql.mapper.QlFinWagesMapper;
import com.ruoyi.ql.service.IQlFinWagesService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 工资管理Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlFinWagesServiceImpl implements IQlFinWagesService {

    private final QlFinWagesMapper baseMapper;

    /**
     * 查询工资管理
     */
    @Override
    public QlFinWagesVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询工资管理列表
     */
    @Override
    public TableDataInfo<QlFinWagesVo> queryPageList(QlFinWagesBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlFinWages> lqw = buildQueryWrapper(bo);
        Page<QlFinWagesVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询工资管理列表
     */
    @Override
    public List<QlFinWagesVo> queryList(QlFinWagesBo bo) {
        LambdaQueryWrapper<QlFinWages> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlFinWages> buildQueryWrapper(QlFinWagesBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlFinWages> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPayDate() != null, QlFinWages::getPayDate, bo.getPayDate());
        lqw.like(StringUtils.isNotBlank(bo.getEmpName()), QlFinWages::getEmpName, bo.getEmpName());
        return lqw;
    }

    /**
     * 新增工资管理
     */
    @Override
    public Boolean insertByBo(QlFinWagesBo bo) {
        QlFinWages add = BeanUtil.toBean(bo, QlFinWages.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改工资管理
     */
    @Override
    public Boolean updateByBo(QlFinWagesBo bo) {
        QlFinWages update = BeanUtil.toBean(bo, QlFinWages.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlFinWages entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除工资管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
