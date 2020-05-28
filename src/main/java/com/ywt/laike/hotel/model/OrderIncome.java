package com.ywt.laike.hotel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderIncome extends BaseEntity implements Serializable {
    private Long incomeId;

    private String incomeTypeCode;

    private String incomeClassCode;

//    @NotNull(message = "收款金额不能为0")
//    @Min(value = 1, message = "收款金额必须大于0")
    private BigDecimal incomeMoney;

    private String orderNo;

    private String hotelNo;

    private String incomeComment;

    @Valid
    private List<PayDetail> payDetails;

    private Long operatorId;

    private String operatorName;

    private Boolean deleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    private Date gmtModified;

    /**
     * 被冲账 id 数据库中没有
     */
    private Long offsetId;

    private static final long serialVersionUID = 1L;

    public Long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Long incomeId) {
        this.incomeId = incomeId;
    }

    public String getIncomeTypeCode() {
        return incomeTypeCode;
    }

    public void setIncomeTypeCode(String incomeTypeCode) {
        this.incomeTypeCode = incomeTypeCode == null ? null : incomeTypeCode.trim();
    }

    public String getIncomeClassCode() {
        return incomeClassCode;
    }

    public void setIncomeClassCode(String incomeClassCode) {
        this.incomeClassCode = incomeClassCode == null ? null : incomeClassCode.trim();
    }

    public BigDecimal getIncomeMoney() {
        return incomeMoney;
    }

    public void setIncomeMoney(BigDecimal incomeMoney) {
        this.incomeMoney = incomeMoney;
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

    public String getIncomeComment() {
        return incomeComment;
    }

    public void setIncomeComment(String incomeComment) {
        this.incomeComment = incomeComment == null ? null : incomeComment.trim();
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
        sb.append(", incomeId=").append(incomeId);
        sb.append(", incomeTypeCode=").append(incomeTypeCode);
        sb.append(", incomeClassCode=").append(incomeClassCode);
        sb.append(", incomeMoney=").append(incomeMoney);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", hotelNo=").append(hotelNo);
        sb.append(", incomeComment=").append(incomeComment);
        sb.append(", payDetails=").append(payDetails);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", deleted=").append(deleted);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }

    public List<PayDetail> getPayDetails() {
        return payDetails;
    }

    public void setPayDetails(List<PayDetail> payDetails) {
        this.payDetails = payDetails;
    }

    public Long getOffsetId() {
        return offsetId;
    }

    public void setOffsetId(Long offsetId) {
        this.offsetId = offsetId;
    }
}