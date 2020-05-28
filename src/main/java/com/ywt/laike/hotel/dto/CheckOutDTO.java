package com.ywt.laike.hotel.dto;

import com.ywt.laike.hotel.model.OrderIncome;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ywt
 */
@Setter
@Getter
public class CheckOutDTO extends BaseDTO {
    /**
     * 要退的房号 多房订单单独退房时
     */
    private String roomNo;

    private String orderNo;

    /**
     * 是否结账退房
     *  true 为结账退房 订单所有房间退房并结账
     *  false 为单房退房 不结账
     */
    private Boolean checked;

    private OrderIncome orderIncome;
}
