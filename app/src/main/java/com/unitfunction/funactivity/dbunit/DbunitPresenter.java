package com.unitfunction.funactivity.dbunit;

/**
 * Created by Vincent on 2018/9/14.
 */

public class DbunitPresenter implements DbunitContract.IPresenter{

    private DbunitContract.IView iView;

    public DbunitPresenter(DbunitContract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void startLogic() {

    }
}
