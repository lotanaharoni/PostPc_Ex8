package com.example.postpc_ex8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.StringReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText numberEntered;
    Button calculateButton;
    RecyclerView recyclerView;
    MyCalculatorAdapter adapter;
    WorkManager workManager;
    CalculationHolder holder = null;

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
        if (holder == null){
            holder = MyCalculatorApp.getAppInstance().getHolder();
        }
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
                String calculationId = workInfo.getOutputData().getString("calculation_id");
                if (workInfo.getState() == WorkInfo.State.SUCCEEDED){
                    if (workInfo.getOutputData().hasKeyWithValueOfType("stopeed", Long.class)){
                        long stopped = workInfo.getOutputData().getLong("stopped", 0);
                        double calculationTime = workInfo.getOutputData().getLong("calculation_time", 0);
                        CalculationItem item = holder.getItem(calculationId);
                        OneTimeWorkRequest calculationRequest = new OneTimeWorkRequest.Builder(CalculationWorker.class).addTag("calc_roots")
                                .setInputData(new Data.Builder().putString("item_id", item.getId()).build()).build();
                        item.setWorkerId(calculationRequest.getId());
                        workManager.enqueue(calculationRequest);
                        holder.markItemPaused(item.getId(), stopped, calculationTime);

                    }
                    else{
                        long root1 = workInfo.getOutputData().getLong("root1", 0);
                        long root2 = workInfo.getOutputData().getLong("root2", 0);
                        double calculationTime = workInfo.getOutputData().getDouble("progress", 0);
                        this.holder.markItemDone(calculationId, root1, root2, calculationTime);
                    }
                }
                else if (workInfo.getState() == WorkInfo.State.FAILED){
                    this.holder.markIteFailed(calculationId);
                }
                else if (workInfo.getState() == WorkInfo.State.RUNNING){
                    Data progress = workInfo.getProgress();
                    String itemProgressId = progress.getString("calcItemId");
                    int progressNumber = progress.getInt("progress", -1);
                    if (progressNumber != -1 && itemProgressId != null){
                        holder.setProgress(itemProgressId, progressNumber);
                    }
                   // holder.
                }
            }
        });

        adapter.onDeleteClick = item -> {
            holder.deleteCalculation(item.getId());
        };

        adapter.onCancelClick = item -> {
            workManager.cancelWorkById(item.getWorkerId());
            holder.markItemCanceled(item.getId());
        };
    }
}