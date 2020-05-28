package com.ywt.laike.hotel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ywt.laike.hotel.model.Order;
import com.ywt.laike.hotel.model.OrderIncome;
import com.ywt.laike.hotel.model.OrderReserve;
import com.ywt.laike.hotel.model.OrderRoom;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: ywt
 */
@Setter
@Getter
public class OrderDTO {
    private Order order;
    private List<OrderRoom> orderRooms;
    private List<OrderReserve> orderReserves;
//    private OrderIncome orderIncome;


}
