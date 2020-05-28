package com.ywt.laike.hotel.web.controller;

import com.ywt.laike.hotel.model.FrontCashFlow;
import com.ywt.laike.hotel.query.FrontCashFlowQuery;
import com.ywt.laike.hotel.service.FinanceService;
import com.ywt.laike.hotel.util.PasswordUtils;
import com.ywt.laike.hotel.web.ResultMessage;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ywt
 */
@RestController
@RequestMapping("/api/finance")
@RequiresAuthentication

public class FinanceController extends BaseController {

    @Autowired
    private FinanceService financeService;

    @RequiresPermissions("hotel:finance")
    @PostMapping("/collect")
    public ResultMessage collectCash(@RequestBody @Validated FrontCashFlow cashFlow) {
        System.out.println(cashFlow);
        System.out.println(getCurrUser());
        ResultMessage msg = new ResultMessage(0, "收账成功");
        verifyPwd(cashFlow.getUserPwd());
        cashFlow.setHotelNo(getCurrHotel().getHotelNo());
        cashFlow.setOperatorId(getCurrUser().getUserId());
        cashFlow.setOperatorName(getCurrUser().getRealName());
        financeService.collectCash(cashFlow);
        return msg;
    }

    @RequiresPermissions("hotel:finance")
    @PostMapping("/addCash")
    public ResultMessage addCash(@RequestBody @Validated FrontCashFlow cashFlow) {
        verifyPwd(cashFlow.getUserPwd());
        ResultMessage msg = new ResultMessage(0, "添加现金成功");
        cashFlow.setHotelNo(getCurrHotel().getHotelNo());
        cashFlow.setOperatorId(getCurrUser().getUserId());
        cashFlow.setOperatorName(getCurrUser().getRealName());
        financeService.addCash(cashFlow);
        return msg;
    }

    /**
     * 验证密码
     * @param pwd
     */
    private void verifyPwd(String pwd) {
        if (!PasswordUtils.isMatchPassword(getCurrUser(), pwd)) {
            throw new IncorrectCredentialsException();
        }
    }
}
