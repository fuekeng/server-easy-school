package com.wechange.easyschool.escommon.exception;

public enum EnumErrorCode {

    ERROR_JWT_SIGNATURE_INVALID("ERRJWT00001"),
    ERROR_JWT_TOKEN_INVALID("ERRJWT00002"),
    ERROR_JWT_TOKEN_EXPIRED("ERRJWT00003"),
    ERROR_JWT_TOKEN_UNSUPPORTED("ERRJWT00004"),
    ERROR_JWT_TOKEN_NOTFOUND("ERRJWT00005"),


    ERROR_DB_ITEM_NOTFOUND("ERRDB00001"),
    ERROR_DB_ITEM_ALREADY_EXIST("ERRDB00002"),

    ERROR_ACCESS_DENIED("ERRACC00001");

    private String  code;

    EnumErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}
