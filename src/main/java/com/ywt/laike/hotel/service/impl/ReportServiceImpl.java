package com.ywt.laike.hotel.service.impl;

import com.ywt.laike.hotel.dao.OrderRoomMapper;
import com.ywt.laike.hotel.dto.RoomDailyReportDTO;
import com.ywt.laike.hotel.model.OrderRoom;
import com.ywt.laike.hotel.query.ReportQuery;
import com.ywt.laike.hotel.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ywt
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderRoomMapper orderRoomMapper;

    /**
     * 获取每个营业日的房间出租报表
     *
     * @param reportQuery reportQuery
     * @return RoomDailyReportDTO
     */
    @Override
    public RoomDailyReportDTO getRoomDailyReport(ReportQuery reportQuery) {
        List<OrderRoom> orderRooms = orderRoomMapper.listOrderRoomsForReport(reportQuery);
        RoomDailyReportDTO roomDailyReportDTO = new RoomDailyReportDTO();
        roomDailyReportDTO.setNewAddRooms(orderRooms.stream()
                .filter(orderRoom ->
                        "daily".equals(orderRoom.getOrderTypeCode())
                                && orderRoom.getRealInTime().getTime() >= reportQuery.getStartTime().getTime())
                .collect(Collectors.toList()));
        roomDailyReportDTO.setStayRooms(orderRooms.stream()
                .filter(orderRoom ->
                        "daily".equals(orderRoom.getOrderTypeCode())
                                && orderRoom.getRealInTime().getTime() < reportQuery.getStartTime().getTime())
                .collect(Collectors.toList()));
        roomDailyReportDTO.setHourRooms(orderRooms.stream()
                .filter(orderRoom -> "hour_room".equals(orderRoom.getOrderTypeCode())).collect(Collectors.toList()));
        return roomDailyReportDTO;
    }
}
