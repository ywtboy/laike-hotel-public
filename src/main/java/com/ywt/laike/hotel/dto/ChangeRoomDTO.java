package com.ywt.laike.hotel.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author: ywt
 */
@Setter
@Getter
@ToString
public class ChangeRoomDTO extends BaseDTO{
    @NotNull(message = "订单房间不能为空")
    private Long orderRoomId;
    @NotBlank(message = "新房房号不能为空")
    private String newRoomNo;
    @NotNull(message = "新房房价不能为空")
    @Min(value = 0, message = "房价不能小于0")
    private BigDecimal newRoomPrice;
    @NotBlank(message = "新房房型不能为空")
    private String newRoomTypeName;
}
