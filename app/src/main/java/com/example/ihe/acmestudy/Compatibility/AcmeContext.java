package com.example.ihe.acmestudy.Compatibility;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

public class AcmeContext extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);

    }
    public static Context getContext(){
        return context;
    }
}
