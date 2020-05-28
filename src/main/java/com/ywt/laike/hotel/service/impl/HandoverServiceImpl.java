package com.ywt.laike.hotel.service.impl;

import com.ywt.laike.hotel.dao.*;
import com.ywt.laike.hotel.dto.HandoverDTO;
import com.ywt.laike.hotel.dto.OnDutyInfoDTO;
import com.ywt.laike.hotel.model.*;
import com.ywt.laike.hotel.query.FrontCashFlowQuery;
import com.ywt.laike.hotel.query.OrderIncomeQuery;
import com.ywt.laike.hotel.query.PayDetailQuery;
import com.ywt.laike.hotel.service.Exception.BusinessException;
import com.ywt.laike.hotel.service.HandoverService;
import com.ywt.laike.hotel.util.PasswordUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author ywt
 */
@Service
public class HandoverServiceImpl implements HandoverService {
    @Autowired
    private HandoverMapper handoverMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FrontCashFlowMapper frontCashFlowMapper;
    @Autowired
    private OrderIncomeMapper orderIncomeMapper;
    @Autowired
    private PayDetailMapper payDetailMapper;

    @Override
    public Integer saveHandover(HandoverDTO handoverDTO) {
        Handover lastHandover = handoverMapper.getLastHandoverByHotelNo(handoverDTO.getHotelNo());
        if (lastHandover != null) {
            System.out.println("不是第一次交班：" + handoverDTO.getOffGoingRealName());
            if (!lastHandover.getOnComingUserId().equals(handoverDTO.getOffGoingUserId())) {
                throw new BusinessException("您不是当班人，无法交班");
            }
        }
        User offGoing = userMapper.selectByPrimaryKey(handoverDTO.getOffGoingUserId());
        System.out.println(offGoing);
        if (!PasswordUtils.isMatchPassword(offGoing, handoverDTO.getOffGoingPwd())) {
            throw new IncorrectCredentialsException("交班人密码错误");
        }
        User onComing = userMapper.getUserByMobileNum(handoverDTO.getOnComingMobile());
        if (!PasswordUtils.isMatchPassword(onComing, handoverDTO.getOnComingPwd())) {
            throw new IncorrectCredentialsException("接班人账号或密码错误");
        }
        FrontCashFlow lastCash = frontCashFlowMapper.getLast(handoverDTO.getHotelNo());
        Handover handover = new Handover();
        if (lastCash == null) {
            handover.setCashMoney(new BigDecimal(0));
        } else {
            handover.setCashMoney(lastCash.getBalance());
        }


        handover.setOffGoingUserId(handoverDTO.getOffGoingUserId());
        handover.setOffGoingRealName(handoverDTO.getOffGoingRealName());
        handover.setHotelNo(handoverDTO.getHotelNo());

        handover.setOnComingUserId(onComing.getUserId());
        handover.setOnComingRealName(onComing.getRealName());
        Date currDate = new Date();
        handover.setHandoverTime(currDate);
        handover.setGmtCreate(currDate);
        handover.setGmtModified(currDate);
        handover.setOffGoingOk(true);
        handover.setOnComingOk(true);
        return handoverMapper.insert(handover);
    }

    /**
     * 获取当前当班期间信息 如接班时间 当班期间财务信息等
     *
     * @param hotelNo
     * @return
     */
    @Override
    public OnDutyInfoDTO getOnDutyInfo(String hotelNo) {
        OnDutyInfoDTO onDutyInfoDTO = new OnDutyInfoDTO();

        OrderIncomeQuery incomeQuery = new OrderIncomeQuery();
        incomeQuery.setHotelNo(hotelNo);

        FrontCashFlowQuery cashFlowQuery = new FrontCashFlowQuery();
        cashFlowQuery.setHotelNo(hotelNo);
        cashFlowQuery.setFlowClass("other_cash");

        Handover lastHandover = handoverMapper.getLastHandoverByHotelNo(hotelNo);
        FrontCashFlow lastFrontCashFlow = frontCashFlowMapper.getLast(hotelNo);
        if (lastHandover != null) {
            onDutyInfoDTO.setOnComingTime(lastHandover.getHandoverTime());
            onDutyInfoDTO.setOnComingRealName(lastHandover.getOnComingRealName());
            onDutyInfoDTO.setOnComingUserId(lastHandover.getOnComingUserId());
            onDutyInfoDTO.setLastHandoverCash(lastHandover.getCashMoney());

            incomeQuery.setStartTime(lastHandover.getHandoverTime());
            cashFlowQuery.setStartTime(lastHandover.getHandoverTime());
        }
        if (lastFrontCashFlow != null) {
            onDutyInfoDTO.setCurrentCash(lastFrontCashFlow.getBalance());
        }

        List<Long> orderIncomeIds = orderIncomeMapper.listOrderIncomeIds(incomeQuery);
        System.out.println("orderIncomeIds==" + orderIncomeIds);
        onDutyInfoDTO.setCurrentOrderBalance(new BigDecimal(0));
        if (orderIncomeIds != null && orderIncomeIds.size() > 0) {
            PayDetailQuery payDetailQuery = new PayDetailQuery();
            payDetailQuery.setTableName("order_income");
            payDetailQuery.setPkIds(orderIncomeIds);
            List<PayDetail> payDetails = payDetailMapper.listPayDetailsByTableNameAndPkIds(payDetailQuery);
            onDutyInfoDTO.setOrderInOut(payDetails);
        }
        onDutyInfoDTO.setOtherFrontCashFlows(frontCashFlowMapper.listFrontCashFlows(cashFlowQuery));
        return onDutyInfoDTO;
    }
}
