package com.unitfunction.funfrag.dbunitfragment;

import com.unitfunction.common.log.LogUtil;

/**
 * Created by Vincent on 2018/9/14.
 */

public class DbunitFragPresenter implements DbunitFragContract.IPresenter{

    static final String TAG = "DbunitFragPresenter";
    DbunitFragContract.IView iView;

    public DbunitFragPresenter(DbunitFragContract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void startLogic() {
        LogUtil.d(TAG, "Start logic", LogUtil.DEBUG);
    }
}
