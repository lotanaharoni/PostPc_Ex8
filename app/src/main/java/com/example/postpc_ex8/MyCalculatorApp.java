package com.example.postpc_ex8;

import android.app.Application;

public class MyCalculatorApp extends Application {
    private static MyCalculatorApp appInstance = null;
    private MyCalculatorLocalDb localDb;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        this.localDb = new MyCalculatorLocalDb(this);
    }

    public static MyCalculatorApp getAppInstance(){
        return appInstance;
    }

    public MyCalculatorLocalDb getLocalDb(){
        return this.localDb;
    }
}
