package com.ywt.laike.hotel.web;

import com.ywt.laike.hotel.service.Exception.BusinessException;
import com.ywt.laike.hotel.service.Exception.ValueDuplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.ReflectionException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: ywt
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    /**
     * 参数验证异常处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultMessage argumentValidationHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String errorMsg = "参数验证错误";
        String errorFiled = "";
        for (FieldError filedError : bindingResult.getFieldErrors()) {
            errorMsg = filedError.getDefaultMessage();
            errorFiled = filedError.getField();
        }
        ResultMessage msg = new ResultMessage();
        msg.setCode(300);
        msg.setMsg(errorMsg);
        log.error("参数验证错误 :{}", errorFiled);
        return msg;
    }

    // shiro

    /**
     * 未认证异常处理
     * @return
     */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
    public ResultMessage authenticationException() {
        ResultMessage msg = new ResultMessage(401, "未登录，或登录超时");
        return msg;
    }

    /**
     * 无权限异常处理
     * @return
     */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public ResultMessage authorizationException() {
        ResultMessage msg = new ResultMessage(403, "没有权限");
        return msg;
    }

    @ResponseBody
    @ExceptionHandler(UnknownAccountException.class)
    public ResultMessage unknownAccountException() {
        ResultMessage msg = new ResultMessage(405, "账号不存在");
        return msg;
    }

    @ResponseBody
    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResultMessage incorrectCredentialsException(IncorrectCredentialsException ex) {
        String errMsg =  ex.getMessage() == null || ex.getMessage().length() > 50 ? "密码错误" : ex.getMessage();
        ResultMessage msg = new ResultMessage(406, errMsg);
        return msg;
    }

    @ResponseBody
    @ExceptionHandler(LockedAccountException.class)
    public ResultMessage lockedAccountException () {
        ResultMessage msg = new ResultMessage(407, "账号被锁定");
        return msg;
    }


    /**
     * 自定义业务异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResultMessage BusinessException(BusinessException ex) {
        ResultMessage msg = new ResultMessage(600, ex.getMsg());
        return msg;
    }


    /**
     * 自定义 值重复异常 处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ValueDuplicationException.class)
    public ResultMessage reflectionExceptionHandlers(ValueDuplicationException ex) {
        ResultMessage msg = new ResultMessage(601, ex.getMsg());
        log.error("自定义-值重复 :{}", ex.getMessage());
        return msg;
    }

    /**
     * 全局异常处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultMessage defaultHandler(Exception ex) {
        ResultMessage msg = new ResultMessage();
        msg.setCode(999);
        msg.setMsg("发生未知错误" + ex.getMessage());
        log.error("发生错误 :{}", ex.getMessage());
        ex.printStackTrace();
        return msg;

    }

    @ResponseBody
    @ExceptionHandler(value = MyBatisSystemException.class)
    public ResultMessage reflectionExceptionHandlers(MyBatisSystemException ex) {
        ResultMessage msg = new ResultMessage();
        msg.setCode(999);
        msg.setMsg("mybatis 系统异常：" + ex.getMessage());
        log.error("mybatis 系统异常 :{}", ex.getMessage());
        return msg;
    }

    @ResponseBody
    @ExceptionHandler(value = DuplicateKeyException.class)
    public ResultMessage reflectionExceptionHandlers(DuplicateKeyException ex) {
        ResultMessage msg = new ResultMessage(702, "值重复，请检查");
        log.error("DuplicateKeyException :{}", ex.getMessage());
        return msg;
    }
}
