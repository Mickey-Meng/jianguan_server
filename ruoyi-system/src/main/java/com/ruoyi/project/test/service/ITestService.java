package com.ruoyi.project.test.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.test.domain.bo.TestBo;
import com.ruoyi.project.test.domain.vo.TestVo;

import java.util.Collection;
import java.util.List;

/**
 * 测试单Service接口
 *
 * @author ruoyi
 * @date 2022-12-02
 */
public interface ITestService {

    /**
     * 查询测试单
     */
    TestVo queryById(Long id);

    /**
     * 查询测试单列表
     */
    TableDataInfo<TestVo> queryPageList(TestBo bo, PageQuery pageQuery);

    /**
     * 查询测试单列表
     */
    List<TestVo> queryList(TestBo bo);

    /**
     * 新增测试单
     */
    Boolean insertByBo(TestBo bo);

    /**
     * 修改测试单
     */
    Boolean updateByBo(TestBo bo);

    /**
     * 校验并批量删除测试单信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
