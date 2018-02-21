package io.jcervelin.ideas.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exceptions {

    private static final Logger logger = LoggerFactory.getLogger(Exceptions.class);

    @ExceptionHandler(InvalidEmailException.class)
    public VndErrors invalidEmailException(InvalidEmailException exception) {
        return createMsg(HttpStatus.BAD_REQUEST.value(), exception);
    }

    private VndErrors createMsg(Integer status, RuntimeException exception) {
        logger.error(exception.getMessage(), exception);
        return new VndErrors(status.toString(), exception.getMessage());
    }

}