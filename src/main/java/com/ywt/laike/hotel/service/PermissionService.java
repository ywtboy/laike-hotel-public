package com.ywt.laike.hotel.service;

import com.ywt.laike.hotel.model.SysRole;

import java.util.List;

/**
 * @author ywt
 */
public interface PermissionService {
    List<SysRole> listRolesByRoleType(String type);
}
