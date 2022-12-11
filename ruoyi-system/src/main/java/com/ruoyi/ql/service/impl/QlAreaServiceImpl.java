package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.ql.domain.bo.QlAreaBo;
import com.ruoyi.ql.domain.vo.QlAreaVo;
import com.ruoyi.ql.domain.QlArea;
import com.ruoyi.ql.mapper.QlAreaMapper;
import com.ruoyi.ql.service.IQlAreaService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 省市区Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlAreaServiceImpl implements IQlAreaService {

    private final QlAreaMapper baseMapper;

    /**
     * 查询省市区
     */
    @Override
    public QlAreaVo queryById(Long Id){
        return baseMapper.selectVoById(Id);
    }


    /**
     * 查询省市区列表
     */
    @Override
    public List<QlAreaVo> queryList(QlAreaBo bo) {
        LambdaQueryWrapper<QlArea> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlArea> buildQueryWrapper(QlAreaBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlArea> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), QlArea::getName, bo.getName());
        lqw.eq(bo.getPid() != null, QlArea::getPid, bo.getPid());
        return lqw;
    }

    /**
     * 新增省市区
     */
    @Override
    public Boolean insertByBo(QlAreaBo bo) {
        QlArea add = BeanUtil.toBean(bo, QlArea.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改省市区
     */
    @Override
    public Boolean updateByBo(QlAreaBo bo) {
        QlArea update = BeanUtil.toBean(bo, QlArea.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlArea entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除省市区
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
