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
    `create_user_id` int(11) DEFAULT NULL COMMENT '创建者Id',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_user_id` int(11) DEFAULT NULL COMMENT '更新者Id',
    `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
    `project_id` int(11) DEFAULT NULL COMMENT '项目id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '合同付款';