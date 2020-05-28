package com.ywt.laike.hotel.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author ywt
 */
@Setter
@Getter
@ToString
public class ChangePwdDTO {
    private Long userId;
    @NotBlank(message = "新密码不能为空")
    private String newPwd;
    @NotBlank(message = "旧密码不能为空")
    private String oldPwd;
}
