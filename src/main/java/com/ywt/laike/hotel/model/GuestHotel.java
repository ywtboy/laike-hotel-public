package com.ywt.laike.hotel.model;

import java.io.Serializable;
import java.util.Date;

public class GuestHotel implements Serializable {
    private Long guestHotelId;

    private String guestName;

    private String guestTel;

    private String certificateNo;

    private Boolean vip;

    private String hotelNo;

    private Boolean deleted;

    private Date gmtCreate;

    private Date gmtModified;

    private static final long serialVersionUID = 1L;

    public Long getGuestHotelId() {
        return guestHotelId;
    }

    public void setGuestHotelId(Long guestHotelId) {
        this.guestHotelId = guestHotelId;
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

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo == null ? null : certificateNo.trim();
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public String getHotelNo() {
        return hotelNo;
    }

    public void setHotelNo(String hotelNo) {
        this.hotelNo = hotelNo == null ? null : hotelNo.trim();
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
        sb.append(", guestHotelId=").append(guestHotelId);
        sb.append(", guestName=").append(guestName);
        sb.append(", guestTel=").append(guestTel);
        sb.append(", certificateNo=").append(certificateNo);
        sb.append(", vip=").append(vip);
        sb.append(", hotelNo=").append(hotelNo);
        sb.append(", deleted=").append(deleted);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }
}