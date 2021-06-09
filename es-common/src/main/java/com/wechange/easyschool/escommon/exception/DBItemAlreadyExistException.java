package com.wechange.easyschool.escommon.exception;

public class DBItemAlreadyExistException extends CommonException {

    String entity;
    String field;
    String value;

    public DBItemAlreadyExistException(EnumErrorCode errorCode, String message, Throwable cause, String entity, String field, String value) {
        super(errorCode, message, cause);
        this.entity=entity;
        this.field=field;
        this.value=value;

    }

    public DBItemAlreadyExistException(EnumErrorCode errorCode, Throwable cause, String entity, String field, String value) {
        super(errorCode,  cause);
        this.entity=entity;
        this.field=field;
        this.value=value;
    }

    public DBItemAlreadyExistException(EnumErrorCode errorCode, String entity, String field, String value) {
        super(errorCode);
        this.entity=entity;
        this.field=field;
        this.value=value;
    }
}
