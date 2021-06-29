package com.example.postpc_ex8;

import android.app.Application;

import androidx.work.WorkManager;

public class MyCalculatorApp extends Application {
    private static MyCalculatorApp appInstance = null;
    private WorkManager calculationWorker;
    private CalculationHolder holder;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        this.holder = new CalculationHolder(this);
        this.calculationWorker = WorkManager.getInstance(this);
    }

    public static MyCalculatorApp getAppInstance(){
        return appInstance;
    }

    public CalculationHolder getHolder(){
        return this.holder;
    }

    public WorkManager getCalculationWorker() {
        return calculationWorker;
    }
}
