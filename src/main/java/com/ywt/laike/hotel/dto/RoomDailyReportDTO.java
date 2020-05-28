package com.ywt.laike.hotel.dto;

import com.ywt.laike.hotel.model.OrderRoom;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author ywt
 */
@Getter
@Setter
public class RoomDailyReportDTO extends BaseDTO {
    private List<OrderRoom> newAddRooms;
    private List<OrderRoom> stayRooms;
    private List<OrderRoom> hourRooms;
}
