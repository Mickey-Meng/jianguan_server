package com.ruoyi.project.flowidatenfo.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工作流单数据业务对象 mea_flow_data_info
 *
 * @author ruoyi
 * @date 2022-12-10
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaFlowDataInfoBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 业务类型
     */
    private String bussinessKey;

    /**
     * 业务数据  
     */
    private String bussinessData;

    /**
     * 状态（0正常 1停用）
     */
    private String status;


}
