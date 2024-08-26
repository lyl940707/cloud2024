package com.lyl.cloud.handle;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理
 */
@ControllerAdvice(annotations = { RestController.class, ResponseBody.class })
public class CloudExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, Object> errorResult = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errorResult.put("errorCode","9999");
        errorResult.put("errorMsg",errors.toString());
        return errorResult;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    Map<String, Object> handleException(Exception exception, HttpServletResponse response) {
        System.out.println("response:" +response);
        exception.printStackTrace();
        Map<String, Object> errorResult = new HashMap<>();
        errorResult.put("errorCode","9999");
        errorResult.put("errorMsg",exception.getMessage());
        return errorResult;
    }

}
