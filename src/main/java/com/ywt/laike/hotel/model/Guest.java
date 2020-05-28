package com.ywt.laike.hotel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Guest implements Serializable {
    private Long guestId;

    private String guestName;

    private String guestTel;

    private String certificateType;

    private String certificateNo;

    private String guestSex;

    private String guestNation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date guestBirthday;

    private String guestAddress;

    private String guestPhoto;

    private Boolean deleted;

    private Date gmtCreate;

    private Date gmtModified;

    private static final long serialVersionUID = 1L;

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
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

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType == null ? null : certificateType.trim();
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo == null ? null : certificateNo.trim();
    }

    public String getGuestSex() {
        return guestSex;
    }

    public void setGuestSex(String guestSex) {
        this.guestSex = guestSex == null ? null : guestSex.trim();
    }

    public String getGuestNation() {
        return guestNation;
    }

    public void setGuestNation(String guestNation) {
        this.guestNation = guestNation == null ? null : guestNation.trim();
    }

    public Date getGuestBirthday() {
        return guestBirthday;
    }

    public void setGuestBirthday(Date guestBirthday) {
        this.guestBirthday = guestBirthday;
    }

    public String getGuestAddress() {
        return guestAddress;
    }

    public void setGuestAddress(String guestAddress) {
        this.guestAddress = guestAddress == null ? null : guestAddress.trim();
    }

    public String getGuestPhoto() {
        return guestPhoto;
    }

    public void setGuestPhoto(String guestPhoto) {
        this.guestPhoto = guestPhoto == null ? null : guestPhoto.trim();
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
        sb.append(", guestId=").append(guestId);
        sb.append(", guestName=").append(guestName);
        sb.append(", guestTel=").append(guestTel);
        sb.append(", certificateType=").append(certificateType);
        sb.append(", certificateNo=").append(certificateNo);
        sb.append(", guestSex=").append(guestSex);
        sb.append(", guestNation=").append(guestNation);
        sb.append(", guestBirthday=").append(guestBirthday);
        sb.append(", guestAddress=").append(guestAddress);
        sb.append(", guestPhoto=").append(guestPhoto);
        sb.append(", deleted=").append(deleted);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }
}