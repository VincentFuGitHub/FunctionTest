package com.unitfunction.funactivity.dbunit;

import com.unitfunction.common.database.DataEntity;
import com.unitfunction.common.database.DbOperation;
import com.unitfunction.common.log.LogUtil;
import com.unitfunction.funfrag.dbunitfragment.DbFragCallBack;

import java.util.Date;

/**
 * Created by Vincent on 2018/9/14.
 */

public class DbunitPresenter implements DbunitContract.IPresenter{

    static final String TAG = "DbunitPresenter";
    private DbunitContract.IView iView;
    DbOperation dbOperation = DbOperation.getInstance();
    private Long id = 0L;           //index of last database item
    private Long recordNum = 0L;

    public DbunitPresenter(DbunitContract.IView iView) {
        this.iView = iView;

        if(dbOperation != null){
            recordNum = dbOperation.getDbRecordNum();
            if(recordNum > 0){
                id = recordNum - 1;
            }
        }
    }

    DbFragCallBack dbFragCallBack = new DbFragCallBack() {
        @Override
        public void createDb() {
            if(dbOperation == null){
                dbOperation = DbOperation.getInstance();
                recordNum = dbOperation.getDbRecordNum();
            }
            LogUtil.d(TAG, "Create Db, record num:" + recordNum, LogUtil.DEBUG);
        }

        @Override
        public void addDbItem() {
            LogUtil.d(TAG, "add Db", LogUtil.DEBUG);
            DataEntity dataEntity = new DataEntity(id + 1, "test", "comment", new Date());
            dbOperation.addDbItem(dataEntity);
            id++;
            recordNum++;
        }

        @Override
        public void deleteItem() {
            LogUtil.d(TAG, "delete Db", LogUtil.DEBUG);

            if(recordNum > 0){
                DataEntity dataEntity = new DataEntity(id, "test", "comment", new Date());
                dbOperation.deleteDbItem(dataEntity);
                id--;
                recordNum--;
            }
        }

        @Override
        public void updateItem() {
            LogUtil.d(TAG, "update Db", LogUtil.DEBUG);
            DataEntity dataEntity = new DataEntity(0L, "update", "update", new Date());
            dbOperation.updateDbItem(dataEntity);
        }

        @Override
        public void queryItem() {
            LogUtil.d(TAG, "query Db Last:\n"+ dbOperation.getDbItemById(id).toString(), LogUtil.DEBUG);
        }
    };

    @Override
    public void startLogic() {
        iView.testDb(dbFragCallBack);
    }
}
