package com.unitfunction.test.internal;

import com.unitfunction.subfunction.httpclient.internal.HttpClientCallBack;
import com.unitfunction.subfunction.httpclient.internal.HttpClientCusData;

/**
 * Created by Vincent on 2018/8/21.
 */

public interface TestSubFunctionContract {
    public interface IView{
        void testHttp(HttpClientCusData httpClientCusData, HttpClientCallBack httpClientCallBack);
    };

    public interface IPresenter{
        void startProcess();
    };
}
