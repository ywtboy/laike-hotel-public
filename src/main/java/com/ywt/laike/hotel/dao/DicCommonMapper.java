package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.DicCommon;
import com.ywt.laike.hotel.query.DicCommonQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DicCommonMapper {
    int deleteByPrimaryKey(Long dicCommonId);

    int insert(DicCommon record);

    int insertSelective(DicCommon record);

    DicCommon selectByPrimaryKey(Long dicCommonId);

    int updateByPrimaryKeySelective(DicCommon record);

    int updateByPrimaryKey(DicCommon record);

    /**
     * 查询字典
     * @param query 条件
     * @return
     */
    List<DicCommon> listDicCommons(DicCommonQuery query);

    /**
     * 查询字典数量
     * @param query 条件
     * @return
     */
    Integer countDicCommon(DicCommonQuery query);
}