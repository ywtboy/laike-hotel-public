package com.ywt.laike.hotel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ywt.laike.hotel.model.Guest;
import com.ywt.laike.hotel.model.Order;
import com.ywt.laike.hotel.model.OrderIncome;
import com.ywt.laike.hotel.model.OrderRoom;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: ywt
 */
@Setter
@Getter
public class CheckInDTO extends BaseDTO {
    @Valid
    private Order order;
    @Valid
    private List<OrderRoom> orderRooms;
    @Valid
    private OrderIncome orderIncome;
    private List<Guest> guests;

    @NotNull(message = "是否预订不能为空")
    private Boolean reserved;
    /**
     * 当前宾馆 由controller提供
     */
    @JsonIgnore // 不进行序列化
    private String currentSessionHotelNo;
    @JsonIgnore // 不进行序列化
    private Long currentUserId;
    @JsonIgnore // 不进行序列化
    private String currentUserName;
}
