package com.ywt.laike.hotel.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author ywt
 */
@Setter
@Getter
public class HandoverDTO {
    private Long offGoingUserId;
    private String offGoingRealName;
    @NotBlank(message = "交班人密码不能为空")
    private String offGoingPwd;
    @NotBlank(message = "接班人手机号不能为空")
    private String onComingMobile;
    @NotBlank(message = "接班人密码不能为空")
    private String onComingPwd;
    private String hotelNo;
}
