package com.example.ihe.acmestudy.Compatibility;

import android.app.Application;
import android.content.Context;

public class AcmeContext extends Application {
    static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }
    public static Context getContext(){
        return context;
    }
}
