package com.ywt.laike.hotel.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author ywt
 */
@Setter
@Getter
public class LoginDTO {
    @NotBlank(message = "手机号不能为空")
    private String mobileNum;
    @NotBlank(message = "密码不能为空")
    private String userPwd;
//    验证码
    private String verificationCode;
}
