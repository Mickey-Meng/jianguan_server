package com.ruoyi.web.controller.ql;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.ql.domain.bo.QlContractGoodsRelBo;
import com.ruoyi.ql.domain.vo.QlContractGoodsRelVo;
import com.ruoyi.ql.service.IQlContractGoodsRelService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 合同与商品关系
 *
 * @author ruoyi
 * @date 2023-05-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/contractGoodsRel/contractGoodsRel")
public class QlContractGoodsRelController extends BaseController {

    private final IQlContractGoodsRelService iQlContractGoodsRelService;

    /**
     * 查询合同与商品关系列表
     */
    @GetMapping("/list")
    public TableDataInfo<QlContractGoodsRelVo> list(QlContractGoodsRelBo bo, PageQuery pageQuery) {
        return iQlContractGoodsRelService.queryPageList(bo, pageQuery);
    }

    /**
     * 分页查询合同与商品关系列表
     */
    @GetMapping("/page")

    public TableDataInfo<QlContractGoodsRelVo> page(QlContractGoodsRelBo bo, PageQuery pageQuery) {
        return iQlContractGoodsRelService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出合同与商品关系列表
     */
    @Log(title = "合同与商品关系" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlContractGoodsRelBo bo, HttpServletResponse response) {
        List<QlContractGoodsRelVo> list = iQlContractGoodsRelService.queryList(bo);
        ExcelUtil.exportExcel(list, "合同与商品关系" , QlContractGoodsRelVo.class, response);
    }

    /**
     * 获取合同与商品关系详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public R<QlContractGoodsRelVo> getInfo(@NotNull(message = "主键不能为空")
                                           @PathVariable Long id) {
        return R.ok(iQlContractGoodsRelService.queryById(id));
    }

    /**
     * 新增合同与商品关系
     */
    @Log(title = "合同与商品关系" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlContractGoodsRelBo bo) {
        return toAjax(iQlContractGoodsRelService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改合同与商品关系
     */
    @Log(title = "合同与商品关系" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlContractGoodsRelBo bo) {
        return toAjax(iQlContractGoodsRelService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除合同与商品关系
     *
     * @param ids 主键串
     */
    @Log(title = "合同与商品关系" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlContractGoodsRelService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}