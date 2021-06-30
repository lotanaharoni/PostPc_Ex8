package com.example.postpc_ex8;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculationHolder{
    private ArrayList<CalculationItem> items;

    public CalculationHolder(Context context){
        this.items = new ArrayList<>();
    }

    public void addNewCalculation(CalculationItem calculationItem){
        items.add(calculationItem);
        Collections.sort(items);
    }

    public void markItemDone(String itemId, long root1, long root2, double calculationTime){
        for (int i = 0; i < this.items.size(); i++){
            if (this.items.get(i).getId().equals(itemId)){
                this.items.get(i).setStatus("calculation_done");
                this.items.get(i).updateRoots(root1, root2, calculationTime);
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

    public void markIteFailed(String itemId){
        for (int i = 0; i < this.items.size(); i++){
            if (this.items.get(i).getId().equals(itemId)){
                this.items.get(i).setStatus("calculation_failed");
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

    public void setProgress(String itemId, int newProgress){
        for (int i = 0; i < this.items.size(); i++){
            if (this.items.get(i).getId().equals(itemId)){
                this.items.get(i).setPreviousProgress(newProgress);
                break;
            }
        }
    }

    public void markItemPaused(String itemId, long stopped, double timeStopped){
        for (int i = 0; i < this.items.size(); i++){
            if (this.items.get(i).getId().equals(itemId)){
                this.items.get(i).setStopped(stopped, timeStopped);
                break;
            }
        }
        Collections.sort(this.items);
    }}