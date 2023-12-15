package com.example.senhas.config.exceptions;

import com.example.senhas.domain.model.ResponseErro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MensagemPersonalizada.class)
    public ResponseEntity<ResponseErro> handleException(MensagemPersonalizada msg) {
        HttpStatus status = msg.getCodigo() != null ? msg.getCodigo() : HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseErro responseErro = new ResponseErro(
                status.value(),
                msg.getMessage());

        return new ResponseEntity<>(responseErro, status);
    }
}
