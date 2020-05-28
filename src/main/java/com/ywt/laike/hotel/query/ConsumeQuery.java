package com.ywt.laike.hotel.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ywt
 */
@Setter
@Getter
@ToString
public class ConsumeQuery extends BaseQuery {
    private Long consumeId;

    private String consumeTypeCode;

    private String consumeItemCode;

    private String consumeItemDetail;

    private Date businessDay;

    private String roomNo;

    private String orderNo;

    private String hotelNo;

    private Long operatorId;

    private Boolean deleted;

}
