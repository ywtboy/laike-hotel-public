package com.ywt.laike.hotel.web.controller;

import com.ywt.laike.hotel.model.Hotel;
import com.ywt.laike.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: ywt
 */
@RestController
public class TestController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping("test")
    public String test() {
        return "你好";
    }

    @RequestMapping("add")
    public Integer add(Hotel hotel){
        System.out.println("酒店名称：" + hotel.getHotelName());
        hotel.setUserId(102L);
        Date date = new Date();
        hotel.setGmtCreate(date);
        hotel.setGmtModified(date);
        return hotelService.saveHotel(hotel);
    }

    @RequestMapping("get")
    public Hotel get(Long id) {
        return hotelService.getHotelById(id);
    }

}
