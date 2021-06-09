package com.wechange.easyschool.escommon.exception;

public class CommonRuntimeException extends RuntimeException {
    EnumErrorCode errorCode;
    Object data;

    public CommonRuntimeException(EnumErrorCode errorCode, String message, Object data, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.data = data;
    }
    public CommonRuntimeException(EnumErrorCode errorCode, Object data, Throwable cause) {
        super( cause);
        this.errorCode = errorCode;
        this.data = data;
    }
    public CommonRuntimeException(EnumErrorCode errorCode, Object data) {
        this.errorCode = errorCode;
        this.data = data;
    }

    public CommonRuntimeException(EnumErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public EnumErrorCode getErrorCode() {
        return errorCode;
    }


}
