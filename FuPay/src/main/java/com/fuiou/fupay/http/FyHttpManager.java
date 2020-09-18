package com.fuiou.fupay.http;

import android.os.Handler;

import com.fuiou.fupay.FyLogUtils;
import com.fuiou.fupay.MechntConst;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FyHttpManager {
    public static final String TAG = "HttpManager";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static FyHttpManager manager;
    private long connectTimeSecond = 20;
    private long readTimeSecond = 60;
    private long writeTimeSecond = 60;
    OkHttpClient longHttpClient; //默认长网络
    private Handler handler;

    private FyHttpManager() {
        super();
        longHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectTimeout(connectTimeSecond, TimeUnit.SECONDS)
                .writeTimeout(writeTimeSecond, TimeUnit.SECONDS)
                .readTimeout(readTimeSecond, TimeUnit.SECONDS)
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .build();
        handler = new Handler();

    }

    public static FyHttpManager getInstance() {
        if (null == manager) {
            synchronized (FyHttpManager.class) {
                manager = new FyHttpManager();
            }
        }
        return manager;
    }



    private OkHttpClient getOkHttpClient() {
        return longHttpClient;
    }

    private Request.Builder requestBuilder(String url, FyHttpParams params) {
        if (params == null) {
            params = new FyHttpParams();
        }

        Request.Builder builder = new Request.Builder();
        builder.url(url);

        String json = params.getJSON();
        RequestBody body = RequestBody.create(JSON, json);
        builder.post(body);

        FyLogUtils.d("request url=" + url);
        FyLogUtils.d("request params\n" + params.getJSON());
        return builder;
    }

    public void fyPostJsonAsync(final String url, FyHttpParams params, FyHttpCallback callback) {
        fyPostAsync(url,  params, callback);
    }


    public void reqAsync(final String url, FyHttpParams params, final FyHttpCallback callback) {
        if (callback != null) {
            if (handler != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onHttpStart();
                    }
                });
            }
        }

        final FyHttpStatus status = new FyHttpStatus();
        OkHttpClient httpClient = getOkHttpClient();
        Call call = httpClient.newCall(requestBuilder(url, params).build());
        final long startTime = System.currentTimeMillis();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                FyLogUtils.d("url=" + url);
                FyLogUtils.d("rsp:请求失败 耗时：" + (System.currentTimeMillis() - startTime) + e);
                if (e instanceof SocketTimeoutException) {
                    status.setTimeOut(true);
                }
                if (callback != null) {
                    status.msg = "网络请求失败,请检查网络"; //e.getMessage();
                    notifyCallback(callback, status);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                status.success = true;
                status.httpCode = response.code();
                status.msg = "成功";
                status.obj = response.body().string();
                FyLogUtils.d("url=" + url);
                FyLogUtils.d("httpCode=" + response.code() + " 耗时：" + (System.currentTimeMillis() - startTime));
                FyLogUtils.d("rsp:\n" + status.obj);
                if (callback != null) {
                    callback.onParseRspData(status);
                    notifyCallback(callback, status);
                }
            }
        });
    }


    /***
     * 富友接口调用，推荐使用该方法
     * @param url 请求的url
     * @param params 参数
     * @param callback 异步返回
     */
    public void fyPostAsync(final String url, final FyHttpParams params, final FyHttpCallback callback) {

        reqAsync(url,  params, new FyHttpCallback() {

            @Override
            public void onHttpStart() {
                super.onHttpStart();
                if (callback != null) {
                    callback.onHttpStart();
                }

            }

            @Override
            public void onParseRspData(FyHttpStatus fyHttpStatus) {
                super.onParseRspData(fyHttpStatus);
                if (fyHttpStatus.success) {
                    try {
                        handleAllRes(params, fyHttpStatus, callback);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        fyHttpStatus.success = false;
                        fyHttpStatus.msg = "请求数据错误，请稍后再试";
                    }
                } else {
                    fyHttpStatus.msg = "[" + fyHttpStatus.code + "]" + fyHttpStatus.msg;
                }
            }

            @Override
            public void onResponse(final FyHttpStatus fyHttpStatus) {
                if (callback != null) {
                    callback.onResponse(fyHttpStatus);
                }
            }
        });
    }

    private void handleAllRes(FyHttpParams params, FyHttpStatus fyHttpStatus, FyHttpCallback callback) throws JSONException {
        JSONObject jsonObject = new JSONObject(fyHttpStatus.obj.toString());
        fyHttpStatus.code = jsonObject.optString("resp_code");
        fyHttpStatus.success = "0000".equals(fyHttpStatus.code);
        fyHttpStatus.msg = jsonObject.optString("resp_desc");
        fyHttpStatus.rspJSONObject = jsonObject;

        if (fyHttpStatus.success) {
            fyHttpStatus.decodeRSARspMsg = decodeRSARspMsg(fyHttpStatus);
        }

        if (fyHttpStatus.success && callback != null) {
            callback.onParseRspData(fyHttpStatus);
        }
    }


    /**
     * 通用解密方法
     *
     * @param status
     * @return
     * @throws JSONException
     */
    private String decodeRSARspMsg(FyHttpStatus status) {
        String message = "";
        try {
            JSONObject jsonObject = new JSONObject(status.obj.toString());

            String messageJsonStr = jsonObject.opt("message").toString();
            String aggpayPrivateKey = MechntConst.getRsaPrivatekKey();
            FyLogUtils.i("message=" + messageJsonStr);
            FyLogUtils.i("rsaPrivateKey=" + aggpayPrivateKey);

            message = FyRSAUtils.decryptByRsaPri(messageJsonStr, aggpayPrivateKey);
            FyLogUtils.i("message=" + message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message;
    }


    private void notifyCallback(final FyHttpCallback callback, final FyHttpStatus fyHttpStatus) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(fyHttpStatus);
            }
        });
    }

}
