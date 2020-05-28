package com.ywt.laike.hotel.service;

import com.ywt.laike.hotel.dto.HandoverDTO;
import com.ywt.laike.hotel.dto.OnDutyInfoDTO;
import com.ywt.laike.hotel.model.Handover;

/**
 * @author ywt
 */
public interface HandoverService {
    Integer saveHandover(HandoverDTO handoverDTO);

    /**
     * 获取当前当班期间信息 如接班时间 当班期间财务信息等
     * @param hotelNo
     * @return
     */
    OnDutyInfoDTO getOnDutyInfo(String hotelNo);
}
