package com.ywt.laike.hotel.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @Author: ywt
 */
@Setter
@Getter
@ToString
public class RoomStateDTO {
    private String roomName;
    private String roomNo;
    private Long roomTypeId;
    private String roomTypeName;
    private BigDecimal roomTypePrice;
    private Long floorId;
    private String floorName;
    private String roomState;
    private String roomStateName;
    private String hotelNo;
    private String orderNo;
    private String roomTag;
    private Boolean locked;
    private String lockedReason;

    private static final long serialVersionUID = 1L;
}
