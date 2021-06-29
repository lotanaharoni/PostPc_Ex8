package com.example.postpc_ex8;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class CalculationWorker extends Worker {

    private static final int MAX_CALCULATION_TIME = 900000;
    String currentId;
    long number;
    long root1;
    long root2;
    double previousCalcTime;

    public CalculationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        currentId = getInputData().getString("calculation_item_id");
        CalculationHolder holder = MyCalculatorApp.getAppInstance().getHolder();
        // TODO: Initiate
    }

    @NonNull
    @Override
    public Result doWork() {
        Data.Builder dataBuilder = new Data.Builder();

        if (number <= 0){
            dataBuilder.putLong("number", number);
            dataBuilder.putString("reason", "illegal_number");
            return Result.failure(dataBuilder.build());
        }
        else if (number == 1){
            root1 = number;
            root2 = 1;
            dataBuilder.putString("number_id", currentId);
            dataBuilder.putLong("number", number);
            dataBuilder.putLong("root1", root1);
            dataBuilder.putLong("root2", root2);
            dataBuilder.putLong("progress", 0);
            return Result.success(dataBuilder.build());
        }
        long timeStartMs = System.currentTimeMillis();
        double sqrtNumber = Math.sqrt(number);
        for (long i = 2; i <=sqrtNumber; i ++){
            long timePassed = System.currentTimeMillis() - timeStartMs;
            if (number % i == 0){
                if (System.currentTimeMillis() - timeStartMs <= MAX_CALCULATION_TIME) {
                    root1 = i;
                    root2 = number / i;
                    dataBuilder.putString("number_id", currentId);
                    dataBuilder.putLong("number", number);
                    dataBuilder.putLong("root1", root1);
                    dataBuilder.putLong("root2", root2);
                    dataBuilder.putLong("progress", System.currentTimeMillis() - timeStartMs);
                    return Result.success(dataBuilder.build());
                }
            }
            if (System.currentTimeMillis() - timeStartMs > MAX_CALCULATION_TIME){
                dataBuilder.putString("number_id", currentId);
                dataBuilder.putLong("number", number);
                dataBuilder.putString("reason", "illegal_time_to_calculate");
                return Result.failure(dataBuilder.build());
            }
        }
        dataBuilder.putString("number_id", currentId);
        dataBuilder.putLong("number", number);
        dataBuilder.putLong("root1", 1);
        dataBuilder.putLong("root2", number);
        dataBuilder.putLong("progress", System.currentTimeMillis() - timeStartMs);
        return Result.success(dataBuilder.build());
    }
}
