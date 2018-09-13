package com.unitfunction.funactivity.http;

import android.os.Bundle;

import com.unitfunction.R;
import com.unitfunction.common.base.BaseActivityForFragment;
import com.unitfunction.funfrag.httpclient.HttpClientFragment;
import com.unitfunction.funfrag.httpclient.internal.HttpClientCallBack;
import com.unitfunction.funfrag.httpclient.internal.HttpClientCusData;

import org.jetbrains.annotations.Nullable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

//import io.reactivex.Observable;

/**
 * Created by fuwencheng on 2018/8/21.
 */

public class HttpActivity extends BaseActivityForFragment implements HttpContract.IView,
        HttpClientFragment.OnHttpClientCallBack{

    HttpContract.IPresenter iPresenter;
    HttpClientCallBack httpClientCallBack;

    @Override
    protected int getContentViewId() {
        return R.id.container;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_container;
    }

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        startLogic();
    }

    private void startLogic(){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                iPresenter = new HttpPresenter(HttpActivity.this);
                iPresenter.startProcess();
            }
        })
                .subscribeOn(Schedulers.newThread())
                .subscribe();
    }

    @Override
    public void testHttp(HttpClientCusData httpClientCusData, HttpClientCallBack httpClientCallBack){
        this.httpClientCallBack = httpClientCallBack;
        startFragment(HttpClientFragment.newInstance("Title", httpClientCusData, this), false);
    }

    @Override
    public HttpClientCallBack getHttpClientCallBack() {
        return httpClientCallBack;
    }
}
