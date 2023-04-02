package com.ruoyi.flowable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.dao.SsFUsersDAO;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.flowable.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<SsFUsersDAO, SsFUsers> implements UserService {

    @Autowired
    private SsFUsersDAO usersDAO;

    /**
     * 查询用户名通过ids
     *
     * @param userIds
     * @return
     */
    @Override
    public Map<Integer, String> getUserNamesByIds(List<Integer> userIds) {
        List<SsFUsers> users = usersDAO.getUserNamesByIds(userIds);
        return Objects.requireNonNull(users).stream().collect(Collectors.toMap(SsFUsers::getId, SsFUsers::getUsername));
    }

    @Override
    public List<SsFUsers> getUsersByIds(List<Integer> userIds) {
        return usersDAO.getUserNamesByIds(userIds);
    }

    /**
     * 查询用户显示名通过登录名称
     *
     * @param userNames
     * @return
     */
    @Override
    public Map<String, String> getNamesByUserName(List<String> userNames) {
        List<SsFUsers> users = usersDAO.getNamesByUserName(userNames);
        return Objects.requireNonNull(users).stream().collect(Collectors.toMap(SsFUsers::getUsername, SsFUsers::getName));
    }
}
