package com.unitfunction.funactivity.http;

import android.util.Log;

import com.unitfunction.funfrag.httpclient.internal.HttpClientCallBack;
import com.unitfunction.funfrag.httpclient.internal.HttpClientCusData;

/**
 * Created by Vincent on 2018/8/21.
 */

public class HttpPresenter implements HttpContract.IPresenter{

    private static final boolean LOG_ENABLE = true;
    private static final String LOG_TAG = "Http client";

    HttpContract.IView iView;

    public HttpPresenter(HttpContract.IView iView) {
        this.iView = iView;
    }

    private HttpClientCallBack httpClientCallBack = new HttpClientCallBack(){
        @Override
        public void onSuccess() {
            Log.d(LOG_TAG, "on Success");
        }

        @Override
        public void onCancel() {
            Log.d(LOG_TAG, "on Cancel");
        }

        @Override
        public void onTimeout() {
            Log.d(LOG_TAG, "on Timeout");
        }
    };

    @Override
    public void startProcess(){
        iView.testHttp(new HttpClientCusData(60), httpClientCallBack);
    }
}
