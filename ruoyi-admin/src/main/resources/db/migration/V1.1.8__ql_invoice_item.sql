CREATE TABLE `ql_invoice_item`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sourceId`       bigint(50) DEFAULT NULL COMMENT '来源Id',
    `sourceType`     varchar(255)   DEFAULT NULL COMMENT '来源类型',
    `code`           varchar(255)   DEFAULT NULL COMMENT '单号',
    `contractCode`   varchar(255)   DEFAULT NULL COMMENT '合同编码',
    `amount`         decimal(15, 2) DEFAULT NULL COMMENT '金额',
    `status`         int(1) DEFAULT '0' COMMENT '状态 0 审批中 1 审批完成 2 驳回',
    `del_flag`       char(1)        DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_user_id` int(11) DEFAULT NULL COMMENT '创建者Id',
    `create_time`    datetime       DEFAULT NULL COMMENT '创建时间',
    `update_user_id` int(11) DEFAULT NULL COMMENT '更新者Id',
    `update_time`    datetime       DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE
);