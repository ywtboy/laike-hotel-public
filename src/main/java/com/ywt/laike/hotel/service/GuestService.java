package com.ywt.laike.hotel.service;

import com.ywt.laike.hotel.model.Guest;
import com.ywt.laike.hotel.model.GuestHotel;
import com.ywt.laike.hotel.query.GuestHotelQuery;

import java.util.List;

/**
 * @author ywt
 */
public interface GuestService {
    /**
     * 查询某宾馆宾客
     * @param guestHotelQuery  查询条件
     * @return List<Guest>
     */
    List<Guest> listGuestsOfHotel(GuestHotelQuery guestHotelQuery);

    /**
     * 查询总数
     * @param guestHotelQuery 查询条件
     * @return Integer
     */
    Integer countGuestsOfHotel(GuestHotelQuery guestHotelQuery);
}
