package com.example.postpc_ex8;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class CalculationWorker extends Worker {

    private static final int MAX_CALCULATION_TIME = 900000;

    public CalculationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        return null;
    }
}
