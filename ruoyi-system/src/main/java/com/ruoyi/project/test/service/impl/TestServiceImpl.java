package com.ruoyi.project.test.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.test.domain.Test;
import com.ruoyi.project.test.domain.bo.TestBo;
import com.ruoyi.project.test.domain.vo.TestVo;
import com.ruoyi.project.test.mapper.TestMapper;
import com.ruoyi.project.test.service.ITestService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 测试单Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-02
 */
@RequiredArgsConstructor
@Service
public class TestServiceImpl implements ITestService {

    private final TestMapper baseMapper;

    /**
     * 查询测试单
     */
    @Override
    public TestVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询测试单列表
     */
    @Override
    public TableDataInfo<TestVo> queryPageList(TestBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Test> lqw = buildQueryWrapper(bo);
        Page<TestVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询测试单列表
     */
    @Override
    public List<TestVo> queryList(TestBo bo) {
        LambdaQueryWrapper<Test> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Test> buildQueryWrapper(TestBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Test> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), Test::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Test::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增测试单
     */
    @Override
    public Boolean insertByBo(TestBo bo) {
        Test add = BeanUtil.toBean(bo, Test.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改测试单
     */
    @Override
    public Boolean updateByBo(TestBo bo) {
        Test update = BeanUtil.toBean(bo, Test.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Test entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除测试单
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
