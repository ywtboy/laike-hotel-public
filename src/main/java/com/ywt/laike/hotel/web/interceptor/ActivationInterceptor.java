package com.ywt.laike.hotel.web.interceptor;

import com.ywt.laike.hotel.dao.HotelMapper;
import com.ywt.laike.hotel.dao.UserMapper;
import com.ywt.laike.hotel.model.Hotel;
import com.ywt.laike.hotel.model.User;
import com.ywt.laike.hotel.web.ResultMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author ywt
 */
public class ActivationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private HotelMapper hotelMapper;
    /**
     * 进入controller之前判断是否激活
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Subject currUser = SecurityUtils.getSubject();
        System.out.println("是否登录："+currUser.isAuthenticated());
        if (currUser.isAuthenticated() && !currUser.hasRole("super_admin")) {
            Session session = currUser.getSession();
            User user = (User) session.getAttribute("currUser");
            if (user != null ){
                Hotel currHotel = hotelMapper.getByHotelNo(user.getCurrHotel().getHotelNo());
                boolean tag = new Date().getTime() < currHotel.getActivationEndTime().getTime();
                System.out.println("是否激活：" + tag);
                if (!tag) {
                    response.setStatus(402);
                }
                return tag;
            }
        }
        return true;
    }


}
