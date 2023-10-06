package com.ruoyi.jianguan.business.quality.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.dao.SsFUsersDAO;
import com.ruoyi.common.utils.MyPageUtil;
import com.ruoyi.jianguan.business.quality.domain.dto.ManageRegimeSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.ManageRegime;
import com.ruoyi.jianguan.business.quality.domain.vo.ManageRegimeDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.ManageRegimePageVo;
import com.ruoyi.jianguan.business.quality.mapper.ManageRegimeMapper;
import com.ruoyi.common.core.domain.dto.PageDTO;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.jianguan.business.quality.service.ManageRegimeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.sequence.util.IdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * 管理制度 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-05-13
 */
@Service
public class ManageRegimeServiceImpl extends ServiceImpl<ManageRegimeMapper, ManageRegime> implements ManageRegimeService {

    @Autowired
    private ManageRegimeMapper manageRegimeMapper;

    @Autowired
    private SsFUsersDAO ssFUsersDAO;

    /**
     * 新增或者更新管理制度数据
     *
     * @param saveDto
     * @return
     */
    @Override
    public boolean addOrUpdate(ManageRegimeSaveDTO saveDto) {
        //属性转换
        ManageRegime manageRegime = new ManageRegime();
        BeanUtils.copyProperties(saveDto, manageRegime);
        //制度文件
        manageRegime.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        //新增
        if (Objects.isNull(saveDto.getId())) {
            manageRegime.setId(IdUtil.nextLongId());
        }
        return this.saveOrUpdate(manageRegime);
    }

    /**
     * 通过id获取一条管理制度数据
     *
     * @param id
     * @return
     */
    @Override
    public ManageRegimeDetailVo getInfoById(Long id) {
        //查询
        ManageRegime manageRegime = this.getById(id);
        if (Objects.isNull(manageRegime)) {
            return null;
        }
        //属性转换
        ManageRegimeDetailVo vo = new ManageRegimeDetailVo();
        BeanUtils.copyProperties(manageRegime, vo);
        //制度文件
        vo.setAttachment(JSONArray.parseArray(manageRegime.getAttachment(), FileModel.class));
        return vo;
    }

    /**
     * 分页查询管理制度数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<ManageRegimePageVo> getPageInfo(PageDTO pageDto) {
        //查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<ManageRegimePageVo> manageRegimes = manageRegimeMapper.getPageInfo(pageDto);
        //判断
        if (Objects.nonNull(manageRegimes) && !manageRegimes.isEmpty()) {
            manageRegimes.forEach(manageRegime -> {
                //数据状态
                manageRegime.setDeletedFlagStr("正常");
            });
        }
        return MyPageUtil.getPageInfo(manageRegimes.stream()
                .sorted(Comparator.comparing(ManageRegimePageVo::getCompileDate).reversed()), manageRegimes.size(), pageDto.getPageSize(), pageDto.getPageNum());
    }

    /**
     * 管理制度导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(PageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<ManageRegimePageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("compileDeptName", "编制单位");
        writer.addHeaderAlias("compileUser", "编制人");
        writer.addHeaderAlias("compileDate", "编制日期");
        writer.addHeaderAlias("regimeContent", "制度内容");
        writer.addHeaderAlias("updateTime", "更新时间");
        writer.addHeaderAlias("deletedFlagStr", "数据状态");
        writer.write(pageInfo.getList(), true);
        writer.merge(5, "管理制度");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("管理制度");
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
