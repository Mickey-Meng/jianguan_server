CREATE TABLE `daily_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(255) DEFAULT NULL COMMENT '晨检内容',
  `report_people` varchar(20) DEFAULT NULL COMMENT '上报人',
  `report_time` datetime DEFAULT NULL COMMENT '上报时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `attachment` json DEFAULT NULL COMMENT '附件',
  `status` int(1) DEFAULT '0' COMMENT '状态 0 审批中 1 审批完成 2 驳回',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建者Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新者Id',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='每日晨报'
