package com.example.postpc_ex8;

import java.io.Serializable;

public class CalculationItem implements Serializable, Comparable<CalculationItem> {

    private long number;
    private long root1;
    private long root2;
    private String status;
    private String id;

    @Override
    public int compareTo(CalculationItem o) {
        return 0;
    }

    public long getNumber() {
        return number;
    }

    public long getRoot1() {
        return root1;
    }

    public long getRoot2() {
        return root2;
    }

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setRoot1(long root1) {
        this.root1 = root1;
    }

    public void setRoot2(long root2) {
        this.root2 = root2;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(String id) {
        this.id = id;
    }
}
