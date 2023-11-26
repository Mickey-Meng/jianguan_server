package com.ruoyi.common.constant;

import cn.hutool.core.util.StrUtil;

/**
 * 审批状态
 *
 * @author G.X.L
 * @since 1.0
 * @date 2023-0405
 */
public enum ApprovalStatusEnum {

    /**
     * 驳回
     */

    REJECT("REJECT", "驳回"),
    PENDING("PENDING", "待审批"),
    APPROVING("APPROVING", "审批中"),
    APPROVED("APPROVED", "已审批");

    private String status;

    private String desc;

    ApprovalStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static String getDesc(String status) throws IllegalArgumentException {
        if(StrUtil.isBlank(status)) {
            throw new IllegalArgumentException("审核状态为空，不能获取审核状态描述");
        }
        for (ApprovalStatusEnum approvalStatusEnum : ApprovalStatusEnum.values()) {
            if(approvalStatusEnum.status.equals(status)) {
                return approvalStatusEnum.desc;
            }
        }
        throw new IllegalArgumentException("status： "+ status +"未匹配到审批状态");
    }
}
