package com.unitfunction.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import org.jetbrains.annotations.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Vincent on 2018/8/21.
 */

public abstract class BaseActivityForFragment extends AppCompatActivity{

    protected abstract int getContentViewId();
    protected abstract int getLayoutId();

    private Unbinder unbinder;
    private boolean firstFragment = true;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy(){
        unbinder.unbind();
        super.onDestroy();
    }

    /*hide input method when click the blank place*/
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(null != this.getCurrentFocus()){
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            assert mInputMethodManager != null;
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    public void startFragment(Fragment fragment, boolean intoStack){
        String tagName = fragment.getClass().getSimpleName();
        if(intoStack && !firstFragment){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(getContentViewId(), fragment, tagName)
                    .addToBackStack(tagName)
                    .commit();
        }
        else{
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(getContentViewId(), fragment, tagName)
                    .addToBackStack(tagName)
                    .commit();
            firstFragment = false;
        }
    }

    public void intentToActivity(Class<? extends Activity> tarActivity){
        Intent intent = new Intent(BaseActivityForFragment.this, tarActivity);
        startActivity(intent);
    }

    public void intentToActivity(Class<? extends Activity> tarActivity, Bundle bundle){
        Intent intent = new Intent(BaseActivityForFragment.this, tarActivity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void intentToActivityForResult(Class<? extends Activity> tarActivity){
        Intent intent = new Intent(BaseActivityForFragment.this, tarActivity);
        startActivityForResult(intent, 1000);
    }

    public void intentToActivityForResult(Class<? extends Activity> tarActivity, Bundle bundle){
        Intent intent = new Intent(BaseActivityForFragment.this, tarActivity);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1000);
    }
}
