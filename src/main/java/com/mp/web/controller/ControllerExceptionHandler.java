package com.mp.web.controller;

import com.mp.common.ServerResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ServerResponse handleConstraintException(ConstraintViolationException e){
        Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
        StringBuilder sb = new StringBuilder();
        for(ConstraintViolation item : constraintViolations){
            sb.append(item.getMessage());
        }
        return ServerResponse.createByErrorMessage(sb.toString());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ServerResponse handleException(Exception e){
        return ServerResponse.createByErrorMessage(e.getMessage());
    }
}
