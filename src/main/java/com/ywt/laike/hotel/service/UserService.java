package com.ywt.laike.hotel.service;

import com.ywt.laike.hotel.dto.ChangePwdDTO;
import com.ywt.laike.hotel.model.User;
import com.ywt.laike.hotel.dto.RegisterDTO;

import java.util.List;
import java.util.Set;

/**
 * @author ywt
 */

public interface UserService {
    User saveUser(User user);

    User updateUser(User user);

    Integer changePwd(ChangePwdDTO changePwdDTO);

    User getUserById(Long userId);

    User getUserByUserName(String userName);

    User getUserByIdCardNo(String idCardNo);

    User getUserByMobileNum(String mobileNum);

    List<User> listUserByHotelNo(String hotelNo);

    /**
     * 酒店所有者注册并添加酒店
     * @param registerDTO
     * @return
     */
    User registerAndAddHotel(RegisterDTO registerDTO) ;

    /**
     * 根据用户名查询角色
     * @param userName
     * @return
     */
    Set<String> listRolesByUserName(String userName);

    /**
     * 根据手机号查询角色
     * @param mobileNum
     * @return
     */
    Set<String> listRolesByMobileNum(String mobileNum);

    /**
     * 根据用户名查询权限
     * @param userName
     * @return
     */
    Set<String> listUserPermissionsByUserName(String userName);

    /**
     * 根据手机号查询权限
     * @param mobileNum
     * @return
     */
    Set<String> listUserPermissionsByMobileNum(String mobileNum);
}
