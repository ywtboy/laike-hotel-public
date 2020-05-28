package com.ywt.laike.hotel.web.controller;

import com.ywt.laike.hotel.dao.SysRoleMapper;
import com.ywt.laike.hotel.model.SysRole;
import com.ywt.laike.hotel.service.PermissionService;
import com.ywt.laike.hotel.web.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ywt
 */
@RestController
@RequestMapping("/api/permission")
public class PermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/hotelRole")
    @ResponseBody
    public ResultMessage getHotelRoles() {
        ResultMessage msg = new ResultMessage(0, "查询成功");
        List<SysRole> sysRoles = permissionService.listRolesByRoleType("hotel");
        if (!getCurrSubject().hasRole("hotel_owner")) {
            sysRoles = sysRoles.stream().filter(sysRole -> !"treasurer".equals(sysRole.getRole()))
                    .collect(Collectors.toList());
        }
        msg.setData(sysRoles);
        return msg;
    }
}
