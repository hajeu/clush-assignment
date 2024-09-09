package com.clush.assignment.global.exception.handler;

import com.clush.assignment.global.exception.ExpectedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Slf4j
@EnableWebMvc
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpectedException.class)
    private ResponseEntity<String> expectedException(ExpectedException ex) {
        log.warn("예상된 예외 발생 : {} ", ex.getMessage());
        log.trace("예상된 예외 세부 정보 : ", ex);
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<String> validationException(MethodArgumentNotValidException ex) {
        log.warn("유효성 검증 실패 : {}", ex.getMessage());
        log.trace("유효성 검증 실패 세부 정보 : ", ex);
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(ex.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> noHandlerFoundException(NoHandlerFoundException ex) {
        log.warn("존재하지 않는 엔드포인트 요청 : {}", ex.getMessage());
        log.trace("존재하지 않는 엔드포인트 세부 정보 : ", ex);
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> unExpectedException(RuntimeException ex) {
        log.error("예상하지 못한 예외 발생 : ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에 문제가 발생하였습니다.");
    }
}
