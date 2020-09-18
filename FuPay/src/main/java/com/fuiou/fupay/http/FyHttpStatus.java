package com.fuiou.fupay.http;

import org.json.JSONObject;

public class FyHttpStatus<T> {
    public static final String ERR_NETOWRK = "-1";
    public boolean success = false;
    public T obj;
    public JSONObject rspJSONObject;
    public String msg;
    public String decodeRSARspMsg;//聚合系统使用-解密出的信息
    public String code = ERR_NETOWRK;
    public int httpCode = -1;
    private boolean timeOut;

    public FyHttpStatus(){
        super();
        success = false;
        msg = "请求失败";
    }

    public FyHttpStatus(String code) {
        this.code = code;
    }

    public FyHttpStatus(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public void setTimeOut(boolean timeOut) {
        this.timeOut = timeOut;
    }

    /***
     * 是否为请求超时
     * @return
     */
    public boolean isTimeOut(){
        return timeOut;
    }
}
