package com.example.postpc_ex8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculationHolder{
    private ArrayList<CalculationItem> items;

    public CalculationHolder(){
        this.items = new ArrayList<>();
    }

    public void addNewCalculation(CalculationItem calculationItem){
        items.add(calculationItem);
        Collections.sort(items);
    }

    public void markItemDone(String itemId){
        for (CalculationItem calculationItem: this.items){
            if (calculationItem.getId().equals(itemId)){
                calculationItem.setStatus("calculation done");
            }
        }
    }

    public void deleteCalculation(String itemId){

    }

    public List<CalculationItem> getItems(){
        return this.items;
    }
}
