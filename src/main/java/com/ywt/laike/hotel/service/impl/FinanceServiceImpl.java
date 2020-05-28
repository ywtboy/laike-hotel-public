package com.ywt.laike.hotel.service.impl;

import com.ywt.laike.hotel.dao.FrontCashFlowMapper;
import com.ywt.laike.hotel.model.FrontCashFlow;
import com.ywt.laike.hotel.query.FrontCashFlowQuery;
import com.ywt.laike.hotel.service.Exception.BusinessException;
import com.ywt.laike.hotel.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ywt
 */
@Service
public class FinanceServiceImpl implements FinanceService {
    @Autowired
    private FrontCashFlowMapper frontCashFlowMapper;
    /**
     * 财务收账
     * @param cashFlow
     * @return
     */
    @Override
    public FrontCashFlow collectCash(FrontCashFlow cashFlow) {
        FrontCashFlow lastCash = frontCashFlowMapper.getLast(cashFlow.getHotelNo());
        if (lastCash == null
                || lastCash.getBalance().compareTo(cashFlow.getFlowMoney()) == -1) {
            throw new BusinessException("现金不足");        }
        cashFlow.setFlowType("outgoings");
        cashFlow.setBalance(lastCash.getBalance().subtract(cashFlow.getFlowMoney()));
        cashFlow.setItemCode("collect_cash");
        cashFlow.setItemName("财务收账");

        Date currDate = new Date();
        cashFlow.setGmtCreate(currDate);
        cashFlow.setGmtModified(currDate);
        cashFlow.setFlowClass("other_cash");
        frontCashFlowMapper.insert(cashFlow);
        return cashFlow;
    }

    /**
     * 添加备用金
     *
     * @param cashFlow
     * @return
     */
    @Override
    public FrontCashFlow addCash(FrontCashFlow cashFlow) {
        FrontCashFlow lastCash = frontCashFlowMapper.getLast(cashFlow.getHotelNo());
        if (lastCash == null) {
            cashFlow.setBalance(cashFlow.getFlowMoney());
        } else {
            cashFlow.setBalance(lastCash.getBalance().add(cashFlow.getFlowMoney()));
        }

        Date currDate = new Date();
        cashFlow.setGmtCreate(currDate);
        cashFlow.setGmtModified(currDate);
        cashFlow.setFlowType("income");
        cashFlow.setFlowClass("other_cash");
        cashFlow.setItemCode("add_cash");
        cashFlow.setItemName("添加备用金");
        frontCashFlowMapper.insert(cashFlow);
        return cashFlow;
    }

    @Override
    public List<FrontCashFlow> listFrontCashFlow(FrontCashFlowQuery cashFlowQuery) {
        return frontCashFlowMapper.listFrontCashFlows(cashFlowQuery);
    }
}
