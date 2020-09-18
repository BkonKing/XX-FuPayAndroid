package com.fuiou.fupay;

import com.fuiou.pay.sdk.FUPayManager;

/**
 * wxw 2020-05-20
 */
public class MechntConst {

    /**
     * todo  以下参数请替换为商户的相关配置信息
     */

    //测试环境
    public static final String MECHNT_CD_DEV="0002900F0096235";
    public static final String RSA_PRIVATE_KEY_DEV = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDIQi1d74mvJr9nMWgZbqT6A35L3rRfp2trpxR/zmh4lMoMLDFCSwoKt/oEZrQeNIIVXf8W/gVUCq6MUs63pvTVF90f1CDJUmdOOZYuJrUcMwa534qna+OhV4ikP6bV74OMpeR/yZ2Azu3PB+k/qkn5jeeIwPghqqtfD5bWCCxYmNLkajJPQu67vHxJKFOiGSO/8b1nSgGR9MXXTR+IYWIWNL+HshPPdZ9GtKsajuatscVc35d7CFSFCa09FSJnf3/O95ztAk768W82o8JF5lGdFiCKmmFKaQh/J4TEtLnvzFkOa+Ezqy/UlHhB/QIX1A1X5js6TovXWNXsxFB3mKLPAgMBAAECggEAP8PytiqD6OWppK4yu4ZjuchK+tWbEao5pjm5aUjMMzkNbPrVZIqIVAlo7uXVDJkjorIfe66++5e7JKtpb0kWRJQosO3W0sQOaPYlP+TLXoNQ7PalK9JK1SEOPV6XPR/a/3oDXD1SjaVWS8n1F3SdwXzR8NWhLPcvQkGwgc5wcHUIzViofLw4TO8LR+U5tjoEK4v3HEG242Q36QB+LTNKrv974RN1niFiDDhoryzIGvEJuD/XQUzZUdLXiTegTeOszyS0wH4RTMQA/MPU60rjOpH1tRsqmmwOHF64tioElCz6FSO8FV/M7myxx8DViHD4wf2OJoddaez1F471hKB2IQKBgQDpz2ajHrXA9UXkPVKMXRtewmpnY93zjtYrdGvQlSR8qgAAFNMJTYWGhsfMyW632NGu5PVB3AOSLw6z0JHNBUFOWfndoWco++Uq7rP2XtGfjsW1Y9KZY9eYqg59tW5XVf+b9fLX+mHyyt6dnUyZ0/8reol9SSC7HigE3bJQtZnrRQKBgQDbQ5zhtXlwmEvNTPH5YIXpCbJJ/QhnQfDHqcglODZ4KfvKe5CX9TdkygDtVmhGruGAsJ8dni7g37gGkvIEf0r3qqfTi/OmOz3B17Po9opV1biwjGT07YDkwweYJlqE+OEGrNbX8YvyIRjTklqRRapfZj17f/SAekDf670xOcztAwKBgA+Tf5TyYM1gCa3w3+T19vLTJN+KaajmJSJz+RLGPM4JTUK0IHMINRMHO5xK9xNMYHu0SRqAG8TLWcFk7bGo3fboS5sWO5chYN0BVf/dxT1J96Xj5o3Ndvl2qsx5H8a1tfef5PO6fHf+R+JcLlQ5KVCuMyajjoios6XPVyfFUWG5AoGAFkg0tStAABU5/s8G+BS7BweZOrOgGv40fdXfJQklX+jEUbtk4kxlKLTuiy/wwN4cxHsucFAW63tQtDpSpepZr/mDvJfIVuhGSmClQ9NqE3b4odhimhFYu+al6uxa3pB8LbV6z88YLa73WujQk1rFCZTWQqqqKjRCS38lZs886AkCgYEAsDS2JwC2XCMpXGGt/dOksFgfFTqJPRjtryMICHlYnJyEVe46ZDfXIS8XXrNIEg6OikScIwJWXvcSIxXcUOCjuNlCHVoJanp5b/p+5Y4g5Ljzy0FtfFC8JBd3VhC6grwuNOGQuSgx8dQGJIFfshFIkeXC7IjiDfQxPsoCCOmigWo=";
    public static final String RSA_PUBLIC_KEY_DEV = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDWbeSPxvfbpY2uWHbX2YXcrHXa88NITHajSmjTWdY0wqS9WqF8OyMT3ucIEuumP20/5D5+OaSETSBxrD7+1HA87TdTcCLn/FUuzVWiu4cD7Oln2/FJDwPxC/5/u14APBU30aLRWJbPVu0W8ZPmCF8718fAiYjP0RjvXU0gSnA/wwIDAQAB";

//    public static final String MECHNT_CD_DEV="104440154112670";
//    public static final String RSA_PRIVATE_KEY_DEV = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDIQi1d74mvJr9nMWgZbqT6A35L3rRfp2trpxR/zmh4lMoMLDFCSwoKt/oEZrQeNIIVXf8W/gVUCq6MUs63pvTVF90f1CDJUmdOOZYuJrUcMwa534qna+OhV4ikP6bV74OMpeR/yZ2Azu3PB+k/qkn5jeeIwPghqqtfD5bWCCxYmNLkajJPQu67vHxJKFOiGSO/8b1nSgGR9MXXTR+IYWIWNL+HshPPdZ9GtKsajuatscVc35d7CFSFCa09FSJnf3/O95ztAk768W82o8JF5lGdFiCKmmFKaQh/J4TEtLnvzFkOa+Ezqy/UlHhB/QIX1A1X5js6TovXWNXsxFB3mKLPAgMBAAECggEAP8PytiqD6OWppK4yu4ZjuchK+tWbEao5pjm5aUjMMzkNbPrVZIqIVAlo7uXVDJkjorIfe66++5e7JKtpb0kWRJQosO3W0sQOaPYlP+TLXoNQ7PalK9JK1SEOPV6XPR/a/3oDXD1SjaVWS8n1F3SdwXzR8NWhLPcvQkGwgc5wcHUIzViofLw4TO8LR+U5tjoEK4v3HEG242Q36QB+LTNKrv974RN1niFiDDhoryzIGvEJuD/XQUzZUdLXiTegTeOszyS0wH4RTMQA/MPU60rjOpH1tRsqmmwOHF64tioElCz6FSO8FV/M7myxx8DViHD4wf2OJoddaez1F471hKB2IQKBgQDpz2ajHrXA9UXkPVKMXRtewmpnY93zjtYrdGvQlSR8qgAAFNMJTYWGhsfMyW632NGu5PVB3AOSLw6z0JHNBUFOWfndoWco++Uq7rP2XtGfjsW1Y9KZY9eYqg59tW5XVf+b9fLX+mHyyt6dnUyZ0/8reol9SSC7HigE3bJQtZnrRQKBgQDbQ5zhtXlwmEvNTPH5YIXpCbJJ/QhnQfDHqcglODZ4KfvKe5CX9TdkygDtVmhGruGAsJ8dni7g37gGkvIEf0r3qqfTi/OmOz3B17Po9opV1biwjGT07YDkwweYJlqE+OEGrNbX8YvyIRjTklqRRapfZj17f/SAekDf670xOcztAwKBgA+Tf5TyYM1gCa3w3+T19vLTJN+KaajmJSJz+RLGPM4JTUK0IHMINRMHO5xK9xNMYHu0SRqAG8TLWcFk7bGo3fboS5sWO5chYN0BVf/dxT1J96Xj5o3Ndvl2qsx5H8a1tfef5PO6fHf+R+JcLlQ5KVCuMyajjoios6XPVyfFUWG5AoGAFkg0tStAABU5/s8G+BS7BweZOrOgGv40fdXfJQklX+jEUbtk4kxlKLTuiy/wwN4cxHsucFAW63tQtDpSpepZr/mDvJfIVuhGSmClQ9NqE3b4odhimhFYu+al6uxa3pB8LbV6z88YLa73WujQk1rFCZTWQqqqKjRCS38lZs886AkCgYEAsDS2JwC2XCMpXGGt/dOksFgfFTqJPRjtryMICHlYnJyEVe46ZDfXIS8XXrNIEg6OikScIwJWXvcSIxXcUOCjuNlCHVoJanp5b/p+5Y4g5Ljzy0FtfFC8JBd3VhC6grwuNOGQuSgx8dQGJIFfshFIkeXC7IjiDfQxPsoCCOmigWo=";
//    public static final String RSA_PUBLIC_KEY_DEV = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDWbeSPxvfbpY2uWHbX2YXcrHXa88NITHajSmjTWdY0wqS9WqF8OyMT3ucIEuumP20/5D5+OaSETSBxrD7+1HA87TdTcCLn/FUuzVWiu4cD7Oln2/FJDwPxC/5/u14APBU30aLRWJbPVu0W8ZPmCF8718fAiYjP0RjvXU0gSnA/wwIDAQAB";

