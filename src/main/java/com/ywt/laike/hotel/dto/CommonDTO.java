package com.ywt.laike.hotel.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ywt
 */
@Setter
@Getter
public class CommonDTO<T> extends BaseDTO {
    private T data;
}
