package com.fuiou.fupay;

import android.util.Log;


public class FyLogUtils {

    private static final boolean IS_NEED_LOG = BuildConfig.DEBUG;

    /**
     * 日志TAG
     */
    public static final String TAG = "fyPaySdk";
    public static final String SPACE = "-";
    public static final String TAG_MAIN = TAG + SPACE + "main";


    public static void v(String msg) {
        v(TAG, msg);
    }


    public static void d(String msg) {
        d(TAG, msg);
    }


    public static void i(String msg) {
        i(TAG, msg);
    }


    public static void w(String msg) {
        w(TAG, msg);
    }


    public static void e(String msg) {
        e(TAG, msg);
    }


    public static void v(String tag, String msg) {
        if (IS_NEED_LOG) {
            Log.v(tag, msg);
        }
    }


    public static void d(String tag, String msg) {
        if (IS_NEED_LOG) {
            Log.d(tag, msg);
        }
    }


    public static void i(String tag, String msg) {
        if (IS_NEED_LOG) {
            Log.i(tag, msg);
        }
    }


    public static void w(String tag, String msg) {
        if (IS_NEED_LOG) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (IS_NEED_LOG) {
            Log.e(tag, msg);
        }
    }



}