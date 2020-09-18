package com.fuiou.fupay.http;

import android.text.TextUtils;

import com.fuiou.fupay.FyLogUtils;
import com.fuiou.fupay.MechntConst;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class FyHttpParams extends LinkedHashMap<String,Object> {

    private static final String TAG = "HttpParams";
    private String json;
    public JSONObject contentJson = new JSONObject();

    public FyHttpParams putContent(String name, Object value){

        try {
            contentJson.put(name,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;
    }

    public void realPutRootInfoRSA(String mchntCd){


        String contentJson=this.getContentJson();
        String aggpayPublicKey= MechntConst.getRsaPublickKey();

        FyLogUtils.i("contentJson=" + contentJson);
        FyLogUtils.i("aggpayPublicKey=" + aggpayPublicKey);

        if (!TextUtils.isEmpty(mchntCd)){
            this.put("mchnt_cd", mchntCd);
        }
        this.put("message", FyRSAUtils.encryptByRsaPub(contentJson, aggpayPublicKey));//密文传输 为了保证安全，建议密文传输
    }




    public String getContentJson(){
        String content="";
        try {
            content=contentJson.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    @Override
    public Object put(String key, Object value) {
        if(value == null){
            return null;
        }
        return super.put(key, value);
    }

    public void put(String key,int val){
        super.put(key,val+"");
    }

    @Override
    public void putAll( Map<? extends String, ? extends Object> m) {
        if(m == null || m.isEmpty()){
           return;
        }
        super.putAll(m);
    }


    public String getJSON() {
        if(json == null){
            json = new JSONObject(this).toString();
        }
        return json;
    }


}
