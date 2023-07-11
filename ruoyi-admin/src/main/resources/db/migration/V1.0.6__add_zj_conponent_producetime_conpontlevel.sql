ALTER table zj_conponent_producetime add COLUMN   `conponentlevel` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '构件级别，暂时用于距“计划完成日”前多少告警提示的计算';
