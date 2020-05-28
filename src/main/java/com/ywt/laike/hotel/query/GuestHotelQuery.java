package com.ywt.laike.hotel.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ywt
 */
@Setter
@Getter
public class GuestHotelQuery extends BaseQuery {

    private String guestName;

    private String guestTel;

    private String certificateNo;
}
