package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HotelMapper {
    int deleteByPrimaryKey(Long hotelId);

    int insert(Hotel record);

    int insertSelective(Hotel record);

    Hotel selectByPrimaryKey(Long hotelId);

    int updateByPrimaryKeySelective(Hotel record);

    int updateByPrimaryKey(Hotel record);

    /**
     * 根据宾馆编号查询宾馆
     * @param hotelNo
     * @return
     */
    Hotel getByHotelNo(String hotelNo);

    /**
     * 删除宾馆 实际为修改deleted 数据库中不删除
     * @param hotelNo
     * @param gmtModified 修改日期
     * @return
     */
    int deleteByHotelNo(String hotelNo, Date gmtModified);

    List<Hotel> listHotels();

    Integer countHotelByProvince(String provinceCode);
}