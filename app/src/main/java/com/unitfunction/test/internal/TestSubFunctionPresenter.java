package com.unitfunction.test.internal;

import com.unitfunction.subfunction.httpclient.internal.HttpClientCallBack;
import com.unitfunction.subfunction.httpclient.internal.HttpClientCusData;

/**
 * Created by Vincent on 2018/8/21.
 */

public class TestSubFunctionPresenter implements TestSubFunctionContract.IPresenter{

    TestSubFunctionContract.IView iView;
    HttpClientCallBack httpClientCallBack;

    public TestSubFunctionPresenter(TestSubFunctionContract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void startProcess(){
        httpClientCallBack = new HttpClientCallBack(){
            @Override
            public void httpCallBack() {

            }

            @Override
            public void httpsCallBack() {

            }
        };
        iView.testHttp(new HttpClientCusData(60), httpClientCallBack);
    }
}
