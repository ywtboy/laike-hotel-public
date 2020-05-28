package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.OrderRoom;
import com.ywt.laike.hotel.query.OrderRoomQuery;
import com.ywt.laike.hotel.query.ReportQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRoomMapper {
    int deleteByPrimaryKey(Long orderRoomId);

    int insert(OrderRoom record);

    int insertSelective(OrderRoom record);

    OrderRoom selectByPrimaryKey(Long orderRoomId);

    int updateByPrimaryKeySelective(OrderRoom record);

    int updateByPrimaryKey(OrderRoom record);

    List<OrderRoom> listOrderRoomsByOrderNo(String orderNo);

    List<OrderRoom> listOrderRoomsByOrderNos(List<String> orderNos);

    List<OrderRoom> listOrderRooms(OrderRoomQuery query);

    List<OrderRoom> listOrderRoomsForReport(ReportQuery reportQuery);
}