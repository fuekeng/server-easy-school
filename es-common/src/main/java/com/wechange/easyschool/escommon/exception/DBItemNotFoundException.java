package com.wechange.easyschool.escommon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DBItemNotFoundException extends CommonException {
    String entity;
    String field;
    String value;

    public DBItemNotFoundException(EnumErrorCode errorCode, String message, Throwable cause, String entity, String field, String value) {
        super(errorCode, message, cause);
        this.entity = entity;
        this.field = field;
        this.value = value;
    }

    public DBItemNotFoundException(EnumErrorCode errorCode, Throwable cause, String entity, String field, String value) {
        super(errorCode, cause);
        this.entity = entity;
        this.field = field;
        this.value = value;
    }

    public DBItemNotFoundException(EnumErrorCode errorCode, String entity, String field, String value) {
        super(errorCode);
        this.entity = entity;
        this.field = field;
        this.value = value;
    }
}
