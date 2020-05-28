package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUserByMobileNum(String mobileNum);

    /**
     * 检查手机和是否已被注册
     * @param mobileNum
     * @return
     */
    Integer countUserByMobileNum(String mobileNum);

    List<User> listUsersByHotelNo(String hotelNo);
}