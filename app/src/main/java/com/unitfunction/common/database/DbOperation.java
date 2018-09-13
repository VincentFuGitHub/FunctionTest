package com.unitfunction.common.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.unitfunction.common.database.daogenerate.DaoMaster;
import com.unitfunction.common.database.daogenerate.DaoSession;
import com.unitfunction.common.database.daogenerate.DataEntityDao;

import java.io.File;

/**
 * Created by Vincent on 2018/9/13.
 */

public class DbOperation {

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
}
