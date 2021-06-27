package com.example.postpc_ex8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText numberEntered;
    Button calculateButton;
    RecyclerView recyclerView;
    MyCalculatorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberEntered = findViewById(R.id.numberToEnter);
        calculateButton = findViewById(R.id.calculateButton);
        recyclerView = findViewById(R.id.showRootsRecycler);

        numberEntered.setText("");
        adapter = new MyCalculatorAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        calculateButton.setOnClickListener(view -> {
            if (!numberEntered.getText().toString().equals("")){
                try {
                    long enteredNumber = Long.parseLong(numberEntered.getText().toString());
                }
                catch (NumberFormatException e){
                    Toast.makeText(this, "Error with the number", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.onDeleteClick = item -> {

        };

        adapter.onCancelClick = item -> {

        };
    }
}