package com.ywt.laike.hotel.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ywt
 */
@Setter
@Getter
@ToString
public class OrderRoomQuery extends BaseQuery {

    private String roomName;

    private String roomNo;

    private String guestName;

    private String guestTel;

    private String guestCertificateNo;

    private String hotelNo;

    private String orderNo;

    private String orderTypeCode;

    private String orderRoomStateCode;

    private Long operatorId;

    private String operatorName;

    private Boolean deleted;

    private Date gmtCreate;

    private Date gmtModified;

    private Long reserveId;

}
