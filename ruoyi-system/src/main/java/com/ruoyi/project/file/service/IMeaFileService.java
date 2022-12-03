package com.ruoyi.project.file.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.file.domain.bo.MeaFileBo;
import com.ruoyi.project.file.domain.vo.MeaFileVo;

import java.util.Collection;
import java.util.List;

/**
 * 附件记录Service接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface IMeaFileService {

    /**
     * 查询附件记录
     */
    MeaFileVo queryById(String fileId);

    /**
     * 查询附件记录列表
     */
    TableDataInfo<MeaFileVo> queryPageList(MeaFileBo bo, PageQuery pageQuery);

    /**
     * 查询附件记录列表
     */
    List<MeaFileVo> queryList(MeaFileBo bo);

    /**
     * 新增附件记录
     */
    Boolean insertByBo(MeaFileBo bo);

    /**
     * 修改附件记录
     */
    Boolean updateByBo(MeaFileBo bo);

    /**
     * 校验并批量删除附件记录信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
