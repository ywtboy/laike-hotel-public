package com.ywt.laike.hotel.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: ywt
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping("/")
    public String index(@RequestHeader(value="User-Agent") String userAgent) {
//        if (isMobile(userAgent)) {
//          return "forward:/m";
//        }
        return "forward:/index.html";
    }

    @GetMapping("/m")
    public String indexM() {
        return "forward:/m/index.html?t=m";
    }

    private boolean isMobile(String userAgent) {
        System.out.println("userAgent=" + userAgent);
        if (userAgent != null &&
                (userAgent.contains("Android") || userAgent.contains("iPhone") || userAgent.contains("iPad") )) {
            return true;
        } else {
            return false;
        }
    }
}
