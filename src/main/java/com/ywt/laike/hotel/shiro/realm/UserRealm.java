package com.ywt.laike.hotel.shiro.realm;

import com.ywt.laike.hotel.dao.HotelMapper;
import com.ywt.laike.hotel.dao.SysResourceMapper;
import com.ywt.laike.hotel.dao.SysRoleMapper;
import com.ywt.laike.hotel.dao.UserMapper;
import com.ywt.laike.hotel.model.Hotel;
import com.ywt.laike.hotel.model.SysResource;
import com.ywt.laike.hotel.model.SysRole;
import com.ywt.laike.hotel.model.User;
import com.ywt.laike.hotel.util.PasswordUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ywt
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取身份 手机号
        String mobileNum = (String) principals.getPrimaryPrincipal();
        User user = userMapper.getUserByMobileNum(mobileNum);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<SysRole> sysRoles = sysRoleMapper.listRolesByIds(user.getRoles());
        // 设置当前用户角色
        authorizationInfo.setRoles(getRoles(sysRoles));
        // 设置当前用户权限
        authorizationInfo.setStringPermissions(getPermissions(sysRoles));
        return authorizationInfo;
    }


    private Set<String> getRoles(List<SysRole> sysRoles) {
        Set<String> roles = new HashSet<>();
        sysRoles.forEach(sysRole -> roles.add(sysRole.getRole()));
        return roles;
    }

    private Set<String> getPermissions(List<SysRole> sysRoles) {
        Set<String> resourceIds = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        sysRoles.forEach(sysRole -> resourceIds.addAll(sysRole.getResourceIdsList()));
        List<SysResource> sysResources = sysResourceMapper.listResourceByIds(resourceIds);
        sysResources.forEach(sysResource -> permissions.add(sysResource.getPermission()));
        return permissions;
    }

    private Boolean isActivated(String hotelNo) {
        Hotel currHotel = hotelMapper.getByHotelNo(hotelNo);
        return new Date().getTime() < currHotel.getActivationEndTime().getTime();
    }



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取身份 手机号
        String mobileNum = (String) token.getPrincipal();
        // 获取用户
        User user = userMapper.getUserByMobileNum(mobileNum);
        if (null == user) {
            throw new UnknownAccountException(); // 手机号错误
        }
        if (user.getLocked()) {
            throw new LockedAccountException(); // 账号被锁定
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getMobileNum(), user.getUserPwd(), getName()
        );
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt() + PasswordUtils.mySalt));
        Hotel currHotel = hotelMapper.getByHotelNo(user.getHotelNo());
        user.setCurrHotel(currHotel);
        SecurityUtils.getSubject().getSession().setAttribute("currUser", user);
        return authenticationInfo;
    }
}
