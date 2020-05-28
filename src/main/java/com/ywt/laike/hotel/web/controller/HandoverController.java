package com.ywt.laike.hotel.web.controller;

import com.ywt.laike.hotel.dto.HandoverDTO;
import com.ywt.laike.hotel.model.Handover;
import com.ywt.laike.hotel.service.HandoverService;
import com.ywt.laike.hotel.web.ResultMessage;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ywt
 */
@RestController
@RequestMapping("/api/handover")
@RequiresAuthentication
public class HandoverController extends BaseController {
    @Autowired
    private HandoverService handoverService;

    @PostMapping
    public ResultMessage saveHandover(@RequestBody @Validated HandoverDTO handoverDTO) {
        ResultMessage msg = new ResultMessage(0, "交班成功");
        handoverDTO.setOffGoingUserId(getCurrUser().getUserId());
        handoverDTO.setOffGoingRealName(getCurrUser().getRealName());
        handoverDTO.setHotelNo(getCurrHotel().getHotelNo());
        handoverService.saveHandover(handoverDTO);
        return msg;
    }

    @GetMapping("/duty")
    public ResultMessage getOnDutyInfo() {
        ResultMessage msg = new ResultMessage(0, "查询成功");
        msg.setData(handoverService.getOnDutyInfo(getCurrHotel().getHotelNo()));
        return msg;
    }


}
