package com.ywt.laike.hotel.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ywt
 */
@Setter
@Getter
public class CancelOrderDTO extends BaseDTO {
    private String orderNo;
    private String cancelReason;
}
