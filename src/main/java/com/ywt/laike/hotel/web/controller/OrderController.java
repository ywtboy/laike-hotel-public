package com.ywt.laike.hotel.web.controller;

import com.ywt.laike.hotel.dto.*;
import com.ywt.laike.hotel.model.OrderConsume;
import com.ywt.laike.hotel.model.OrderIncome;
import com.ywt.laike.hotel.model.OrderRoom;
import com.ywt.laike.hotel.model.User;
import com.ywt.laike.hotel.query.OrderQuery;
import com.ywt.laike.hotel.query.OrderRoomQuery;
import com.ywt.laike.hotel.service.OrderQueryService;
import com.ywt.laike.hotel.service.OrderService;
import com.ywt.laike.hotel.web.ResultMessage;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ywt
 */
@RestController
@RequestMapping("/api/order")
@RequiresAuthentication
public class OrderController extends BaseController {
    private final OrderService orderService;
    private final OrderQueryService orderQueryService;

    @Autowired
    public OrderController(OrderService orderService, OrderQueryService orderQueryService) {
        this.orderService = orderService;
        this.orderQueryService = orderQueryService;
    }

    @GetMapping
    public ResultMessage listOrders(OrderQuery orderQuery) {
        orderQuery.setHotelNo(getCurrHotel().getHotelNo());
        orderQuery.setCurrentSessionHotelNo(getCurrHotel().getHotelNo());
//        ResultMessage<List<Order>> msg = new ResultMessage<>(0, "订单查询成功");
//        msg.setCount(orderService.countOrder(orderQuery));
//        msg.setData(orderService.listOrders(orderQuery));
        return orderQueryService.listOrders(orderQuery);
    }

    @GetMapping("/rooms")
    public ResultMessage listOrderRooms(OrderRoomQuery orderRoomQuery) {
        orderRoomQuery.setHotelNo(getCurrHotel().getHotelNo());
        ResultMessage<List<OrderRoom>> msg = new ResultMessage<>(0, "查询成功");
        msg.setData(orderQueryService.listOrderRooms(orderRoomQuery));
        return msg;
    }

    @PostMapping("/checkIn")
    public ResultMessage<CheckInDTO> checkIn(@RequestBody @Validated CheckInDTO checkInDTO) {
        setCurrentInfo(checkInDTO);
        ResultMessage<CheckInDTO> msg = new ResultMessage<>(0, "入住登记成功");
        msg.setData(orderService.checkIn(checkInDTO));
        return msg;
    }

    @PostMapping("/reserve")
    public ResultMessage<CheckInOfReserveDTO> reserve(@RequestBody @Validated CheckInOfReserveDTO checkInOfReserveDTO) {
        setCurrentInfo(checkInOfReserveDTO);
        ResultMessage<CheckInOfReserveDTO> msg = new ResultMessage<>(0, "预订成功");
        msg.setData(orderService.checkInOfReserve(checkInOfReserveDTO));
        return msg;
    }

    @PostMapping("/allotRoom")
    public ResultMessage<AllotRoomDTO> allotRoom(@RequestBody @Validated AllotRoomDTO allotRoomDTO) {
        setCurrentInfo(allotRoomDTO);
        ResultMessage<AllotRoomDTO> msg = new ResultMessage<>(0, "分配房间成功");
        msg.setData(orderService.allotRoom(allotRoomDTO));
        return msg;
    }

    @PostMapping("/checkOut")
    public ResultMessage<CheckOutDTO> checkOut(@RequestBody CheckOutDTO checkOutDTO) {
        setCurrentInfo(checkOutDTO);
        String info = checkOutDTO.getChecked() ? "结账成功" : "退房成功";
        ResultMessage<CheckOutDTO> msg = new ResultMessage<>(0, info);
        msg.setData(orderService.checkOut(checkOutDTO));
        return msg;
    }

    @PostMapping("/room/change")
    public ResultMessage changeRoom(@RequestBody @Validated ChangeRoomDTO changeRoomDTO) {
        setCurrentInfo(changeRoomDTO);
        ResultMessage msg = new ResultMessage<>(0, "换房成功");
        msg.setData(orderService.changeRoom(changeRoomDTO));
        return msg;
    }

    @GetMapping("/{orderNo}")
    public ResultMessage getOrderInfoByNo(@PathVariable String orderNo) {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setOrderNo(orderNo);
        orderQuery.setCurrentSessionHotelNo(getCurrHotel().getHotelNo());
        ResultMessage<OrderDTO> msg = new ResultMessage<>(0, "订单查询成功");
        msg.setData(orderService.getOrderInfoByOrderNo(orderQuery));
        return msg;
    }

    @GetMapping("/{orderNo}/consume")
    public ResultMessage getOrderConsumeByNo(@PathVariable String orderNo) {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setOrderNo(orderNo);
        orderQuery.setCurrentSessionHotelNo(getCurrHotel().getHotelNo());
        ResultMessage<List<OrderConsume>> msg = new ResultMessage<>(0, "订单消费查询成功");
        msg.setData(orderService.listOrderConsumesByOrderNo(orderQuery));
        return msg;
    }

    @PostMapping("/{orderNo}/consume")
    public ResultMessage saveOrderConsumeByNo(@PathVariable String orderNo, @RequestBody OrderConsume orderConsume) {
        CommonDTO<OrderConsume> orderConsumeDTO = new CommonDTO<>();
//        设置当前信息
        setCurrentInfo(orderConsumeDTO);
        orderConsumeDTO.setData(orderConsume);
        ResultMessage<OrderConsume> msg = new ResultMessage<>(0, "订单消费保存成功");
        msg.setData(orderService.saveOrderConsume(orderConsumeDTO));
        return msg;
    }

    @GetMapping("/{orderNo}/income")
    public ResultMessage getOrderIncomeByNo(@PathVariable String orderNo) {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setOrderNo(orderNo);
        orderQuery.setCurrentSessionHotelNo(getCurrHotel().getHotelNo());
        ResultMessage<List<OrderIncome>> msg = new ResultMessage<>(0, "订单收款查询成功");
        msg.setData(orderService.listOrderIncomesByOrderNo(orderQuery));


        return msg;
    }

    @PostMapping("/{orderNo}/income")
    public ResultMessage saveOrderIncomeByNo(
//            @PathVariable String orderNo,
            @RequestBody @Validated OrderIncome orderIncome) {
        CommonDTO<OrderIncome> orderIncomeDTO = new CommonDTO<>();
        //        设置当前信息
        setCurrentInfo(orderIncomeDTO);
        orderIncomeDTO.setData(orderIncome);
        ResultMessage<OrderIncome> msg = new ResultMessage<>(0, "订单收款保存成功");
        msg.setData(orderService.saveOrderIncome(orderIncomeDTO));
        return msg;
    }

    private void setCurrentInfo(BaseDTO baseDTO) {
        User currUser = getCurrUser();
        baseDTO.setCurrentSessionHotelNo(currUser.getCurrHotel().getHotelNo());
        baseDTO.setCurrentUserId(currUser.getUserId());
        baseDTO.setCurrentUserName(currUser.getRealName());
    }
}
