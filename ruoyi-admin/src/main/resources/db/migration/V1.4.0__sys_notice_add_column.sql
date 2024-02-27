alter table sys_notice add column business_id bigint default null comment '业务Id';
alter table sys_notice add column business_type varchar(128) default null comment '业务类型';