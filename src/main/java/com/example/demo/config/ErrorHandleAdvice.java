package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * Created by wujianjiang on 2018-11-12.
 */
@ControllerAdvice
@Slf4j
public class ErrorHandleAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ServiceResult handlerException(Exception e, HttpServletRequest request) {
        log.error("请求：{} 发生异常！EXCEPTION:{}", request.getRequestURI(), e);
        log.error("EXCEPTION:", e);
        return new ServiceResult("Error", "服务器内部错误");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ServiceResult handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.info("参数校验异常:参数名{}，异常提示：{}", fieldError.getField(), fieldError.getDefaultMessage());
        return new ServiceResult("Error", fieldError.getDefaultMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ServiceResult handleBindException(ConstraintViolationException ex) {
        return new ServiceResult("Error", ex.getConstraintViolations().iterator().next().getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ServiceResult handleBindException(BindException ex) {
        //校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为 BeanPropertyBindingResult
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.info("参数校验异常:参数名{}，异常提示：{}", fieldError.getField(), fieldError.getDefaultMessage());
        return new ServiceResult("Error", fieldError.getDefaultMessage());
    }

}
