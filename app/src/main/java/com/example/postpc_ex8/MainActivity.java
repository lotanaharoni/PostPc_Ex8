package com.example.postpc_ex8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText numberEntered;
    Button calculateButton;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberEntered = findViewById(R.id.numberToEnter);
        calculateButton = findViewById(R.id.calculateButton);
        recyclerView = findViewById(R.id.showRootsRecycler);

        calculateButton.setOnClickListener(view -> {

        });
    }
}