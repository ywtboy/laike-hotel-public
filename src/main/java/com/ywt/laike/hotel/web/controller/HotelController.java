package com.ywt.laike.hotel.web.controller;

import com.ywt.laike.hotel.service.HotelService;
import com.ywt.laike.hotel.web.ResultMessage;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ywt
 */
@RestController
@RequestMapping("api/hotel")
@RequiresAuthentication
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping("/{hotelNo}")
    public ResultMessage getHotel(@PathVariable String hotelNo) {
        ResultMessage msg = new ResultMessage();
        msg.setCode(0);
        msg.setMsg("查询成功" + hotelNo);
        msg.setData(hotelService.getHotelByNo(hotelNo));
        return msg;
    }

    @GetMapping()
    public ResultMessage getHotel() {
        ResultMessage msg = new ResultMessage();
        msg.setCode(0);
        msg.setMsg("查询成功");
        msg.setData(hotelService.listHotels());
        return msg;
    }

    @PostMapping
    public ResultMessage addHotel() {
        ResultMessage msg = new ResultMessage();
        return msg;
    }

    @DeleteMapping("/{hotelNo}")
    public ResultMessage deleteHotel(@PathVariable String hotelNo) {
        ResultMessage msg = new ResultMessage();
        return msg;
    }

    /**
     * 修改宾馆信息
     * @param hotelNo
     * @return
     */
    @PutMapping("/{hotelNo}")
    public ResultMessage updateHotel(@PathVariable String hotelNo) {
        ResultMessage msg = new ResultMessage();
        return msg;
    }
}
