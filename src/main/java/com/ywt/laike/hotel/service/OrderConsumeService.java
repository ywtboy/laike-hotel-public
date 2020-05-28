package com.ywt.laike.hotel.service;

import com.ywt.laike.hotel.model.OrderConsume;

/**
 * @author ywt
 */
public interface OrderConsumeService {
    /**
     * 自动在住房间房租入账
     *  给不是当天入住的房间
     */
    void autoConsumeEntry();

    /**
     * 撤销一条非系统消费
     * @param orderConsume id hotel
     * @return int
     */
    Integer cancelConsume(OrderConsume orderConsume);
}
