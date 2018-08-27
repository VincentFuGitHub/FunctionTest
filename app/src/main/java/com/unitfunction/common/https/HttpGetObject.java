package com.unitfunction.common.https;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by fuwencheng on 2018/7/16.
 */

public class HttpGetObject {
    private static boolean LOG_ENABLE = true;
    private static String LOG_TAG = "HttpGetObject";
    private OkHttpClient mOkHttpClient = null;
    private Map<String, String> httpHeader = null;
    private String url = null;
    private String rspStr = null;
    private Request mRequest = null;

    public HttpGetObject() {
        mOkHttpClient = new OkHttpClient();
    }

    public OkHttpClient getMokHttpClient() {
        return mOkHttpClient;
    }

    public void setHttpHeader(Map<String, String> httpHeader) {
        this.httpHeader = httpHeader;
    }

    private String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRspStr() {
        return this.rspStr;
    }

    private void setRspStr(String rspStr) {
        this.rspStr = rspStr;
    }

    private void setRequest(){
        Request.Builder tmpBuild = new HttpAddHeader(this.httpHeader).addRequestBuilderHeader();
        this.mRequest = tmpBuild.get().url(url).build();
    }

    public void handleHttpGetCommu(){
        setRequest();
        Call call = mOkHttpClient.newCall(this.mRequest);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.print("enqueue fail->" + call.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String rspStr = response.body().string();
                System.out.print("enqueue response -> " + rspStr);
                setRspStr(rspStr);
            }
        });
    }
}
