package com.ywt.laike.hotel.web.controller;

import com.ywt.laike.hotel.model.Hotel;
import com.ywt.laike.hotel.model.User;
import com.ywt.laike.hotel.service.Exception.BusinessException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


/**
 * @author ywt
 */
public abstract class BaseController {

    protected Subject getCurrSubject() {
        return SecurityUtils.getSubject();
    }

    protected User getCurrUser() {
        Subject currUser = SecurityUtils.getSubject();
        if (!currUser.isAuthenticated()) {
            throw new UnauthenticatedException();
        }
        Session session = currUser.getSession();
        return  (User) session.getAttribute("currUser");
    }

    protected Hotel getCurrHotel() {
        if (getCurrUser().getCurrHotel() == null) {
            throw new BusinessException("您的账号没有酒店/宾馆");
        }
        return  getCurrUser().getCurrHotel();
    }
}
