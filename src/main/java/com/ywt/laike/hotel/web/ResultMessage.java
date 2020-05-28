package com.ywt.laike.hotel.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: ywt
 */
@Getter
@Setter
@ToString
public class ResultMessage<T> {
    private Integer code;
    private String msg;
    private T data;
    private Integer count;

    public ResultMessage() {}

    public ResultMessage(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultMessage(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultMessage(Integer code, String msg, T data, Integer count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }
}
