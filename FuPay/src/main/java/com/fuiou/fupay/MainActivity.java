package com.fuiou.fupay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.fuiou.pay.sdk.EnvType;
import com.fuiou.pay.sdk.FUPayCallBack;
import com.fuiou.pay.sdk.FUPayParamModel;
import com.fuiou.pay.sdk.FUPaySDK;
import com.fuiou.pay.sdk.FUPayType;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity  {

    private SimpleDateFormat dateSdf = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat dateTimeSdf = new SimpleDateFormat("yyyyMMddHHmmss");

    private String mchntCd; // 商户号
    private String orderId; // 商户订单号
    private String orderDate; // 订单时间
    private String orderAmt; // 金额 单位：分
    private String backNotifyUrl; // 后台回调地址
    private String goodsName;  // 商品名称
    private String goodsDetail;  // 商品详情
    private String orderTmStart; // 订单起始时间
    private String orderTmEnd; // 订单结束时间
    private EnvType curEnv = EnvType.PRO;
    private FUPayType curFuPayType = FUPayType.ALL_PAY;
    private String token;
    private String wxAppId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent data = getIntent();
        mchntCd = data.getStringExtra("mchntCd");
        orderId = data.getStringExtra("orderId");
        orderDate = data.getStringExtra("orderDate");
        orderAmt = data.getStringExtra("orderAmt");
        backNotifyUrl = data.getStringExtra("backNotifyUrl");
        goodsName = data.getStringExtra("goodsName");
        goodsDetail = data.getStringExtra("goodsDetail");
        orderTmStart = data.getStringExtra("orderTmStart");
        orderTmEnd = data.getStringExtra("orderTmEnd");
        token = data.getStringExtra("token");
        wxAppId = data.getStringExtra("WX_APP_ID");

        goPay();
    }

    private void close() {
        MainActivity.this.finish();
    }

    private void goPay() {
        final FUPayParamModel payModel = new FUPayParamModel();

        payModel.mchntCd = mchntCd.trim(); // 商户号
        payModel.orderId = orderId.trim(); // 商户订单号
        payModel.orderDate = orderDate.trim(); // 订单日期
        payModel.orderAmt = Long.parseLong(TextUtils.isEmpty(orderAmt.toString()) ? "0" : orderAmt.toString()); // 金额 单位：分
        payModel.backNotifyUrl = backNotifyUrl.trim(); // 后台回调地址
        payModel.goodsName = goodsName.toString().trim(); // 商品名称
        payModel.goodsDetail = goodsDetail.toString().trim(); // 商品详情
        payModel.orderTmStart = orderTmStart.toString().trim();  //订单起始时间
        payModel.orderTmEnd = orderTmEnd.toString().trim(); //订单结束时间
        //scheme格式  fuioupay://商户号/序列号 需要与manifests的招商银行data数据保持一致，否则无法回调
        payModel.appScheme = "fuioupay://" + mchntCd.toLowerCase() + "/01";
        //设置token
        payModel.order_token=token;
        //设置当前支付环境
        FUPaySDK.setPayEnvType(curEnv);
        //微信支付和微信小程序支付使用
        if (wxAppId != null) {
            FUPaySDK.initWXApi(wxAppId.toString().trim());
        }

//        FUKeyConfig fuKeyConfig = new FUKeyConfig();//商户密钥相关信息
//        fuKeyConfig.aggpayPrivateKey = MechntConst.RSA_PRIVATE_KEY_PRO;
//        fuKeyConfig.aggpayPublicKey = MechntConst.RSA_PUBLIC_KEY_PRO;
//        //配置商户密钥
//        FUPaySDK.initFUKeyConfig(fuKeyConfig);
        //设置回调监听
        final FUPayCallBack payCallBack = new FUPayCallBack() {
            @Override
            public void payResultCallBack(boolean isSuc, String msg, String code) {
                /**
                 * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。*/
                // FyLogUtils.d(resultMsg);
                CallBackUtils.doCallBackMethod(isSuc, msg, code);
                close();
            }
        };
        //配置支付结果界面展示
        FUPaySDK.setShowFUResultView(false);
        /**
         * TODO  ****** 此处模拟商户获取token ******
         *       为了传输安全，商户对接时，需要将此步骤放在服务端，
         *       客户端拿到token后，调用sdk开启支付步骤。
         *       注：一个订单号，获取一次token。
         */
//        FyDataManager.getInstance().doAllToken(payModel, new FyOnDataListener<FyTokenRes>() {
//            @Override
//            public void callBack(FyHttpStatus<FyTokenRes> status) {
//                if (status.success){
//                    String token1= "";
//                    try {
//                        token1 = status.obj.order_token;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    //设置token
//                    payModel.order_token=token1;
//                    //根据选择的支付类型进行处理
//                    FUPaySDK.startPayType(MainActivity.this, curFuPayType, payModel, payCallBack);
//                }
//            }
//        });
        FUPaySDK.startPayType(MainActivity.this, curFuPayType, payModel, payCallBack);
    }
}
