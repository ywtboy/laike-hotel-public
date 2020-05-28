package com.ywt.laike.hotel.model;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

public class DicType implements Serializable {
    private Long dicTypeId;

    @NotBlank(message = "字典类型代码不能为空")
    private String dicTypeCode;
    @NotBlank(message = "字典类型名称不能为空")
    private String dicTypeName;
    @NotBlank(message = "字典类型分类不能为空")
    private String dicTypeClass;

    private Boolean deleted;

    private Date gmtCreate;

    private Date gmtModified;

    private static final long serialVersionUID = 1L;

    public Long getDicTypeId() {
        return dicTypeId;
    }

    public void setDicTypeId(Long dicTypeId) {
        this.dicTypeId = dicTypeId;
    }

    public String getDicTypeCode() {
        return dicTypeCode;
    }

    public void setDicTypeCode(String dicTypeCode) {
        this.dicTypeCode = dicTypeCode == null ? null : dicTypeCode.trim();
    }

    public String getDicTypeName() {
        return dicTypeName;
    }

    public void setDicTypeName(String dicTypeName) {
        this.dicTypeName = dicTypeName == null ? null : dicTypeName.trim();
    }

    public String getDicTypeClass() {
        return dicTypeClass;
    }

    public void setDicTypeClass(String dicTypeClass) {
        this.dicTypeClass = dicTypeClass == null ? null : dicTypeClass.trim();
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
        sb.append(", dicTypeId=").append(dicTypeId);
        sb.append(", dicTypeCode=").append(dicTypeCode);
        sb.append(", dicTypeName=").append(dicTypeName);
        sb.append(", dicTypeClass=").append(dicTypeClass);
        sb.append(", deleted=").append(deleted);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }
}