package com.ywt.laike.hotel.web.controller;

import com.ywt.laike.hotel.dto.LoginDTO;
import com.ywt.laike.hotel.dto.RegisterDTO;
import com.ywt.laike.hotel.model.User;
import com.ywt.laike.hotel.service.UserService;
import com.ywt.laike.hotel.web.ResultMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ywt
 */
@RestController
@RequestMapping("/api/")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResultMessage register(@RequestBody @Validated RegisterDTO registerDTO) {
        ResultMessage msg = new ResultMessage(0, "注册成功");
        userService.registerAndAddHotel(registerDTO);
        return msg;
    }

    @PostMapping("/login")
    public ResultMessage<User> login(@RequestBody @Validated LoginDTO loginDTO) {
        ResultMessage<User> msg = new ResultMessage<>(0, "登录成功");
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        if (!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(loginDTO.getMobileNum(),
                    loginDTO.getUserPwd());
            currentUser.login(token);
        } else {
            msg.setCode(101);
            msg.setMsg("请勿重复登录");
        }
        User currUser = (User) session.getAttribute("currUser");
        msg.setData(currUser);
        return msg;
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public ResultMessage<User> logout() {
        ResultMessage<User> msg = new ResultMessage<>(0, "退出成功");
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return msg;
    }

    @RequiresAuthentication
    @GetMapping("/currUser")
    public ResultMessage<User> getCurrUser() {
        ResultMessage<User> msg = new ResultMessage<>(0, "获取成功");
        Session session = SecurityUtils.getSubject().getSession();
        User currUser = (User) session.getAttribute("currUser");
        msg.setData(currUser);
        return msg;
    }
}
