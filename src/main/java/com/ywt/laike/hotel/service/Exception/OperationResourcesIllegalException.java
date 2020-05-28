package com.ywt.laike.hotel.service.Exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ywt
 */
@Getter
@Setter
public class OperationResourcesIllegalException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;

    public OperationResourcesIllegalException() {
        super("操作资源非法");
    }

    public OperationResourcesIllegalException(String msg) {
        super("自定义异常-操作资源非法:" + msg);
        this.msg = msg;
    }
}
