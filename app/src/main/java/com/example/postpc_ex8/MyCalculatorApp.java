package com.example.postpc_ex8;

import android.app.Application;

import androidx.work.WorkManager;

public class MyCalculatorApp extends Application {
    private static MyCalculatorApp appInstance = null;
    private WorkManager calculationWorker;
    private MyCalculatorLocalDb localDb;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        this.localDb = new MyCalculatorLocalDb(this);
        this.calculationWorker = WorkManager.getInstance(this);
    }

    public static MyCalculatorApp getAppInstance(){
        return appInstance;
    }

    public MyCalculatorLocalDb getLocalDb(){
        return this.localDb;
    }

    public WorkManager getCalculationWorker() {
        return calculationWorker;
    }
}
