package com.ywt.laike.hotel.service;

import com.ywt.laike.hotel.model.Order;
import com.ywt.laike.hotel.model.OrderRoom;
import com.ywt.laike.hotel.query.OrderQuery;
import com.ywt.laike.hotel.query.OrderRoomQuery;
import com.ywt.laike.hotel.web.ResultMessage;

import java.util.List;

/**
 * @author ywt
 */
public interface OrderQueryService {
    ResultMessage<List<Order>> listOrders(OrderQuery query);

    List<OrderRoom> listOrderRooms(OrderRoomQuery orderRoomQuery);

}
