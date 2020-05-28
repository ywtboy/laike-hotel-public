package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.OrderIncome;
import com.ywt.laike.hotel.query.OrderIncomeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderIncomeMapper {
    int deleteByPrimaryKey(Long incomeId);

    int insert(OrderIncome record);

    int insertSelective(OrderIncome record);

    OrderIncome selectByPrimaryKey(Long incomeId);

    int updateByPrimaryKeySelective(OrderIncome record);

    int updateByPrimaryKey(OrderIncome record);

    List<OrderIncome> listOrderIncomesByOrderNo(String orderNo);

    List<OrderIncome> listOrderIncomes(OrderIncomeQuery query);

    List<Long> listOrderIncomeIds(OrderIncomeQuery query);
}