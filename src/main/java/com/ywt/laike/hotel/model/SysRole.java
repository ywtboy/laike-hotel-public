package com.ywt.laike.hotel.model;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

public class SysRole implements Serializable {
    private Long id;

    private String role;

    private String roleName;

    private String description;

    private String roleType;

    private String resourceIds;

    private String hotelNo;

    private Boolean available = true;

    private Long operatorId;

    private String operatorName;

    private Date gmtCreate;

    private Date gmtModified;

    /**
     * 拥有的资源 把数据库以 id,id,id...形式存储的数据转化为list 方便应用使用
     */
    private Set<String> resourceIdsList;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    public String getHotelNo() {
        return hotelNo;
    }

    public void setHotelNo(String hotelNo) {
        this.hotelNo = hotelNo == null ? null : hotelNo.trim();
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
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
        sb.append(", id=").append(id);
        sb.append(", role=").append(role);
        sb.append(", roleName=").append(roleName);
        sb.append(", description=").append(description);
        sb.append(", roleType=").append(roleType);
        sb.append(", resourceIds=").append(resourceIds);
        sb.append(", hotelNo=").append(hotelNo);
        sb.append(", available=").append(available);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }

    public Set<String> getResourceIdsList() {
        // 如果资源List为null 则创建
        if (resourceIdsList == null) {
            resourceIdsList = new HashSet<>();
        }
        // 如果资源列表字符串为null 则结束
        if (StringUtils.isEmpty(resourceIds)) {
            return resourceIdsList;
        }
        // 把资源列表字符串转换成数组
        String[] resourceStrs = resourceIds.split(",");
        // 把资源字符串数组转化成List
        for (String resourceStr : resourceStrs) {
            if (!StringUtils.isEmpty(resourceStr)) {
                resourceIdsList.add(resourceStr);
            }
        }
        return resourceIdsList;
    }

    public void setResourceIdsList(Set<String> resourceIdsList) {
        this.resourceIdsList = resourceIdsList;
        if (CollectionUtils.isEmpty(resourceIdsList)) {
            // setResourceIds("");
            return;
        }
        // 把资源List转化成角色字符串
        StringBuilder s = new StringBuilder();
        for (String resourceId : resourceIdsList) {
            s.append(resourceId);
            s.append(",");
        }
        setResourceIds(s.toString());
    }

}