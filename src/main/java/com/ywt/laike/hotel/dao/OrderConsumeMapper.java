package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.OrderConsume;
import com.ywt.laike.hotel.query.ConsumeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderConsumeMapper {
    int deleteByPrimaryKey(Long consumeId);

    int insert(OrderConsume record);

    int insertSelective(OrderConsume record);

    OrderConsume selectByPrimaryKey(Long consumeId);

    int updateByPrimaryKeySelective(OrderConsume record);

    int updateByPrimaryKey(OrderConsume record);

    List<OrderConsume> listOrderConsumesByOrderNo(String orderNo);

    List<OrderConsume> listOrderConsumes(ConsumeQuery consumeQuery);

    int insertBatch(List<OrderConsume> orderConsumes);
}