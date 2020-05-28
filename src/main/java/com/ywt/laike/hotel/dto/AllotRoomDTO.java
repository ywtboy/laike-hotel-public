package com.ywt.laike.hotel.dto;

import com.ywt.laike.hotel.model.OrderRoom;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ywt
 */
@Setter
@Getter
public class AllotRoomDTO extends BaseDTO {
    @Valid
    private List<OrderRoom> orderRooms;
}
