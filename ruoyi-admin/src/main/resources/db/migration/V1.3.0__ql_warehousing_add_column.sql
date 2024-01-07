ALTER table ql_warehousing  add   warehousing_userid  bigint(20)  COMMENT '入库对接人id';
ALTER table ql_warehousing  add   warehousing_releaseuserid  bigint(20)  COMMENT '入库审核人id';

ALTER table ql_outbound  add   outbound_userid  bigint(20)  COMMENT '出库对接人id';
ALTER table ql_outbound  add   outbound_releaseuserid  bigint(20)  COMMENT '出库审核人id';