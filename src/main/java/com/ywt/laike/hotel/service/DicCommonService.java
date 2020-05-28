package com.ywt.laike.hotel.service;

import com.ywt.laike.hotel.model.DicCommon;
import com.ywt.laike.hotel.model.DicType;
import com.ywt.laike.hotel.query.DicCommonQuery;

import java.util.List;

/**
 * @Author: ywt
 */
public interface DicCommonService {
    /**
     * 保存一条公共字典
     * @param dicCommon
     * @return
     */
    DicCommon saveDicCommon(DicCommon dicCommon);

    /**
     * 修改一条公共字典
     * @param dicCommon
     * @return
     */
    DicCommon updataDicCommon(DicCommon dicCommon);

    /**
     * 查询字典
     * @param query 查询条件
     * @return
     */
    List<DicCommon> listDicCommons(DicCommonQuery query);

    /**
     * 查询字典总数
     * @param query 查询条件
     * @return
     */
    Integer countDicCommon(DicCommonQuery query);

    /**
     * 按类型查询字典
     * @param dicTypeCode
     * @return
     */
    List<DicCommon> listDicCommonsByType(String dicTypeCode);

    /**
     * 保存一条字典类型
     * @param dicType
     * @return
     */
    DicType saveDicType(DicType dicType);

    /**
     * 修改一条字典类型
     * @param dicType
     * @return
     */
    DicType updateDicType(DicType dicType);

    /**
     * 查询字典类型
     * @param dicTypeClass common/hotel
     * @return
     */
    List<DicType> listDicTypes(String dicTypeClass);


}
