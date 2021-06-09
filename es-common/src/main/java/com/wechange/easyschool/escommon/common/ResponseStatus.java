package com.wechange.easyschool.escommon.common;

public enum ResponseStatus {

    SUCCESS(0), FAILED(1), ABORTED(2);

    private  int status;

    ResponseStatus(int status){
        this.status = status;
    }

    public int getStatus(){
        return  this.status;
    }
}
