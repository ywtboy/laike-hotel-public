package com.ywt.laike.hotel.model;

import java.io.Serializable;
import java.util.Date;

public class OrderOperationRecord extends BaseEntity implements Serializable {
    private Long orderOperationRecordId;

    private String operationCode;

    private String operationName;

    private Long operatorId;

    private String operatorName;

    private String hotelNo;

    private String orderNo;

    private String operationContent;

    private String operationBefore;

    private String operationAfter;

    private Date gmtCreate;

    private Date gmtModified;

    private static final long serialVersionUID = 1L;

    public Long getOrderOperationRecordId() {
        return orderOperationRecordId;
    }

    public void setOrderOperationRecordId(Long orderOperationRecordId) {
        this.orderOperationRecordId = orderOperationRecordId;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode == null ? null : operationCode.trim();
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName == null ? null : operationName.trim();
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

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent == null ? null : operationContent.trim();
    }

    public String getOperationBefore() {
        return operationBefore;
    }

    public void setOperationBefore(String operationBefore) {
        this.operationBefore = operationBefore == null ? null : operationBefore.trim();
    }

    public String getOperationAfter() {
        return operationAfter;
    }

    public void setOperationAfter(String operationAfter) {
        this.operationAfter = operationAfter == null ? null : operationAfter.trim();
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
        sb.append(", orderOperationRecordId=").append(orderOperationRecordId);
        sb.append(", operationCode=").append(operationCode);
        sb.append(", operationName=").append(operationName);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", hotelNo=").append(hotelNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", operationContent=").append(operationContent);
        sb.append(", operationBefore=").append(operationBefore);
        sb.append(", operationAfter=").append(operationAfter);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }
}