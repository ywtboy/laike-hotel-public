package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.DicHotel;
import org.springframework.stereotype.Repository;

@Repository
public interface DicHotelMapper {
    int deleteByPrimaryKey(Long dicHotelId);

    int insert(DicHotel record);

    int insertSelective(DicHotel record);

    DicHotel selectByPrimaryKey(Long dicHotelId);

    int updateByPrimaryKeySelective(DicHotel record);

    int updateByPrimaryKey(DicHotel record);
}