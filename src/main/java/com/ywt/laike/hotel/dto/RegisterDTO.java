package com.ywt.laike.hotel.dto;

import com.ywt.laike.hotel.query.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

/**
 * @author ywt
 */
@Setter
@Getter
@ToString
public class RegisterDTO {
    @NotBlank(message = "真实姓名不能为空")
    private String realName;
    @NotBlank(message = "手机号不能为空")
    private String mobileNum;
    @NotBlank(message = "密码不能为空")
    private String userPwd;
    @NotBlank(message = "酒店/宾馆名称不能为空")
    private String hotelName;
    @NotBlank(message = "国家代码不能为空")
    private String countryCode;
    @NotBlank(message = "身份代码不能为空")
    private String provinceCode;
    @NotBlank(message = "城市代码不能为空")
    private String cityCode;
    @NotBlank(message = "区县代码不能为空")
    private String districtCode;
    @NotBlank(message = "具体地址不能为空")
    private String address;
    @NotBlank(message = "热线电话不能为空")
    private String hotline;

}
