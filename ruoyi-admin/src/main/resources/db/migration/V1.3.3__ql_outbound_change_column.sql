ALTER table ql_warehousing  CHANGE   warehousing_userid  warehousing_user_id bigint(20)  COMMENT '入库对接人id';
ALTER table ql_warehousing  CHANGE   warehousing_releaseuserid warehousing_releaseuser_id bigint(20)  COMMENT '入库审核人id';

ALTER table ql_outbound  CHANGE   outbound_userid outbound_user_id bigint(20)  COMMENT '出库对接人id';
ALTER table ql_outbound  CHANGE   outbound_releaseuserid outbound_releaseuser_id bigint(20)  COMMENT '出库审核人id';