package com.ywt.laike.hotel.util;

import com.ywt.laike.hotel.dao.OrderMapper;
import com.ywt.laike.hotel.query.OrderQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Author: ywt
 * 类似雪花算法
 * 编号生成类
 */
@Component
public class NumGenerationUtils {

    // 宾馆编号位数
    private final static long HOTEL_NO_BITS = 24L;
    // 天数位数
    private final static long DAY_BITS = 16L;
    // 序号位数
    private final static long SEQUENCE_BITS = 9L;

    //sequence掩码，确保sequnce不会超出上限
    private final static long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);

    // 宾馆编号左移位数
    private final static long HOTEL_NO_SHIFT = DAY_BITS + SEQUENCE_BITS;
    // 天数左移位数
    private final static long DAY_SHIFT = SEQUENCE_BITS;

    @Autowired
    private OrderMapper orderMapper;

    private static OrderMapper orderMapperTemp;

    @PostConstruct
    public void init() {
        orderMapperTemp = orderMapper;
    }

    public static String getOrderNo(Long hotelNo) {
        Long orderNo = (hotelNo << HOTEL_NO_SHIFT) | (getNextDay() << DAY_SHIFT) | getNextSequence(hotelNo.toString());
        return orderNo.toString();
    }

    private static long getNextDay() {
        LocalDate begin = LocalDate.of(2019, 1, 1);
        LocalDate now = LocalDate.now();
        return now.toEpochDay() - begin.toEpochDay();
    }

    private static long getNextSequence(String hotelNo) {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setHotelNo(hotelNo);
        orderQuery.setOrderDate(LocalDate.now().toString());
        return orderMapperTemp.countOrder(orderQuery);
    }

    public static void main(String[] args) {
        NumGenerationUtils.getNextDay();
        System.out.println(SEQUENCE_MASK);

        BigDecimal balance = new BigDecimal(5);
        BigDecimal a = new BigDecimal(-10);
        balance = balance.add(a);
        System.out.println("balance:" + balance);
    }
}
