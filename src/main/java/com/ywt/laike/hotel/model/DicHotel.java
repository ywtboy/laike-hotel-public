package com.ywt.laike.hotel.model;

import java.io.Serializable;
import java.util.Date;

public class DicHotel implements Serializable {
    private Long dicHotelId;

    private String dicHotelCode;

    private String dicHotelCommonCode;

    private String dicHotelName;

    private Long dicTypeId;

    private Long parentId;

    private Integer sortNo;

    private Long hotelNo;

    private Byte isLocked;

    private Boolean deleted;

    private Date gmtCreate;

    private Date gmtModified;

    private static final long serialVersionUID = 1L;

    public Long getDicHotelId() {
        return dicHotelId;
    }

    public void setDicHotelId(Long dicHotelId) {
        this.dicHotelId = dicHotelId;
    }

    public String getDicHotelCode() {
        return dicHotelCode;
    }

    public void setDicHotelCode(String dicHotelCode) {
        this.dicHotelCode = dicHotelCode == null ? null : dicHotelCode.trim();
    }

    public String getDicHotelCommonCode() {
        return dicHotelCommonCode;
    }

    public void setDicHotelCommonCode(String dicHotelCommonCode) {
        this.dicHotelCommonCode = dicHotelCommonCode == null ? null : dicHotelCommonCode.trim();
    }

    public String getDicHotelName() {
        return dicHotelName;
    }

    public void setDicHotelName(String dicHotelName) {
        this.dicHotelName = dicHotelName == null ? null : dicHotelName.trim();
    }

    public Long getDicTypeId() {
        return dicTypeId;
    }

    public void setDicTypeId(Long dicTypeId) {
        this.dicTypeId = dicTypeId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Long getHotelNo() {
        return hotelNo;
    }

    public void setHotelNo(Long hotelNo) {
        this.hotelNo = hotelNo;
    }

    public Byte getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Byte isLocked) {
        this.isLocked = isLocked;
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
        sb.append(", dicHotelId=").append(dicHotelId);
        sb.append(", dicHotelCode=").append(dicHotelCode);
        sb.append(", dicHotelCommonCode=").append(dicHotelCommonCode);
        sb.append(", dicHotelName=").append(dicHotelName);
        sb.append(", dicTypeId=").append(dicTypeId);
        sb.append(", parentId=").append(parentId);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", hotelNo=").append(hotelNo);
        sb.append(", isLocked=").append(isLocked);
        sb.append(", deleted=").append(deleted);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }
}