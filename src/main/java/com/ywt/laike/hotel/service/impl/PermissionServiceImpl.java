package com.ywt.laike.hotel.service.impl;

import com.ywt.laike.hotel.dao.SysRoleMapper;
import com.ywt.laike.hotel.model.SysRole;
import com.ywt.laike.hotel.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ywt
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public List<SysRole> listRolesByRoleType(String type) {
        return sysRoleMapper.listRolesByType(type);
    }
}
