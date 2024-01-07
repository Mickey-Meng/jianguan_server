package com.ruoyi.ql.dao;

import com.ruoyi.ql.domain.report.query.QlInventoryQuery;
import com.ruoyi.ql.domain.report.vo.InventoryDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InventoryDAO {

    List<InventoryDetail> query(QlInventoryQuery inventoryQuery);

    List<InventoryDetail> queryOutbound(QlInventoryQuery inventoryQuery);

    List<InventoryDetail> queryWarehousing(QlInventoryQuery inventoryQuery);

    List<InventoryDetail> queryCumulativeInventory(QlInventoryQuery inventoryQuery);
}
