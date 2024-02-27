package com.ruoyi.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.domain.bo.SysNoticeBo;
import com.ruoyi.system.mapper.SysNoticeMapper;
import com.ruoyi.system.service.ISysNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 公告 服务层实现
 *
 * @author Lion Li
 */
@RequiredArgsConstructor
@Service
public class SysNoticeServiceImpl implements ISysNoticeService {

    private final SysNoticeMapper baseMapper;

    @Override
    public TableDataInfo<SysNotice> selectPageNoticeList(SysNoticeBo notice, PageQuery pageQuery, Boolean b) {

        LambdaQueryWrapper<SysNotice> lqw = new LambdaQueryWrapper<SysNotice>()
            .like(StringUtils.isNotBlank(notice.getNoticeTitle()), SysNotice::getNoticeTitle, notice.getNoticeTitle())
            .eq(StringUtils.isNotBlank(notice.getNoticeType()), SysNotice::getNoticeType, notice.getNoticeType())
                //receiveId=0,默认为全部用户
                .in( true!= b  ,SysNotice::getReceiveId, 0,notice.getReceiveId())
                .eq(StringUtils.isNotBlank(notice.getReadStatus()), SysNotice::getReadStatus, notice.getReadStatus())
                .lt(null !=notice.getExpirationDate(), SysNotice::getExpirationDate, new Date())
                .like(StringUtils.isNotBlank(notice.getCreateBy()), SysNotice::getCreateBy, notice.getCreateBy())
                .eq(StrUtil.isNotBlank(notice.getBusinessType()), SysNotice::getBusinessType, notice.getBusinessType())
                .in(CollUtil.isNotEmpty(notice.getBusinessIds()), SysNotice::getBusinessId, notice.getBusinessIds());
        Page<SysNotice> page = baseMapper.selectPage(pageQuery.build(), lqw);
        return TableDataInfo.build(page);
    }

    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId) {
        return baseMapper.selectById(noticeId);
    }

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNoticeBo notice) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysNotice>()
            .like(StringUtils.isNotBlank(notice.getNoticeTitle()), SysNotice::getNoticeTitle, notice.getNoticeTitle())
            .eq(StringUtils.isNotBlank(notice.getNoticeType()), SysNotice::getNoticeType, notice.getNoticeType())
            .like(StringUtils.isNotBlank(notice.getCreateBy()), SysNotice::getCreateBy, notice.getCreateBy())

         .like(StringUtils.isNotBlank(notice.getReceiveName()), SysNotice::getReceiveName, notice.getReceiveName())
         .eq(StringUtils.isNotBlank(notice.getReadStatus()), SysNotice::getReadStatus, notice.getReadStatus())
         .eq(notice.getReadTime() != null, SysNotice::getReadTime, notice.getReadTime())
                .eq(StrUtil.isNotBlank(notice.getBusinessType()), SysNotice::getBusinessType, notice.getBusinessType())
                .in(CollUtil.isNotEmpty(notice.getBusinessIds()), SysNotice::getBusinessId, notice.getBusinessIds())
        );
    }

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNotice notice) {
        return baseMapper.insert(notice);
    }

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice) {
        return baseMapper.updateById(notice);
    }

    @Override
    public int updateNoticeReadStatus(Long noticeId) {
        return baseMapper.updateNoticeReadStatus(noticeId);
    }

    /**
     * 删除公告对象
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId) {
        return baseMapper.deleteById(noticeId);
    }

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds) {
        return baseMapper.deleteBatchIds(Arrays.asList(noticeIds));
    }
}
