package com.ywt.laike.hotel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author ywt
 */
@Setter
@Getter
public abstract class BaseEntity {
    private Long operatorId;

    private String operatorName;

    private Boolean deleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    private Date gmtModified;
}
