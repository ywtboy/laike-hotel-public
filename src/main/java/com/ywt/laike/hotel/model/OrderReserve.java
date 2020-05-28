package com.ywt.laike.hotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderReserve extends BaseEntity implements Serializable {
    private Long reserveId;

    private Long roomTypeId;

    private String roomTypeName;

    private BigDecimal roomTypePrice;

    private String hotelNo;

    private String orderNo;

    private Integer reserveNum;

    private Integer allottedNum;

    private Date estimateInTime;

    private Date estimateOutTime;

    private Long operatorId;

    private String operatorName;

    private Boolean deleted;

    private Date gmtCreate;

    private Date gmtModified;

    private static final long serialVersionUID = 1L;

    public Long getReserveId() {
        return reserveId;
    }

    public void setReserveId(Long reserveId) {
        this.reserveId = reserveId;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName == null ? null : roomTypeName.trim();
    }

    public BigDecimal getRoomTypePrice() {
        return roomTypePrice;
    }

    public void setRoomTypePrice(BigDecimal roomTypePrice) {
        this.roomTypePrice = roomTypePrice;
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

    public Integer getReserveNum() {
        return reserveNum;
    }

    public void setReserveNum(Integer reserveNum) {
        this.reserveNum = reserveNum;
    }

    public Integer getAllottedNum() {
        return allottedNum;
    }

    public void setAllottedNum(Integer allottedNum) {
        this.allottedNum = allottedNum;
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
        sb.append(", reserveId=").append(reserveId);
        sb.append(", roomTypeId=").append(roomTypeId);
        sb.append(", roomTypeName=").append(roomTypeName);
        sb.append(", roomTypePrice=").append(roomTypePrice);
        sb.append(", hotelNo=").append(hotelNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", reserveNum=").append(reserveNum);
        sb.append(", allottedNum=").append(allottedNum);
        sb.append(", estimateInTime=").append(estimateInTime);
        sb.append(", estimateOutTime=").append(estimateOutTime);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", deleted=").append(deleted);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }
}