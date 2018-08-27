package com.unitfunction.common.https;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fuwencheng on 2018/7/17.
 */

public class HttpCommu {

    private Context context;

    public HttpCommu(Context context) {
        this.context = context;
    }

    public void doHttpCommu(final HttpCommuElement httpCommuElement, final boolean isSsl){
        Observable.create(new ObservableOnSubscribe<Object>(){
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                int httpCommuStatus = 0;
                HttpObject httpObject = new HttpObject(HttpCommu.this.context, isSsl);
                /*AppLogUtils.debug(true, "HttpCommu",  "Htttp Url = " + httpCommuElement.getUrl());
                AppLogUtils.debug(true, "HttpCommu",  "Htttp Body = " + httpCommuElement.getBody());*/
                Log.d("HttpCommu", "Htttp Url = "  + httpCommuElement.getUrl());
                Log.d("HttpCommu", "Htttp Body = " + httpCommuElement.getBody());
                if(httpCommuElement.getMethod() == HttpCommuElement.Method.GET){
                    httpCommuStatus = httpObject.handleHttpsGetCommu(httpCommuElement.getUrl(),
                                                                     httpCommuElement.getHeader());
                }
                else if(httpCommuElement.getMethod() == HttpCommuElement.Method.POST){
                    httpCommuStatus = httpObject.handleHttpsPostCommu(httpCommuElement.getUrl(),
                                                                      httpCommuElement.getHeader(),
                                                                      httpCommuElement.getBody());
                }
                else{
                    httpCommuStatus = httpObject.handleHttpsPatchCommu(httpCommuElement.getUrl(),
                                                                       httpCommuElement.getHeader(),
                                                                       httpCommuElement.getBody());
                }

                httpCommuElement.setHttpCommuStatus(httpCommuStatus);
                httpCommuElement.setStrRspRet(httpObject.getRspStr());
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        httpCommuElement.handleRspRet();
                    }
                });
    }
}
