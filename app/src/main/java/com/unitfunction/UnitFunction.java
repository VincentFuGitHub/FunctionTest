package com.unitfunction;

import android.app.Application;
import android.content.Context;

/**
 * Created by Vincent on 2018/9/13.
 */

public class UnitFunction extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
