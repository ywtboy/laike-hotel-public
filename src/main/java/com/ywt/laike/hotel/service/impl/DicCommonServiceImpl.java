package com.ywt.laike.hotel.service.impl;

import com.ywt.laike.hotel.dao.DicCommonMapper;
import com.ywt.laike.hotel.dao.DicTypeMapper;
import com.ywt.laike.hotel.model.DicCommon;
import com.ywt.laike.hotel.model.DicType;
import com.ywt.laike.hotel.query.DicCommonQuery;
import com.ywt.laike.hotel.service.DicCommonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: ywt
 */
@Service
public class DicCommonServiceImpl implements DicCommonService {

    @Autowired
    private DicTypeMapper dicTypeMapper;
    @Autowired
    private DicCommonMapper dicCommonMapper;

    /**
     * 保存一条公共字典
     *
     * @param dicCommon
     * @return
     */
    @Override
    public DicCommon saveDicCommon(DicCommon dicCommon) {
        dicCommon.setParentId(0L);
        dicCommon.setDicLevel(0);
        dicCommon.setSortNo(999);
        dicCommon.setDeleted(false);
        Date date = new Date();
        dicCommon.setGmtCreate(date);
        dicCommon.setGmtModified(date);
        dicCommonMapper.insert(dicCommon);
        return dicCommon;
    }

    /**
     * 修改一条公共字典
     *
     * @param dicCommon
     * @return
     */
    @Override
    public DicCommon updataDicCommon(DicCommon dicCommon) {
        dicCommon.setGmtModified(new Date());
        dicCommonMapper.updateByPrimaryKeySelective(dicCommon);
        return dicCommon;
    }

    /**
     * 查询字典
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public List<DicCommon> listDicCommons(DicCommonQuery query) {
        List<DicType> dicTypes = dicTypeMapper.listDicTypes("common");
        List<DicCommon> dicCommons = dicCommonMapper.listDicCommons(query);
        dicCommons.forEach(dicCommon -> dicTypes.forEach(dicType -> {
            if (dicCommon.getDicTypeId().equals(dicType.getDicTypeId())) {
                dicCommon.setDicTypeName(dicType.getDicTypeName());
            }
        }));
        return dicCommons;
    }

    /**
     * 查询字典总数
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public Integer countDicCommon(DicCommonQuery query) {

        return dicCommonMapper.countDicCommon(query);
    }

    /**
     * 按类型查询字典
     *
     * @param dicTypeCode
     * @return
     */
    @Override
    public List<DicCommon> listDicCommonsByType(String dicTypeCode) {
        DicType dicType = dicTypeMapper.getDicTypeByCode(dicTypeCode);
        DicCommonQuery dicCommonQuery = new DicCommonQuery();
        dicCommonQuery.setDicTypeId(dicType.getDicTypeId());
        return dicCommonMapper.listDicCommons(dicCommonQuery);
    }

    /**
     * 保存一条字典类型
     *
     * @param dicType
     * @return
     */
    @Override
    public DicType saveDicType(DicType dicType) {
        dicType.setDeleted(false);
        Date date = new Date();
        dicType.setGmtCreate(date);
        dicType.setGmtModified(date);
        dicTypeMapper.insert(dicType);
        return dicType;
    }

    /**
     * 修改一条字典类型
     *
     * @param dicType
     * @return
     */
    @Override
    public DicType updateDicType(DicType dicType) {
        dicType.setGmtModified(new Date());
        dicTypeMapper.updateByPrimaryKeySelective(dicType);
        return dicType;
    }

    /**
     * 查询字典类型
     *
     * @param dicTypeClass common/hotel
     * @return
     */
    @Override
    public List<DicType> listDicTypes(String dicTypeClass) {

        return dicTypeMapper.listDicTypes(dicTypeClass);
    }
}
