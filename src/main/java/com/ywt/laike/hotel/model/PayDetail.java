package com.ywt.laike.hotel.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayDetail implements Serializable {
    private Long payDetailId;

    private String tableName;

    private Long pkId;

    @NotBlank(message = "支付方式不能为空")
    private String payMethodCode;

    @Min(value = 1, message = "不能小于0")
    private BigDecimal payMoney;
    /**
     * 已冲账金额
     */
    private BigDecimal offsetMoney;
    /**
     * 被冲账id
     */
    private Long offsetId;

    private Boolean deleted;

    private Date gmtCreate;

    private Date gmtModified;
    /**
     * 收入/支出
     */
    private String payType;

    private static final long serialVersionUID = 1L;

    public Long getPayDetailId() {
        return payDetailId;
    }

    public void setPayDetailId(Long payDetailId) {
        this.payDetailId = payDetailId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getPayMethodCode() {
        return payMethodCode;
    }

    public void setPayMethodCode(String payMethodCode) {
        this.payMethodCode = payMethodCode == null ? null : payMethodCode.trim();
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
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
        sb.append(", payDetailId=").append(payDetailId);
        sb.append(", tableName=").append(tableName);
        sb.append(", pkId=").append(pkId);
        sb.append(", payMethodCode=").append(payMethodCode);
        sb.append(", payMoney=").append(payMoney);
        sb.append(", deleted=").append(deleted);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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