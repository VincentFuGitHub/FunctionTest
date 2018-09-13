package com.unitfunction.funfrag.httpclient.internal;

import android.content.Context;

import java.util.List;

/**
 * Created by Vincent on 2018/8/22.
 */

public interface HttpClientContract {
    public interface IPresent{
        void startLogic();
        void httpCallBack(Context context);
        void httpsCallBack(Context context);
    }

    public interface IView{
        void updateRecyclerView(List<String> list);
    }
}
