package com.unitfunction.funfrag.httpclient.internal;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.unitfunction.common.https.HttpCommu;
import com.unitfunction.common.https.HttpCommuElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Vincent on 2018/8/22.
 */

public class HttpClientPresent implements HttpClientContract.IPresent{

    private final String LOG_TAG = "HttpClientPresent";
    private final boolean LOG_ENABLE = true;
    private HttpClientContract.IView iView;
    private HttpClientCallBack httpClientCallBack;

    public HttpClientPresent(HttpClientContract.IView iView, HttpClientCallBack httpClientCallBack) {
        this.iView = iView;
        this.httpClientCallBack = httpClientCallBack;
    }

    @Override
    public void startLogic() {

    }

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
                List<String> result = new ArrayList<>();
                result.add("Success");
                iView.updateRecyclerView(result);
                httpClientCallBack.onSuccess();
            }

            @Override
            public void handleHttpErrRet(String strRspRet) {
                Gson gson = new Gson();
                Log.d(LOG_TAG,  "http return err");
                List<String> result = new ArrayList<>();
                result.add("Fail");
                iView.updateRecyclerView(result);
                httpClientCallBack.onSuccess();
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
}
