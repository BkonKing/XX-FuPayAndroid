package com.fuiou.fupay;

import android.content.Intent;

import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModule;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;

import org.json.JSONException;
import org.json.JSONObject;

public class FuPay extends UZModule {

    public FuPay(UZWebView webView) {
        super(webView);
    }

    public UZModuleContext pay_moduleContext;

    public void jsmethod_pay(final UZModuleContext moduleContext) {
        Intent intent = new Intent(context(), MainActivity.class);
        intent.putExtra("mchntCd", moduleContext.optString("mchntCd"));
        intent.putExtra("orderId", moduleContext.optString("orderId"));
        intent.putExtra("orderDate", moduleContext.optString("orderDate"));
        intent.putExtra("orderAmt", moduleContext.optString("orderAmt"));
        intent.putExtra("backNotifyUrl", moduleContext.optString("backNotifyUrl"));
        intent.putExtra("goodsName", moduleContext.optString("goodsName"));
        intent.putExtra("goodsDetail", moduleContext.optString("goodsDetail"));
        intent.putExtra("orderTmStart", moduleContext.optString("orderTmStart"));
        intent.putExtra("orderTmEnd", moduleContext.optString("orderTmEnd"));
        intent.putExtra("token", moduleContext.optString("token"));
        // intent.putExtra("WX_APP_ID", getFeatureValue("fuyou", "wxAppId"));

        pay_moduleContext = moduleContext;
        CallBackUtils.setCallBack(new CallBack() {
            @Override
            public void doSomeThing(boolean isSuc, String msg, String code) {
                addPayListener(isSuc, msg, code);
            }
        });

        startActivity(intent);
    }

    public void addPayListener(boolean isSuc, String msg, String code) {
        try {
            if (pay_moduleContext != null) {
                JSONObject result = new JSONObject();
                result.put("isSuc", isSuc);
                result.put("msg", msg);
                result.put("code", code);
                pay_moduleContext.success(result, true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
