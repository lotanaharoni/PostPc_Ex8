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
        for (int i = 0; i < this.items.size(); i++){
            if (this.items.get(i).getId().equals(itemId)){
                this.items.get(i).setStatus("calculation done");
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

    public List<CalculationItem> getItems(){
        return this.items;
    }
}
