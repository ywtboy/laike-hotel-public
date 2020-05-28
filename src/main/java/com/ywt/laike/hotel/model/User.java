package com.ywt.laike.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;

@Setter
@Getter
public class User implements Serializable {
    private Long userId;


    private String userName;

    private String realName;

    private String nickName;
    @NotBlank(message = "手机号不能为空")
    private String mobileNum;

    private Date birthday;

    private String userSex;

    private String idCardNo;

    private String address;

    private String userType;

    private String hotelNo;

    @JsonIgnore // 不进行序列化
    private String salt;

    @NotBlank(message = "密码不能为空")
//    @JsonIgnore // 不进行序列化
    private String userPwd;

    @JsonIgnore // 不进行序列化
    private String roleIds;

    private Date lastLoginTime;

    private Boolean locked = false;

    private Long operatorId;

    private String operatorName;

    private Date gmtCreate;

    private Date gmtModified;

    private Set<String> roles;

    private Hotel currHotel;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum == null ? null : mobileNum.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo == null ? null : idCardNo.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getHotelNo() {
        return hotelNo;
    }

    public void setHotelNo(String hotelNo) {
        this.hotelNo = hotelNo == null ? null : hotelNo.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    @JsonIgnore // 不进行序列化
    public String getUserPwd() {
        return userPwd;
    }

    @JsonProperty
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds == null ? null : roleIds.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
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
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", realName=").append(realName);
        sb.append(", nickName=").append(nickName);
        sb.append(", mobileNum=").append(mobileNum);
        sb.append(", birthday=").append(birthday);
        sb.append(", userSex=").append(userSex);
        sb.append(", idCardNo=").append(idCardNo);
        sb.append(", address=").append(address);
        sb.append(", userType=").append(userType);
        sb.append(", hotelNo=").append(hotelNo);
        sb.append(", salt=").append(salt);
        sb.append(", userPwd=").append(userPwd);
        sb.append(", roleIds=").append(roleIds);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", locked=").append(locked);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }

    public Set<String> getRoles() {
        roles = new HashSet<String>();
        // 如果角色列表字符串为null 则结束
        if (StringUtils.isEmpty(roleIds)) {
            return roles;
        }
        // 把角色列表字符串转换成数组
        String[] roleIdStrs = roleIds.split(",");
        for (String roleIdStr : roleIdStrs) {
            if (!StringUtils.isEmpty(roleIdStr)) {
                roles.add(roleIdStr);
            }
        }
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
        if (CollectionUtils.isEmpty(roles)) {
            return;
        }
        // 把角色Set转化成角色字符串
        StringBuilder s = new StringBuilder();
        for (String roleId : roles) {
            s.append(roleId);
            s.append(",");
        }
        setRoleIds(s.toString());
    }

//    public List<Long> getRoleIdsList() {
//        roleIdsList = new ArrayList<Long>();
//        // 如果角色列表字符串为null 则结束
//        if (StringUtils.isEmpty(roleIds)) {
//            return roleIdsList;
//        }
//        // 把角色列表字符串转换成数组
//        String[] roleIdStrs = roleIds.split(",");
//        // 把角色字符串数组转化成List
//        for (String roleIdStr : roleIdStrs) {
//            if (!StringUtils.isEmpty(roleIdStr)) {
//                roleIdsList.add(Long.valueOf(roleIdStr));
//            }
//        }
//        return roleIdsList;
//    }
//
//    public void setRoleIdsList(List<Long> roleIdsList) {
//        this.roleIdsList = roleIdsList;
//        if (CollectionUtils.isEmpty(roleIdsList)) {
//            return;
//        }
//        // 把角色List转化成角色字符串
//        StringBuilder s = new StringBuilder();
//        for (Long roleId : roleIdsList) {
//            s.append(roleId);
//            s.append(",");
//        }
//        setRoleIds(s.toString());
//    }
}