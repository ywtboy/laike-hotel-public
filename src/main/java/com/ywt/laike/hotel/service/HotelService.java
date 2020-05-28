package com.ywt.laike.hotel.service;

import com.ywt.laike.hotel.model.Hotel;

import java.util.List;

/**
 * @Author: ywt
 */
public interface HotelService {
    int saveHotel(Hotel hotel);

    Hotel getHotelById(Long id);

    /**
     * 根据宾馆编号查询宾馆
     *
     * @param hotelNo
     * @return
     */
    Hotel getHotelByNo(String hotelNo);

    /**
     * 删除宾馆 实际为修改deleted 数据库中不删除
     * @param hotelNo
     * @return
     */
    int deleteHotel(String hotelNo);

    List<Hotel> listHotels();
}
