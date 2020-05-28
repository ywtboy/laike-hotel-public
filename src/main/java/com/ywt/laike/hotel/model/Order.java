package com.ywt.laike.hotel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order extends BaseEntity implements Serializable {
    private Long orderId;

    private String orderNo;

    @NotBlank(message = "客人姓名不能为空")
    private String guestName;

    private String guestTel;

    private String guestCertificateNo;

    private String hotelNo;

    private String orderStateCode;
    /**
     * 订单类型 日租房/钟点房
     */
    @NotBlank(message = "订单类型不能为空")
    private String orderTypeCode;

    @NotBlank(message = "渠道类型不能为空")
    private String channelCode;

    private String channelOrderNo;
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+8")
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date checkInTime;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkOutTime;

    private BigDecimal orderMoney;

    private BigDecimal payMoney;

    @NotBlank(message = "支付类型不能为空")
    private String payTypeCode;

    private String orderComment;

    private Long operatorId;

    private String operatorName;

    @NotBlank(message = "客户端类型不能为空")
    private String clientTypeCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",  timezone = "GMT+8")
    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",  timezone = "GMT+8")
    private Date gmtModified;

    private List<OrderRoom> orderRooms;

    private List<OrderReserve> orderReserves;

    private static final long serialVersionUID = 1L;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName == null ? null : guestName.trim();
    }

    public String getGuestTel() {
        return guestTel;
    }

    public void setGuestTel(String guestTel) {
        this.guestTel = guestTel == null ? null : guestTel.trim();
    }

    public String getGuestCertificateNo() {
        return guestCertificateNo;
    }

    public void setGuestCertificateNo(String guestCertificateNo) {
        this.guestCertificateNo = guestCertificateNo == null ? null : guestCertificateNo.trim();
    }

    public String getHotelNo() {
        return hotelNo;
    }

    public void setHotelNo(String hotelNo) {
        this.hotelNo = hotelNo == null ? null : hotelNo.trim();
    }

    public String getOrderStateCode() {
        return orderStateCode;
    }

    public void setOrderStateCode(String orderStateCode) {
        this.orderStateCode = orderStateCode == null ? null : orderStateCode.trim();
    }

    public String getOrderTypeCode() {
        return orderTypeCode;
    }

    public void setOrderTypeCode(String orderTypeCode) {
        this.orderTypeCode = orderTypeCode == null ? null : orderTypeCode.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayTypeCode() {
        return payTypeCode;
    }

    public void setPayTypeCode(String payTypeCode) {
        this.payTypeCode = payTypeCode == null ? null : payTypeCode.trim();
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment == null ? null : orderComment.trim();
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getClientTypeCode() {
        return clientTypeCode;
    }

    public void setClientTypeCode(String clientTypeCode) {
        this.clientTypeCode = clientTypeCode == null ? null : clientTypeCode.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", guestName=").append(guestName);
        sb.append(", guestTel=").append(guestTel);
        sb.append(", guestCertificateNo=").append(guestCertificateNo);
        sb.append(", hotelNo=").append(hotelNo);
        sb.append(", orderStateCode=").append(orderStateCode);
        sb.append(", orderTypeCode=").append(orderTypeCode);
        sb.append(", channelCode=").append(channelCode);
        sb.append(", channelOrderNo=").append(channelOrderNo);
        sb.append(", checkInTime=").append(checkInTime);
        sb.append(", checkOutTime=").append(checkOutTime);
        sb.append(", orderMoney=").append(orderMoney);
        sb.append(", payMoney=").append(payMoney);
        sb.append(", payTypeCode=").append(payTypeCode);
        sb.append(", orderComment=").append(orderComment);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", clientTypeCode=").append(clientTypeCode);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }

    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public void setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
    }

    public List<OrderRoom> getOrderRooms() {
        return orderRooms;
    }

    public void setOrderRooms(List<OrderRoom> orderRooms) {
        this.orderRooms = orderRooms;
    }

    public List<OrderReserve> getOrderReserves() {
        return orderReserves;
    }

    public void setOrderReserves(List<OrderReserve> orderReserves) {
        this.orderReserves = orderReserves;
    }
}