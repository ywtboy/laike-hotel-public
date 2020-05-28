package com.ywt.laike.hotel.service.Exception;

/**
 * @Author: ywt
 */
public class NotCurrentHotelException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;

    public NotCurrentHotelException() {
        super("操作的不是当前hotel");
    }

    public NotCurrentHotelException(String msg) {
        super("自定义异常-操作的不是当前hotel:" + msg);
        this.msg = msg;
    }
}
