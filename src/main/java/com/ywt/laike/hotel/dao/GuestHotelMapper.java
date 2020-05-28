package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.GuestHotel;
import com.ywt.laike.hotel.query.GuestHotelQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestHotelMapper {
    int deleteByPrimaryKey(Long guestHotelId);

    int insert(GuestHotel record);

    int insertSelective(GuestHotel record);

    GuestHotel selectByPrimaryKey(Long guestHotelId);

    int updateByPrimaryKeySelective(GuestHotel record);

    int updateByPrimaryKey(GuestHotel record);

    List<GuestHotel> listGuestHotels(GuestHotelQuery guestHotelQuery);

    Integer countGuestsOfHotel(GuestHotelQuery guestHotelQuery);
}