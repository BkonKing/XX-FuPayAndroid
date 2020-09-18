package com.fuiou.fupay.http;




public interface FyOnDataListener<T1> {
    void callBack(FyHttpStatus<T1> status);
}
