package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.MD5;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.ql.domain.QlShopGoodsType;
import com.ruoyi.ql.domain.vo.QlShopGoodsTypeVo;


import com.ruoyi.ql.domain.vo.TreeVo;
import com.ruoyi.ql.mapper.QlShopGoodsTypeMapper;
import liquibase.pro.packaged.T;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.ql.domain.bo.QlShopGoodsBo;
import com.ruoyi.ql.domain.vo.QlShopGoodsVo;
import com.ruoyi.ql.domain.QlShopGoods;
import com.ruoyi.ql.mapper.QlShopGoodsMapper;
import com.ruoyi.ql.service.IQlShopGoodsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlShopGoodsServiceImpl implements IQlShopGoodsService {

    private final QlShopGoodsMapper baseMapper;

    private final QlShopGoodsTypeMapper qlShopGoodsTypeMapper;


    /**
     * 查询商品信息
     */
    @Override
    public QlShopGoodsVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询商品信息列表
     */
    @Override
    public TableDataInfo<QlShopGoodsVo> queryPageList(QlShopGoodsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlShopGoods> lqw = buildQueryWrapper(bo);
        Page<QlShopGoodsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商品信息列表
     */
    @Override
    public List<QlShopGoodsVo> queryList(QlShopGoodsBo bo) {
        LambdaQueryWrapper<QlShopGoods> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlShopGoods> buildQueryWrapper(QlShopGoodsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlShopGoods> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getSupplierName()), QlShopGoods::getSupplierName, bo.getSupplierName());
        lqw.eq(bo.getGoodsTypeId() != null, QlShopGoods::getGoodsTypeId, bo.getGoodsTypeId());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsCode()), QlShopGoods::getGoodsCode, bo.getGoodsCode());
        lqw.like(StringUtils.isNotBlank(bo.getGoodsName()), QlShopGoods::getGoodsName, bo.getGoodsName());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsSearchstandard()), QlShopGoods::getGoodsSearchstandard, bo.getGoodsSearchstandard());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsUnit()), QlShopGoods::getGoodsUnit, bo.getGoodsUnit());
        return lqw;
    }

    /**
     * 新增商品信息
     */
    @Override
    public Boolean insertByBo(QlShopGoodsBo bo) {
        QlShopGoods add = BeanUtil.toBean(bo, QlShopGoods.class);
        String goodCode = MD5.create().digestHex(add.getSupplierId() + add.getGoodsName() + add.getGoodsSearchstandard());
        add.setGoodsCode(goodCode);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商品信息
     */
    @Override
    public Boolean updateByBo(QlShopGoodsBo bo) {
        QlShopGoods update = BeanUtil.toBean(bo, QlShopGoods.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlShopGoods entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商品信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public List<TreeVo> goodsTree() {
        LambdaQueryWrapper<QlShopGoodsType> lqw = new LambdaQueryWrapper<>();
        lqw.eq(QlShopGoodsType::getDelFlag, 0);
        List<QlShopGoodsTypeVo> qlShopGoodsTypeVos = qlShopGoodsTypeMapper.selectVoList(lqw);

        List<TreeVo> list = new ArrayList<>();

        for (QlShopGoodsTypeVo qlShopGoodsTypeVo : qlShopGoodsTypeVos) {

            Long parentId = qlShopGoodsTypeVo.getParentId();


            if (parentId == 0) {
                TreeVo treeVo = new TreeVo();
                treeVo.setLabel(qlShopGoodsTypeVo.getGoodsTypeName());
                treeVo.setValue(qlShopGoodsTypeVo.getId() + "");
                List<TreeVo> child = findChild(qlShopGoodsTypeVo.getId(), qlShopGoodsTypeVos);
                if (child.size()>0) {
                    treeVo.setChildren(child);
                }else {
                    treeVo.setChildren(null);
                }
                list.add(treeVo);
            }
        }

        return list;
    }

    List<TreeVo> findChild(Long id, List<QlShopGoodsTypeVo> qlShopGoodsTypeVos) {
        List<TreeVo> list = new ArrayList<>();

        for (QlShopGoodsTypeVo qlShopGoodsTypeVo : qlShopGoodsTypeVos) {
            if (qlShopGoodsTypeVo.getParentId().equals(id)) {
                TreeVo treeVo = new TreeVo();
                treeVo.setLabel(qlShopGoodsTypeVo.getGoodsTypeName());
                treeVo.setValue(qlShopGoodsTypeVo.getId() + "");
                List<TreeVo> child = findChild(qlShopGoodsTypeVo.getId(), qlShopGoodsTypeVos);
                if (child.size()>0) {
                    treeVo.setChildren(child);
                }else {
                    treeVo.setChildren(null);
                }

                list.add(treeVo);
            }
        }
        return list;
    }
}
