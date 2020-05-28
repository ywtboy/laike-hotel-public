package com.ywt.laike.hotel.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderConsume extends BaseEntity implements Serializable {
    private Long consumeId;

    @NotBlank(message = "消费类型不能为空")
    private String consumeTypeCode;

    @NotBlank(message = "消费项目不能为空")
    private String consumeItemCode;

    @NotBlank(message = "消费项目不能为空")
    private String consumeItemDetail;

    @NotBlank(message = "消费详情不能为空")
    private BigDecimal consumeMoney;

    private Date businessDay;

    private String roomNo;

    private String roomName;

    private String orderNo;

    private String hotelNo;

    private String consumeComment;

    private Long operatorId;

    private String operatorName;

    /**
     * 已冲账金额
     */
    private BigDecimal offsetMoney;
    /**
     * 被冲账id
     */
    private Long offsetId;

    private Boolean deleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    private Date gmtModified;

    private static final long serialVersionUID = 1L;

    public Long getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(Long consumeId) {
        this.consumeId = consumeId;
    }

    public String getConsumeTypeCode() {
        return consumeTypeCode;
    }

    public void setConsumeTypeCode(String consumeTypeCode) {
        this.consumeTypeCode = consumeTypeCode == null ? null : consumeTypeCode.trim();
    }

    public String getConsumeItemCode() {
        return consumeItemCode;
    }

    public void setConsumeItemCode(String consumeItemCode) {
        this.consumeItemCode = consumeItemCode == null ? null : consumeItemCode.trim();
    }

    public String getConsumeItemDetail() {
        return consumeItemDetail;
    }

    public void setConsumeItemDetail(String consumeItemDetail) {
        this.consumeItemDetail = consumeItemDetail == null ? null : consumeItemDetail.trim();
    }

    public BigDecimal getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(BigDecimal consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public Date getBusinessDay() {
        return businessDay;
    }

    public void setBusinessDay(Date businessDay) {
        this.businessDay = businessDay;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo == null ? null : roomNo.trim();
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getHotelNo() {
        return hotelNo;
    }

    public void setHotelNo(String hotelNo) {
        this.hotelNo = hotelNo == null ? null : hotelNo.trim();
    }

    public String getConsumeComment() {
        return consumeComment;
    }

    public void setConsumeComment(String consumeComment) {
        this.consumeComment = consumeComment == null ? null : consumeComment.trim();
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
        sb.append(", consumeId=").append(consumeId);
        sb.append(", consumeTypeCode=").append(consumeTypeCode);
        sb.append(", consumeItemCode=").append(consumeItemCode);
        sb.append(", consumeItemDetail=").append(consumeItemDetail);
        sb.append(", consumeMoney=").append(consumeMoney);
        sb.append(", businessDay=").append(businessDay);
        sb.append(", roomNo=").append(roomNo);
        sb.append(", roomName=").append(roomName);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", hotelNo=").append(hotelNo);
        sb.append(", consumeComment=").append(consumeComment);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", deleted=").append(deleted);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }

    public BigDecimal getOffsetMoney() {
        return offsetMoney;
    }

    public void setOffsetMoney(BigDecimal offsetMoney) {
        this.offsetMoney = offsetMoney;
    }

    public Long getOffsetId() {
        return offsetId;
    }

    public void setOffsetId(Long offsetId) {
        this.offsetId = offsetId;
    }
}