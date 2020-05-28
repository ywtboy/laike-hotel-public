package com.ywt.laike.hotel.service;

import com.ywt.laike.hotel.dto.RoomDailyReportDTO;
import com.ywt.laike.hotel.query.ReportQuery;

/**
 * @author ywt
 */
public interface ReportService {
    /**
     * 获取每个营业日的房间出租报表
     * @param reportQuery reportQuery
     * @return RoomDailyReportDTO
     */
    RoomDailyReportDTO getRoomDailyReport(ReportQuery reportQuery);
}
