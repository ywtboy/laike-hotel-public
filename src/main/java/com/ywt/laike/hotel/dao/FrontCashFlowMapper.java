package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.FrontCashFlow;
import com.ywt.laike.hotel.query.FrontCashFlowQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrontCashFlowMapper {
    int deleteByPrimaryKey(Long frontCashFlowId);

    int insert(FrontCashFlow record);

    int insertSelective(FrontCashFlow record);

    FrontCashFlow selectByPrimaryKey(Long frontCashFlowId);

    int updateByPrimaryKeySelective(FrontCashFlow record);

    int updateByPrimaryKey(FrontCashFlow record);

    Integer count(String hotelNo);

    FrontCashFlow getLast(String hotelNo);

    List<FrontCashFlow> listFrontCashFlows(FrontCashFlowQuery query);
}