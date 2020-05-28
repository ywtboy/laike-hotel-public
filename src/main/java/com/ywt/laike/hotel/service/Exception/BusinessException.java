package com.ywt.laike.hotel.service.Exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ywt
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {
    private Integer code;
    private String msg;

    public BusinessException() {
        super("自定义-业务异常");
    }

    public BusinessException(String msg) {
        super("自定义-业务异常: " + msg);
        this.msg = msg;
    }

    public BusinessException(Integer code, String msg) {
        super("自定义-业务异常: " + msg);
        this.msg = msg;
        this.code = code;
    }
}
