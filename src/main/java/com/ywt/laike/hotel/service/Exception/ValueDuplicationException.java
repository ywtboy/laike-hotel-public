package com.ywt.laike.hotel.service.Exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ywt
 */
@Getter
@Setter
public class ValueDuplicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;

    public ValueDuplicationException() {
        super("值重复");
    }

    public ValueDuplicationException(String msg) {
        super("自定义异常-值重复:" + msg);
        this.msg = msg;
    }
}
