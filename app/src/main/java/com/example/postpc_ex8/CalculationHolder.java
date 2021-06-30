package com.example.postpc_ex8;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CalculationHolder{
    private final HashMap<String, CalculationItem> items;
    private final MutableLiveData<List<CalculationItem>> itemsMutable;
    public final LiveData<List<CalculationItem>> itemsLive;
    private SharedPreferences sp;

    public CalculationHolder(Context context){
        this.items = new HashMap<>();
        this.itemsMutable = new MutableLiveData();
        this.itemsLive = itemsMutable;
        this.sp = context.getSharedPreferences("local_items", Context.MODE_PRIVATE);
        loadFromLocal();
    }

    public void addNewCalculation(long number){
        CalculationItem newItem = new CalculationItem(number);
        items.put(newItem.getId(), newItem);
        saveToLocal(newItem);
        itemsMutable.setValue(this.getItems());
    }

    public void markItemDone(CalculationItem item, long root1, long root2, double calculationTime){
        item.updateRoots(root1, root2, calculationTime);
        this.saveToLocal(item);
        itemsMutable.setValue(this.getItems());
    }

    public void markItemCanceled(String itemId){
        if (this.getItem(itemId)!= null){
            CalculationItem canceledItem = this.getItem(itemId);
            canceledItem.setStatus("calculation_stopped");
            saveToLocal(canceledItem);
            itemsMutable.setValue(this.getItems());
        }
    }

    public void markIteFailed(String itemId){
        if (this.getItem(itemId)!= null){
            CalculationItem failedItem = this.getItem(itemId);
            failedItem.setStatus("calculation_failed");
            saveToLocal(failedItem);
            itemsMutable.setValue(this.getItems());
        }
    }

    public void markItemInProgress(String itemId){
        if (this.getItem(itemId)!= null){
            CalculationItem UpdatedItem = this.getItem(itemId);
            UpdatedItem.setStatus("currently_calculation");
            saveToLocal(UpdatedItem);
            itemsMutable.setValue(this.getItems());
        }
    }

    public void deleteCalculation(String itemId){
        if (this.getItem(itemId)!= null){
            this.items.remove(itemId);
            SharedPreferences.Editor editor = sp.edit();
            editor.remove(itemId);
            editor.apply();
            itemsMutable.setValue(this.getItems());
        }
    }

    public List<CalculationItem> getItems(){
        return new ArrayList<>(items.values());
    }

    public CalculationItem getItem(String itemId){
        if (itemId != null && items.containsKey(itemId)){
            return items.get(itemId);
        }
        return null;
    }

    public void setProgress(String itemId, int newProgress){
        if (this.getItem(itemId) != null && (newProgress >= 0 && newProgress <=100)){
            CalculationItem updatedItem = this.getItem(itemId);
            updatedItem.setCalculationProgress(newProgress);
            saveToLocal(updatedItem);
            itemsMutable.setValue(this.getItems());
        }
    }

    public void loadFromLocal(){
        Gson gson = new Gson();
        for (String item : sp.getAll().keySet()){
            String itemSer = sp.getString(item, null);
            if (itemSer != null){
                CalculationItem newItem = gson.fromJson(itemSer, CalculationItem.class);
                if (newItem != null){
                    this.items.put(newItem.getId(), newItem);
                }
            }
        }
    }

    public void saveToLocal(CalculationItem newItem){
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(newItem.getId(), gson.toJson(newItem));
        editor.apply();
    }
}