package com.yyds.imageloadingframe.util;

import android.util.Log;

/**
 * Created by 阿飞の小蝴蝶 on 2022/9/13
 * Describe:
 */
public class LogUtil {

    public static final String TAG = "yyds-zy";
    private int mLogLevel = 0;
    private static LogUtil instance;
    private LogUtil() {}

    public static LogUtil getInstance() {
        if (instance == null) {
            synchronized (LogUtil.class) {
                if(instance == null) {
                    instance = new LogUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 日志等级0~5, 0=verbose,1=debug,2=info,3=warn,4=error 5=failtal
     * @param logLevel
     */
    public void setLogLevel(int logLevel) {
        mLogLevel = logLevel;
    }

    public void output(String msg) {
        switch (mLogLevel) {
            case 0:
                Log.v(TAG,msg);
                break;
            case 1:
                Log.d(TAG,msg);
                break;
            case 2:
                Log.i(TAG,msg);
                break;
            case 3:
                Log.w(TAG,msg);
                break;
            case 4:
                Log.e(TAG,msg);
                break;
            case 5:
                Log.wtf(TAG,msg);
                break;
        }
    }
}
