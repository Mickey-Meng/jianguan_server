CREATE TABLE `zz_contract_payment`
(
    `id`          BIGINT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `type`        VARCHAR(50)    DEFAULT NULL COMMENT '款项类型',
    `amount`      DECIMAL(20, 2) DEFAULT NULL COMMENT '款项金额',
    `record_time` datetime       DEFAULT NULL COMMENT '填报日期',
    `remark`      VARCHAR(500)   DEFAULT NULL COMMENT '备注',
    `attachment`  json           DEFAULT NULL COMMENT '附件',
    `status`      INT ( 1 ) DEFAULT '0' COMMENT '状态 0 审批中 1 审批完成 2 驳回',
    `del_flag`    CHAR(1)        DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`   VARCHAR(64)    DEFAULT '' COMMENT '创建者',
    `create_time` datetime       DEFAULT NULL COMMENT '创建时间',
    `update_by`   VARCHAR(64)    DEFAULT '' COMMENT '更新者',
    `update_time` datetime       DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '合同付款';