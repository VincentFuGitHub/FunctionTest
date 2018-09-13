package com.unitfunction.common.log;

import android.util.Log;

/**
 * Created by Vincent on 2018/9/12.
 */

public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG   = 2;
    public static final int INFO    = 3;
    public static final int WARN    = 4;
    public static final int ERROR   = 5;
    public static final int NOTHING = 6;
//    public static final int LEVEL   = VERBOSE;

    public static void v(String tag, String msg, int level){
        if(level <= VERBOSE){
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg, int level){
        if(level <= DEBUG){
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg, int level){
        if(level <= INFO){
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg, int level){
        if(level <= WARN){
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg, int level){
        if(level <= ERROR){
            Log.e(tag, msg);
        }
    }
}
