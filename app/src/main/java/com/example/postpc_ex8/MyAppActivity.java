package com.example.postpc_ex8;

import android.app.Application;

public class MyAppActivity extends Application {
    private static  MyAppActivity appInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }
}
