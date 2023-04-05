package com.ruoyi.jianguan.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.contract.domain.dto.EnterExitUserPageDTO;
import com.ruoyi.jianguan.contract.domain.entity.EnterExitUser;
import com.ruoyi.jianguan.contract.mapper.EnterExitUserMapper;
import com.ruoyi.jianguan.contract.service.EnterExitUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 进退场人员 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-05-28
 */
@Service
public class EnterExitUserServiceImpl extends ServiceImpl<EnterExitUserMapper, EnterExitUser> implements EnterExitUserService {


    @Autowired
    private EnterExitUserMapper enterExitUserMapper;

    /**
     * 获取进出场人员清单通过进出场id
     *
     * @param id
     * @return
     */
    @Override
    public List<EnterExitUser> getUserByEnterId(Long id) {
        LambdaQueryWrapper<EnterExitUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EnterExitUser::getEnterExitId, id);
        return this.list(queryWrapper);
    }

    /**
     * 分页查询人员一览
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<EnterExitUser> getPageInfo(EnterExitUserPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<EnterExitUser> enterExitUsers = enterExitUserMapper.getPageInfo(pageDto);
        return new PageInfo<>(enterExitUsers);
    }

    /**
     * 人员批量退场
     *
     * @param userIds
     * @return
     */
    @Override
    public ResponseBase exitUsers(List<Long> userIds) {
        if (Objects.isNull(userIds) || userIds.isEmpty()){
            return ResponseBase.error("需要选中人员！");
        }
        //批量更新
        EnterExitUser user = new EnterExitUser();
        user.setType(1);
        return ResponseBase.success(this.update(user,new LambdaQueryWrapper<EnterExitUser>().in(EnterExitUser::getId,userIds)));
    }
}
