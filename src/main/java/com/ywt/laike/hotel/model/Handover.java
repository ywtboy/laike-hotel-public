package com.ywt.laike.hotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Handover implements Serializable {
    private Long handoverId;

    private Long offGoingUserId;

    private String offGoingRealName;

    private Long onComingUserId;

    private String onComingRealName;

    private Date handoverTime;

    private BigDecimal cashMoney;

    private Boolean offGoingOk;

    private Boolean onComingOk;

    private String handoverComment;

    private String hotelNo;

    private Boolean mandatory = false;

    private Date gmtCreate;

    private Date gmtModified;


    private String onComingTel;

    private static final long serialVersionUID = 1L;

    public Long getHandoverId() {
        return handoverId;
    }

    public void setHandoverId(Long handoverId) {
        this.handoverId = handoverId;
    }

    public Long getOffGoingUserId() {
        return offGoingUserId;
    }

    public void setOffGoingUserId(Long offGoingUserId) {
        this.offGoingUserId = offGoingUserId;
    }

    public String getOffGoingRealName() {
        return offGoingRealName;
    }

    public void setOffGoingRealName(String offGoingRealName) {
        this.offGoingRealName = offGoingRealName == null ? null : offGoingRealName.trim();
    }

    public Long getOnComingUserId() {
        return onComingUserId;
    }

    public void setOnComingUserId(Long onComingUserId) {
        this.onComingUserId = onComingUserId;
    }

    public String getOnComingRealName() {
        return onComingRealName;
    }

    public void setOnComingRealName(String onComingRealName) {
        this.onComingRealName = onComingRealName == null ? null : onComingRealName.trim();
    }

    public Date getHandoverTime() {
        return handoverTime;
    }

    public void setHandoverTime(Date handoverTime) {
        this.handoverTime = handoverTime;
    }

    public BigDecimal getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(BigDecimal cashMoney) {
        this.cashMoney = cashMoney;
    }

    public Boolean getOffGoingOk() {
        return offGoingOk;
    }

    public void setOffGoingOk(Boolean offGoingOk) {
        this.offGoingOk = offGoingOk;
    }

    public Boolean getOnComingOk() {
        return onComingOk;
    }

    public void setOnComingOk(Boolean onComingOk) {
        this.onComingOk = onComingOk;
    }

    public String getHandoverComment() {
        return handoverComment;
    }

    public void setHandoverComment(String handoverComment) {
        this.handoverComment = handoverComment == null ? null : handoverComment.trim();
    }

    public String getHotelNo() {
        return hotelNo;
    }

    public void setHotelNo(String hotelNo) {
        this.hotelNo = hotelNo == null ? null : hotelNo.trim();
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
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
        sb.append(", handoverId=").append(handoverId);
        sb.append(", offGoingUserId=").append(offGoingUserId);
        sb.append(", offGoingRealName=").append(offGoingRealName);
        sb.append(", onComingUserId=").append(onComingUserId);
        sb.append(", onComingRealName=").append(onComingRealName);
        sb.append(", handoverTime=").append(handoverTime);
        sb.append(", cashMoney=").append(cashMoney);
        sb.append(", offGoingOk=").append(offGoingOk);
        sb.append(", onComingOk=").append(onComingOk);
        sb.append(", handoverComment=").append(handoverComment);
        sb.append(", hotelNo=").append(hotelNo);
        sb.append(", mandatory=").append(mandatory);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }

    public String getOnComingTel() {
        return onComingTel;
    }

    public void setOnComingTel(String onComingTel) {
        this.onComingTel = onComingTel;
    }
}