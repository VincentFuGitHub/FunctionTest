package com.unitfunction.common.database;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.unitfunction.UnitFunction;
import com.unitfunction.common.database.daogenerate.DaoMaster;
import com.unitfunction.common.database.daogenerate.DaoSession;

import java.io.File;

import butterknife.Unbinder;

/**
 * Created by Vincent on 2018/9/13.
 */

public class DbHelper extends DaoMaster.OpenHelper{

    private static volatile DbHelper INSTANCE;
    private DaoSession daoSession;

    public DbHelper(Context context, String name) {
        super(context, name);
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    public static DbHelper getInstance() {
        if(INSTANCE == null){
            //make customer path
            File path = new File(Environment.getExternalStorageDirectory(), "data.db");
            synchronized (DbHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new DbHelper(UnitFunction.getContext(), path.getAbsolutePath(), null);
                }
            }
        }
        return INSTANCE;
    }

    public synchronized DaoSession getDaoSession(){
        if(daoSession == null){
            SQLiteDatabase db = DbHelper.getInstance().getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
        }

        return daoSession;
    }

}
