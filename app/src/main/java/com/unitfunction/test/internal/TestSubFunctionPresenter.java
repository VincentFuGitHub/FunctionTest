package com.unitfunction.test.internal;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.unitfunction.common.https.HttpCommu;
import com.unitfunction.common.https.HttpCommuElement;
import com.unitfunction.subfunction.httpclient.internal.HttpClientCallBack;
import com.unitfunction.subfunction.httpclient.internal.HttpClientCusData;

import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by Vincent on 2018/8/21.
 */

public class TestSubFunctionPresenter implements TestSubFunctionContract.IPresenter{

    private static final boolean LOG_ENABLE = true;
    private static final String LOG_TAG = "Http client";

    TestSubFunctionContract.IView iView;
    HttpClientCallBack httpClientCallBack;

    public TestSubFunctionPresenter(TestSubFunctionContract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void startProcess(){
        httpClientCallBack = new HttpClientCallBack(){
            @Override
            public void httpCallBack(Context context) {
                class HttpClientTest extends HttpCommuElement {
                    /**
                     * @param method
                     * @param url
                     * @param header
                     * @param body
                     */
                    public HttpClientTest(Method method, String url, Map<String, String> header, String body) {
                        super(method, url, header, body);
                    }

                    @Override
                    public void handleHttpSucRet(String strRspRet) {
                        Gson gson = new Gson();
                        Log.d(LOG_TAG, "Http Ret Success");
                    }

                    @Override
                    public void handleHttpErrRet(String strRspRet) {
                        Gson gson = new Gson();
                        Log.d(LOG_TAG,  "http return err");
                    }
                }

                HttpClientTest httpClientTest = new HttpClientTest(HttpCommuElement.Method.GET,
                        "https://sb-api.revenuemonster.my", null, null);
                new HttpCommu(context).doHttpCommu(httpClientTest, false);
            }

            @Override
            public void httpsCallBack(Context context) {

                class HttpClientTest extends HttpCommuElement {
                    /**
                     * @param method
                     * @param url
                     * @param header
                     * @param body
                     */
                    public HttpClientTest(Method method, String url, Map<String, String> header, String body) {
                        super(method, url, header, body);
                    }

                    @Override
                    public void handleHttpSucRet(String strRspRet) {
                        Gson gson = new Gson();
                        Log.d(LOG_TAG, "Http Ret Success");
                    }

                    @Override
                    public void handleHttpErrRet(String strRspRet) {
                        Gson gson = new Gson();
                        Log.d(LOG_TAG,  "http return err");
                    }
                }

                HttpClientTest httpClientTest = new HttpClientTest(HttpCommuElement.Method.GET,
                        "https://sb-api.revenuemonster.my", null, null);
                new HttpCommu(context).doHttpCommu(httpClientTest, true);
            }
        };
        iView.testHttp(new HttpClientCusData(60), httpClientCallBack);
    }
}
