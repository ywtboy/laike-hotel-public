package com.ywt.laike.hotel.model;

import java.io.Serializable;
import java.util.Date;

public class DicCommon implements Serializable {
    private Long dicCommonId;

    private String dicCommonCode;

    private String dicCommonName;

    private Long dicTypeId;

    private Long parentId;

    private Integer dicLevel;

    private Integer sortNo;

    private Boolean deleted;

    private Date gmtCreate;

    private Date gmtModified;
    /**
     * 非数据库字段
     */
    private String dicTypeName;

    private static final long serialVersionUID = 1L;

    public Long getDicCommonId() {
        return dicCommonId;
    }

    public void setDicCommonId(Long dicCommonId) {
        this.dicCommonId = dicCommonId;
    }

    public String getDicCommonCode() {
        return dicCommonCode;
    }

    public void setDicCommonCode(String dicCommonCode) {
        this.dicCommonCode = dicCommonCode == null ? null : dicCommonCode.trim();
    }

    public String getDicCommonName() {
        return dicCommonName;
    }

    public void setDicCommonName(String dicCommonName) {
        this.dicCommonName = dicCommonName == null ? null : dicCommonName.trim();
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

    public Integer getDicLevel() {
        return dicLevel;
    }

    public void setDicLevel(Integer dicLevel) {
        this.dicLevel = dicLevel;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
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
        sb.append(", dicCommonId=").append(dicCommonId);
        sb.append(", dicCommonCode=").append(dicCommonCode);
        sb.append(", dicCommonName=").append(dicCommonName);
        sb.append(", dicTypeId=").append(dicTypeId);
        sb.append(", parentId=").append(parentId);
        sb.append(", dicLevel=").append(dicLevel);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", deleted=").append(deleted);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }

    public String getDicTypeName() {
        return dicTypeName;
    }

    public void setDicTypeName(String dicTypeName) {
        this.dicTypeName = dicTypeName;
    }
}