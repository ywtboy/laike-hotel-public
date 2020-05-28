package com.ywt.laike.hotel.web.controller;

import com.ywt.laike.hotel.model.Guest;
import com.ywt.laike.hotel.model.OrderRoom;
import com.ywt.laike.hotel.query.GuestHotelQuery;
import com.ywt.laike.hotel.query.OrderRoomQuery;
import com.ywt.laike.hotel.service.GuestService;
import com.ywt.laike.hotel.web.ResultMessage;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ywt
 */
@RestController
@RequestMapping("/api/guest")
public class GuestController extends BaseController {
    @Autowired
    private GuestService guestService;

    @GetMapping
    @RequiresAuthentication
    public ResultMessage<List<Guest>> listGuestsOfHotel(GuestHotelQuery guestHotelQuery) {
        guestHotelQuery.setHotelNo(getCurrHotel().getHotelNo());
        ResultMessage<List<Guest>> msg = new ResultMessage<>(0, "查询成功");
        msg.setData(guestService.listGuestsOfHotel(guestHotelQuery));
        msg.setCount(guestService.countGuestsOfHotel(guestHotelQuery));
        return msg;
    }
}
