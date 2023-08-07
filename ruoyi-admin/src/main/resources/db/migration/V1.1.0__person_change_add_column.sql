
ALTER table jianguan3.zj_person_change add `create_user_id` int(11) DEFAULT NULL COMMENT '创建者Id';
ALTER table jianguan3.zj_person_change add `create_time` datetime DEFAULT NULL COMMENT '创建时间';
ALTER table jianguan3.zj_person_change add `update_user_id` int(11) DEFAULT NULL COMMENT '更新者Id';
ALTER table jianguan3.zj_person_change add `update_time` datetime DEFAULT NULL COMMENT '最后更新时间';