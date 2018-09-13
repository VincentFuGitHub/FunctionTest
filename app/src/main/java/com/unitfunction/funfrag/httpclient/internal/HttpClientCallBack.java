package com.unitfunction.funfrag.httpclient.internal;

/**
 * Created by Vincent on 2018/8/23.
 */

public interface HttpClientCallBack {
/*    void httpCallBack(Context context);
    void httpsCallBack(Context context);*/
    void onSuccess();
    void onCancel();
    void onTimeout();
}
