package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.DicType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DicTypeMapper {
    int deleteByPrimaryKey(Long dicTypeId);

    int insert(DicType record);

    int insertSelective(DicType record);

    DicType selectByPrimaryKey(Long dicTypeId);

    int updateByPrimaryKeySelective(DicType record);

    int updateByPrimaryKey(DicType record);

    List<DicType> listDicTypes(@Param("dicTypeClass") String dicTypeClass);

    DicType getDicTypeByCode(String dicTypeCode);
}