package com.ruoyi.flowable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.domain.entity.SsFUsers;

import java.util.List;
import java.util.Map;

/**
 * 用户 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-11
 */
public interface UserService extends IService<SsFUsers> {
    /**
     * 查询用户名通过ids
     *
     * @param userIds
     * @return
     */
    Map<Integer, String> getUserNamesByIds(List<Integer> userIds);


    /**
     * 查询用户名通过ids
     *
     * @param userIds
     * @return
     */
    List<SsFUsers> getUsersByIds(List<Integer> userIds);

    /**
     * 查询用户显示名通过登录名称
     *
     * @param userNames
     * @return
     */
    Map<String, String> getNamesByUserName(List<String> userNames);
}
