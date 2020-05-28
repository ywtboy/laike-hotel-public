package com.ywt.laike.hotel.service;

import com.ywt.laike.hotel.dto.*;
import com.ywt.laike.hotel.model.Order;
import com.ywt.laike.hotel.model.OrderConsume;
import com.ywt.laike.hotel.model.OrderIncome;
import com.ywt.laike.hotel.query.OrderQuery;

import java.util.List;

/**
 * @Author: ywt
 */
public interface OrderService {

    /**
     * 取消订单
     * @param cancelOrderDTO
     * @return
     */
    CancelOrderDTO cancelOrder(CancelOrderDTO cancelOrderDTO);
    /**
     * 入住
     * @param checkInDTO
     * @return
     */
    CheckInDTO checkIn(CheckInDTO checkInDTO);

    /**
     * 房型预订登记
     * @param checkInOfReserveDTO
     * @return
     */
    CheckInOfReserveDTO checkInOfReserve(CheckInOfReserveDTO checkInOfReserveDTO);

    /**
     * 分配房间
     * @param allotRoomDTO 分配房间DTO
     * @return AllotRoomDTO
     */
    AllotRoomDTO allotRoom(AllotRoomDTO allotRoomDTO);

    /**
     * 预订办理入住
     * @param handleCheckInDTO 预订入住 DTO
     * @return HandleCheckInDTO
     */
    HandleCheckInDTO handleCheckIn(HandleCheckInDTO handleCheckInDTO);
    /**
     * 结账退房
     * @param checkOutDTO
     * @return
     */
    CheckOutDTO checkOut(CheckOutDTO checkOutDTO);

    /**
     * 续费登记
     * @param checkStayDTO
     * @return
     */
    CheckStayDTO checkStay(CheckStayDTO checkStayDTO);

    /**
     * 换房
     * @param changeRoomDTO
     * @return
     */
    ChangeRoomDTO changeRoom(ChangeRoomDTO changeRoomDTO);

    /**
     * 查询订单列表
     * @param orderQuery
     * @return
     */
    List<Order> listOrders(OrderQuery orderQuery);

    /**
     * 查询订单数
     * @param orderQuery
     * @return
     */
    Integer countOrder(OrderQuery orderQuery);

    /**
     * 根据订单号查询订单信息
     * @param orderQuery
     * @return
     */
    OrderDTO getOrderInfoByOrderNo(OrderQuery orderQuery);

    /**
     * 根据订单号查询订单消费信息
     * @param orderQuery
     * @return
     */
    List<OrderConsume> listOrderConsumesByOrderNo(OrderQuery orderQuery);

    /**
     * 根据订单号查询订单缴费信息
     * @param orderQuery
     * @return
     */
    List<OrderIncome> listOrderIncomesByOrderNo(OrderQuery orderQuery);

    /**
     * 保存一条消费信息
     * @param orderConsumeDTO
     * @return
     */
    OrderConsume saveOrderConsume(CommonDTO<OrderConsume> orderConsumeDTO);

    /**
     * 保存一条缴费信息
     * @param orderIncomeDTO
     * @return
     */
    OrderIncome saveOrderIncome(CommonDTO<OrderIncome> orderIncomeDTO);

    /**
     * 检查操作的订单是否属于当前hotel
     * @param hotelNo currentSessionHotelNo
     * @return
     */
    default Boolean isCurrentHotel(String hotelNo, String currentSessionHotelNo) {
        return hotelNo.equals(currentSessionHotelNo);
    }
}
