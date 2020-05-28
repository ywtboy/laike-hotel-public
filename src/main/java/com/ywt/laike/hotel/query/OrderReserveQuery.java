package com.ywt.laike.hotel.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ywt
 */
@Setter
@Getter
public class OrderReserveQuery extends BaseQuery {
    /**
     * 是否全部分配
     */
    private Boolean allotted;

    private String orderNo;

    private String guestName;

    private String guestTel;

}
