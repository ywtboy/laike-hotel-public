package com.ywt.laike.hotel.dto;

import com.ywt.laike.hotel.model.Guest;
import com.ywt.laike.hotel.model.Order;
import com.ywt.laike.hotel.model.OrderIncome;
import com.ywt.laike.hotel.model.OrderReserve;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ywt
 */
@Getter
@Setter
public class CheckInOfReserveDTO extends BaseDTO {
    @Valid
    private List<OrderReserve> orderReserves;
    @Valid
    private OrderIncome orderIncome;
    @Valid
    private List<Guest> guests;
    @Valid
    private Order order;
}