    //生产环境
    public static final String MECHNT_CD_PRO="0003910F1065110";
    public static final String RSA_PRIVATE_KEY_PRO = "MIIEowIBAAKCAQEAtYrxIQxDZqsikWr7gyLg/CXByNC4vvcmYzITOUZoXieM7nBG+COt+8UCpo7CMdqAULFN9MYk2vjyakMOq7XPpUyGnrtQs274sDo6tHiGFOOgAKdjmuw8rUmVvlzUjmeMR793hh/eGH+GkK9IaGg6yLFbTOHX2/8suG3aVKmNarpHutDxXZ6+G11+ccSVtsE7p904BZW24vdhHAaM/ZNXY1qATJTPtQk4Cg39KdeJD4dH+OiQqpQDPhxU4PQCPPNqKQRYPwdDc2AllKA5ED/ZLS5seflRsshmimrYCLuDLXKIkr69LL46ZGk4SnqXLpBIGSMdjg31qlutD+lPnHJytQIDAQABAoIBAEXeprUoahxuiwxal1g+KUcCJRXrtt7QcjbFQ/7MKUjrgeMW1UmVOdXnWgDOetEDHlVwF+e4BuE75Nie3FubysH8tOcMgp+8evwgPQ3OQRRzEtaNldk/SI8M/hPY2HnCQowulYrKYwkcs6alv4FndBBfqhrp5NEn4YdNyDTDWMkVJzdVHQzwGomwbC5Qv8/YxW24w3vRdl8hb4Pwxnkp2/p2U/yuWRwZon3crlvoujUy1PgCRdd7SRHcJsv7wBXOZmqX4jGlxkaUNfdd2e7Q9HPvUfXYV1gCL+8Vdznwblfq3LcwcbDBesp7IdNulZtllgLaWSyLI3NB52Jfhyg1E1UCgYEA9gxARu8kTps9PAzMUuipzVuYL+csy9sypH7S9LmexZrYMzdGpHjFNN2ovTzZMWwl6ynZVgnIAYM/8RpvGyxcgaTrxo4Y8sGWpwSCsU9FwlOGn3qXZO7AviXRAue11m7py25RfY5XR0PlTdRgGWfbm2tHKGCkuBBzSeBuWU17X7MCgYEAvOLCra3KAKZuNWVGY+gUEY6nxhcxsWKwe2ZhnlCt7YAuR32jcmOGwmovB22GhtV/fAUiJi3vIOMm67dPSEK2eIl43pFll6+4yGeUijae2lxKo88zL0WKt+s9W4tfK39XaokG0kGX/IQC5InZpV6UloIcghAwBzTI9+SEaoMr7/cCgYAT9y6IEwO5NTOk2c0CtUqCFXql+HZUHHdhjcEEhsNxeX0tusCKvBoUjiARKwzcy8/P41d1edm29bWMlP6LD/B5vK49MG6p1DerWdxZEvuwJyL3cfUzBYSyaVMqjy7cNafXEB6gt5oxeDr1M9SQezMAy5W8mwsCvKL+Ta3mpTjrrQKBgQCQuRX8yNmrodlzZAOI7PrjcPAvoPncNgeOpSVKjfqVrM7IzAT90jAgqgcao9qdWNvzBGPj6PH9us3JcYx3i1YQCRTpQ3IZxPPJ6UMwmyUfnfM6i7lmENx2ivFaQPFMfKdX42Gsm8F+Iw+sV8rBIDStFUgOh6SlANayyQ6EJbj2hQKBgAt7dqmA15tJg7sHGe+vcaqIAga/OeMP7N7hYOM4HQLW2JeakM1KH+SZHJqz+PajF1aUeaXiYvnp9lD+8zHtL4COJ/TqVB5a9Hh55JbvFuqc0pwTscovGFUHNl2NPROKmU6YIFURyhh8/UmHeRlklfbrQOlPRs8FkttXDZYCq/Vn";
    public static final String RSA_PUBLIC_KEY_PRO = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCGyaaRnOStRXDnn5KSZ83vnn1SZdcd84zlKiJXRv3UdHtZY7ysI2DfZZGnBpwVTYmGzb+cKseWZ5UJh2GaSCsib169RmshIhbnked1xRgEdVmw/O0Wx18EWHWesproi5OWhQFwIRFXMWabuL3FSzwzVgti7HQsjayyxnIdHh8FpwIDAQAB";

    //订单号前缀-请更换为分配的前缀规则
    public static final String ORDEER_PRE = "1077";

    //请更换为商户自己在微信开放平台申请的appid-微信小程序支付使用
    public static final String WX_APP_ID="wxc77630e19856c1fb";//

    public static String getRsaPublickKey() {

        String pubKey="";
        switch (FUPayManager.getInstance().getPayEnvType()){
            case PRO:
            case UAT:
                pubKey= MechntConst.RSA_PUBLIC_KEY_PRO;
                break;
            case DEV:
                pubKey= MechntConst.RSA_PUBLIC_KEY_DEV;
                break;
        }
        return pubKey;
    }
    public static String getRsaPrivatekKey() {

        String priKey="";
        switch (FUPayManager.getInstance().getPayEnvType()){
            case PRO:
            case UAT:
                priKey= MechntConst.RSA_PRIVATE_KEY_PRO;
                break;
            case DEV:
                priKey= MechntConst.RSA_PRIVATE_KEY_DEV;
                break;
        }
        return priKey;
    }

}
