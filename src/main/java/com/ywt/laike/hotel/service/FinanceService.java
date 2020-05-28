package com.ywt.laike.hotel.service;

import com.ywt.laike.hotel.model.FrontCashFlow;
import com.ywt.laike.hotel.query.FrontCashFlowQuery;

import java.util.List;

/**
 * @author ywt
 */
public interface FinanceService {
    /**
     * 财务收账
     * @param cashFlow
     * @return
     */
    FrontCashFlow collectCash(FrontCashFlow cashFlow);

    /**
     * 添加备用金
     * @param cashFlow
     * @return
     */
    FrontCashFlow addCash(FrontCashFlow cashFlow);

    List<FrontCashFlow> listFrontCashFlow(FrontCashFlowQuery cashFlowQuery);
}
