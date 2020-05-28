package com.ywt.laike.hotel.service.impl;

import com.ywt.laike.hotel.dao.*;
import com.ywt.laike.hotel.dto.ChangePwdDTO;
import com.ywt.laike.hotel.dto.RegisterDTO;
import com.ywt.laike.hotel.model.*;
import com.ywt.laike.hotel.service.Exception.ValueDuplicationException;
import com.ywt.laike.hotel.service.UserService;
import com.ywt.laike.hotel.util.PasswordUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ywt
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Override
    public User saveUser(User user) {
        user.setUserName(user.getMobileNum());
        user.setUserType("hotel");
        user.getRoles();
        Set roleIdsSet = user.getRoles();
        roleIdsSet.add("2");
        user.setRoles(roleIdsSet);// 酒店人员
        // 加密
        PasswordUtils.encryptPassword(user);
        Date currDate = new Date();
        user.setGmtCreate(currDate);
        user.setGmtModified(currDate);
        userMapper.insert(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public Integer changePwd(ChangePwdDTO changePwdDTO) {
        System.out.println(changePwdDTO);
        User user = userMapper.selectByPrimaryKey(changePwdDTO.getUserId());
        if (null == user) {
            throw new UnknownAccountException();
        }
        if (!PasswordUtils.isMatchPassword(user, changePwdDTO.getOldPwd())) {
            throw new IncorrectCredentialsException("旧密码错误");
        }
        user.setUserPwd(changePwdDTO.getNewPwd());
        PasswordUtils.encryptPassword(user);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public User getUserByUserName(String userName) {
        return null;
    }

    @Override
    public User getUserByIdCardNo(String idCardNo) {
        return null;
    }

    @Override
    public User getUserByMobileNum(String mobileNum) {

        return userMapper.getUserByMobileNum(mobileNum);
    }

    @Override
    public List<User> listUserByHotelNo(String hotelNo) {
        return userMapper.listUsersByHotelNo(hotelNo).stream()
                .filter(user -> "hotel".equals(user.getUserType())).collect(Collectors.toList());
    }

    /**
     * 酒店所有者注册并添加酒店
     *
     * @param registerDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User registerAndAddHotel(RegisterDTO registerDTO) {

        Integer hotelCount = userMapper.countUserByMobileNum(registerDTO.getMobileNum());
        if (hotelCount > 0) {
            throw new ValueDuplicationException("手机号已被注册");
        }
        Date currDate = new Date();
        // 生成宾馆编号
        Integer count = hotelMapper.countHotelByProvince(registerDTO.getProvinceCode());
        String sequence = String.format("%03d", count + 1);
        String hotelNo = registerDTO.getProvinceCode() + sequence;

        User user = new User();
        user.setRealName(registerDTO.getRealName());
        user.setMobileNum(registerDTO.getMobileNum());
        user.setUserName(registerDTO.getMobileNum());
        user.setAddress(registerDTO.getAddress());
        user.setUserType("hotel_owner");
        user.setUserPwd(registerDTO.getUserPwd());
        // 加密
        PasswordUtils.encryptPassword(user);
        user.setHotelNo(hotelNo);
        // 设置默认角色
        Set<String> roleIdsList = new HashSet<>(2);
        roleIdsList.add("2"); // 酒店人员
        roleIdsList.add("3"); // 酒店所有者
        user.setRoles(roleIdsList);
        user.setGmtCreate(currDate);
        user.setGmtModified(currDate);
        userMapper.insert(user);

        Hotel hotel = new Hotel();
        hotel.setHotelNo(hotelNo);
        hotel.setHotelName(registerDTO.getHotelName());
        hotel.setCountryCode(registerDTO.getCountryCode());
        hotel.setProvinceCode(registerDTO.getProvinceCode());
        hotel.setCityCode(registerDTO.getCityCode());
        hotel.setDistrictCode(registerDTO.getDistrictCode());
        hotel.setAddress(registerDTO.getAddress());
        hotel.setHotline(registerDTO.getHotline());
        hotel.setUserId(user.getUserId());
        // 设置到期时间 新注册试用 3天
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(3);
        Date date = Date.from(localDateTime.atZone(ZoneId.of("Asia/Shanghai")).toInstant());
        hotel.setActivationEndTime(date);
        hotel.setGmtCreate(currDate);
        hotel.setGmtModified(currDate);
        hotelMapper.insert(hotel);
        return user;
    }

    /**
     * 根据用户名查询角色
     *
     * @param userName
     * @return
     */
    @Override
    public Set<String> listRolesByUserName(String userName) {
        return null;
    }

    /**
     * 根据手机号查询角色
     *
     * @param mobileNum
     * @return
     */
    @Override
    public Set<String> listRolesByMobileNum(String mobileNum) {
        return null;
    }

    /**
     * 根据用户名查询权限
     *
     * @param userName
     * @return
     */
    @Override
    public Set<String> listUserPermissionsByUserName(String userName) {
        return null;
    }

    /**
     * 根据手机号查询权限
     *
     * @param mobileNum
     * @return
     */
    @Override
    public Set<String> listUserPermissionsByMobileNum(String mobileNum) {
        return null;
    }
}
