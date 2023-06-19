CREATE TABLE `zz_contract_payment`
(
    `id`          BIGINT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `type`        VARCHAR(50)    DEFAULT NULL COMMENT '款项类型',
    `type_code`   VARCHAR(50)    DEFAULT NULL COMMENT '付款类型编码，数据字典id',
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


INSERT INTO `data_dictionary`(`id`, `parent_id`, `ancestors`, `code`, `name`, `order_num`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `dept_id`) VALUES (50, 0, NULL, 'FKLX', '付款类型', 1, '0', 'admin', '2023-06-14 19:59:46', 'admin', '2023-06-14 20:15:19', NULL, NULL);
INSERT INTO `data_dictionary`(`id`, `parent_id`, `ancestors`, `code`, `name`, `order_num`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `dept_id`) VALUES (51, 50, NULL, 'FKLX-AQWMGLF', '安全文明措施费', 1, '0', 'admin', '2023-06-14 20:02:02', 'admin', '2023-06-14 20:02:02', NULL, NULL);
INSERT INTO `data_dictionary`(`id`, `parent_id`, `ancestors`, `code`, `name`, `order_num`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `dept_id`) VALUES (52, 50, NULL, 'FKLX-ZBHTFK', '总包合同付款', 2, '0', 'admin', '2023-06-14 20:02:35', 'admin', '2023-06-14 20:02:35', NULL, NULL);
INSERT INTO `data_dictionary`(`id`, `parent_id`, `ancestors`, `code`, `name`, `order_num`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `dept_id`) VALUES (53, 50, NULL, 'FKLX-JLHTFK', '监理合同付款', 3, '0', 'admin', '2023-06-14 20:03:01', 'admin', '2023-06-14 20:03:01', NULL, NULL);
INSERT INTO `data_dictionary`(`id`, `parent_id`, `ancestors`, `code`, `name`, `order_num`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `dept_id`) VALUES (55, 50, NULL, 'FKLX-SJHTFK', '三检合同付款', 4, '0', 'admin', '2023-06-14 20:03:49', 'admin', '2023-06-14 20:03:49', NULL, NULL);
INSERT INTO `data_dictionary`(`id`, `parent_id`, `ancestors`, `code`, `name`, `order_num`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `dept_id`) VALUES (56, 50, NULL, 'FKLX-SJ-HTFK', '审计合同付款', 5, '0', 'admin', '2023-06-14 20:05:11', 'admin', '2023-06-14 20:05:11', NULL, NULL);
INSERT INTO `data_dictionary`(`id`, `parent_id`, `ancestors`, `code`, `name`, `order_num`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `dept_id`) VALUES (58, 50, NULL, 'FKLX-ZYFBHTFK', '专业分包合同付款', 6, '0', 'admin', '2023-06-14 20:05:51', 'admin', '2023-06-14 20:05:51', NULL, NULL);


INSERT INTO `data_dictionary`(`id`, `parent_id`, `ancestors`, `code`, `name`, `order_num`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `dept_id`) VALUES (59, 14, NULL, 'dsfjcdwzlgl', '第三方检测单位资料管理', 4, '0', 'admin', '2023-06-15 21:43:38', 'admin', '2023-06-15 21:43:38', NULL, NULL);
INSERT INTO `data_dictionary`(`id`, `parent_id`, `ancestors`, `code`, `name`, `order_num`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `dept_id`) VALUES (60, 59, NULL, 'dsfjcdwzlgl-day', '日', 1, '0', 'admin', '2023-06-15 21:44:13', 'admin', '2023-06-15 21:44:13', NULL, NULL);
INSERT INTO `data_dictionary`(`id`, `parent_id`, `ancestors`, `code`, `name`, `order_num`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `dept_id`) VALUES (61, 59, NULL, 'dsfjcdwzlgl-week', '周', 2, '0', 'admin', '2023-06-15 21:44:24', 'admin', '2023-06-15 21:44:48', NULL, NULL);
INSERT INTO `data_dictionary`(`id`, `parent_id`, `ancestors`, `code`, `name`, `order_num`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `dept_id`) VALUES (62, 59, NULL, 'dsfjcdwzlgl-month', '月', 3, '0', 'admin', '2023-06-15 21:45:00', 'admin', '2023-06-15 21:45:00', NULL, NULL);



alter table zj_file add `status`      INT ( 1 ) DEFAULT '0' COMMENT '状态 0 审批中 1 审批完成 2 驳回';