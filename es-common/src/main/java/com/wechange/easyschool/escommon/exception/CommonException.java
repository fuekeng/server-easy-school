package com.wechange.easyschool.escommon.exception;


public class CommonException extends Exception {
    EnumErrorCode errorCode;
    Object data;

    public CommonException(EnumErrorCode errorCode, String message, Object data, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.data = data;
    }
    public CommonException(EnumErrorCode errorCode, Object data, Throwable cause) {
        super( cause);
        this.errorCode = errorCode;
        this.data = data;
    }
    public CommonException(EnumErrorCode errorCode, Object data) {
        this.errorCode = errorCode;
        this.data = data;
    }

    public CommonException(EnumErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public EnumErrorCode getErrorCode() {
        return errorCode;
    }


}
