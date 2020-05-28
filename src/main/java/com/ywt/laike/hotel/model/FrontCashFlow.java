package com.ywt.laike.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FrontCashFlow implements Serializable {
    private Long frontCashFlowId;

    private String flowType;

    @NotNull(message = "金额不能为空")
    @Min(value = 1, message = "金额必须大于0")
    private BigDecimal flowMoney;

    private BigDecimal balance;

    private String flowClass;

    private String itemCode;

    private String itemName;

    private String itemDetail;

    private String hotelNo;

    private String orderNo;

    private Long operatorId;

    private String operatorName;

    private Date gmtCreate;

    private Date gmtModified;

    /**
     * 有时需要验证密码
     */
    private String userPwd;

    private static final long serialVersionUID = 1L;

    public Long getFrontCashFlowId() {
        return frontCashFlowId;
    }

    public void setFrontCashFlowId(Long frontCashFlowId) {
        this.frontCashFlowId = frontCashFlowId;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType == null ? null : flowType.trim();
    }

    public BigDecimal getFlowMoney() {
        return flowMoney;
    }

    public void setFlowMoney(BigDecimal flowMoney) {
        this.flowMoney = flowMoney;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail == null ? null : itemDetail.trim();
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
        sb.append(", frontCashFlowId=").append(frontCashFlowId);
        sb.append(", flowType=").append(flowType);
        sb.append(", flowMoney=").append(flowMoney);
        sb.append(", balance=").append(balance);
        sb.append(", itemCode=").append(itemCode);
        sb.append(", itemName=").append(itemName);
        sb.append(", itemDetail=").append(itemDetail);
        sb.append(", hotelNo=").append(hotelNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", userPwd=").append(userPwd);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }

    public String getFlowClass() {
        return flowClass;
    }

    public void setFlowClass(String flowClass) {
        this.flowClass = flowClass;
    }

    @JsonIgnore
    public String getUserPwd() {
        return userPwd;
    }
    @JsonProperty
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}