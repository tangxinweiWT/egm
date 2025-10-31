package com.pyming.demo.infrastructure.common.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyming.demo.infrastructure.common.exception.AppException;
import com.pyming.demo.infrastructure.common.http.HttpResult;
import com.pyming.demo.infrastructure.common.http.HttpStatusEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestControllerAdvice("com.pyming")
public class GlobalExceptionHandler {
    private ObjectMapper objMapper;

    @Autowired
    public void setObjMapper(ObjectMapper objMapper) {
        this.objMapper = objMapper;
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public HttpResult bindExceptionHandler(BindException e) throws JsonProcessingException {
        BindingResult bindResult = e.getBindingResult();
        List<FieldError> allErrors = bindResult.getFieldErrors();
        Map<String, String> errMap = new HashMap<String, String>();
        for (FieldError error : allErrors) {
            errMap.put(error.getField(), error.getDefaultMessage());
        }

        log.info("返回数据：" + objMapper.writeValueAsString(errMap));
        HttpResult rs = HttpResult.error(HttpStatusEnum.GL400.code(), HttpStatusEnum.GL400.message());
        rs.setData(errMap);
        return rs;
    }

    @ExceptionHandler(value = AppException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public HttpResult appExceptionHandler(AppException e) {
        return HttpResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public HttpResult tokenExpiredHandler(TokenExpiredException e) {
        return HttpResult.error(HttpStatusEnum.GL401.code(), "Token 已过期");
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public HttpResult httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        return HttpResult.error(HttpStatusEnum.GL400.code(), e.getMessage());
    }

}
