package com.wechange.easyschool.escommon.common;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
public class RestResponse implements Serializable {
    private Object data;
    private String message;
    private ResponseStatus status;
   // private int code;
    private HttpStatus code;

    
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public RestResponse() {
		super();
	}

	public RestResponse(Object data, String message, ResponseStatus status, HttpStatus code) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public RestResponse(Object data, ResponseStatus status, HttpStatus code) {
        this.data = data;
        this.status = status;
        this.code = code;
    }

}
