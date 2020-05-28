package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.PayDetail;
import com.ywt.laike.hotel.query.PayDetailQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayDetailMapper {
    int deleteByPrimaryKey(Long payDetailId);

    int insert(PayDetail record);

    int insertSelective(PayDetail record);

    PayDetail selectByPrimaryKey(Long payDetailId);

    int updateByPrimaryKeySelective(PayDetail record);

    int updateByPrimaryKey(PayDetail record);

    List<PayDetail> listPayDetails(PayDetail record);

    List<PayDetail> listPayDetailsByTableNameAndPkIds(PayDetailQuery query);
//    List<PayDetail> listPayDetailsByTableNameAndPkIds(List<Long> pkIds);
}