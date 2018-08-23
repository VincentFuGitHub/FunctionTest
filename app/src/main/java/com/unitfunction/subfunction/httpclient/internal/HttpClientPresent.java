package com.unitfunction.subfunction.httpclient.internal;

/**
 * Created by Vincent on 2018/8/22.
 */

public class HttpClientPresent implements HttpClientContract.IPresent{

    private HttpClientContract.IView iView;

    public HttpClientPresent(HttpClientContract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void startLogic() {

    }

}
