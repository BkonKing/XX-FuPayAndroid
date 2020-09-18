package com.fuiou.fupay.http;

import com.fuiou.fupay.FyLogUtils;

public abstract class FyHttpCallback {

    private static final String TAG = "HttpCallback";

    public void onHttpStart(){
        FyLogUtils.d(TAG+" load...");
    }

    public void onParseRspData(FyHttpStatus status){
        FyLogUtils.d(TAG+" onParseRspData...");
    }

    public abstract void onResponse(FyHttpStatus fyHttpStatus);


}
