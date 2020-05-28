package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.RoomType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomTypeMapper {
    int deleteByPrimaryKey(Long roomTypeId);

    int insert(RoomType record);

    int insertSelective(RoomType record);

    RoomType selectByPrimaryKey(Long roomTypeId);

    int updateByPrimaryKeySelective(RoomType record);

    int updateByPrimaryKey(RoomType record);

    /**
     * 查询某宾馆下所有房型
     * @param hotelNo
     * @return
     */
    List<RoomType> listRoomTypesByHotelNo(String hotelNo);

}