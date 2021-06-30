package com.example.postpc_ex8;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyCalculatorViewHolder extends RecyclerView.ViewHolder {
    TextView itemCalculationTitle;
    ImageButton itemDeleteImageButton;
    ImageButton itemStopImageButton;
    ProgressBar progressBarCalculation;
    View view;


    public MyCalculatorViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemCalculationTitle = itemView.findViewById(R.id.itemCalculationTitle);
        this.itemDeleteImageButton = itemView.findViewById(R.id.itemDeleteImageButton);
        this.itemStopImageButton = itemView.findViewById(R.id.itemStopImageButton);
        this.progressBarCalculation = itemView.findViewById(R.id.progressBarCalculation);
        this.view = itemView;
    }
}
