package com.example.demo.config;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wujianjiang on 2019-1-16.
 */
@Controller
@RequestMapping("/error")
public class SystemErrorController extends BasicErrorController {

    public SystemErrorController() {
        this(new DefaultErrorAttributes(), new ErrorProperties());
    }

    public SystemErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);
    }

    public SystemErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }

    /**
     * 定义500的错误JSON信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/500")
    @ResponseBody
    public ResponseEntity error500(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new ServiceResult("ERROR", "服务器内部错误"), status);
    }

    /**
     * 404
     * @param request
     * @return
     */
    @RequestMapping(value = "/404")
    @ResponseBody
    public ResponseEntity<ServiceResult> error404(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new ServiceResult("NotFound", "找不到页面"), status);
    }

    /**
     * 403
     * @param request
     * @return
     */
    @RequestMapping(value = "/403")
    @ResponseBody
    public ResponseEntity<ServiceResult> error403(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new ServiceResult("Unauthorized", "没有权限访问该资源"), status);
    }

    /**
     * 401
     * @param request
     * @return
     */
    @RequestMapping(value = "/401")
    @ResponseBody
    public ResponseEntity<ServiceResult> error401(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new ServiceResult("NoAccess", "未授权"), status);
    }

    /**
     * 400
     * @param request
     * @return
     */
    @RequestMapping(value = "/400")
    @ResponseBody
    public ResponseEntity<ServiceResult> error400(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new ServiceResult("InvalidRequest", "无效的请求"), status);
    }
}
