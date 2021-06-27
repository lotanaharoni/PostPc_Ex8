package com.example.postpc_ex8;

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

    @Override
    public void onBindViewHolder(@NonNull MyCalculatorViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.allItems.size();
    }

    public void setData(List<CalculationItem> newItems){
        this.allItems.clear();
        this.allItems.addAll(newItems);
        Collections.sort(this.allItems);
        notifyDataSetChanged();
    }
}
