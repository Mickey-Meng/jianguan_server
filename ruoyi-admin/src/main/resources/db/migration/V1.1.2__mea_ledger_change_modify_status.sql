alter table mea_ledger_change modify status varchar(20) DEFAULT 'APPROVING' COMMENT 'REJECT: 驳回, PENDING: 待审批, APPROVING: 审批中, APPROVED: 已审批';

