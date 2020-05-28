package com.ywt.laike.hotel.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ywt
 */
@Setter
@Getter
public class OrderQuery extends BaseQuery {

    private String orderNo;

    private String guestName;

    private String guestTel;

    private String guestCertificateNo;

    private String hotelNo;

    private String orderStateCode;

    private String orderTypeCode;

    private String channelCode;

    private String channelOrderNo;

    private String roomNo;

    private OrderRoomQuery orderRoomQuery;

    private OrderReserveQuery orderReserveQuery;

    private String queryType;

    /**
     * 某天 yyyy-MM-dd
     */
    private String orderDate;


}
