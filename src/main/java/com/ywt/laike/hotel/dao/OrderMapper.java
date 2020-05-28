package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.Order;
import com.ywt.laike.hotel.query.OrderQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByOrderNoSelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> listOrders(OrderQuery orderQuery);

    Integer countOrder(OrderQuery orderQuery);

    Order getOrderByNo(String orderNo);

    List<Order> listOrdersByOrderNos(List<String> orderNos);

    int updateOrderMoneyByOrderNosBatch(List<Order> orders);
}