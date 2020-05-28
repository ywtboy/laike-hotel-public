package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.Guest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestMapper {
    int deleteByPrimaryKey(Long guestId);

    int insert(Guest record);

    int insertSelective(Guest record);

    Guest selectByPrimaryKey(Long guestId);

    int updateByPrimaryKeySelective(Guest record);

    int updateByPrimaryKey(Guest record);

    Guest getGuestByCertificateNo(String certificateNo);

    List<Guest> listGuestsByCertificateNos(List<String> certificateNos);
}