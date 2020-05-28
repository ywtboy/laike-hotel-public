package com.ywt.laike.hotel.dto;

import com.ywt.laike.hotel.model.Room;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: ywt
 */
@Getter
@Setter
@ToString
public class RoomDTO {
    private Long roomTypeId;

    @NotBlank(message = "房型名称不能为空")
    private String roomTypeName;

    private BigDecimal roomTypePrice;

    private String hotelNo;

    private Integer sortNo;

    private List<Room> rooms;

    private static final long serialVersionUID = 1L;
}
