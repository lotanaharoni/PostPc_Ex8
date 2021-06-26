package com.example.postpc_ex8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText numberEntered;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberEntered = findViewById(R.id.numberToEnter);
        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(view -> {

        });
    }
}