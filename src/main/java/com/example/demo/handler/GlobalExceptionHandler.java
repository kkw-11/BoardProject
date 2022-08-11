package com.example.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.NestedServletException;

@ControllerAdvice //해당 패키지 어디에서라도 Exception이 발생하면 해당 클래스로 들어옴
@Controller
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public String handlerArgumentException(IllegalArgumentException e){
        log.info(e.getMessage());
        return "error";
    }

}
