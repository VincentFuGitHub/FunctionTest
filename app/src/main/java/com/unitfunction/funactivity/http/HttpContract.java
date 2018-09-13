package com.unitfunction.funactivity.http;

import com.unitfunction.funfrag.httpclient.internal.HttpClientCallBack;
import com.unitfunction.funfrag.httpclient.internal.HttpClientCusData;

/**
 * Created by Vincent on 2018/8/21.
 */

public interface HttpContract {
    public interface IView{
        void testHttp(HttpClientCusData httpClientCusData, HttpClientCallBack httpClientCallBack);
    };

    public interface IPresenter{
        void startProcess();
    };
}
