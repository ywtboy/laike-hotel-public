package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.OrderOperationRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderOperationRecordMapper {
    int deleteByPrimaryKey(Long orderOperationRecordId);

    int insert(OrderOperationRecord record);

    int insertSelective(OrderOperationRecord record);

    OrderOperationRecord selectByPrimaryKey(Long orderOperationRecordId);

    int updateByPrimaryKeySelective(OrderOperationRecord record);

    int updateByPrimaryKey(OrderOperationRecord record);

    List<OrderOperationRecord> listOrderOperationRecordsByOrderNo(String orderNo);
}