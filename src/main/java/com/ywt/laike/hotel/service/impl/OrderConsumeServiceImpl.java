package com.ywt.laike.hotel.service.impl;

import com.ywt.laike.hotel.dao.OrderConsumeMapper;
import com.ywt.laike.hotel.dao.OrderMapper;
import com.ywt.laike.hotel.dao.OrderRoomMapper;
import com.ywt.laike.hotel.model.Order;
import com.ywt.laike.hotel.model.OrderConsume;
import com.ywt.laike.hotel.model.OrderRoom;
import com.ywt.laike.hotel.query.ConsumeQuery;
import com.ywt.laike.hotel.query.OrderRoomQuery;
import com.ywt.laike.hotel.service.OrderConsumeService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ywt
 */
@Service
@Log
public class OrderConsumeServiceImpl implements OrderConsumeService {

    @Autowired
    private OrderConsumeMapper orderConsumeMapper;
    @Autowired
    private OrderRoomMapper orderRoomMapper;
    @Autowired
    private OrderMapper orderMapper;

    // 营业日开始时间 @todo 暂时 需要数据库获取 每个酒店可能不同
    private final int BUSINESS_DAY_START_HOUR = 6;
    /**
     * 自动在住房间房租入账
     * 给不是当天入住的房间
     * @// TODO: 当数据量增很大时需要分批 多线程
     */
    @Override
    @Scheduled(cron = "0 15 0 * * ?") // 0 15 0 * * ? 每天 0:15:00      0/5 * * * * ? 每5秒
    @Transactional(rollbackFor = Exception.class)
    public void autoConsumeEntry() {
        long startTime = new Date().getTime();

        OrderRoomQuery orderRoomQuery = new OrderRoomQuery();
        LocalDateTime time = LocalDateTime.of(LocalDate.now().minusDays(1),
                LocalTime.of(BUSINESS_DAY_START_HOUR, 0, 0));
        Date businessDay = Date.from(time.atZone(ZoneId.of("Asia/Shanghai")).toInstant());
        orderRoomQuery.setEndTime(businessDay);
        orderRoomQuery.setOrderTypeCode("daily");
        orderRoomQuery.setOrderRoomStateCode("live");
        List<OrderRoom> orderRooms = orderRoomMapper.listOrderRooms(orderRoomQuery);

        if (orderRooms != null && !orderRooms.isEmpty()) {

            // 获取本次夜审订单号list
            List<String> orderNos = orderRooms.stream().map(OrderRoom::getOrderNo).collect(Collectors.toList());
            // 去重
            orderNos = orderNos.stream().distinct().collect(Collectors.toList());
            List<Order> orderList = orderMapper.listOrdersByOrderNos(orderNos);

            List<OrderConsume> orderConsumes = new ArrayList<>(orderRooms.size());
            orderRooms.forEach(orderRoom -> {
                OrderConsume orderConsume = new OrderConsume();
                orderConsume.setConsumeTypeCode("income");
                orderConsume.setConsumeItemCode("rent");
                orderConsume.setConsumeItemDetail("夜审房租");
                orderConsume.setConsumeMoney(orderRoom.getRoomPrice());
                orderConsume.setBusinessDay(businessDay);
                orderConsume.setRoomNo(orderRoom.getRoomNo());
                orderConsume.setRoomName(orderRoom.getRoomName());
                orderConsume.setOrderNo(orderRoom.getOrderNo());
                orderConsume.setHotelNo(orderRoom.getHotelNo());
                orderConsume.setOperatorId(1L);
                orderConsume.setOperatorName("系统");
                orderConsume.setDeleted(false);
                Date current = new Date();
                orderConsume.setGmtCreate(current);
                orderConsume.setGmtModified(current);

                if (1 == orderRoom.getRoomPrice().compareTo(new BigDecimal(0))) {
                    orderConsumes.add(orderConsume);

                    orderList.forEach(order -> {
                        if (order.getOrderNo().equals(orderConsume.getOrderNo())) {
                            order.setOrderMoney(order.getOrderMoney().add(orderConsume.getConsumeMoney()));
                        }
                    });
                }
            });
            orderMapper.updateOrderMoneyByOrderNosBatch(orderList);
            orderConsumeMapper.insertBatch(orderConsumes);
            log.info("本次共计入账：" + orderConsumes.size() + "用时毫秒：" + (new Date().getTime() - startTime));
        } else {
            log.info("本次共计入账：0" + "用时毫秒：" + (new Date().getTime() - startTime));
        }

    }

    /**
     * 撤销一条非系统消费
     *
     * @param orderConsume id hotel
     * @return int
     */
    @Override
    public Integer cancelConsume(OrderConsume orderConsume) {
        return null;
    }
}
