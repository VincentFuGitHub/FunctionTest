package com.unitfunction.common.base;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.Nullable;

import java.nio.Buffer;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Vincent on 2018/8/22.
 */

public abstract class BaseFragment extends Fragment {
    private Unbinder unbinder;
    protected Context context;

    protected abstract int  getLayoutId();
    protected abstract void initData(Bundle saveInstanceStatus);
    protected abstract void initView(View view);
    protected abstract void initPresent();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        View rootView = inflater.inflate(getLayoutId(), container,false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState){
        super.onViewCreated(view, saveInstanceState);
        initData(saveInstanceState);
        initPresent();
        initView(view);
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden){
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unbinder.unbind();
    }

    protected void intentToActivity(Class<? extends Activity> tarActivity){
        Intent intent = new Intent(context, tarActivity);
        context.startActivity(intent);
    }

    protected void intentToActivity(Class<? extends Activity> tarActivity, Bundle bundle){
        Intent intent = new Intent(context, tarActivity);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
