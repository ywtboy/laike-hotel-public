package com.ywt.laike.hotel.web.controller;

import com.ywt.laike.hotel.model.DicCommon;
import com.ywt.laike.hotel.model.DicType;
import com.ywt.laike.hotel.query.DicCommonQuery;
import com.ywt.laike.hotel.service.DicCommonService;
import com.ywt.laike.hotel.web.ResultMessage;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ywt
 */
@RestController
@RequestMapping("/api")
@RequiresAuthentication
public class DicCommonController {
    @Autowired
    private DicCommonService dicCommonService;


    @GetMapping("/dicCommon/{dicTypeCode}")
    public ResultMessage getDicCommonByType(@PathVariable String dicTypeCode) {
        System.out.println("dicTypeCode:" + dicTypeCode);
        ResultMessage msg = new ResultMessage(0, "查询字典成功");
        msg.setData(dicCommonService.listDicCommonsByType(dicTypeCode));
        return msg;
    }

    @GetMapping("/dicType")
    public ResultMessage getDicType(String dicTypeClass) {
        ResultMessage msg = new ResultMessage(0, "查询字典类型成功");
        msg.setData(dicCommonService.listDicTypes(dicTypeClass));
        return msg;
    }

    @GetMapping("/dicCommon")
    public ResultMessage getDicCommon(DicCommonQuery dicCommonQuery) {
        System.out.println("dicCommonQuery:" + dicCommonQuery);
        ResultMessage msg = new ResultMessage(0, "查询字典成功");
        msg.setData(dicCommonService.listDicCommons(dicCommonQuery));
        msg.setCount(dicCommonService.countDicCommon(dicCommonQuery));
        return msg;
    }

    @RequiresRoles("super_admin")
    @PostMapping("/dicCommon")
    public ResultMessage addDicCommon(@RequestBody DicCommon dicCommon) {
        System.out.println("dicCommon: " + dicCommon);
        ResultMessage msg = new ResultMessage(0, "添加字典成功");
        msg.setData(dicCommonService.saveDicCommon(dicCommon));
        return msg;
    }

    @RequiresRoles("super_admin")
    @PatchMapping("/dicCommon")
    public ResultMessage updateDicCommon(@RequestBody DicCommon dicCommon) {
        System.out.println("dicCommon: " + dicCommon);
        ResultMessage msg = new ResultMessage(0, "修改字典成功");
        msg.setData(dicCommonService.updataDicCommon(dicCommon));
        return msg;
    }

    @RequiresRoles("super_admin")
    @PostMapping("/dicType")
    public ResultMessage addDicType(@RequestBody DicType dicType) {
        System.out.println("dicType: " + dicType);
        ResultMessage msg = new ResultMessage(0, "添加字典类型成功");
        msg.setData(dicCommonService.saveDicType(dicType));
        return msg;
    }

    @RequiresRoles("super_admin")
    @PatchMapping("/dicType")
    public ResultMessage updateDicType(@RequestBody DicType dicType) {
        System.out.println("dicType: " + dicType);
        ResultMessage msg = new ResultMessage(0, "修改字典类型成功");
        msg.setData(dicCommonService.updateDicType(dicType));
        return msg;
    }
}
