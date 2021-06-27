package com.example.postpc_ex8;

import android.app.Application;

public class MyCalculatorApp extends Application {
    private static MyCalculatorApp appInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }

    public static MyCalculatorApp getAppInstance(){
        return appInstance;
    }
}
