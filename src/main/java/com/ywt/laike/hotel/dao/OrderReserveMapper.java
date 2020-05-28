package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.OrderReserve;
import com.ywt.laike.hotel.query.OrderReserveQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderReserveMapper {
    int deleteByPrimaryKey(Long reserveId);

    int insert(OrderReserve record);

    int insertSelective(OrderReserve record);

    OrderReserve selectByPrimaryKey(Long reserveId);

    int updateByPrimaryKeySelective(OrderReserve record);

    int updateByPrimaryKey(OrderReserve record);

    List<OrderReserve> listOrderReservesByOrderNo(String orderNo);

    List<OrderReserve> listOrderReservesByOrderNos(List<String> orderNos);

    List<OrderReserve> listOrderReserves(OrderReserveQuery orderReserveQuery);

}