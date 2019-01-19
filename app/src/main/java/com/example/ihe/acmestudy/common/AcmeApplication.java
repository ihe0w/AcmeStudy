package com.example.ihe.acmestudy.common;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

public class AcmeApplication extends Application {
    private static Context context;
    private static AcmeApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
        application=this;

    }
    public static Context getContext(){
        return context;
    }
    public static Context getApplication() {
        return application;
    }
}
