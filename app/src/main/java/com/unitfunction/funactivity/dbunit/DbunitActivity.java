package com.unitfunction.funactivity.dbunit;

import android.os.Bundle;

import com.unitfunction.R;
import com.unitfunction.common.base.BaseActivityForFragment;
import com.unitfunction.funfrag.dbunitfragment.DbFragCallBack;
import com.unitfunction.funfrag.dbunitfragment.DbunitFragment;

import org.jetbrains.annotations.Nullable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Vincent on 2018/9/14.
 */

public class DbunitActivity extends BaseActivityForFragment implements DbunitContract.IView{

    DbunitContract.IPresenter iPresenter;

    @Override
    protected int getContentViewId() {
        return R.id.container;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_container;
    }

    @Override
    protected void onCreate(@Nullable Bundle saveInstance){
        super.onCreate(saveInstance);
        init();
    }

    private void init(){
        Observable.create(new ObservableOnSubscribe<Object>() {

            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception{
                iPresenter = new DbunitPresenter(DbunitActivity.this);
                iPresenter.startLogic();
            }
        })
                .subscribeOn(Schedulers.newThread())
                .subscribe();
    }

    @Override
    void testDb(DbFragCallBack dbFragCallBack){
        startFragment();
    }

}