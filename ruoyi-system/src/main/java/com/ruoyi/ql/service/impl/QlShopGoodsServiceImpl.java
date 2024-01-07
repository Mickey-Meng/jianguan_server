package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.ql.domain.QlShopGoods;
import com.ruoyi.ql.domain.QlShopGoodsType;
import com.ruoyi.ql.domain.bo.QlContractGoodsRelBo;
import com.ruoyi.ql.domain.bo.QlShopGoodsBo;
import com.ruoyi.ql.domain.bo.QlWarehousingDetailBo;
import com.ruoyi.ql.domain.vo.*;
import com.ruoyi.ql.mapper.QlShopGoodsMapper;
import com.ruoyi.ql.mapper.QlShopGoodsTypeMapper;
import com.ruoyi.ql.service.IQlContractGoodsRelService;
import com.ruoyi.ql.service.IQlShopGoodsService;
import com.ruoyi.ql.service.IQlWarehousingDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private final IQlWarehousingDetailService warehousingDetailService;

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
        lqw.eq(bo.getSupplierId()!= null, QlShopGoods::getSupplierId, bo.getSupplierId());
        lqw.like(StringUtils.isNotBlank(bo.getSupplierName()), QlShopGoods::getSupplierName, bo.getSupplierName());
        lqw.eq(bo.getGoodsTypeId() != null, QlShopGoods::getGoodsTypeId, bo.getGoodsTypeId());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsCode()), QlShopGoods::getGoodsCode, bo.getGoodsCode());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsBrand()), QlShopGoods::getGoodsBrand, bo.getGoodsBrand());
        lqw.like(StringUtils.isNotBlank(bo.getGoodsName()), QlShopGoods::getGoodsName, bo.getGoodsName());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsSearchstandard()), QlShopGoods::getGoodsSearchstandard, bo.getGoodsSearchstandard());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsUnit()), QlShopGoods::getGoodsUnit, bo.getGoodsUnit());
        lqw.eq(ObjectUtil.isNotNull(bo.getId()), QlShopGoods::getId, bo.getId());
        return lqw;
    }

    /**
     * 新增商品信息
     */
    @Override
    public Boolean insertByBo(QlShopGoodsBo bo) {
        QlShopGoods add = BeanUtil.toBean(bo, QlShopGoods.class);
      /*  String goodCode = MD5.create().digestHex(add.getSupplierId() + add.getGoodsName() + add.getGoodsSearchstandard());
        add.setGoodsCode(goodCode);*/
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
//            已用于采购合同/销售合同/入库单/出库单的产品，不允许删除
            QlWarehousingDetailBo qlWarehousingDetailBo =  new QlWarehousingDetailBo();
            qlWarehousingDetailBo.setGoodsIds(ids.stream().map(String::valueOf).collect(Collectors.toList()));
            List<QlWarehousingDetailVo> qlWarehousingDetailVos = warehousingDetailService.queryList(qlWarehousingDetailBo);
            if(CollUtil.isNotEmpty(qlWarehousingDetailVos)) {
                throw new ServiceException("已用于采购合同/销售合同/入库单/出库单的产品，不允许删除");
            }
            IQlContractGoodsRelService contractGoodsRelService = ApplicationContextHolder.getBean(IQlContractGoodsRelService.class);
            QlContractGoodsRelBo qlContractGoodsRelBo = new QlContractGoodsRelBo();
            qlContractGoodsRelBo.setGoodsIds(new ArrayList<>(ids));
            List<QlContractGoodsRelVo> qlContractGoodsRelVos = contractGoodsRelService.queryList(qlContractGoodsRelBo);
            if(CollUtil.isNotEmpty(qlContractGoodsRelVos)) {
                throw new ServiceException("已用于采购合同/销售合同/入库单/出库单的产品，不允许删除");
            }
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
