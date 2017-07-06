package com.Factory.factory;

public enum ResultCode {
    SUCCESS(0, "Request or Post sucess"),
    WARN(-1, "Request or Post failed");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.msg = msg;
    }
    public void setcode(int code){
    	this.code=code;
    }
    public int getcode() {
        return code;
    }

    public String getmsg() {
        return msg;
    }
    public void setmsg(String msg){
    	this.msg=msg;
    }
}
