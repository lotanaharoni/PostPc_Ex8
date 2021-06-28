package com.example.postpc_ex8;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyCalculatorAdapter extends RecyclerView.Adapter<MyCalculatorViewHolder> {

    OnItemClickListener onDeleteClick;
    OnItemClickListener onCancelClick;
    ArrayList<CalculationItem> allItems;

    public MyCalculatorAdapter(){
        super();
        this.allItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyCalculatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_one_item_calculation, parent, false);
        return new MyCalculatorViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyCalculatorViewHolder holder, int position) {
        CalculationItem item = this.allItems.get(position);

        switch (item.getStatus()) {
            case "currently_calculation":
                holder.itemDeleteImageButton.setVisibility(View.GONE);
                holder.progressBarCalculation.setVisibility(View.VISIBLE);
                holder.itemCancelImageButton.setVisibility(View.VISIBLE);
                setDeleteButton(holder, item);
                break;
            case "calculation_done":
                holder.itemDeleteImageButton.setVisibility(View.VISIBLE);
                holder.progressBarCalculation.setVisibility(View.GONE);
                holder.itemCancelImageButton.setVisibility(View.GONE);
                holder.itemCalculationTitle.setText(returnTitle(item));
                setDeleteButton(holder, item);
                break;
            case "calculation_failed":
                holder.itemDeleteImageButton.setVisibility(View.VISIBLE);
                holder.progressBarCalculation.setVisibility(View.GONE);
                holder.itemCancelImageButton.setVisibility(View.GONE);
                holder.itemCalculationTitle.setText("Calculation failed");
                setDeleteButton(holder, item);
                break;
            case "calculation_canceled":
                holder.itemDeleteImageButton.setVisibility(View.VISIBLE);
                holder.progressBarCalculation.setVisibility(View.GONE);
                holder.itemCancelImageButton.setVisibility(View.GONE);
                holder.itemCalculationTitle.setText("Calculation canceled");
                setDeleteButton(holder, item);
                break;
        }
    }

    private String returnTitle(CalculationItem calculationItem){
        if (calculationItem.getRoot1() == 1 || calculationItem.getRoot2() == 1){
            return "The" + calculationItem.getNumber() +"number is prime";
        }
        return "The roots of " + calculationItem.getNumber() + " are" +
                calculationItem.getRoot1() + ", " + calculationItem.getRoot2();
    }

    private void setDeleteButton(@NonNull MyCalculatorViewHolder holder, CalculationItem item){
        holder.itemDeleteImageButton.setOnClickListener(v -> {
            if (onDeleteClick == null) return;
            onDeleteClick.onClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return this.allItems.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<CalculationItem> newItems){
        this.allItems.clear();
        this.allItems.addAll(newItems);
        Collections.sort(this.allItems);
        notifyDataSetChanged();
    }
}
