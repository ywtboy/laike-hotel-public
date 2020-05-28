package com.ywt.laike.hotel.web.controller;

import com.ywt.laike.hotel.dto.RoomDTO;
import com.ywt.laike.hotel.model.Room;
import com.ywt.laike.hotel.model.User;
import com.ywt.laike.hotel.service.RoomService;
import com.ywt.laike.hotel.web.ResultMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author: ywt
 */
@RequiresAuthentication
@RestController
@RequestMapping("api/")
public class RoomController extends BaseController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/roomType")
    public ResultMessage addRoomTypeAndRoom(@RequestBody @Validated RoomDTO roomDto) {
        ResultMessage msg = new ResultMessage();
        msg.setCode(0);
        msg.setMsg("添加成功");
//        msg.setData(roomDto);
        msg.setData(roomService.saveRoomTypeAndRoom(roomDto, getCurrUser().getCurrHotel().getHotelNo()));
        return msg;
    }

    @PatchMapping("/roomType")
    public ResultMessage updateRoomTypeAndRoom(@RequestBody @Validated RoomDTO roomDto) {
        ResultMessage msg = new ResultMessage(0, "修改房型成功");
        msg.setData(roomService.updateRoomTypeAndRoom(roomDto, getCurrUser().getCurrHotel().getHotelNo()));
        return msg;
    }

    @GetMapping("/roomType")
    public ResultMessage getRoomTypeAndRoom() {
        ResultMessage msg = new ResultMessage();
        msg.setCode(0);
        msg.setMsg("查询成功");
        msg.setData(roomService.listRoomTypeAndRooms(getCurrUser().getCurrHotel().getHotelNo()));
        return msg;
    }

    @GetMapping("/roomState")
    public ResultMessage getRoomState() {
        ResultMessage msg = new ResultMessage(0, "查询房态成功");
        msg.setData(roomService.listRoomStates(getCurrHotel().getHotelNo()));
        return msg;
    }

    @PatchMapping("/roomState/{roomNo}")
    public ResultMessage getRoomState(@PathVariable String roomNo, String roomState) {
        ResultMessage msg = new ResultMessage(0, "修改房态成功");
        Room room = new Room();
        room.setRoomNo(roomNo);
        room.setRoomState(roomState);
        room.setHotelNo(getCurrUser().getCurrHotel().getHotelNo());
        msg.setData(roomService.updateRoomState(room));
        return msg;
    }
}
