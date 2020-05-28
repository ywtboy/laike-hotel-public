package com.ywt.laike.hotel.service.impl;

import com.ywt.laike.hotel.dao.GuestHotelMapper;
import com.ywt.laike.hotel.dao.GuestMapper;
import com.ywt.laike.hotel.model.Guest;
import com.ywt.laike.hotel.model.GuestHotel;
import com.ywt.laike.hotel.query.GuestHotelQuery;
import com.ywt.laike.hotel.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ywt
 */
@Service
public class GuestServiceImpl implements GuestService {
    @Autowired
    private GuestHotelMapper guestHotelMapper;
    @Autowired
    private GuestMapper guestMapper;

    /**
     * 查询某宾馆宾客
     *
     * @param guestHotelQuery 查询条件
     * @return List<Guest>
     */
    @Override
    public List<Guest> listGuestsOfHotel(GuestHotelQuery guestHotelQuery) {
        List<GuestHotel> guestHotels = guestHotelMapper.listGuestHotels(guestHotelQuery);
        if (null != guestHotels && guestHotels.size() > 0) {
            List<String> idNos = guestHotels
                    .stream()
                    .map(guestHotel -> guestHotel.getCertificateNo()).collect(Collectors.toList());
            return guestMapper.listGuestsByCertificateNos(idNos);
        }
        return null;
    }

    /**
     * 查询总数
     *
     * @param guestHotelQuery 查询条件
     * @return Integer
     */
    @Override
    public Integer countGuestsOfHotel(GuestHotelQuery guestHotelQuery) {
        return guestHotelMapper.countGuestsOfHotel(guestHotelQuery);
    }
}
