package com.unitfunction.common.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.unitfunction.common.database.daogenerate.DaoMaster;
import com.unitfunction.common.database.daogenerate.DaoSession;
import com.unitfunction.common.database.daogenerate.DataEntityDao;
import com.unitfunction.common.log.LogUtil;

import java.io.File;

/**
 * Created by Vincent on 2018/9/13.
 */

public class DbOperation {

    static final String TAG = "DbOperation";
    private static volatile DbOperation INSTANCE;
    private DataEntityDao dataEntityDao;

    private DbOperation() {
        initDb();
    }

    public static DbOperation getInstance(){
        if(INSTANCE == null) {
            synchronized (DbOperation.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DbOperation();
                }
            }
        }

        return INSTANCE;
    }

    private void initDb(){
            // do this in your activities/fragments to get hold of a DAO
        dataEntityDao = DbHelper.getInstance().getDaoSession().getDataEntityDao();
    }

    public boolean addDbItem(DataEntity dataEntity){
        if(dataEntityDao == null){
            initDb();
        }

        if(dataEntity != null){
            try{
                dataEntityDao.insert(dataEntity);
                return true;
            }catch (Exception e){
                LogUtil.e(TAG, e.toString(), LogUtil.ERROR);
            }

        }
        return false;
    }

    public boolean deleteDbItem(DataEntity dataEntity){
        if(dataEntityDao == null){
            initDb();
        }

        if(dataEntity != null){
            try{
                dataEntityDao.delete(dataEntity);
                return true;
            }catch(Exception e){
                LogUtil.e(TAG, e.toString(), LogUtil.ERROR);
            }
        }
        return false;
    }

    public boolean updateDbItem(DataEntity dataEntity){
        if(dataEntityDao == null){
            initDb();
        }

        if(dataEntity != null){
            try{
                dataEntityDao.update(dataEntity);
                return true;
            }catch(Exception e){
                LogUtil.e(TAG, e.toString(), LogUtil.ERROR);
            }
        }
        return false;
    }

    public DataEntity getDbItemById(Long id){
        if(dataEntityDao == null){
            initDb();
        }

        dataEntityDao.detachAll();
        return dataEntityDao.load(id);
    }

    public Long getDbRecordNum(){
        if(dataEntityDao == null){
            initDb();
        }

        return dataEntityDao.count();
    }
}
