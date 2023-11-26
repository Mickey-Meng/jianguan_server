ALTER table ql_warehousing add COLUMN  rate decimal(15,2) COLLATE utf8_bin DEFAULT NULL COMMENT '税率';
ALTER table ql_warehousing add COLUMN  amount_notax decimal(15,2) COLLATE utf8_bin DEFAULT NULL COMMENT '不含税金额';
ALTER table ql_warehousing add COLUMN  amount_tax decimal(15,2) COLLATE utf8_bin DEFAULT NULL COMMENT '税额';
ALTER table ql_warehousing add COLUMN  license_plate varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '车牌号码';

ALTER table ql_fin_invoice add COLUMN  discount varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '折扣';



ALTER table ql_fin_invoice_sale add COLUMN  discount varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '折扣';
