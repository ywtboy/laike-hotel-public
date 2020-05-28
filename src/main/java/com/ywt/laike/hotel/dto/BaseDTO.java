package com.ywt.laike.hotel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ywt.laike.hotel.model.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author ywt
 */
@Setter
@Getter
public abstract class BaseDTO {
    /**
     * 当前宾馆 由controller提供
     */
    @JsonIgnore // 不进行序列化
    private String currentSessionHotelNo;
    @JsonIgnore // 不进行序列化
    private Long currentUserId;
    @JsonIgnore // 不进行序列化
    private String currentUserName;

}
