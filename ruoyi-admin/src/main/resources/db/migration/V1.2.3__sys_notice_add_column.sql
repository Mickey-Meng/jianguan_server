
ALTER table sys_notice add    receive_id  varchar(32) DEFAULT NULL COMMENT '接收者id';
ALTER table sys_notice add    receive_name  varchar(32) DEFAULT NULL COMMENT '接收者名称';
ALTER table sys_notice add    read_status  varchar(32) DEFAULT NULL COMMENT '阅读状态  1:已读   0：未读';
ALTER table sys_notice add    read_time  datetime  COMMENT '阅读时间';
