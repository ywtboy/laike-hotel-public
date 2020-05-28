package com.ywt.laike.hotel.web.controller;

import com.ywt.laike.hotel.dto.ChangePwdDTO;
import com.ywt.laike.hotel.dto.RegisterDTO;
import com.ywt.laike.hotel.model.User;
import com.ywt.laike.hotel.service.UserService;
import com.ywt.laike.hotel.web.ResultMessage;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ywt
 */
@RestController
@RequestMapping("/api/user")
@RequiresAuthentication
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @PostMapping("/pwd")
    public ResultMessage changePwd(@RequestBody @Validated ChangePwdDTO changePwdDTO) {
        ResultMessage msg = new ResultMessage(0, "密码修改成功");
        changePwdDTO.setUserId(getCurrUser().getUserId());
        userService.changePwd(changePwdDTO);
        return msg;
    }

    @PostMapping()
    @RequiresPermissions("hotel:setting:staff")
    public ResultMessage addStaff(@RequestBody @Validated User user) {
        ResultMessage msg = new ResultMessage(0, "添加员工成功");
        user.setHotelNo(getCurrHotel().getHotelNo());
        user.setOperatorId(getCurrUser().getUserId());
        user.setOperatorName(getCurrUser().getRealName());
        userService.saveUser(user);
        return msg;
    }

    @GetMapping()
    @RequiresPermissions("hotel:setting:staff")
    public ResultMessage getHotelUsers() {
        ResultMessage msg = new ResultMessage(0, "查询员工成功");
        msg.setData(userService.listUserByHotelNo(getCurrHotel().getHotelNo()));
        return msg;
    }
}
