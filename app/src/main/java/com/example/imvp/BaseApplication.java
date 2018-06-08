package com.example.imvp;

import android.app.Application;
import android.content.Context;


/**
 * Created by Zaifeng on 2018/3/1.
 */

public class BaseApplication extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
