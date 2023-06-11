package com.ruoyi.jianguan.business.quality.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.dao.SsFUsersDAO;
import com.ruoyi.common.core.domain.dto.PageDTO;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.jianguan.business.quality.domain.dto.ManageTargetSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.ManageTarget;
import com.ruoyi.jianguan.business.quality.domain.vo.ManageTargetDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.ManageTargetPageVo;
import com.ruoyi.jianguan.business.quality.mapper.ManageTargetMapper;
import com.ruoyi.jianguan.business.quality.service.ManageTargetService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

/**
 * 管理目标 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-05-13
 */
@Service
public class ManageTargetServiceImpl extends ServiceImpl<ManageTargetMapper, ManageTarget> implements ManageTargetService {

    @Autowired
    private ManageTargetMapper manageTargetMapper;

    @Autowired
    private SsFUsersDAO ssFUsersDAO;

    @Autowired
    private ISysUserService iSysUserService;

    /**
     * 新增或者更新管理目标数据
     *
     * @param saveDto
     * @return
     */
    @Override
    public boolean addOrUpdate(ManageTargetSaveDTO saveDto) {
        ManageTarget manageTarget = new ManageTarget();
        //属性copy
        BeanUtils.copyProperties(saveDto, manageTarget);
        //文件
        manageTarget.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        //新增
        if (Objects.isNull(saveDto.getId())) {
            manageTarget.setId(IdUtil.nextLongId());
        }
        return this.saveOrUpdate(manageTarget);
    }

    /**
     * 通过id获取一条管理目标数据
     *
     * @param id
     * @return
     */
    @Override
    public ManageTargetDetailVo getInfoById(Long id) {
        //查询
        ManageTarget manageTarget = this.getById(id);
        if (Objects.isNull(manageTarget)) {
            return null;
        }
        //属性转换
        ManageTargetDetailVo vo = new ManageTargetDetailVo();
        BeanUtils.copyProperties(manageTarget, vo);
        //创建人名称
        String name = ssFUsersDAO.getNameByUserId(manageTarget.getCreateUserId());
        vo.setCreateUserName(name);
        //文件
        vo.setAttachment(JSONArray.parseArray(manageTarget.getAttachment(), FileModel.class));
        return vo;
    }

    /**
     * 分页查询管理目标数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<ManageTargetPageVo> getPageInfo(PageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<ManageTargetPageVo> manageTargets = manageTargetMapper.getPageInfo(pageDto);
        if (Objects.nonNull(manageTargets) && !manageTargets.isEmpty()) {

            manageTargets.forEach(manageTarget -> {
//                SsFUsers ssFUsers = ssFUsersDAO.selectById(manageTarget.getCreateUserId());
                SysUser sysUser = iSysUserService.selectUserById(manageTarget.getCreateUserId().longValue());
                if (Objects.nonNull(sysUser)) {
                    manageTarget.setCreateName(sysUser.getUserName());
                }
            });
        }
        return new PageInfo(manageTargets);
    }

    /**
     * 管理目标导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(PageDTO pageDto, HttpServletResponse response)  {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<ManageTargetPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("title", "标题");
        writer.addHeaderAlias("createName", "登记人");
        writer.addHeaderAlias("publishDate", "发布时间");
        writer.addHeaderAlias("projectName", "登记部门");
        writer.write(pageInfo.getList(), true);
        writer.merge(3, "管理目标");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");

        String name = URLEncoder.encode("管理目标");
        response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xls");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        IoUtil.close(out);
    }
}
