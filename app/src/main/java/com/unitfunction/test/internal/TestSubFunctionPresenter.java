package com.unitfunction.test.internal;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.unitfunction.common.https.HttpCommu;
import com.unitfunction.common.https.HttpCommuElement;
import com.unitfunction.subfunction.httpclient.HttpClientFragment;
import com.unitfunction.subfunction.httpclient.internal.HttpClientCallBack;
import com.unitfunction.subfunction.httpclient.internal.HttpClientCusData;

import java.util.ArrayList;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by Vincent on 2018/8/21.
 */

public class TestSubFunctionPresenter implements TestSubFunctionContract.IPresenter{

    private static final boolean LOG_ENABLE = true;
    private static final String LOG_TAG = "Http client";

    TestSubFunctionContract.IView iView;

    public TestSubFunctionPresenter(TestSubFunctionContract.IView iView) {
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
