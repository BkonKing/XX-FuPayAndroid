package com.fuiou.fupay.http;

import android.text.TextUtils;

import com.fuiou.pay.sdk.FUPayManager;
import com.fuiou.pay.sdk.FUPayParamModel;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;



public class FyDataManager {

    private static final String TAG = "DataManager";
    private static FyDataManager instance;
    private Gson gson;

    public synchronized static FyDataManager getInstance() {
        if (null == instance) {
            instance = new FyDataManager();
        }
        return instance;
    }

    private FyDataManager() {
        super();
        gson = new Gson();
    }

    public void doAllToken(FUPayParamModel payModel, final FyOnDataListener<FyTokenRes> fyOnDataListener) {

        FyHttpParams params = new FyHttpParams();;

        params
                .putContent("mchnt_cd", payModel.mchntCd)
                .putContent("order_date", payModel.orderDate)
                .putContent("order_id", payModel.orderId)
                .putContent("ver", "1.0.0")
                .realPutRootInfoRSA(payModel.mchntCd);

        String url = getAllBaseUrl() + "token.do";
        FyHttpManager.getInstance().fyPostJsonAsync(url, params, new FyHttpCallback() {

            @Override
            public void onHttpStart() {
                super.onHttpStart();
            }

            @Override
            public void onResponse(final FyHttpStatus status) {
                if (status.success) {
                    if (!TextUtils.isEmpty(status.decodeRSARspMsg)) {
                        status.obj = gsonFromJson(status.decodeRSARspMsg, FyTokenRes.class);
                    }
                }

                if (fyOnDataListener != null) {
                    fyOnDataListener.callBack(status);
                }
            }
        });
    }

    private  <T> T gsonFromJson(String json, Class<T> classOfT) {
        T t= null;
        try {
            t = gson.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return t;
    }


    public static String getAllBaseUrl() {

        String url="";
        switch (FUPayManager.getInstance().getPayEnvType()){
            case PRO:
            case UAT:
                url= "https://aggapp.fuioupay.com/";
                break;
            case DEV:
                url= "https://aggapp-test.fuioupay.com/";
                break;
        }
        return url;
    }

}
