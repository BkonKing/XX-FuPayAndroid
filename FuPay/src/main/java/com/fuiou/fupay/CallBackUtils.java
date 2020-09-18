package com.fuiou.fupay;

public class CallBackUtils {
    private static CallBack mCallBack;

    public static void setCallBack(CallBack callBack) {
        mCallBack = callBack;
    }

    public static void doCallBackMethod(boolean isSuc, String msg, String code) {
        mCallBack.doSomeThing(isSuc, msg, code);
    }
}
