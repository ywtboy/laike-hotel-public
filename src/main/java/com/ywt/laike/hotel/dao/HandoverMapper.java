package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.Handover;
import org.springframework.stereotype.Repository;

@Repository
public interface HandoverMapper {
    int deleteByPrimaryKey(Long handoverId);

    int insert(Handover record);

    int insertSelective(Handover record);

    Handover selectByPrimaryKey(Long handoverId);

    int updateByPrimaryKeySelective(Handover record);

    int updateByPrimaryKey(Handover record);

    Handover getLastHandoverByHotelNo(String hotelNo);
}