package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.Floor;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorMapper {
    int deleteByPrimaryKey(Long floorId);

    int insert(Floor record);

    int insertSelective(Floor record);

    Floor selectByPrimaryKey(Long floorId);

    int updateByPrimaryKeySelective(Floor record);

    int updateByPrimaryKey(Floor record);
}