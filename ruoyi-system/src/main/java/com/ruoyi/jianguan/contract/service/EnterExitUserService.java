package com.ruoyi.jianguan.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.contract.domain.dto.EnterExitUserPageDTO;
import com.ruoyi.jianguan.contract.domain.entity.EnterExitUser;

import java.util.List;

/**
 * 进退场人员 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-28
 */
public interface EnterExitUserService extends IService<EnterExitUser> {
    /**
     * 获取进出场人员清单通过进出场id
     *
     * @param id
     * @return
     */
    List<EnterExitUser> getUserByEnterId(Long id);

    /**
     * 分页查询人员一览
     *
     * @param pageDto
     * @return
     */
    PageInfo<EnterExitUser> getPageInfo(EnterExitUserPageDTO pageDto);

    /**
     * 人员批量退场
     *
     * @param userIds
     * @return
     */
    ResponseBase exitUsers(List<Long> userIds);
}
