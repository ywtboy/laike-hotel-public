package com.ywt.laike.hotel.service.impl;

import com.ywt.laike.hotel.dao.HotelMapper;
import com.ywt.laike.hotel.model.Hotel;
import com.ywt.laike.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ywt
 */
@Service
public class HotelServiceImpl implements HotelService {

    private final HotelMapper hotelMapper;

    @Autowired
    public HotelServiceImpl(HotelMapper hotelMapper) {
        this.hotelMapper = hotelMapper;
    }

    @Override
    public int saveHotel(Hotel hotel) {
        String hotelNo = hotel.getProvinceCode() + hotel.getCityCode() + hotel.getDistrictCode() + "001";
        hotel.setHotelNo(hotelNo);
        hotel.setDeleted(false);
        return hotelMapper.insert(hotel);
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelMapper.selectByPrimaryKey(id);
    }

    @Override
    public Hotel getHotelByNo(String hotelNo) {
        return hotelMapper.getByHotelNo(hotelNo);
    }

    @Override
    public int deleteHotel(String hotelNo) {
        return hotelMapper.deleteByHotelNo(hotelNo, new Date());
    }

    @Override
    public List<Hotel> listHotels() {
        return hotelMapper.listHotels();
    }
}
