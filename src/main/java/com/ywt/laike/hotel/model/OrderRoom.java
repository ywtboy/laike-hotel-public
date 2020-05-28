package com.ywt.laike.hotel.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderRoom extends BaseEntity implements Serializable {
    private Long orderRoomId;

    @NotBlank(message = "房号不能为空")
    private String roomName;
    @NotBlank(message = "房间唯一编号不能为空")
    private String roomNo;
    @NotBlank(message = "房型不能为空")
    private String roomTypeName;
    @NotNull(message = "房价不能为空")
    @Min(value = 0, message = "房价不能小于0")
    private BigDecimal roomPrice;
    @NotBlank(message = "客人姓名不能为空")
    private String guestName;

    private String guestTel;

    private String guestCertificateNo;

    private String otherGuestCertificateNos;

    private String hotelNo;

    private String orderNo;

    private String orderTypeCode;

    private Date estimateInTime;

    private Date estimateOutTime;

    private String orderRoomStateCode;

    private Date realInTime;

    private Date realOutTime;

    private Long operatorId;

    private String operatorName;

    private Long reserveId;

    private Boolean deleted;

    private Date gmtCreate;

    private Date gmtModified;

    private static final long serialVersionUID = 1L;

    public Long getOrderRoomId() {
        return orderRoomId;
    }

    public void setOrderRoomId(Long orderRoomId) {
        this.orderRoomId = orderRoomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo == null ? null : roomNo.trim();
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName == null ? null : roomTypeName.trim();
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
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

    public String getOtherGuestCertificateNos() {
        return otherGuestCertificateNos;
    }

    public void setOtherGuestCertificateNos(String otherGuestCertificateNos) {
        this.otherGuestCertificateNos = otherGuestCertificateNos == null ? null : otherGuestCertificateNos.trim();
    }

    public String getHotelNo() {
        return hotelNo;
    }

    public void setHotelNo(String hotelNo) {
        this.hotelNo = hotelNo == null ? null : hotelNo.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getOrderTypeCode() {
        return orderTypeCode;
    }

    public void setOrderTypeCode(String orderTypeCode) {
        this.orderTypeCode = orderTypeCode == null ? null : orderTypeCode.trim();
    }

    public Date getEstimateInTime() {
        return estimateInTime;
    }

    public void setEstimateInTime(Date estimateInTime) {
        this.estimateInTime = estimateInTime;
    }

    public Date getEstimateOutTime() {
        return estimateOutTime;
    }

    public void setEstimateOutTime(Date estimateOutTime) {
        this.estimateOutTime = estimateOutTime;
    }

    public String getOrderRoomStateCode() {
        return orderRoomStateCode;
    }

    public void setOrderRoomStateCode(String orderRoomStateCode) {
        this.orderRoomStateCode = orderRoomStateCode == null ? null : orderRoomStateCode.trim();
    }

    public Date getRealInTime() {
        return realInTime;
    }

    public void setRealInTime(Date realInTime) {
        this.realInTime = realInTime;
    }

    public Date getRealOutTime() {
        return realOutTime;
    }

    public void setRealOutTime(Date realOutTime) {
        this.realOutTime = realOutTime;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
        sb.append(", orderRoomId=").append(orderRoomId);
        sb.append(", roomName=").append(roomName);
        sb.append(", roomNo=").append(roomNo);
        sb.append(", roomTypeName=").append(roomTypeName);
        sb.append(", roomPrice=").append(roomPrice);
        sb.append(", guestName=").append(guestName);
        sb.append(", guestTel=").append(guestTel);
        sb.append(", guestCertificateNo=").append(guestCertificateNo);
        sb.append(", otherGuestCertificateNos=").append(otherGuestCertificateNos);
        sb.append(", hotelNo=").append(hotelNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", orderTypeCode=").append(orderTypeCode);
        sb.append(", estimateInTime=").append(estimateInTime);
        sb.append(", estimateOutTime=").append(estimateOutTime);
        sb.append(", orderRoomStateCode=").append(orderRoomStateCode);
        sb.append(", realInTime=").append(realInTime);
        sb.append(", realOutTime=").append(realOutTime);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", deleted=").append(deleted);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }

    public Long getReserveId() {
        return reserveId;
    }

    public void setReserveId(Long reserveId) {
        this.reserveId = reserveId;
    }
}