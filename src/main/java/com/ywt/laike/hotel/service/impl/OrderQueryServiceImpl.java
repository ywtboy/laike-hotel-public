package com.ywt.laike.hotel.service.impl;

import com.ywt.laike.hotel.dao.OrderMapper;
import com.ywt.laike.hotel.dao.OrderReserveMapper;
import com.ywt.laike.hotel.dao.OrderRoomMapper;
import com.ywt.laike.hotel.model.Order;
import com.ywt.laike.hotel.model.OrderReserve;
import com.ywt.laike.hotel.model.OrderRoom;
import com.ywt.laike.hotel.query.OrderQuery;
import com.ywt.laike.hotel.query.OrderReserveQuery;
import com.ywt.laike.hotel.query.OrderRoomQuery;
import com.ywt.laike.hotel.service.OrderQueryService;
import com.ywt.laike.hotel.web.ResultMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ywt
 */
@Service
public class OrderQueryServiceImpl implements OrderQueryService {
    private final OrderRoomMapper orderRoomMapper;
    private final OrderMapper orderMapper;
    private final OrderReserveMapper orderReserveMapper;

    public OrderQueryServiceImpl(OrderRoomMapper orderRoomMapper, OrderMapper orderMapper, OrderReserveMapper orderReserveMapper) {
        this.orderRoomMapper = orderRoomMapper;
        this.orderMapper = orderMapper;
        this.orderReserveMapper = orderReserveMapper;
    }

    @Override
    public ResultMessage<List<Order>> listOrders(OrderQuery query) {
        List<Order> orders = new ArrayList<>();
        int count = 0;
        List<OrderRoom> orderRooms = new ArrayList<>();
        List<OrderReserve> orderReserves = new ArrayList<>();
        // 所有的
        if (null == query.getQueryType()) {
            orders = orderMapper.listOrders(query);
            List<String> orderNos = new ArrayList<>();
            orders.forEach(order -> orderNos.add(order.getOrderNo()));
            orderRooms = orderRoomMapper.listOrderRoomsByOrderNos(orderNos);
            count = orderMapper.countOrder(query);

            // 未分配的
//            OrderReserveQuery orderReserveQuery = new OrderReserveQuery();
//            orderReserveQuery.setHotelNo(query.getHotelNo()); // 设置当前宾馆号
//            orderReserveQuery.setAllotted(false);
            orderReserves = orderReserveMapper.listOrderReservesByOrderNos(orderNos);
        }
        // 正住的
        if ("live".equals(query.getQueryType())) {
            query.getOrderRoomQuery().setHotelNo(query.getHotelNo()); // 设置当前宾馆号
            orderRooms = orderRoomMapper.listOrderRooms(query.getOrderRoomQuery());
            List<String> orderNos = new ArrayList<>();
            orderRooms.forEach(orderRoom -> orderNos.add(orderRoom.getOrderNo()));
            List<String> orderNosTemp = orderNos.stream().distinct().collect(Collectors.toList());
            orders = orderMapper.listOrdersByOrderNos(orderNosTemp);
            count = orders.size();
            if (query.getLimit() < count) {
                int fromIndex = query.getOffset();
                int toIndex = query.getOffset() + query.getLimit();
                if (toIndex >= count) {
                    toIndex = count;
                }
                orders = orders.subList(fromIndex, toIndex);
            }
        }
        // 预订
        if ("reserve".equals(query.getQueryType())) {
            List<String> orderNos = new ArrayList<>();

            OrderRoomQuery orderRoomQuery = query.getOrderRoomQuery();

            OrderReserveQuery orderReserveQuery = new OrderReserveQuery();

            if (null != orderRoomQuery) {
                // 已分配的
                orderRoomQuery.setGuestName(query.getGuestName());
                orderRoomQuery.setGuestTel(query.getGuestTel());
                orderRoomQuery.setHotelNo(query.getHotelNo()); // 设置当前宾馆号
                orderRoomQuery.setOrderRoomStateCode("no_live");
                orderRooms = orderRoomMapper.listOrderRooms(orderRoomQuery);
                orderRooms.forEach(orderRoom -> orderNos.add(orderRoom.getOrderNo()));

                orderReserveQuery.setEstimateInTimeStart(orderRoomQuery.getEstimateInTimeStart());
                orderReserveQuery.setEstimateInTimeEnd(orderRoomQuery.getEstimateInTimeEnd());
            }

            // 未分配的
            orderReserveQuery.setGuestName(query.getGuestName());
            orderReserveQuery.setGuestTel(query.getGuestTel());
            orderReserveQuery.setHotelNo(query.getHotelNo()); // 设置当前宾馆号
            orderReserveQuery.setAllotted(false);
            orderReserves = orderReserveMapper.listOrderReserves(orderReserveQuery);
            orderReserves.forEach(orderReserve -> orderNos.add(orderReserve.getOrderNo()));

            List<String> orderNosTemp = orderNos.stream().distinct().collect(Collectors.toList());
            count = orderNosTemp.size();

            orders = orderMapper.listOrdersByOrderNos(orderNosTemp);
        }

        if (orders.size() > 0) {
            for (Order order : orders) {
                List<OrderRoom> orderRoomsTemp = new ArrayList<>();
                for (OrderRoom orderRoom : orderRooms) {
                    if (orderRoom.getOrderNo().equals(order.getOrderNo())) {
                        orderRoomsTemp.add(orderRoom);
                    }
                }
                order.setOrderRooms(orderRoomsTemp);

                List<OrderReserve> orderReservesTemp = new ArrayList<>();
                for (OrderReserve orderReserve : orderReserves) {
                    if (orderReserve.getOrderNo().equals(order.getOrderNo())) {
                        orderReservesTemp.add(orderReserve);
                    }
                }
                order.setOrderReserves(orderReservesTemp);
            }
        }
        ResultMessage<List<Order>> msg = new ResultMessage<>(0, "查询成功", orders, count);
        return msg;
    }

    @Override
    public List<OrderRoom> listOrderRooms(OrderRoomQuery orderRoomQuery) {
        return orderRoomMapper.listOrderRooms(orderRoomQuery);
    }
}
