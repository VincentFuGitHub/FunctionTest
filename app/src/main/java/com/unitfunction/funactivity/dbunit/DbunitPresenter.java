package com.unitfunction.funactivity.dbunit;

import com.unitfunction.funfrag.dbunitfragment.DbFragCallBack;

/**
 * Created by Vincent on 2018/9/14.
 */

public class DbunitPresenter implements DbunitContract.IPresenter{

    private DbunitContract.IView iView;

    public DbunitPresenter(DbunitContract.IView iView) {
        this.iView = iView;
    }

    DbFragCallBack dbFragCallBack = new DbFragCallBack() {
        @Override
        public void createDb() {

        }

        @Override
        public void addDbItem() {

        }

        @Override
        public void deleteItem() {

        }

        @Override
        public void updateItem() {

        }

        @Override
        public void queryItem() {

        }
    };

    @Override
    public void startLogic() {
        iView.testDb(dbFragCallBack);
    }
}
