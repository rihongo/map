package com.rihongo.map.exception;

import com.rihongo.map.model.dto.CommonResponseDto;
import com.rihongo.map.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private final MessageUtil messageUtil;

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity handleBusinessException(BusinessException e) {
        log.error("BusinessException", e);
        return new ResponseEntity(
                CommonResponseDto
                        .builder()
                        .message(e.getMessage())
                        .build()
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        return new ResponseEntity(
                CommonResponseDto
                        .builder()
                        .message(messageUtil.getMessage("system.default.error.message"))
                        .build()
                , HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleBindException(BindException e) {
        log.error("handleBindException", e);
        return new ResponseEntity(
                CommonResponseDto
                        .builder()
                        .message(e.getBindingResult().getFieldError().getDefaultMessage())
                        .build()
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleAll(Exception e, HttpServletRequest request) {
        log.error(e.getMessage());

        Enumeration params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String name = (String) params.nextElement();
            log.error(name + ": " + request.getParameter(name));
        }

        return new ResponseEntity(
                CommonResponseDto
                        .builder()
                        .message(messageUtil.getMessage("system.default.error.message"))
                        .build()
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
