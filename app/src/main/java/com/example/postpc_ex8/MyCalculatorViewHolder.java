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
    ImageButton itemCancelImageButton;
    ProgressBar progressBarCalculation;


    public MyCalculatorViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemCalculationTitle = itemView.findViewById(R.id.itemCalculationTitle);
        this.itemDeleteImageButton = itemView.findViewById(R.id.itemDeleteImageButton);
        this.itemCancelImageButton = itemView.findViewById(R.id.itemCancelImageButton);
        this.progressBarCalculation = itemView.findViewById(R.id.progressBarCalculation);
    }
}
