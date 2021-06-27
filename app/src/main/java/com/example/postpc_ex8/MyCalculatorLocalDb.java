package com.example.postpc_ex8;

import android.content.Context;
import android.content.SharedPreferences;

public class MyCalculatorLocalDb {
    private SharedPreferences sp = null;

    public MyCalculatorLocalDb(Context context){
        this.sp = context.getSharedPreferences("local_db", Context.MODE_PRIVATE);
    }
}
