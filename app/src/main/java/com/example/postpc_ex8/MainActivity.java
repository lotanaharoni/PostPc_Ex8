package com.example.postpc_ex8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText numberEntered;
    Button calculateButton;
    RecyclerView recyclerView;
    MyCalculatorAdapter adapter;
    WorkManager workManager;
    CalculationHolder holder;

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
        workManager = MyCalculatorApp.getAppInstance().getCalculationWorker();
        holder = new CalculationHolder();
        LiveData<List<WorkInfo>> workTasks = workManager.getWorkInfosByTagLiveData("calculation_item_id");

        calculateButton.setOnClickListener(view -> {
            if (!numberEntered.getText().toString().equals("")){
                try {
                    long enteredNumber = Long.parseLong(numberEntered.getText().toString());
                    if (enteredNumber < 0){
                        throw new NumberFormatException();
                    }
                    CalculationItem newItem = new CalculationItem(enteredNumber);
                    holder.addNewCalculation(newItem);
                    int indexOf = holder.indexOf(newItem.getId());
                    if (indexOf != -1){
                        adapter.notifyItemInserted(indexOf);
                    }
                    // TODO: update in db
                    // TODO: worker: start work
                }
                catch (NumberFormatException e){
                    Toast.makeText(this, "Error with the number", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show();
            }
        });

        workTasks.observe(this, workInfos -> {
            for (WorkInfo workInfo : workInfos){
                // TODO: add
            }
        });

        adapter.onDeleteClick = item -> {
            holder.deleteCalculation(item.getId());
        };

        adapter.onCancelClick = item -> {
          //  TODO: workManager.cancel
            holder.markItemCanceled(item.getId());
        };
    }
}