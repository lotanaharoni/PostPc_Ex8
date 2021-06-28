package com.example.postpc_ex8;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class CalculationWorker extends Worker {

    private static final int MAX_CALCULATION_TIME = 900000;
    String currentId;
    long number;
    long root1;
    long root2;

    public CalculationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        if (number <= 0){
            System.out.println("Wrong number");
        }
        else if (number == 1){
            root1 = number;
            root2 = 1;
            // TODO: return
        }
        long timeStartMs = System.currentTimeMillis();
        double sqrtNumber = Math.sqrt(number);
        for (long i = 2; i <=sqrtNumber; i ++){
            long timePassed = System.currentTimeMillis() - timeStartMs;
            if (number % i == 0){
                if (TimeUnit.MILLISECONDS.toSeconds(timePassed) <= 20) { // TODO: Change time
                    root1 = i;
                    root2 = number / i;
                    // sendSuccessResults(resultIntent, root1, root2, numberToCalculateRootsFor, TimeUnit.MILLISECONDS.toSeconds(timePassed));
                    // return;
                }
            }
            if (TimeUnit.MILLISECONDS.toSeconds(timePassed) > 20){ //TODO: Change time
                // sendFailResults(resultIntent,timePassed, numberToCalculateRootsFor);
               //  return;
            }
        }


        return null;
    }
}
