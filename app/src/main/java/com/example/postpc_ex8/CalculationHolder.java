package com.example.postpc_ex8;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculationHolder{
    private ArrayList<CalculationItem> items;
    private SharedPreferences sp = null;


    public CalculationHolder(Context context){
        this.items = new ArrayList<>();
        this.sp = context.getSharedPreferences("local_db", Context.MODE_PRIVATE);
    }

    public void addNewCalculation(CalculationItem calculationItem){
        items.add(calculationItem);
        Collections.sort(items);
    }

    public void markItemDone(String itemId){
        for (int i = 0; i < this.items.size(); i++){
            if (this.items.get(i).getId().equals(itemId)){
                this.items.get(i).setStatus("calculation_done");
                break;
            }
        }
        Collections.sort(this.items);
    }

    public void markItemCanceled(String itemId){
        for (int i = 0; i < this.items.size(); i++){
            if (this.items.get(i).getId().equals(itemId)){
                this.items.get(i).setStatus("calculation_canceled");
                break;
            }
        }
        Collections.sort(this.items);
    }

    public void deleteCalculation(String itemId){
        for (int i = 0; i < this.items.size(); i++){
            if (this.items.get(i).getId().equals(itemId)){
                this.items.remove(this.items.get(i));
                break;
            }
        }
        Collections.sort(this.items);
    }

    public int indexOf(String itemId){
        for (int i = 0; i < this.items.size(); i++){
            if (this.items.get(i).getId().equals(itemId)){
                return i;
            }
        }
        return -1;
    }

    public List<CalculationItem> getItems(){
        return this.items;
    }

    public void updateIItemTitle(String itemId, String newTitle){
        for (int i = 0; i < this.items.size(); i++){
            if (this.items.get(i).getId().equals(itemId)){
                this.items.get(i).setStatus(newTitle);
                break;
            }
        }
    }

    public CalculationItem getItem(String itemId){
        for (int i = 0; i < this.items.size(); i++){
            if (this.items.get(i).getId().equals(itemId)){
                return this.items.get(i);
            }
        }
        return null;
    }
}