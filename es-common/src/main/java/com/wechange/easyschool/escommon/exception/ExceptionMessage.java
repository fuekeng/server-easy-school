package com.wechange.easyschool.escommon.exception;

import java.time.Instant;
import java.time.chrono.Chronology;
import java.util.Date;

public class ExceptionMessage {
    private String date;
    private String path;
    private String className;
    private String message;
    private EnumErrorCode code;
    
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
