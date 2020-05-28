package com.ywt.laike.hotel.web.controller;

import com.ywt.laike.hotel.dto.RoomDailyReportDTO;
import com.ywt.laike.hotel.query.ReportQuery;
import com.ywt.laike.hotel.service.ReportService;
import com.ywt.laike.hotel.web.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ywt
 */
@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @RequestMapping("/room")
    public ResultMessage getRoomDailyReport(ReportQuery reportQuery) {
        ResultMessage msg = new ResultMessage(0, "查询成功");
        msg.setData(reportService.getRoomDailyReport(reportQuery));
        return msg;
    }
}
