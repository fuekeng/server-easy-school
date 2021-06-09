package com.wechange.easyschool.escommon.exception;

public class AccessDeniedException extends CommonRuntimeException {

    public AccessDeniedException(EnumErrorCode errorCode, String message, Object data, Throwable cause) {
        super(errorCode, message, data, cause);
    }

    public AccessDeniedException(EnumErrorCode errorCode, Object data, Throwable cause) {
        super(errorCode, data, cause);
    }

    public AccessDeniedException(EnumErrorCode errorCode, Object data) {
        super(errorCode, data);
    }

    public AccessDeniedException(EnumErrorCode errorCode) {
        super(errorCode);
    }
}
