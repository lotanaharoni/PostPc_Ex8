package com.example.postpc_ex8;

import android.content.Context;
import android.content.SharedPreferences;

public class MyLocalDbActivity {
    private SharedPreferences sp = null;

    public MyLocalDbActivity(Context context){
        this.sp = context.getSharedPreferences("local_db", Context.MODE_PRIVATE);
    }
}
